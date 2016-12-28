'use strict';

// tag::vars[]
import React from "react"
import client from "../client";

var ReactBsTable  = require('react-bootstrap-table');
var BootstrapTable = ReactBsTable.BootstrapTable;
var TableHeaderColumn = ReactBsTable.TableHeaderColumn;
// end::vars[]

function dateFormatter(cell, row) {
    return new Date(cell).toLocaleString('de-DE', { weekday: 'short', year: 'numeric', month: 'long', day: 'numeric' });
}

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
        return (
            <BootstrapTable data={this.state.events} striped hover>
                <TableHeaderColumn dataField='date' width='150' dataFormat={dateFormatter }>Datum</TableHeaderColumn>
                <TableHeaderColumn dataField='name' width='250'>Event</TableHeaderColumn>
                <TableHeaderColumn dataField='description'>Beschreibung</TableHeaderColumn>
                <TableHeaderColumn isKey hidden dataField='_links.self.href'>Link</TableHeaderColumn>
            </BootstrapTable>
        );
    }
}
// end::app[]


