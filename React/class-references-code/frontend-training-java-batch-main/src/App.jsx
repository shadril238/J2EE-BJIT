import { useState } from "react";
import "./App.css";
import CustomTitle from "./components/title";
import Button from "./components/button.component";
import { PERSON_INFO } from "./utils/constants";

function App() {
  var varDetails = {
    name: "Angkon Kumar Roy",
    email: "a@gmail.com",
    phone: "123456",
    address: {
      street: "street Details",
      city: "Dhaka",
    },
    tags: ["1", "tag2"],
  };

  console.log("varDetails ", varDetails);

  const [showDetails, setShowDetails] = useState(false);
  const [personDetails, setPersonDetails] = useState(PERSON_INFO);

  const handlePersonChange = () => {
    // console.log("Person data change clicked");
    setPersonDetails({
      ...personDetails,
      name: "Changed Name",
      address: {
        ...personDetails.address,
        city: "Khulna",
      },
    });

    // changing the details in variables
    varDetails = {
      name: "Changed in var",
      email: "a@gmail.com",
      phone: "3333333",
      address: {
        street: "street Details",
        city: "Dhaka",
      },
      tags: ["1", "tag2"],
    };
  };

  const calculateSum = (number1, number2) => {
    let sum = number1 + number2;
    sum = sum + 1;
    return sum;
  };

  return (
    <>
      <CustomTitle title="Contact Information" textColor="#6002b8" />
      <p>Name: {personDetails.name}</p>
      <button
        onClick={() => {
          console.log("button pressed  ");
          setShowDetails(true);
          const sum = calculateSum(10, 20);
          console.log(`The sum is ${sum}`);
          // alert(`The sum is ${sum} the name is ${personDetails.name}`);
        }}
      >
        Show Details
      </button>
      {/* <button onClick={handlePersonChange}>Change name</button> */}
      <Button btnText="Change Info" handleOnClick={handlePersonChange} />
      {showDetails == true && (
        <div>
          <p>Email: {personDetails.email}</p>
          <p>phone: {personDetails.phone}</p>
          <p>City: {personDetails.address.city}</p>
        </div>
      )}
    </>
  );
}

export default App;
