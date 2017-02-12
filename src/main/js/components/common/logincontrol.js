import React, { PropTypes }  from "react"
import { Link } from 'react-router'
import Auth from "./auth"

/**
 * controls for login and logout
 *
 */
export default class LoginControl extends React.Component {

    /**
     * constructor for component
     * @param props
     */
    constructor(props) {
        super(props);

        // binding is important to use this within functions
        this.handleLoginClick = this.handleLoginClick.bind(this);
        this.handleLogoutClick = this.handleLogoutClick.bind(this);

        // set state
        this.state = {isLoggedIn: false};
    }

    /**
     * show login form
     */
    handleLoginClick() {
        this.context.router.replace('/login');
        this.setState({isLoggedIn: true});
    }

    /**
     * logout user, delete token
     * show index page
     */
    handleLogoutClick() {
        Auth.deauthenticateUser();
        this.setState({isLoggedIn: false});
        this.context.router.replace('/');
    }

    /**
     * render component
     *
     * @returns {XML}
     */
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

// important to use router
LoginControl.contextTypes = {
    router: PropTypes.object.isRequired
};

/**
 * show login button
 * @param props
 * @returns {XML}
 * @constructor
 */
function LoginButton(props) {
    return (
        <button className="btn btn-default navbar-btn" onClick={props.onClick}>Login</button>
    );
}

/**
 * show logout button
 * @param props
 * @returns {XML}
 * @constructor
 */
function LogoutButton(props) {
    return (
        <button className="btn btn-default navbar-btn" onClick={props.onClick}>
            Logout
        </button>
    );
}

/**
 * show greeting in header
 *
 * @param props
 * @returns {XML}
 * @constructor
 */
function Greeting(props) {
    const isLoggedIn = Auth.isUserAuthenticated();
    if (isLoggedIn) {
        return <UserGreeting />;
    }
    return <GuestGreeting />;
}

/**
 * show username in greeting
 * @param props
 * @returns {XML}
 * @constructor
 */
function UserGreeting(props) {
    return <p className="navbar-text">Welcome {Auth.getUser()}</p>;

}

/**
 * show greeting for guest
 *
 * @param props
 * @returns {XML}
 * @constructor
 */
function GuestGreeting(props) {
    return (
        <p className="navbar-text">Please sign in</p>
    );
}