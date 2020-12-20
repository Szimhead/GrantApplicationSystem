import React from 'react';
import {Student} from "../types";
import {ButtonBlue} from "./button-blue";

export const StudentDetails = ({student}:{student:Student}) => {
    return (
        <div>
            <h4>Student name:</h4>
            <p>{student.name}</p>
            <h4>Student email:</h4>
            <p>{student.email}</p>
            <h4>Student address:</h4>
            <p>{student.address}</p>
            <h4 className="d-inline-flex pr-4">CV:</h4>
            <ButtonBlue text={"show"} link={"#"}/>
        </div>
    )
}