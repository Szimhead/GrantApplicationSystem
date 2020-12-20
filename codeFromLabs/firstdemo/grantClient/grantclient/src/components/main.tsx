// src/components/main.tsx

import React from 'react';

import { Jumbotron } from './jumbotron';
import { Card } from './card';
import { DropList } from './dropList';
import { SwitchButton } from './switchButton';

export const Main = () => {
    return (
        <main role="main">
            <Jumbotron />
            <div className="album py-5 bg-light">
                <div className="container">
                    {/*<SwitchButton  show={true} title={"Poka mi co ma"}/>*/}

                    <div className="row">
                        <Card />
                        <Card />
                        <Card />
                        <Card />
                        <Card />
                        <Card />
                        <Card />
                        <Card />
                        <Card />
                    </div>
                </div>
            </div>
        </main>
    );
};