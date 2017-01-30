'use strict';

import React from "react"
import { Link } from 'react-router';

export default class ParticipantItem extends React.Component {

    render() {
        return (
            <tr>
                <td>{this.props.participant.lastname}</td>
                <td>{this.props.participant.firstname}</td>
                <td>{this.props.participant.year}</td>
                <td>{this.props.participant.club.name}</td>
            </tr>
        )
    }
}


