import React from 'react';
import './footer.component.css';

const FooterComponent = (props) => {
    return (
        <div>
            <footer className="footer">
                <p>{props.footerTitle} </p>
            </footer>
        </div>
    );
}

export default FooterComponent;
