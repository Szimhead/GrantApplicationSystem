import React, {useState} from 'react';

import {Borders} from "./borders";
import {Review} from "../types";
import {ListGroup} from "react-bootstrap";
import {ReviewDetails} from "./reviewDetails";
import {TextAndButton} from "./textAndButton";
import {FinalDetails} from "./FinalDetails";

type ReviewsListI = {
    reviews: Review[],
    final: Review | null,
    extra: string
}

export const content = (extra: string) => {
    let visible = <TextAndButton text={"Add new review and classification"} buttonText={"Go"} buttonLink={"#"}/>
    if (extra === "")
        return
    else
        return visible
}

export const ReviewsList = ({reviews, final=null, extra}: ReviewsListI) => {
    const [toShow, setToShow] = useState(<></>)
    const [changed, setChanged] = useState(false)
    let i = <></>;

    let handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        let index: string | null = e.currentTarget.getAttribute("data-rb-event-key")
        if (index === "final") {
            setToShow(<FinalDetails review={final}/>)
            setChanged(true)
        } else {
            setToShow(<ReviewDetails review={reviews[Number(index)]}/>)
            setChanged(true)
        }
    }

    const rightComponent = () => {
        if (changed)
            return toShow
        else
            return i
    }

    const handleNull = () => {
        if (reviews.length != 0) {
            i = <ReviewDetails review={reviews[0]}/>;
            return <div className="container">
                <div className="row mt-3 justify-content-center">
                    <div className="w-50 d-flex">
                        <Borders title={"Reviews List"} content={
                            <ListGroup as="ul" className="w-100" defaultActiveKey={"0"}>
                                <ListGroup.Item as="li"
                                                className="mb-3"
                                                eventKey='final'
                                                onClick={handleClick}
                                                action>Final Evaluation</ListGroup.Item>
                                {reviews.map(
                                    (review: Review, index) => <ListGroup.Item as="li"
                                                                               eventKey={"" + index}
                                                                               className="border"
                                                                               onClick={handleClick}
                                                                               action>Review {review.id}</ListGroup.Item>
                                )}
                            </ListGroup>}/>
                    </div>
                    <div className="w-50">
                        <Borders title={"Selected Review"} content={rightComponent()}/>
                        {content(extra)}
                    </div>
                </div>
            </div>
        } else
            return <div className="container"><Borders title={""} content={"no reviews"}/></div>
    }

    return (
        handleNull()
    );
};

export default ReviewsList;