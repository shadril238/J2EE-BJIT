import { useState, useEffect } from "react";
import "./productListingAll.component.css";
import ProductListingCardComponent from "../cards/productlistingcard/productListingCard.component";
import axios from "axios";
import axiosInstanceBookService from "../../../utils/axiosInstanceBookService";

const ProductListingAllComponent = () => {
  const [bookList, setBookList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    axiosInstanceBookService
      .get("/all")
      .then((resp) => {
        console.log(resp.data);
        setBookList(resp.data);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

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

export default ProductListingAllComponent;
