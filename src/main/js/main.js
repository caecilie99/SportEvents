'use strict';

import React from "react"
import ReactDOM from "react-dom";
import { Router, Route, Link, IndexRoute, hashHistory, browserHistory } from 'react-router';
import Events from "./components/index/events";
import EventDetail from "./components/detail/eventdetail";
import Body from "./components/body";
import About from "./components/about";
import Login from "./components/login";

ReactDOM.render(
	<Router history={browserHistory}>
		<Route path="/" component={Body}>
			<Route path="/events" component={Events}/>
			<Route path="/events/:id" component={EventDetail}/>
			<Route path="/about" component={About}/>
			<Route path="/login" component={Login}/>
		</Route>
	</Router>,
	document.getElementById('app')
)

