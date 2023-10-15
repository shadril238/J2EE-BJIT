import { useState } from 'react';
import CounterComponent from '../components/counter.component.jsx';
import './counter-app.style.css'
import HeaderComponent from '../components/header.component.jsx';
import FooterComponent from '../components/footer.component.jsx';


const CounterAppPage = () => {
    const [count, setCount] = useState(0);
    const increment = () => {
        setCount(count + 1);
    };

    const decrement = () => {
        setCount(count - 1);
    };

    return (
        <div>
            <HeaderComponent headerText="Simple Counter App" />
            <CounterComponent count={count} increment={increment} decrement={decrement} />
        </div>
    );
}

export default CounterAppPage;
