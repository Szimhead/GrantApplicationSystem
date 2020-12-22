import React, {FormEvent} from "react";
import {Button, Form, Row} from "react-bootstrap";
import {ButtonBlue} from "./button-blue";
import {ButtonGrey} from "./button-grey";
import {ApplicationControllerApi, GrantCallControllerApi, ReviewDTO} from "../clientAPI";
import {useParams} from "react-router-dom";

type ReviewFormI = {
    label: string,
    appId: number
}

export const ReviewForm = ({label, appId}: ReviewFormI) => {

    const applicationAPI = new ApplicationControllerApi();

    let handleSubmit = (e:FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        const formData = new FormData(e.currentTarget),
            formDataObj = Object.fromEntries(formData.entries());

        let review: ReviewDTO = {
            id: 0,
            comment: formDataObj["comment"] as string,
            isAccepted: formDataObj["isAccepted"] == "true",
            reviewerId: 18
        }

        applicationAPI.addReviewUsingPOST(appId, review).then( (value) => alert("Submitted Review"))
            .catch( (error) => alert("An error has occurred"))

    }
    return (
        <Form className="w-100 p-4" onSubmit={handleSubmit}>
            <Form.Group as={Row}>
                <div className="col">
                    <Form.Label>{label}</Form.Label>
                </div>
                <div className="col">
                    <Form.Check type="radio" name="isAccepted" id="yes" label="true"/>
                </div>
                <div className="col">
                    <Form.Check type="radio" name="isAccepted" id="no" label="false"/>
                </div>
            </Form.Group>

            <Form.Group controlId="comment">
                <Form.Label>Comment:</Form.Label>
                <Form.Control name="comment" type="text" placeholder="Enter your comment"/>
            </Form.Group>
            <div className="row justify-content-end">
                <ButtonGrey link={"/"} text={"cancel"}/>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </div>
        </Form>
    );
}
