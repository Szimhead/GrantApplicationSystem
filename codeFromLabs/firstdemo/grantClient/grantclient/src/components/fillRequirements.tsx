import React from 'react';
import {Container, Form} from "react-bootstrap";
import {Requirement} from "../types";
import {ButtonBlue} from "./button-blue";

type RequirementI = {
    requirements: Requirement[]
}

export const content = (requirement: Requirement) => {
    let placeholder: string = "Enter your "+requirement.name
    let textInput = <>
        <Form.Control type="text" placeholder={placeholder}/>
    </>
    let fileInput = <Form.File id={requirement.name}/>
    if (requirement.contentType === "text")
        return textInput
    else
        return fileInput
}

export const FillRequirementBox = ({requirements}: RequirementI) => {
    return (
        <Container className="d-flex px-1 mt-3">
                <Form className="w-100 p-4">
                    {requirements.map(
                        (requirement: Requirement) =>
                            <>
                                <Form.Group controlId={requirement.name}>
                                    <div className="col-3 pl-4">
                                        <h5><Form.Label>{requirement.name}</Form.Label></h5>
                                    </div>
                                    <div className="col-9 pb-4">
                                        {content(requirement)}
                                    </div>
                                </Form.Group>
                            </>
                    )}
                    <div className="row justify-content-end pr-5">
                        <ButtonBlue link={"/"} text={"Submit application"}/>
                    </div>
                </Form>
        </Container>
    );
};