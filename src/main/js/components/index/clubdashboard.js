'use strict';

import React from "react"
import DocumentTitle from "react-document-title";
import Auth from "../common/auth";
import ClubParticipantItem from "../item/clubparticipantitem";

export default class ClubDashboard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {club: [], participants: []};
    }

    componentDidMount() {
        console.log("GET infos for user  ", Auth.getUser(), "Token ", Auth.getToken());
        if (Auth.isUserAuthenticated()) {
            fetch('user/'+Auth.getUser()+"/club", {
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
                        });
                });
        }
    }

    render() {
        var participants = this.state.participants.map(participant =>
            <ClubParticipantItem key={participant.id} participant={participant}/>
        );
        return (
            <DocumentTitle title="Dashboard">

                <div key={this.state.club.id} className="panel panel-default">
                    <div  className="panel-heading">
                        <h4>Dashboard - {this.state.club.name}</h4>
                        <h5>Responsible:</h5>
                        <p><i>Name: </i></p>
                    </div>
                    <div className="panel-body text-left nopadding">

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
                </div>
            </DocumentTitle>
        );
    }
}


