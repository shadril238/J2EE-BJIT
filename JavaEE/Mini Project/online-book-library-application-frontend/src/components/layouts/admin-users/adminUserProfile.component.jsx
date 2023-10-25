import { useState, useEffect } from "react";
import "./adminUserProfile.component.css";
import UserImg from "../../../assets/user_img.png";
import axiosInstanceUsersService from "../../../utils/axiosInstanceUsersService";
import { useParams, Link } from "react-router-dom";

const AdminUserProfileComponent = () => {
  const { id } = useParams();
  console.log("User Id ", id);
  const [userData, setUserData] = useState({});
  useEffect(() => {
    axiosInstanceUsersService.get(`/${id}`).then((resp) => {
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
        to={`/admin/user-profile/edit/${userData?.id}`}
        className="button-primary"
        id="edit"
      >
        Update Profile
      </Link>
      <a href="/user-profile/edit" className="button-primary" id="delete">
        Delete Profile
      </a>
    </section>
  );
};

export default AdminUserProfileComponent;
