import React from "react";
import { Link } from "react-router-dom";
import "./adminItemListingCard.component.css";

const AdminItemListingCardComponent = ({ bookData }) => {
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
          // to={`/book-details/${borrowList.book?.id}`}
          className="product-listing-button-remove"
        >
          Remove
        </Link>
      </div>
    </div>
  );
};

export default AdminItemListingCardComponent;
