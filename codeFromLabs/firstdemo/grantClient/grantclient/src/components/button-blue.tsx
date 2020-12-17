import React from 'react';

type ButtonBlueI = {text:string, link:string}

export const ButtonBlue = ({text, link}:ButtonBlueI) => {
    return (
        <a href={link} className="btn btn-primary m-2">
            {text}
        </a>
    );
};