import React from 'react';

type BordersI = {
    title: string,
    content: React.ReactNode
}

export const Borders = ({title, content}: BordersI) => {
    return (
        <div className="col px-1">
            <div className="row mx-4 mt-4">
                <h5>{title}</h5>
            </div>
            <div className="row border mx-2 p-3">
                {content}
            </div>
        </div>
    );
};