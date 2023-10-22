import React from "react";
import "./productListing.component.css";
import ProductListingCardComponent from "../cards/productlistingcard/productListingCard.component";

const ProductListingComponent = () => {
  return (
    <div className="product-listing-container">
      <div className="container">
        <h2>
          Here are some <span className="text-primary">books</span> that you
          might like
        </h2>

        <div className="listing-container">
          <ProductListingCardComponent />
          <ProductListingCardComponent />
          <ProductListingCardComponent />
          <ProductListingCardComponent />
        </div>
      </div>
    </div>
  );
};

export default ProductListingComponent;
