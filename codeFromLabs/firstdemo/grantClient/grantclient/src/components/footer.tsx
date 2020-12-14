// src/components/footer.tsx

/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';

export const Footer = () => {
    return (
        <footer className="text-muted py-5">
            <div className="container">
                <p className="float-right">
                    <a href="#">Back to top</a>
                </p>
                <p>
                    Album example is &copy; Bootstrap, but please download and customize
                    it for yourself!
                </p>
            </div>
        </footer>
    );
};