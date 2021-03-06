import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route} from "react-router-dom";
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
import Login from "./pages/LoginPage";

ReactDOM.render(
    <React.StrictMode>
        <Router>
            <Route exact path={"/"} component={App} />
            <Route path={"/pages/studentHomepage"} component={StudentHomepage} />
            <Route path={"/pages/reviewerApplication/:id"} component={ReviewerApplication} />
            <Route path={"/pages/anonymousHomepage"} component={AnonymousHomepage} />
            <Route path={"/pages/fundedApplications"} component={FundedApplications} />
            <Route path={"/pages/signup"} component={Signup} />
            <Route path={"/pages/login"} component={Login} />
            <Route path={"/pages/reviews/:id"} component={ReviewsListPage} />
            <Route path={"/pages/panelPage/:id"} component={PanelPage} />
            <Route path={"/pages/addReview/:id"} component={AddReviewPage} />
            <Route path={"/pages/addFinal"} component={AddFinalEvalPage} />
            <Route path={"/pages/reviewerHomepage/:revId"} component={ReviewerHomepage} />
            <Route path={"/pages/studentProfile/:id"} component={StudentProfile} />
            <Route path={"/pages/grantCallPage"} component={GrantCallPage} />
            <Route path={"/pages/submittedAppPage"} component={SubmittedAppPage} />
            <Route path={"/pages/evaledAppPage/:id"} component={EvaledAppPage} />
            <Route path={"/pages/fillOutAppPage"} component={FillOutAppPage} />
        </Router>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
