import ButtonComponent from './button.component';
import './styles/counter.style.css'
const CounterComponent = ({ count, increment, decrement }) => {
    return (
        <div>
            <p>Count: {count}</p>
            <ButtonComponent btnText="Increment" handleOnClick={increment} />
            <ButtonComponent btnText="Decrement" handleOnClick={decrement} />
        </div>
    );
}

export default CounterComponent;
