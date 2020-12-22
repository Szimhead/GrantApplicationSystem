import React, {useState} from 'react';

import {Borders} from "./borders";
import {Answer, Review} from "../types";
import {ListGroup} from "react-bootstrap";
import {ReviewDetails} from "./reviewDetails";
import {TextAndButton} from "./textAndButton";
import {FinalDetails} from "./FinalDetails";

type ReviewsListI = {
    reviews: Review[],
    final: Review
    extra: string
}

export const content = (extra: string) => {
    let visible = <TextAndButton text={"Add new review and classification"} buttonText={"Go"} buttonLink={"#"}/>
    if (extra == "")
        return
    else
        return visible
}

export const ReviewsList = ({reviews, final, extra}: ReviewsListI) => {
    const [currIndex, setIndex] = useState(0)

    const [toShow, setToShow] = useState(<ReviewDetails review={reviews[currIndex]}/>)

    let handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        let index: string | null = e.currentTarget.getAttribute("data-rb-event-key")
        if (index == null) {
            setIndex(0)
            setToShow(<ReviewDetails review={reviews[Number(index)]}/>)
        } else if (index == "final") {
            setToShow(<FinalDetails review={final}/>)
        } else {
            setIndex(Number(index))
            setToShow(<ReviewDetails review={reviews[Number(index)]}/>)
        }
    }

    return (
        <div className="container">
            <div className="row mt-3 justify-content-center">
                <div className="w-50 d-flex">
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
                </div>
                <div className="w-50">
                    <Borders title={"Selected Review"} content={toShow}/>
                    {content(extra)}
                </div>
            </div>
        </div>
    );
};

export default ReviewsList;