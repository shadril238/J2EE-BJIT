import React from "react";
import "./productListingCard.component.css";
import ProductImage from "../../../../assets/showcase-bg.jpg";

const ProductListingCardComponent = () => {
  return (
    <div className="product-listing-card">
      <div className="product-listing-img-container">
        <img
          src={ProductImage}
          alt="product-listing-image"
          className="product-listing-image"
        />
      </div>

      <div className="product-listing-details-container">
        <h3>Book Name</h3>
        <p className="author-name">Authuor Name</p>
        <p className="status">Status</p>
        <a href="#" className="product-listing-button">
          Button
        </a>
      </div>
    </div>
  );
};

export default ProductListingCardComponent;
