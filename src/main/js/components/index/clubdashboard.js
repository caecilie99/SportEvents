'use strict';

import React from "react"
import DocumentTitle from "react-document-title";
import Auth from "../common/auth";
import ClubParticipantItem from "../item/clubparticipantitem";
/**
 * Dashboard for club
 * only visible, if resposible has signed in
 * shows all participants with competitions and events
 * responsible can delete participants
 */
export default class ClubDashboard extends React.Component {

    /**
     * constructor for component
     * @param props
     */
    constructor(props) {
        super(props);
        this.state = {club: [], participants: []};
        //this.removeParticipant = this.cancelParticipant.bind(this);
    }

    /**
     * is invoked immediately after a component is mounted
     * load data from a remote endpoint
     */
    componentDidMount() {
        console.log("GET infos for user  ", Auth.getUser(), "Token ", Auth.getToken());
        // load data from a remote endpoint
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

                    // get all participants for club
                    fetch('/club/'+this.state.club.id+'/participants', {
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
                            this.setState({participants: json});
                            console.log("Size ", this.state.participants.length);
                        });
                });
        }
    }

    /**
     * delete complete participant with all assigned competitions
     *
     * @param participant
     */
    removeParticipant(participant){
        console.log(participant.lastname,participant.firstname,participant.competition[0].id);
        if (Auth.isUserAuthenticated()) {
            fetch('/participant/'+participant.id+'/'+participant.competition[0].id, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization' : Auth.getToken()
                }
            }).then(function(response) {
                console.log(response.status);
                var participants = this.state.participants;
                // delete participant in state
                participants.splice(participants.indexOf(participant), 1);
                this.setState({participants});
            }.bind(this));
/*
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
                    this.setState({participants: []});
                    // get all participants for club
                    fetch('club/'+this.state.club.id+'/participants', {
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
                            this.setState({participants: json});
                            console.log("Size ", this.state.participants.length);
                        });
                });
*/
        }
    }

    /**
     * render component
     * @returns {XML}
     */
    render() {
        var scope = this;
        // set participants for table row, unique key identifier is very important for react!!!
        // set onClick-handler for component to removeParticipants
        var participants = this.state.participants.map(participant =>
            <ClubParticipantItem onClick={scope.removeParticipant.bind(this, participant)} key={participant.id+'-'+participant.competition[0].id} participant={participant}/>
        );
        return (
            <DocumentTitle title="Dashboard">
                <div key={'club'+this.state.club.id} className="panel panel-default">
                    <div  className="panel-heading">
                        <h4>Dashboard - {this.state.club.name}</h4>
                    </div>
                    <div className="panel-body text-left nopadding">
                        <p>You have registered the following persons to events:</p>
                        <table className="table">
                            <thead>
                            <tr>
                                <th>Lastname</th>
                                <th>Firstname</th>
                                <th>Year</th>
                                <th>Date</th>
                                <th>Event</th>
                                <th>Competition</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                                {participants}
                            </tbody>
                        </table>

                    </div>
                </div>
            </DocumentTitle>
        );
    }
}
