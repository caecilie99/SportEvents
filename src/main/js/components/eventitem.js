'use strict';

// tag::vars[]
import React from "react"
// end::vars[]

function dateFormatter(formatdate) {
    return new Date(formatdate).toLocaleString('de-DE', { weekday: 'short', year: 'numeric', month: 'long', day: 'numeric' });
}

// tag::app[]
export default class EventItem extends React.Component {

    render() {
        return (
            <div key={this.props.event._links.self.href} className="col-sm-6 col-md-3">
                <div  key={this.props.event._links.self.href} className="panel panel-default">
                    <div className="panel-heading">
                        <h4 className="panel-title">{this.props.event.name}</h4>
                    </div>
                    <div className="panel-body text-left nopadding">
                        <p>{dateFormatter(this.props.event.date)}</p>
                        <p>{this.props.event.description}</p>
                    </div>
                </div>
            </div>
        )
    }
}
// end::app[]


