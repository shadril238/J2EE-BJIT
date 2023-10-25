import React, { useEffect, useState } from "react";
import "./userProfile.component.css";
import UserImg from "../../../assets/user_img.png";
import { useNavigate, Link } from "react-router-dom";
import axiosInstanceUserService from "../../../utils/axiosInstanceUserService";

const UserProfileComponent = () => {
  const navigate = useNavigate();
  const [userData, setUserData] = useState({});
  useEffect(() => {
    axiosInstanceUserService.get("").then((resp) => {
      const data = resp.data;
      console.log("Current user data ", data);
      setUserData(data);
    });
  }, []);

  return (
    <section className="profile-main-container">
      <div className="user-profile-container">
        <div className="left-container">
          <img src={UserImg} alt="user-img" className="user-img" />
          <h4>{`${userData?.firstName} ${userData?.lastName}`}</h4>
          <p>{userData?.role}</p>
        </div>
        <div className="right-container">
          <div className="user-profile-details">
            <h3>Profile Details</h3>
            <div className="user-profile-details-data">
              <div className="data">
                <h4>Email</h4>
                <p>{userData?.email}</p>
              </div>
              <div className="data">
                <h4>Address</h4>
                <p>{userData?.address}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Link
        to={`/user-profile/edit/${userData?.id}`}
        className="button-primary"
        id="edit"
      >
        Update Profile
      </Link>
      {/* <a href="/user-profile/edit" className="button-primary" id="delete">
        Delete Profile
      </a> */}
    </section>
  );
};

export default UserProfileComponent;
