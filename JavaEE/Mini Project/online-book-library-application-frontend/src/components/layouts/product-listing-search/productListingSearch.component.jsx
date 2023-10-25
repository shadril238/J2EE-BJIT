import React from "react";
import "./productListingSearch.component.css";
import ProductListingCardComponent from "../cards/productlistingcard/productListingCard.component";

const ProductListingSearchComponent = ({ bookList }) => {
  return (
    <section className="product-listing-all-container">
      <div className="container">
        <div className="grid-container">
          {bookList.map((book) => {
            return (
              <div className="grid-item" key={book?.id}>
                <ProductListingCardComponent
                  bookData={book}
                  buttonText={"View This Book"}
                />
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default ProductListingSearchComponent;
