'use strict';

import React from "react"
import client from "../../client";
import Formatter from "../../api/formatter";

export default class EventDetail extends React.Component {

    constructor(props) {
        super(props);
        this.state = {event: []};
    }

    componentWillMount () {
        console.log('GET /events/'+this.props.params.id);
        fetch('/api/events/'+this.props.params.id)
            .then( (response) => {
                return response.json() })
            .then( (json) => {
                console.log("Success");
                this.setState({event: json});
            });
    }

    render() {
        console.log('Render /events/'+this.props.params.id);
        return (
            <div key={this.props.params.id}>
                <div  key={this.props.params.id} className="panel panel-default">

                    <div className="panel-heading">
                        <h4 className="panel-title">{this.state.event.name}</h4>
                        <h5>{Formatter.formatDate(this.state.event.date)}</h5>
                    </div>
                    <div className="panel-body text-left nopadding">
                        <div className="media">
                            <div className="media-left media-middle">
                                <img className="media-object" src="http://lorempixel.com/200/200"/>
                            </div>
                            <div className="media-body">
                                <h4 className="media-heading">{this.state.event.description}</h4>
                            </div>
                        </div>
                    </div>
                    <div className="panel-footer">
                        <h1>Detail</h1>
                    </div>

                </div>
            </div>
        )
    }
}


