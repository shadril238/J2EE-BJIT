import React from "react";
import "./detailsSection.component.css";
import BookDetailImg from "../../../assets/showcase-bg.jpg";

const DetailsSectionComponent = () => {
  return (
    <section className="detail-section-container">
      <div className="container">
        <div className="flex-container">
          <div className="book-img-container">
            <img src={BookDetailImg} alt="Book" />
          </div>

          <div className="book-detail-container">
            <h2>BookName</h2>
            <p className="text-primary">Author Name</p>
            <p className="book-description">Book Description</p>
            <p>
              <b>Language</b> : English
            </p>
            <p>
              <b>Book Length</b> : 300 Pages
            </p>
            <h3>
              <b>Status</b> : Borrowed
            </h3>

            <a href="#" className="shelf-button">
              Add to My Shelf
            </a>
          </div>
        </div>
      </div>
    </section>
  );
};

export default DetailsSectionComponent;
