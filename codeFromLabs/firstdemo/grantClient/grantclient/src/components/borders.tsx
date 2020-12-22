import React from 'react';
import {Container} from "react-bootstrap";

type BordersI = {
    title: string,
    content: React.ReactNode
}

export const Borders = ({title, content}: BordersI) => {
    return (
        <Container className=" px-1">
            <Container className="mx-4 mt-4">
                <h5>{title}</h5>
            </Container>
            <Container className=" border mx-2 p-3">
                {content}
            </Container>
        </Container>
    );
};