import { useState, useEffect, useContext } from "react";
import "./borrowedProductListing.component.css";
import ProductListingCardComponent from "../layouts/cards/productlistingcard/productListingCard.component";
import axiosInstanceUsersService from "../../utils/axiosInstanceUsersService";
import axiosInstanceUserService from "../../utils/axiosInstanceUserService";

const BorrowedProductListingComponent = () => {
  const [bookList, setBookList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [userData, setUserData] = useState({});

  useEffect(() => {
    axiosInstanceUserService.get("").then((resp) => {
      const data = resp.data;
      console.log(data);
      setUserData(data);
    });
  }, []);

  const currentUserId = userData.id;
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
  }, [userData]);

  return (
    <section className="product-listing-all-container">
      <div className="container">
        <div className="grid-container">
          {bookList.map((book) => {
            return (
              <div className="grid-item" key={book?.id}>
                <ProductListingCardComponent
                  bookData={book}
                  buttonText={"Return This Book"}
                />
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default BorrowedProductListingComponent;
