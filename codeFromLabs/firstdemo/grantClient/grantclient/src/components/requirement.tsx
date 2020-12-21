import React, {useState} from 'react';
import {Container, ListGroup} from "react-bootstrap";
import {Answer, Requirement, Review} from "../types";
import {ButtonBlue} from "./button-blue";

type RequirementI = {
    answers: Answer[]
}

export const content = (answer: Answer) => {
    let text = <p>{answer.content}</p>
    let button = <ButtonBlue text={"Download file"} link={"#"}/>
    if (answer.requirement.contentType == "text")
        return text
    else
        return button
}

export const RequirementBox = ({answers}: RequirementI) => {
    return (
        <Container className="d-flex px-1 mt-4">
            <div className="row">
            {answers.map(
                (answer: Answer, index) =>
                    <>

                            <div className="col-3 pb-5 pl-4">
                                <h5>{answer.requirement.name}:</h5>
                            </div>
                            <div className="col-9">
                                {content(answer)}
                            </div>
                    </>
            )}

            </div>
        </Container>
    );
};