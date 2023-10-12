import React from 'react';
import './styles/footer.style.css';

const FooterComponent = ({footerText}) => {
    return (
        <footer className="footer">
            <p>{footerText}</p>
        </footer>
    );
}

export default FooterComponent;
