'use strict';

// tag::vars[]
import React from "react"
import ReactDOM from "react-dom";
import { Router, Route, Link, IndexRoute, hashHistory, browserHistory } from 'react-router';
import Events from "./components/events";
import Body from "./components/body";
import About from "./components/about";
import Login from "./components/login";

// tag::render[]
ReactDOM.render(
	<Router history={browserHistory}>
		<Route path="/" component={Body}>
			<Route path="/events" component={Events}/>
			<Route path="/about" component={About}/>
			<Route path="/login" component={Login}/>
		</Route>
	</Router>,
	document.getElementById('app')
)
// end::render[]

