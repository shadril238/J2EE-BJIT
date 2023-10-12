import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import CounterAppPage from './pages/counter-app.page'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <div style={{display:"flex", justifyContent: "center"}}>
      <CounterAppPage />
    </div>
      
    </>
  )
}

export default App
