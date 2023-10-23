import React, { useContext } from "react";
import "./myShelf.component.css";
import MyshelfItemCardComponent from "../cards/myshelf-item-card/myshelfItemCard.component";
import { ShelfContext } from "../../../App";

const MyShelfComponent = () => {
  const { myShelfData, setMyShelfData } = useContext(ShelfContext);
  console.log("My Shelf data from component :", myShelfData);
  return (
    <section className="myshelf-item-container">
      <div className="container">
        {myShelfData?.title ? (
          <React.Fragment>
            <h2>My Shelf</h2>
            <MyshelfItemCardComponent />
            <button className="button-primary">Borrow This Book</button>
          </React.Fragment>
        ) : (
          <h1>Currently your shelf is Empty!</h1>
        )}
      </div>
    </section>
  );
};

export default MyShelfComponent;
