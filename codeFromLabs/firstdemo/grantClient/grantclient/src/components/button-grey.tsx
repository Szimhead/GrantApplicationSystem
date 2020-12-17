import React from 'react';

type ButtonGreyI = {text:string, link:string}

export const ButtonGrey = ({text, link}:ButtonGreyI) => {
    return (
        <a href={link} className="btn btn-secondary m-2">
            {text}
        </a>
    );
};