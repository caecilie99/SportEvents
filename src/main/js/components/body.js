
import React from "react"
import Header from "./common/header";
import Footer from "./common/footer";
import NavLink from "./common/navlink";

export default class Body extends React.Component {
    render() {
        return (
            <div>
                <Header/>
                <div className="content">
                    <h1>React Router Tutorial</h1>
                    <ul role="nav">
                        <li><NavLink to="/events">Events</NavLink></li>
                        <li><NavLink to="/about">About</NavLink></li>
                    </ul>
                    {this.props.children}
                </div>
                <Footer/>
            </div>

        )
    }
}