'use strict';

import React, { PropTypes } from "react";
import Formatter from "../../api/formatter";

/**
 * shows one participant with assigned competition in table row, used in dashboard
 */

export default class ClubParticipantItem extends React.Component {

    /**
     * render component
     * @returns {XML}
     */
    render() {
        return (
            <tr>
                <td>{this.props.participant.lastname}</td>
                <td>{this.props.participant.firstname}</td>
                <td>{this.props.participant.year}</td>
                <td>{Formatter.formatDate(this.props.participant.competition[0].event.date)}</td>
                <td>{this.props.participant.competition[0].event.name}</td>
                <td>{this.props.participant.competition[0].name}</td>
                <td><button type="button" className="btn btn-default btn-xs" onClick={this.props.onClick}>Cancel</button></td>
            </tr>
        )
    }
}
