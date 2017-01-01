'use strict';

// tag::vars[]
import React from "react"
import DocumentTitle from "react-document-title";
import client from "../client";
import EventItem from "./eventitem";

/*var ReactBsTable  = require('react-bootstrap-table');
var BootstrapTable = ReactBsTable.BootstrapTable;
var TableHeaderColumn = ReactBsTable.TableHeaderColumn;*/
// end::vars[]

// tag::app[]
export default class Events extends React.Component {

    constructor(props) {
        super(props);
        this.state = {events: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/events'}).done(response => {
            this.setState({events: response.entity._embedded.events});
        });
    }

    render() {
        var events = this.state.events.map(event =>
            <EventItem key={event._links.self.href} event={event}/>
        );
        return (
            <DocumentTitle title="Show Events">
                <div>
                    <div className="row">
                        {events}
                    </div>
                </div>
            </DocumentTitle>
        );
    }
}
// end::app[]


