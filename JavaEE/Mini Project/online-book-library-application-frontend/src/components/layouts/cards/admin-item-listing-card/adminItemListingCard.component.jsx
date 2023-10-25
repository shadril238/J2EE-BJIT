import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import "./adminItemListingCard.component.css";
import axiosInstanceBookService from "../../../../utils/axiosInstanceBookService";
import { useNavigate } from "react-router-dom";

const AdminItemListingCardComponent = ({ bookData }) => {
  const navigate = useNavigate();

  const handleRemoveBook = () => {
    // console.log(bookData?.id);
    const deleteData = {
      id: bookData?.id,
    };

    axiosInstanceBookService
      .post("/delete", deleteData)
      .then((resp) => {
        // console.log(resp.data);
        // navigate(window.location.pathname, { replace: true });
        navigate(0);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="product-listing-card">
      <div className="product-listing-img-container">
        <img
          src={bookData?.imgUrl}
          alt="product-listing-image"
          className="product-listing-image"
        />
      </div>

      <div className="product-listing-details-container">
        <h3>{bookData?.title}</h3>
        <p className="author-name">{bookData?.author}</p>
        <p className="status">{bookData?.status}</p>
      </div>

      <div className="card-button-container">
        <Link
          to={`/admin/book-details/${bookData?.id}`}
          className="product-listing-button-details"
        >
          Details
        </Link>
        <Link
          onClick={handleRemoveBook}
          className="product-listing-button-remove"
        >
          Remove
        </Link>
      </div>
    </div>
  );
};

export default AdminItemListingCardComponent;
