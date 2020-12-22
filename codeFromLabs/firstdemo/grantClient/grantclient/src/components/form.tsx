import React from "react";
import {Form, Row} from "react-bootstrap";
import {ButtonBlue} from "./button-blue";
import {ButtonGrey} from "./button-grey";

type ReviewFormI = {
    label: string
}

export const ReviewForm = ({label}: ReviewFormI) => {
    return (
        <>
            <Form className="w-100 p-4">
                <Form.Group as={Row}>
                    <div className="col">
                        <Form.Label>{label}</Form.Label>
                    </div>
                    <div className="col">
                        <Form.Check type="radio" name="isAccepted" id="yes" label="yes"/>
                    </div>
                    <div className="col">
                        <Form.Check type="radio" name="isAccepted" id="no" label="no"/>
                    </div>
                </Form.Group>

                <Form.Group controlId="comment">
                    <Form.Label>Comment:</Form.Label>
                    <Form.Control type="text" placeholder="Enter your comment"/>
                </Form.Group>
                <div className="row justify-content-end">
                    <ButtonGrey link={"/"} text={"cancel"}/>
                    <ButtonBlue link={"/"} text={"add"}/>
                </div>
            </Form>
        </>
    );
}
