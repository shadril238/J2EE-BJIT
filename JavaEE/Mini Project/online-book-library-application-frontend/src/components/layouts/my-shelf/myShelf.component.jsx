import React, { useContext } from "react";
import "./myShelf.component.css";
import MyshelfItemCardComponent from "../cards/myshelf-item-card/myshelfItemCard.component";
import { ShelfContext } from "../../../App";
import axiosInstanceBookService from "../../../utils/axiosInstanceBookService";

const MyShelfComponent = () => {
  const { myShelfData, setMyShelfData } = useContext(ShelfContext);
  console.log("My Shelf data from MyShelfComponent :", myShelfData);

  const handleBorrowBook = (e) => {
    e.preventDefault();
    const bookId = myShelfData?.id;
    console.log(`Borrow Book id : ${bookId}`);

    axiosInstanceBookService
      .get(`/${bookId}/borrow`)
      .then((resp) => {
        const data = resp.data;
        console.log("Borrow book response : ", data);
        setMyShelfData({});
        //todo: navigate to Borrow Books List
      })
      .catch((error) => {
        console.log("Error ", error.response.data);
        //todo: set a toastr message for this
      });
  };

  return (
    <section className="myshelf-item-container">
      <div className="container">
        {myShelfData?.title ? (
          <React.Fragment>
            <h2>My Shelf</h2>
            <MyshelfItemCardComponent />
            <button className="button-primary" onClick={handleBorrowBook}>
              Borrow This Book
            </button>
          </React.Fragment>
        ) : (
          <h1>Currently your shelf is Empty!</h1>
        )}
      </div>
    </section>
  );
};

export default MyShelfComponent;
