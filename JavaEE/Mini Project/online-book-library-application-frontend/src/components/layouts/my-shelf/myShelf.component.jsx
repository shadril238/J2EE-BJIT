import React, { useContext } from "react";
import "./myShelf.component.css";
import MyshelfItemCardComponent from "../cards/myshelf-item-card/myshelfItemCard.component";

const MyShelfComponent = () => {
  return (
    <section className="myshelf-item-container">
      <div className="container">
        <h2>My Shelf</h2>
        <MyshelfItemCardComponent />
      </div>
    </section>
  );
};

export default MyShelfComponent;
