import React, { PropTypes } from 'react';
import Auth from './common/auth';

export default class LoginPage extends React.Component {

    /**
     * Class constructor.
     */
    constructor(props, context) {
        super(props, context);

        const storedMessage = localStorage.getItem('successMessage');
        let successMessage = '';

        if (storedMessage) {
            successMessage = storedMessage;
            localStorage.removeItem('successMessage');
        }

        // set the initial component state
        this.state = {
            errors: {},
            successMessage,
            user: {
                username: '',
                password: ''
            }
        };

        this.processForm = this.processForm.bind(this);
        this.changeUser = this.changeUser.bind(this);
    }

    /**
     * Process the form.
     *
     * @param {object} event - the JavaScript event object
     */
    processForm(event) {
        // prevent default action. in this case, action is the form submission event
        event.preventDefault();

        // create a string for an HTTP body message
        const username = encodeURIComponent(this.state.user.username);
        const password = encodeURIComponent(this.state.user.password);

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
                // success

                // change the component-container state
                this.setState({
                    errors: {}
                });

                // save the token
                Auth.authenticateUser(username, response.token);

                // change the current URL to /
                this.context.router.replace('/');

            } else {
                // failure

                // change the component state
                const errors = response.errors ? response.errors : {};
                errors.summary = response.message;

                this.setState({
                    errors
                });
            }
        }.bind(this));

    }

    /**
     * Change the user object.
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
        return (
            <form onSubmit={this.processForm}>
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="username" value={this.state.user.username} onChange={this.changeUser}/>
                    <input type="password" name="password" class="form-control" placeholder="password" value={this.state.user.password} onChange={this.changeUser}/>
                </div>
                <button type="submit" class="btn btn-default">Login</button>
            </form>

        );
    }

}

LoginPage.contextTypes = {
    router: PropTypes.object.isRequired
};

