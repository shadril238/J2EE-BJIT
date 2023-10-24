import React, { useState, useEffect, useContext } from "react";
import "./borrowHistory.component.css";
import axiosInstanceUsersService from "../../../utils/axiosInstanceUsersService";
import axiosInstanceUserService from "../../../utils/axiosInstanceUserService";
import BorrowHistoryListingCardComponent from "../cards/borrow-history-listing-card/borrowHistoryListingCard.component";

const BorrowHistoryComponent = () => {
  const [borrowBookList, setBorrowBookList] = useState([]);
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
      .get(`/${currentUserId}/history`)
      .then((resp) => {
        const data = resp.data;
        setBorrowBookList(data);
        // console.log("Borrow history data : ", borrowBookList);
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
        <h2 className="container-header">My Borrow History</h2>
        <div className="grid-container">
          {borrowBookList.map((borrowList) => {
            // console.log("data", borrowList);

            return (
              <div className="grid-item" key={borrowList?.id}>
                <BorrowHistoryListingCardComponent
                  borrowList={borrowList}
                  buttonText={"Make a Review"}
                />
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default BorrowHistoryComponent;
