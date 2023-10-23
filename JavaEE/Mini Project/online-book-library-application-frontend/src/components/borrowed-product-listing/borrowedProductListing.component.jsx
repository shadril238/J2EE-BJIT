import { useState, useEffect, useContext } from "react";
import "./borrowedProductListing.component.css";
import ProductListingCardComponent from "../layouts/cards/productlistingcard/productListingCard.component";
import axios from "axios";
import axiosInstanceBookService from "../../utils/axiosInstanceBookService";
import { UserContext } from "../../App";
import axiosInstanceUsersService from "../../utils/axiosInstanceUsersService";

const BorrowedProductListingComponent = () => {
  const [bookList, setBookList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const user = useContext(UserContext);

  const currentUserId = user.id;
  console.log("Current user id : ", currentUserId);

  useEffect(() => {
    axiosInstanceUsersService
      .get(`/${currentUserId}/borrowed-books`)
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
                <ProductListingCardComponent bookData={book} />
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default BorrowedProductListingComponent;
