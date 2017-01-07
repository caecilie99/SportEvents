'use strict';

import React from "react"
import { Link } from 'react-router';

export default class CompetitionItem extends React.Component {

    render() {
        return (
            <tr>
                <td>{this.props.competition.description}</td>
                <td>{this.props.competition.fee}</td>
            </tr>
        )
    }
}


