import React from 'react';

type BordersI = { title: string }

export const Borders = ({title}: BordersI) => {
    return (
        <div className="col">
            <div className="row mx-4 pl-5">
                <h5>{title}</h5>
            </div>
            <div className="row border mx-4 p-3">
                content
            </div>
        </div>
    );
};