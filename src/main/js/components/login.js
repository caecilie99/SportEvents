import React, { PropTypes } from 'react';
import Auth from './common/auth';

/**
 * login for user
 */
export default class LoginPage extends React.Component {

    /**
     * Class constructor.
     */
    constructor(props, context) {
        super(props, context);

        // set the initial component state
        this.state = {
            errors: {},
            successMessage,
            user: {
                username: '',
                password: ''
            }
        };

        // important to get access to this in functions
        this.processForm = this.processForm.bind(this);
        this.changeUser = this.changeUser.bind(this);
    }

    /**
     * Process the form.
     * Authenticate user from a remote endpoint
     *
     * @param {object} event - the JavaScript event object
     */
    processForm(event) {
        // prevent default action. in this case, action is the form submission event
        event.preventDefault();

        // create a string for an HTTP body message
        const username = encodeURIComponent(this.state.user.username);
        const password = encodeURIComponent(this.state.user.password);
        console.log("username: ", username, "passwort: ", password)

        // Send username and password to server and get response
        fetch('/api/auth', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password,
            })
        }).then(function(response) {
            if(response.status == 200) {
                // Success
                // change the component-container state
                this.setState({
                    errors: {}
                });

                // Examine the text in the response
                response.json().then(function(json) {
                    // save the token
                    console.log("user ", username, "token ", json.token)
                    Auth.authenticateUser(username, json.token);
                    if (Auth.isUserAuthenticated())
                        // change the current URL to /dashboard
                        this.context.router.replace('/dashboard');
                }.bind(this));

            } else {
                // Failure
                // change the component state
                const errors = response.errors ? response.errors : {};
                errors.summary = "Fehler bei der Authentifizierung"//response.message;

                this.setState({
                    errors : errors
                });
            }
        }.bind(this));

    }

    /**
     * Change the user object.
     * invoked by onChange
     *
     * @param {object} event - the JavaScript event object
     */
    changeUser(event) {
        const field = event.target.name;
        const user = this.state.user;
        user[field] = event.target.value;

        this.setState({
            user
        });
    }

    /**
     * Render the component.
     */
    render() {
        console.log("Render");
        return (
            <div className="row justify-content-end">
                <div className="col-sm-6 pull-right">
                    <form onSubmit={this.processForm}>
                        {this.state.errors.summary && <p className="alert alert-danger">{this.state.errors.summary}</p>}
                        <div className="form-group">
                            <input type="text" name="username" className="form-control" placeholder="username" value={this.state.user.username} onChange={this.changeUser}/>
                            <input type="password" name="password" className="form-control" placeholder="password" value={this.state.user.password} onChange={this.changeUser}/>
                        </div>
                        <button type="submit" className="btn btn-default">Login</button>
                    </form>
                </div>
            </div>

        );
    }
}

// import for access to router
LoginPage.contextTypes = {
    router: PropTypes.object.isRequired
};

