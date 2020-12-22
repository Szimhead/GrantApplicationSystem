import React, {useState} from 'react';

import {Application} from "../types";
import {ListGroup} from "react-bootstrap";
import {ApplicationDTO} from "../clientAPI";
import {Borders} from "./borders";
import {TextAndButton} from "./textAndButton";

type Applications = {
    applications: ApplicationDTO[]
}

export const ApplicationList = ({applications}: Applications) => {
    const [currIndex, setIndex] = useState(0)
    let i = 0;

    let handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        let index: string | null = e.currentTarget.getAttribute("data-rb-event-key")
        if (index != null)
            setIndex(Number(index))
    }

    const rightIndex = () => {
        if (currIndex == 0)
            return i
        else
            return currIndex
    }

    const handleNull = () => {
        if (applications.length != 0) {
            i = applications[0].id;
            return <div className="row justify-content-center">
                <div className="w-50">
                    <Borders title={"Application List"}
                             content={<ListGroup as="ul" className="w-100" defaultActiveKey={"" + applications[0].id}>
                                 {applications.map((application: Application) => <ListGroup.Item as="li"
                                                                                                 eventKey={"" + application.id}
                                                                                                 onClick={handleClick}
                                                                                                 action>Application, id: {application.id}</ListGroup.Item>
                                 )}
                             </ListGroup>}/>
                </div>
                <div className="col-5 mt-5">
                    <div className="row">
                        <TextAndButton text={"See application and student details"} buttonText={"Go"}
                                       buttonLink={"/pages/reviewerApplication/"+rightIndex()}/>
                    </div>
                    <div className="row">
                        <TextAndButton text={"See reviews"} buttonText={"Go"}
                                       buttonLink={"/pages/reviews/"+rightIndex()}/>
                    </div>
                    <div className="row mx-2 p-4 justify-content-center">
                        <h5 className="text-center">To post a review and
                            classification go to reviews.</h5>
                    </div>
                </div>
            </div>
        } else
            return <>no applications</>
    }


    return (
        handleNull()
    );
};

export default ApplicationList;

