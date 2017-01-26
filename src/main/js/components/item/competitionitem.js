'use strict';

import React from "react"
import { Link } from 'react-router';

export default class CompetitionItem extends React.Component {

    render() {
        return (
            <a href="#" className="list-group-item">{this.props.competition.name}</a>
        )
    }
}


