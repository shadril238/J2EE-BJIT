import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./signUpForm.component.css";
import axiosInstanceUserService from "../../../../utils/axiosInstanceUserService";
import { toast } from "react-toastify";

const SignUpFormComponent = () => {
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

    const signupData = {
      firstName,
      lastName,
      email,
      password,
      address,
      role,
    };

    axiosInstanceUserService
      .post("/register", signupData)
      .then((resp) => {
        console.log("Signup Data - ", signupData);
        console.log("The Response - ", resp);
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
    <form onSubmit={handleRegister}>
      <div className="form-group">
        <label htmlFor="fname">First Name</label>
        <input
          type="text"
          id="fname"
          className="form-input"
          placeholder="Enter your first name"
          value={firstName}
          onChange={(e) => {
            setFirstName(e.target.value);
          }}
        />
      </div>

      <div className="form-group">
        <label htmlFor="lname">Last Name</label>
        <input
          type="text"
          id="lname"
          className="form-input"
          placeholder="Enter your last name"
          value={lastName}
          onChange={(e) => {
            setLastName(e.target.value);
          }}
        />
      </div>

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
        <label htmlFor="address">Address</label>
        <input
          className="form-input"
          type="text"
          id="address"
          placeholder="Enter youra address"
          value={address}
          onChange={(e) => {
            setAddress(e.target.value);
          }}
        />
      </div>

      <input type="hidden" value={role} />

      <div className="form-group">
        <input type="submit" value="Sign up" className="button-primary" />
      </div>
    </form>
  );
};

export default SignUpFormComponent;
