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
import {Signup} from "./pages/Signup";
import PanelPage from "./pages/PanelPage";
import AddReviewPage from "./pages/AddReviewPage";
import AddFinalEvalPage from "./pages/AddFinalEvalPage";
import ReviewerHomepage from "./pages/ReviewerHomepage";
import {StudentProfile} from "./pages/StudentProfile";
import SubmittedAppPage from "./pages/SubmittedAppPage";
import GrantCallPage from "./pages/GrantCallPage";
import EvaledAppPage from "./pages/EvaledAppPage";
import FillOutAppPage from "./pages/FilOutAppPage";

ReactDOM.render(
    <React.StrictMode>
        <Router>
            <Route exact path={"/"} component={App} />
            <Route path={"/pages/studentHomepage"} component={StudentHomepage} />
            <Route path={"/pages/reviewerApplication"} component={ReviewerApplication} />
            <Route path={"/pages/anonymousHomepage"} component={AnonymousHomepage} />
            <Route path={"/pages/fundedApplications"} component={FundedApplications} />
            <Route path={"/pages/signup"} component={Signup} />
            <Route path={"/pages/reviews"} component={ReviewsListPage} />
            <Route path={"/pages/panelPage"} component={PanelPage} />
            <Route path={"/pages/addReview"} component={AddReviewPage} />
            <Route path={"/pages/addFinal"} component={AddFinalEvalPage} />
            <Route path={"/pages/reviewerHomepage"} component={ReviewerHomepage} />
            <Route path={"/pages/studentProfile"} component={StudentProfile} />
            <Route path={"/pages/grantCallPage"} component={GrantCallPage} />
            <Route path={"/pages/submittedAppPage"} component={SubmittedAppPage} />
            <Route path={"/pages/evaledAppPage"} component={EvaledAppPage} />
            <Route path={"/pages/fillOutAppPage"} component={FillOutAppPage} />
        </Router>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
