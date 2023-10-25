import React, { useContext, useEffect } from "react";
import "./updateProfileForm.component.css";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axiosInstanceUsersService from "../../../../utils/axiosInstanceUsersService";
import axiosInstanceUserService from "../../../../utils/axiosInstanceUserService";
import { toast } from "react-toastify";
import { UserContext } from "../../../../App";

const UpdateProfileFormComponent = () => {
  const currUser = useContext(UserContext); // this can be improved by using the useUser hook
  const navigate = useNavigate();
  const { id } = useParams();
  const [userId, setUserId] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [address, setAddress] = useState("");
  const [role, setRole] = useState("CUSTOMER");
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  console.log("Current user id ", currUser?.id);
  useEffect(() => {
    axiosInstanceUsersService.get(`/${id}`).then((resp) => {
      const data = resp.data;
      setUserId(data?.id);
      setFirstName(data?.firstName);
      setLastName(data?.lastName);
      setEmail(data?.email);
      setPassword(data?.password);
      setAddress(data?.address);
      setRole(data?.role);
      console.log("User data from Id", data);
    });
  }, []);

  const handleUpdate = (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null);

    const updateData = {
      id: userId,
      firstName,
      lastName,
      email,
      password,
      address,
      role,
    };

    axiosInstanceUserService
      .post(`/update/${id}`, updateData)
      .then((resp) => {
        console.log("The Response - ", resp);

        if (currUser?.role == "CUSTOMER") {
          navigate("/profile");
        } else {
          if (currUser?.id == userId) {
            navigate("/admin/profile");
          } else if (currUser?.id !== userId) {
            navigate(`/admin/users/profile/${userId}`);
          }
        }
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
    <section className="update-form-container">
      <h3 className="header-update-form">Update Profile</h3>
      <form onSubmit={handleUpdate}>
        <div className="form-group">
          <input type="hidden" value={userId} />
          <label htmlFor="fname">First Name</label>
          <input
            type="text"
            id="fname"
            className="form-input"
            placeholder="Enter first name"
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
            placeholder="Enter last name"
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
            placeholder="Enter email"
            value={email}
            onChange={(e) => {
              setEmail(e.target.value);
            }}
            disabled
          />
        </div>
        {/* <input type="hidden" value={password} /> */}
        <div className="form-group">
          <label htmlFor="address">Address</label>
          <input
            className="form-input"
            type="text"
            id="address"
            placeholder="Enter  address"
            value={address}
            onChange={(e) => {
              setAddress(e.target.value);
            }}
          />
        </div>

        <input type="hidden" value={role} />

        <div className="form-group">
          <input type="submit" value="Update" className="button-primary" />
        </div>
      </form>
    </section>
  );
};

export default UpdateProfileFormComponent;
