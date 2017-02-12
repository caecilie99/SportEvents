'use strict';

import React from "react";
import Formatter from "../../api/formatter";
import CompetitionItem from "../item/competitionitem";

/**
 * show all details for event
 * date, name, description, image, promoter, list of competitions with participants
 */
export default class EventDetail extends React.Component {

    /**
     * constructor
     *
     * @param props
     */
    constructor(props) {
        super(props);
        this.state = {event: [], competitions: []};
    }

    /**
     * is invoked immediately after a component is mounted
     * load data from a remote endpoint
     */
    componentWillMount () {
        // load event data from a remote endpoint
        fetch('/event/'+this.props.params.id)
            .then( (response) => {
                return response.json() })
            .then( (json) => {
                // set set with received data
                this.setState({event: json});
            });
        console.log('GET /event/'+this.props.params.id+'/competition');
        // load competition data from a remote endpoint
        fetch('/event/'+this.props.params.id+'/competition')
            .then( (response) => {
                return response.json() })
            .then( (json) => {
                // set set with received data
                this.setState({competitions: json});
            });
    }

    /**
     * render component
     *
     * @returns {XML}
     */
    render() {
        // set competitions, unique key identifier is very important for react!!!
        var competitions = this.state.competitions.map(competition =>
            <CompetitionItem key={competition.id} competition={competition}/>
        );
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
                                <img className="media-object" src={'data:image/jpg;base64,'+this.state.event.image}/>
                            </div>
                            <div className="media-body">
                                <h4 className="media-heading">{this.state.event.description}</h4>
                            </div>
                        </div>
                    </div>
                    <div className="panel-footer">
                        <div  className="panel panel-default">
                            <div className="panel-heading">
                                <h4 className="panel-title">Competitions</h4>
                            </div>
                            <div className="panel-body text-left nopadding">
                                <div className="row">
                                        {competitions}
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}


