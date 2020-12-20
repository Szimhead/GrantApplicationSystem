import React, {useState} from 'react';

import {Borders} from "./borders";
import {Review} from "../types";
import {ListGroup} from "react-bootstrap";
import {ReviewDetails} from "./reviewDetails";
import {ButtonBlue} from "./button-blue";
import {FinalDetails} from "./FinalDetails";

const AddReview = () => {
    return (
        <div className="col px-1 mt-2">
            <div className=" row border mx-2 p-3 justify-content-center">
                <h5 className="d-inline-flex align-self-center pr-3">Add new review and classifcation</h5>
                <ButtonBlue text={"Go"} link={"#"}/>
            </div>
        </div>
    );
};

type ReviewsListI = {
    reviews: Review[],
    final: Review
}

export const ReviewsList = ({reviews, final}:ReviewsListI) => {
    const [currIndex, setIndex] = useState(0)

    const [toShow, setToShow] = useState(<ReviewDetails review={reviews[currIndex]}/>)

    let handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        let index: string | null = e.currentTarget.getAttribute("data-rb-event-key")
        if (index == null) {
            setIndex(0)
            setToShow(<ReviewDetails review={reviews[currIndex]}/>)
        } else if (index == "final") {
            setToShow(<FinalDetails review={final}/>)
        } else {
            setIndex(Number(index))
            setToShow(<ReviewDetails review={reviews[currIndex]}/>)
        }
    }

    return (
        <div className="container">
            <div className="row mt-4 justify-content-center">
                <Borders title={"Reviews List"} content={
                    <ListGroup as="ul" className="w-100" defaultActiveKey={"" + reviews[0].id}>
                        <ListGroup.Item as="li"
                                        className="mb-3"
                                        eventKey='final'
                                        onClick={handleClick}
                                        action>Final Evaluation</ListGroup.Item>
                        {reviews.map(
                            (review: Review, index) => <ListGroup.Item as="li"
                                                                       eventKey={"" + review.id}
                                                                       className="border"
                                                                       onClick={handleClick}
                                                                       action>Review {review.id}</ListGroup.Item>
                        )}
                    </ListGroup>}/>
                <div className="col-7">
                    <Borders title={"Selected Review"} content={toShow}/>
                    <AddReview/>
                </div>
            </div>
        </div>
    );
};

export default ReviewsList;