import React from "react";
import "./adminUserListingCard.component.css";
import { Link, useNavigate } from "react-router-dom";
import UserImg from "../../../../assets/user_img.png";

const AdminUserListingCardComponent = ({ userData }) => {
  const navigate = useNavigate();

  // const handleRemoveBook = () => {
  //   // console.log(bookData?.id);
  //   const deleteData = {
  //     id: bookData?.id,
  //   };

  //   axiosInstanceBookService
  //     .post("/delete", deleteData)
  //     .then((resp) => {
  //       // console.log(resp.data);
  //       // navigate(window.location.pathname, { replace: true });
  //       navigate(0);
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // };
  const handleUserDetails = (e) => {
    e.preventDefault();
    navigate(`/admin/users/profile/${userData?.id}`);
  };

  return (
    <div>
      <div className="user-listing-card">
        <div className="user-listing-img-container">
          <img
            src={UserImg}
            alt="user-listing-image"
            className="user-listing-image"
          />
        </div>

        <div className="user-listing-details-container">
          <h3>{`${userData?.firstName} ${userData?.lastName}`}</h3>
          <p className="user-role">{userData?.role}</p>
          <p className="user-mail">{userData?.email}</p>
        </div>

        <div className="card-button-container">
          <Link
            // to={`/admin/book-details/${bookData?.id}`}
            className="user-listing-button-borrow"
          >
            Borrows
          </Link>
          <Link
            onClick={handleUserDetails}
            className="user-listing-button-remove"
          >
            Details
          </Link>
        </div>
      </div>
    </div>
  );
};

export default AdminUserListingCardComponent;
