export default class Auth {

    /**
     * Format date from UTC to date string
     * @param formatdate
     * @returns {string}
     */
    static formatDate(formatdate) {
        return new Date(formatdate).toLocaleString('de-DE', { weekday: 'short', year: 'numeric', month: 'long', day: 'numeric' });
    }
}
