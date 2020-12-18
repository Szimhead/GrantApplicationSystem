import React from 'react';

type PageTitleI = { title: string, extraText: string }

export const PageTitle = ({title, extraText}: PageTitleI) => {
    return (
        <div className="container">
            <div className="row mt-4 border-bottom">
                <div className="col-10">
                    <h1>{title}</h1>
                </div>
                <div className="col-2 align-self-end mb-1">
                    {extraText}
                </div>
            </div>
        </div>
    );
};