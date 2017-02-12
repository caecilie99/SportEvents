'use strict';

import React from "react"
import Footer from "./common/footer";
import Menu from "./common/menu";

/**
 * main body component
 * shows menu, main and footer
 */
export default class Body extends React.Component {
    render() {
        return (
            <div>
                <Menu/>
                <main className="container">
                    {this.props.children}
                </main>
                <Footer/>
            </div>

        )
    }
}