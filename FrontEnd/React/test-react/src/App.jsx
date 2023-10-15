import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import DemoComponent from './components/DemoComponent'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <DemoComponent></DemoComponent>
    </>
  )
}

export default App
