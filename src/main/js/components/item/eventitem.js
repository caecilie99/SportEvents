'use strict';

import React from "react"
import { Link } from 'react-router';
import Formatter from "../../api/formatter";

// tag::app[]
export default class EventItem extends React.Component {

    render() {
        return (
            <div key={this.props.event.id} className="col-sm-6 col-md-3">
                <div  key={this.props.event.id} className="panel panel-default">
                    <div className="panel-heading">
                        <h4 className="panel-title">{this.props.event.name}</h4>
                        <h5>{Formatter.formatDate(this.props.event.date)}</h5>
                    </div>
                    <div className="panel-body text-left nopadding">
                        <Link to={"events/"+this.props.event.id}>
                            <img src="http://lorempixel.com/200/200"/>
                        </Link>
                        <p>{this.props.event.description}</p>
                    </div>
                </div>
            </div>
        )
    }
}
// end::app[]


