import React, { PropTypes }  from "react"
import { Link } from 'react-router'
import Auth from "./auth"

export default class LoginControl extends React.Component {
    constructor(props) {
        super(props);
        this.handleLoginClick = this.handleLoginClick.bind(this);
        this.handleLogoutClick = this.handleLogoutClick.bind(this);
        this.state = {isLoggedIn: false};
    }

    handleLoginClick() {
        this.context.router.replace('/login');
        this.setState({isLoggedIn: true});
    }

    handleLogoutClick() {
        Auth.deauthenticateUser();
        this.setState({isLoggedIn: false});
        this.context.router.replace('/');
    }

    render() {
        const isLoggedIn = Auth.isUserAuthenticated();

        let button = null;
        if (isLoggedIn) {
            button = <LogoutButton onClick={this.handleLogoutClick} />;
        } else {
            button = <LoginButton onClick={this.handleLoginClick}/>;
        }

        return (
            <div>
                <Greeting isLoggedIn={isLoggedIn} />
                {button}
            </div>
        );
    }
}

LoginControl.contextTypes = {
    router: PropTypes.object.isRequired
};

function LoginButton(props) {
    return (
        <button className="btn btn-default navbar-btn" onClick={props.onClick}>Login</button>
    );
}

function LogoutButton(props) {
    return (
        <button className="btn btn-default navbar-btn" onClick={props.onClick}>
            Logout
        </button>
    );
}

function Greeting(props) {
    const isLoggedIn = Auth.isUserAuthenticated();
    if (isLoggedIn) {
        return <UserGreeting />;
    }
    return <GuestGreeting />;
}

function UserGreeting(props) {
    return <p className="navbar-text">Welcome {Auth.getUser()}</p>;

}

function GuestGreeting(props) {
    return (
        <p className="navbar-text">Please sign in</p>
    );
}