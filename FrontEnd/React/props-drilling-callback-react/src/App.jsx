import React, { createContext, useContext, useState, useEffect } from 'react'; // Import createContext

import './App.css';
import FooterComponent from './components/footer.component.jsx';
import HeaderComponent from './components/header.component.jsx';
import axios from 'axios';
import BodyComponent from './components/body.component.jsx';

// Context API
export const ProductContext = createContext({ data: [], loading: true }); 

function App(){
  // useState
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  // Get API data using axios and useEffect
  useEffect(() => {
    axios.get("https://dummyjson.com/products").then((res) => {
      if (Array.isArray(res.data.products)) {
        setData(res.data.products);
      } 
      setLoading(false);
      
    })
    .catch((error) => {
      console.error(error);
      setLoading(false);
    });
  }, []);

  return (
    <>
      {/* Props drilling */}
      <HeaderComponent headerTitle="Products List" />
      <ProductContext.Provider value={{ data, loading }}>
        <BodyComponent />
      </ProductContext.Provider>
      <FooterComponent footerTitle="Copyright - 2023 @ shadril238" />
    </>
  );
}

export default App;
