'use strict';

import React from "react"
import {Link} from 'react-router';
import ParticipantItem from "../item/participantitem";
import Auth from "../common/auth";

/**
 * show competition for event,
 * used in eventsdetail
 */

export default class CompetitionItem extends React.Component {

    /**
     * Class constructor.
     */
    constructor(props, context) {
        super(props, context);

        // set the initial component state
        this.state = {
            competition: {},
            club: {},
            newparticipant: {
                firstname: '',
                lastname: '',
                year:''
            }
        };

        // important to get access to this in functions
        this.addParticipant = this.addParticipant.bind(this);
        this.changeUser = this.changeUser.bind(this);
    }

    componentWillMount(){
        this.setState({competition : this.props.competition})
        if (Auth.isUserAuthenticated()) {
            fetch('/user/'+Auth.getUser()+"/club", {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization' : Auth.getToken()
                }
            })
                .then( (response) => {
                    return response.json() })
                .then( (json) => {
                    console.log("Success ", json);
                    this.setState({club: json});
                });
        }
    }

    addParticipant(event) {
        // prevent default action. in this case, action is the form submission event
        event.preventDefault();

        if (Auth.isUserAuthenticated()) {
            var comp = this.state.competition;
            var test = new Object();

            // create a string for an HTTP body message
            const firstname = encodeURIComponent(this.state.newparticipant.firstname);
            const lastname = encodeURIComponent(this.state.newparticipant.lastname);
            const year = encodeURIComponent(this.state.newparticipant.year);

            console.log('/event/'+this.state.competition.event.id+'/'+this.state.competition.id+'/participants/'+this.state.club.id+'/add');
            fetch('/event/'+this.state.competition.event.id+'/'+this.state.competition.id+'/participants/'+this.state.club.id+'/add', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization' : Auth.getToken()
                },
                body: JSON.stringify({
                    lastname: lastname,
                    firstname: firstname,
                    year: year,
                })
            }).then(function(response) {
                if(response.status == 200) {
                    // Examine the text in the response
                    response.json().then(function(json) {
                        console.log(json);
                        test.id=json.id;
                        test.lastname = json.firstname;
                        test.firstname=json.lastname;
                        test.year = json.year;
                        test.club = json.club;

                        if (comp.participants==null)
                            comp.participants = new Array();
                        comp.participants.push(test);
                        console.log('add ', comp.participants.length, ' ', test.lastname);
                        this.setState({competition : comp});

                    }.bind(this));

                } else {
                    // Failure
                    console.log("Failure ", response.status);
                }
            }.bind(this));
        }
    }

    /**
     * Change the newparticipant object.
     * invoked by onChange
     *
     * @param {object} event - the JavaScript event object
     */
    changeUser(event) {
        const field = event.target.name;
        const newparticipant = this.state.newparticipant;
        newparticipant[field] = event.target.value;

        this.setState({
            newparticipant
        });
    }

    render() {
        // set participants for participantitem, unique key identifier is very important for react!!!
        var participants = this.state.competition.participants.map(participant =>
            <ParticipantItem key={participant.id} participant={participant}/>
        );

        return (
            <div key={this.state.competition.id} className="panel panel-default">
                <div className="panel-heading">{this.state.competition.name} <span className="badge">(TN {this.state.competition.participants.length})</span></div>
                <div className="panel-body">
                    <p>{this.state.competition.description}</p>
                    {Auth.isUserAuthenticated() && <form className="form-inline" onSubmit={this.addParticipant}>
                        <input type="text" className="form-control mb-2 mr-sm-2 mb-sm-0" name="firstname" placeholder="Jane" value={this.state.newparticipant.firstname} onChange={this.changeUser}/>
                        <input type="text" className="form-control mb-2 mr-sm-2 mb-sm-0" name="lastname" placeholder="Doe" value={this.state.newparticipant.lastname} onChange={this.changeUser}/>
                        <input type="text" className="form-control mb-2 mr-sm-2 mb-sm-0" name="year" placeholder="1986" value={this.state.newparticipant.year} onChange={this.changeUser}/>
                        <button type="button" className="btn btn-default" onClick={this.addParticipant}>Add</button>
                    </form>}
                </div>
                <table className="table">
                    <thead>
                    <tr>
                        <th>Lastname</th>
                        <th>Firstname</th>
                        <th>year</th>
                        <th>club</th>
                    </tr>
                    </thead>
                    <tbody>
                        {participants}
                    </tbody>
                </table>
            </div>
        )
    }
}


