'use strict';

import React from "react"
import { Link } from 'react-router';
import Formatter from "../../api/formatter";

/**
 * show one event with date, name, promoter, image, description, used in events
 */
export default class EventItem extends React.Component {

    /**
     * render component
     *
     * @returns {XML}
     */
    render() {
        // import for react: unique key for component !!!
        return (
            <div key={this.props.event.id} className="col-sm-6 col-md-3">
                <div className="panel panel-default">
                    <div className="panel-heading">
                        <h4 className="panel-title">{this.props.event.name}</h4>
                        <h5>{Formatter.formatDate(this.props.event.date)}</h5>
                    </div>
                    <div className="panel-body text-left nopadding">
                        <div className="thumbnail">
                            <Link to={"events/"+this.props.event.id}>
                                <img src={'data:image/jpg;base64,'+this.props.event.image}/>
                            </Link>
                        </div>
                        <p>{this.props.event.description}</p>
                    </div>
                </div>
            </div>
        )
    }
}


