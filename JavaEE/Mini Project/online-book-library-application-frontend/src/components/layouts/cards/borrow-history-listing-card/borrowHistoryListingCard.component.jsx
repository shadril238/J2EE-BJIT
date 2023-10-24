import React from "react";
import "./borrowHistoryListingCard.component.css";
import { Link, useNavigate } from "react-router-dom";
import axiosInstanceBookService from "../../../../utils/axiosInstanceBookService";

const BorrowHistoryListingCardComponent = ({ borrowList, buttonText }) => {
  // console.log("data", borrowList.book);
  return (
    <div className="product-listing-card">
      <div className="product-listing-img-container">
        <img
          src={borrowList?.book?.imgUrl}
          alt="product-listing-image"
          className="product-listing-image"
        />
      </div>

      <div className="product-listing-details-container">
        <h3>{borrowList?.book?.title}</h3>
        <p className="author-name">{borrowList?.book?.author}</p>
        <p className="date">Borrow Date : {borrowList?.borrowDate}</p>
        <p className="date">Due Date : {borrowList?.dueDate}</p>
        <p className="date">Return Date : {borrowList?.returnDate}</p>
      </div>

      {buttonText === "Make a Review" && (
        <div className="card-button-container">
          <Link
            // to={`/book-details/${borrowList.book?.id}`}
            className="product-listing-button"
          >
            {buttonText}
          </Link>
        </div>
      )}
    </div>
  );
};

export default BorrowHistoryListingCardComponent;
