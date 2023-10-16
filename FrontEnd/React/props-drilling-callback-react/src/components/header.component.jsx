import React from 'react';
import './header.component.css';

const HeaderComponent = (props) => {
    return (
        <div className="header">
            <h1>{props.headerTitle}</h1>
        </div>
    );
}

export default HeaderComponent;
