'use strict';

import React from "react"
import {Link} from 'react-router';
import ParticipantItem from "../item/participantitem";


export default class CompetitionItem extends React.Component {

    render() {
        console.log('Render competition '+this.props.competition.id);
        var participants = this.props.competition.participants.map(participant =>
            <ParticipantItem key={participant.id} participant={participant}/>
        );

        return (
            <div key={this.props.competition.id} className="panel panel-default">
                <div className="panel-heading">{this.props.competition.name} <span className="badge">(TN {this.props.competition.participants.length})</span></div>
                <div className="panel-body">
                    <p>{this.props.competition.description}</p>
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


