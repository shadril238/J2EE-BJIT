import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { axiosInstance } from "./../utils/axiosInstance";

const LoginformComponent = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (e) => {
    e.preventDefault();
    console.log("Loggin in");

    const userCredential = {
      email,
      password,
    };

    axiosInstance.post("/login", userCredential).then((resp) => {
      const data = resp.data;

      console.log("Response from login ", data);
      localStorage.setItem("token", data.Authorization);
      navigate("/");
    });
  };

  return (
    <div>
      <h1>Login Page</h1>
      <form onSubmit={handleLogin}>
        <div>
          <h4>Email</h4>
          <input
            value={email}
            placeholder="Enter email"
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
        </div>
        <div>
          <h4>Password</h4>
          <input
            value={password}
            placeholder="Enter password"
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginformComponent;
