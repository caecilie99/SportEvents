
import React from "react"
import { Link } from 'react-router'
import NavLink from "./navlink"

export default class Menu extends React.Component {
    render() {
        return (
            <div className="navbar navbar-default headroom">
                <div className="navbar-header">
                    <Link className="navbar-brand" to="/about">Sport<span className="light">Event</span></Link>
                </div>
                <div className={"navbar-collapse navbar-page-header navbar-left effect brackets collapse"}>
                    <ul className="nav navbar-nav" role="nav">
                        <li><NavLink to="/events">Events</NavLink></li>
                        <li><NavLink to="/about">About</NavLink></li>
                    </ul>
                </div>
            </div>
        )
    }
}
