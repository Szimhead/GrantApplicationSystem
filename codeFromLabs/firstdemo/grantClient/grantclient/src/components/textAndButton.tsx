import {ButtonBlue} from "./button-blue";
import React from "react";

type textAndButtonI = {
    text: string,
    buttonText: string
    buttonLink: string
}

export const TextAndButton = ({text, buttonText, buttonLink}: textAndButtonI) => {
    return (
        <div className="col px-1 mt-2">
            <div className=" row border mx-2 p-3 justify-content-center">
                <div className="col align-self-center">
                    <h5 className="d-inline-flex pr-3">{text}</h5>
                </div>
                <div className="col-3">
                    <ButtonBlue text={buttonText} link={buttonLink}/>
                </div>
            </div>
        </div>
    );
};