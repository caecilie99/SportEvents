
import React from "react"
import Header from "./common/header";
import Footer from "./common/footer";
import Menu from "./common/menu";

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