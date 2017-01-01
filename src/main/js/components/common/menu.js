
import React from "react"
import { Link } from 'react-router'
import NavLink from "./navlink"
import Auth from './auth';

export default class Menu extends React.Component {
    render() {
        return (
            <header className="navbar navbar-inverse">
                <div className="container">
                    <div className="navbar-header">
                        <Link className="navbar-brand" to="/about">Sport<span className="light">Event</span></Link>
                    </div>
                    <nav className="navbar-collapse navbar-page-header navbar-left">
                        <ul className="nav navbar-nav  navbar-left" role="nav">
                            <li><NavLink to="/events">Events</NavLink></li>
                            <li><NavLink to="/about">About</NavLink></li>
                        </ul>
                    </nav>
                    <div className="navbar-right">
                        <p className="navbar-text navbar-right">
                            <Link className="navbar-link" to="/login">Login</Link>
                        </p>
                    </div>

                </div>
            </header>
        )
    }
}

function Greeting(props) {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
        return <UserGreeting />;
    }
    return <GuestGreeting />;
}
