import { useState, useEffect } from "react";
import "./productListing.component.css";
import ProductListingCardComponent from "../cards/productlistingcard/productListingCard.component";
import axiosInstanceBookService from "../../../utils/axiosInstanceBookService";

const ProductListingComponent = () => {
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
    <div className="product-listing-container">
      <div className="container">
        <h2>
          Here are some <span className="text-primary">books</span> that you
          might like
        </h2>

        <div className="listing-container">
          {bookList.slice(0, 4).map((book) => {
            return (
              <ProductListingCardComponent
                key={book?.id}
                bookData={book}
                buttonText={"View This Book"}
              />
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default ProductListingComponent;
