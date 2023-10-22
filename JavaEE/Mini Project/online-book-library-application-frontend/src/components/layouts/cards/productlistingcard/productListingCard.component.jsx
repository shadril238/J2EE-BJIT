import React from "react";
import "./productListingCard.component.css";
import ProductImage from "../../../../assets/showcase-bg.jpg";

const ProductListingCardComponent = ({ bookData }) => {
  return (
    <div className="product-listing-card">
      <div className="product-listing-img-container">
        <img
          src={bookData.imgUrl}
          alt="product-listing-image"
          className="product-listing-image"
        />
      </div>

      <div className="product-listing-details-container">
        <h3>{bookData.title}</h3>
        <p className="author-name">{bookData.author}</p>
        <p className="status">{bookData.status}</p>
        <a href="#" className="product-listing-button">
          Button
        </a>
      </div>
    </div>
  );
};

export default ProductListingCardComponent;
