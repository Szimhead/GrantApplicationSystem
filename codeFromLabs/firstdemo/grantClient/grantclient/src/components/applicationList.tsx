import React, {useState} from 'react';

import {Application} from "../types";
import {ListGroup} from "react-bootstrap";
import {ApplicationDTO} from "../clientAPI";

type Applications = {
    applications: ApplicationDTO[]
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

    const handleNull = () => {
        if (applications.length != 0) {
            return <ListGroup as="ul" className="w-100" defaultActiveKey={"" + applications[0].id}>
                {applications.map((application: Application) => <ListGroup.Item as="li"
                                                                                eventKey={"" + application.id}
                                                                                onClick={handleClick}
                                                                                action>Application {application.id}</ListGroup.Item>
                )}
            </ListGroup>
        } else
            return <>no applications</>
    }


    return (
        handleNull()
    );
};

export default ApplicationList;