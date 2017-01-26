'use strict';

import React from "react"
import DocumentTitle from "react-document-title";
import client from "../../client";
import EventItem from "../item/eventitem";

export default class Events extends React.Component {

    constructor(props) {
        super(props);
        this.state = {events: []};
    }

    componentDidMount() {
        console.log("GET events");
        client({method: 'GET', path: 'event/list'}).done(response => {
            console.log("Response: ", response.entity);
            this.setState({events: response.entity});
        });
    }

    render() {
        var events = this.state.events.map(event =>
            <EventItem key={event.id} event={event}/>
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


