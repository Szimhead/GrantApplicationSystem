// src/components/jumbotron.tsx

/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import {ButtonBlue} from "./button-blue";
import {ButtonGrey} from "./button-grey";

export const Jumbotron = () => {
    return (
        <section className="jumbotron text-center mb-0 bg-white">
            <div className="container">
                <h1 className="jumbotron-heading">Testing, attention please</h1>
                <p className="lead text-muted">
                    hey, you can try clicking those buttons below
                </p>
                <p>
                    <ButtonBlue text={"student homepage"} link={"/pages/studentHomepage"}/>
                    <ButtonBlue text={"reviewer homepage"} link={"/pages/reviewerHomepage"}/>
                    <ButtonBlue text={"Anonymous Homepage"} link={"/pages/anonymousHomepage"}/>
                    <ButtonGrey text={"Grant Call Page"} link={"/pages/grantCallPage"}/>
                    <ButtonGrey text={"Student Profile"} link={"/pages/studentProfile"}/>
                    <ButtonGrey text={"Funded Applications"} link={"/pages/fundedApplications"}/>
                    <ButtonGrey text={"Student Profile"} link={"/pages/studentProfile"}/>
                </p>
            </div>
        </section>
    );
};