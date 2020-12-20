import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import StudentHomepage from "./pages/StudentHomepage";
import ReviewerApplication from "./pages/ReviewerApplication";
import AnonymousHomepage from "./pages/AnonymousHomepage";
import FundedApplications from "./pages/FundedApplications";
import ReviewsListPage from "./pages/ReviewsListPage";


ReactDOM.render(
    <React.StrictMode>
        <Router>
            <Route exact path={"/"} component={App} />
            <Route path={"/pages/studentHomepage"} component={StudentHomepage} />
            <Route path={"/pages/reviewerApplication"} component={ReviewerApplication} />
            <Route path={"/pages/anonymousHomepage"} component={AnonymousHomepage} />
            <Route path={"/pages/fundedApplications"} component={FundedApplications} />
            <Route path={"/pages/reviews"} component={ReviewsListPage} />
        </Router>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
