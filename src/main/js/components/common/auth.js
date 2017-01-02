export default class Auth {

    /**
     * Authenticate a user. Save a token string in Local Storage
     *
     * @param {string} user
     * @param {string} token
     */
    static authenticateUser(user, token) {
        sessionStorage.setItem('token', token);
        sessionStorage.setItem('user', user);
    }

    /**
     * Check if a user is authenticated - check if a token is saved in Local Storage
     *
     * @returns {boolean}
     */
    static isUserAuthenticated() {
        return sessionStorage.getItem('token') !== null;
    }

    /**
     * Deauthenticate a user. Remove a token from Local Storage.
     *
     */
    static deauthenticateUser() {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('user');
    }

    /**
     * Get a token value.
     *
     * @returns {string}
     */

    static getToken() {
        return sessionStorage.getItem('token');
    }

    /**
     * Get username
     *
     * @returns {string}
     */
    static getUser() {
        return sessionStorage.getItem('user');
    }

}

