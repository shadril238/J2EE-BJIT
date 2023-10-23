import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./loginForm.component.css";
import axiosInstanceUserService from "../../../../utils/axiosInstanceUserService";

const LoginFormComponent = () => {
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

    axiosInstanceUserService.post("/login", userCredential).then((resp) => {
      const data = resp.data;
      console.log("Response from login ", data);
      // Set the token to the local storage
      localStorage.setItem("token", data?.Authorization);
      // Set the role to the local storage
      localStorage.setItem("role", data?.role);
      navigate("/");
    });
  };

  return (
    <form onSubmit={handleLogin}>
      <div className="form-group">
        <label htmlFor="email">Email</label>
        <input
          type="email"
          id="email"
          className="form-input"
          placeholder="Enter your email"
          value={email}
          onChange={(e) => {
            setEmail(e.target.value);
          }}
        />
      </div>

      <div className="form-group">
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          className="form-input"
          placeholder="Enter your password"
          value={password}
          onChange={(e) => {
            setPassword(e.target.value);
          }}
        />
      </div>

      <div className="form-group">
        <input type="submit" value="Login" className="button-primary" />
      </div>
    </form>
  );
};

export default LoginFormComponent;
