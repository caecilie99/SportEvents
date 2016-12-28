
import React from "react"
import Header from "./common/header";
import Footer from "./common/footer";
import Menu from "./common/menu";

export default class Body extends React.Component {
    render() {
        return (
            <div>
                <Menu/>
                {this.props.children}
                <Footer/>
            </div>

        )
    }
}