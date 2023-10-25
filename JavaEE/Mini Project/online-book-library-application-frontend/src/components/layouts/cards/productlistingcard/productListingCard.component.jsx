import React from "react";
import "./productListingCard.component.css";
import ProductImage from "../../../../assets/showcase-bg.jpg";
import { Link, useNavigate } from "react-router-dom";
import axiosInstanceBookService from "../../../../utils/axiosInstanceBookService";

const ProductListingCardComponent = ({ bookData, buttonText }) => {
  const navigate = useNavigate();

  const handleReturnBook = (id) => {
    console.log(`Returning book with id ${id}`);
    axiosInstanceBookService
      .get(`/${id}/return`)
      .then((resp) => {
        const data = resp.data;
        // console.log("Response data from return book : ", data);
        // alert("Successfully returned the book!");
        //todo: toastr notification
      })
      .catch((error) => {
        console.log("Error ", error);
      })
      .finally(() => {
        navigate("/");
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

      {buttonText === "View This Book" && (
        <div className="card-button-container">
          <Link
            to={`/book-details/${bookData?.id}`}
            className="product-listing-button"
          >
            {buttonText}
          </Link>
        </div>
      )}
      {buttonText === "Return This Book" && (
        <div className="card-button-container">
          <a
            onClick={() => handleReturnBook(bookData?.id)}
            className="product-listing-button"
          >
            {buttonText}
          </a>
        </div>
      )}
    </div>
  );
};

export default ProductListingCardComponent;
