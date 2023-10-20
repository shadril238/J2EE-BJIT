import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { axiosInstance } from "./../utils/axiosInstance";
import { toast } from "react-toastify";

const RegistrationformComponent = () => {
  const navigate = useNavigate();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [role, setRole] = useState("CUSTOMER");
  const [isRegistrationDone, setIsRegistrationDone] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");

  const handleRegister = (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null);

    const data = {
      firstName,
      lastName,
      email,
      password,
      address,
      role,
    };

    axiosInstance
      .post("/register", data)
      .then((resp) => {
        console.log(data);
        console.log("The Response", resp);
        setIsRegistrationDone(true);
        toast.success("Registration Done. Please login for visit this site");
        navigate("/login");
      })
      .catch((error) => {
        console.log("Error is ", error.response);
        if (error.response.status == 400) {
          toast.warn(error.response.data);
        } else {
          setError(error.response.data.message);
        }
      })
      .finally(() => {
        setIsLoading(false);
      });
  };

  return (
    <div
      style={{ display: "flex", flexDirection: "column", alignItems: "center" }}
    >
      <h1>Registration</h1>
      {isRegistrationDone && (
        <h2 style={{ color: "green" }}>Successfully Done Registration</h2>
      )}

      {isLoading && <h1>Loading.....</h1>}
      <form onSubmit={handleRegister}>
        <div>
          <h4>First Name</h4>
          <input
            value={firstName}
            placeholder="Enter First Name"
            onChange={(e) => {
              setFirstName(e.target.value);
            }}
          />
        </div>

        <div>
          <h4>Last Name</h4>
          <input
            value={lastName}
            placeholder="Enter Last Name"
            onChange={(e) => {
              setLastName(e.target.value);
            }}
          />
        </div>
        <div>
          <h4>Email</h4>
          <input
            value={email}
            placeholder="Enter Email"
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
        </div>

        <div>
          <h4>Password</h4>
          <input
            value={password}
            placeholder="Enter Password"
            type="password"
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />
        </div>
        <div>
          <h4>Address</h4>
          <input
            value={address}
            placeholder="Enter Adress"
            onChange={(e) => {
              setAddress(e.target.value);
            }}
          />
        </div>

        <div>
          <h4>Role</h4>
          <select
            value={role}
            onChange={(e) => {
              setRole(e.target.value);
            }}
          >
            <option value="CUSTOMER">CUSTOMER</option>
            <option value="ADMIN">ADMIN</option>
          </select>
        </div>

        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegistrationformComponent;
