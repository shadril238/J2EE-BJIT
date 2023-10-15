import React from 'react';
import './styles/header.style.css';

const HeaderComponent = ({headerText}) => {
    return (
        <>
            <ul className="navbar">
                <li><a className="active" >{headerText}</a></li>
            </ul>
        </>
    );
}

export default HeaderComponent;
