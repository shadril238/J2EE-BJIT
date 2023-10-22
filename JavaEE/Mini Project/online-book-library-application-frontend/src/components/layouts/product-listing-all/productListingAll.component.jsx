import React from "react";
import "./productListingAll.component.css";
import ProductListingCardComponent from "../cards/productlistingcard/productListingCard.component";

const ProductListingAllComponent = () => {
  return (
    <section className="product-listing-all-container">
      <div className="container">
        <div className="grid-container">
          <div className="grid-item">
            <ProductListingCardComponent />
          </div>

          <div className="grid-item">
            <ProductListingCardComponent />
          </div>

          <div className="grid-item">
            <ProductListingCardComponent />
          </div>

          <div className="grid-item">
            <ProductListingCardComponent />
          </div>

          <div className="grid-item">
            <ProductListingCardComponent />
          </div>

          <div className="grid-item">
            <ProductListingCardComponent />
          </div>

          <div className="grid-item">
            <ProductListingCardComponent />
          </div>

          <div className="grid-item">
            <ProductListingCardComponent />
          </div>

          <div className="grid-item">
            <ProductListingCardComponent />
          </div>
        </div>
      </div>
    </section>
  );
};

export default ProductListingAllComponent;
