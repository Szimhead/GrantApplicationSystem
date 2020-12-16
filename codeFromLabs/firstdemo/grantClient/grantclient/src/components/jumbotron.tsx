// src/components/jumbotron.tsx

/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import {ButtonBlue} from "./button-blue";
import {ButtonGrey} from "./button-grey";

export const Jumbotron = () => {
    return (
        <section className="jumbotron text-center mb-0 bg-white">
            <div className="container">
                <h1 className="jumbotron-heading">Album example</h1>
                <p className="lead text-muted">
                    Something short and leading about the collection below—its contents,
                    the creator, etc. Make it short and sweet, but not too short so folks
                    don't simply skip over it entirely.
                </p>
                <p>
                    <ButtonBlue text={"Main call to action"} link={"#"}/>
                    <ButtonGrey text={"Secondary action"} link={"#"}/>
                </p>
            </div>
        </section>
    );
};