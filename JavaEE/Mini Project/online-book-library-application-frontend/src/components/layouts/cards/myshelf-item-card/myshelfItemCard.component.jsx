import React from "react";
import "./myshelfItemCard.component.css";
import ShelfImg from "../../../../assets/auth-bg.jpg";

const MyshelfItemCardComponent = () => {
  return (
    <section className="myshelf-item">
      <div className="myshelf-item-img-container">
        <img src={ShelfImg} alt="shelf-item-img" className="myshelf-item-img" />
      </div>
      <div className="myshelf-item-content-container">
        <h2>Book Name</h2>
        <p>Author Name</p>
        <h3 className="myshelf-item-status">Status</h3>
      </div>
    </section>
  );
};

export default MyshelfItemCardComponent;
