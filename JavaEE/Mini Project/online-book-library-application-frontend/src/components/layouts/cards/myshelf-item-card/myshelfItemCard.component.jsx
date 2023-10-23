import React, { useContext } from "react";
import "./myshelfItemCard.component.css";
import ShelfImg from "../../../../assets/auth-bg.jpg";
import { ShelfContext } from "../../../../App";

const MyshelfItemCardComponent = () => {
  const { myShelfData, setMyShelfData } = useContext(ShelfContext);
  console.log("My Shelf data from MyshelfItemCardComponent :", myShelfData);
  return (
    <section className="myshelf-item">
      <div className="myshelf-item-img-container">
        <img
          src={myShelfData?.imgUrl}
          alt="shelf-item-img"
          className="myshelf-item-img"
        />
      </div>
      <div className="myshelf-item-content-container">
        <h2>{myShelfData?.title}</h2>
        <p>{myShelfData?.author}</p>
        <p>{myShelfData?.pageLength}</p>
        <p>{myShelfData?.description}</p>
        <h3 className="myshelf-item-status">Status : {myShelfData?.status}</h3>
      </div>
    </section>
  );
};

export default MyshelfItemCardComponent;
