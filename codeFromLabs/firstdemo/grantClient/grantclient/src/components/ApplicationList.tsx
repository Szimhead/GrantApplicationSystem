import React, {useState} from 'react';

import {Application} from "../types";
import {ListGroup} from "react-bootstrap";

type Applications = {
    applications: Application[]
}

export const ApplicationList = ({applications}: Applications) => {
    const [currIndex, setIndex] = useState(0)

    let handleClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        let index: string | null = e.currentTarget.getAttribute("data-rb-event-key")
        if (index == null)
            setIndex(0)
        else
            setIndex(Number(index))

    }

    return (
        <ListGroup as="ul" className="w-100" defaultActiveKey={"" + applications[0].id}>
            {applications.map((application: Application) => <ListGroup.Item as="li"
                                                           eventKey={"" + application.id}
                                                           onClick={handleClick}
                                                           action>Application {application.id}</ListGroup.Item>
            )}
        </ListGroup>
    );
};

export default ApplicationList;