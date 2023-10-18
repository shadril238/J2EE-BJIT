import { useNavigate } from 'react-router-dom';
import './header.component.css';

const HeaderComponent = (props) => {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    
    return (
        <div className="header">
            <h1>{props.headerTitle}</h1>
        </div>
    );
}

export default HeaderComponent;