import React, { useEffect, useState } from "react";
import "./detailsSection.component.css";
import BookDetailImg from "../../../assets/showcase-bg.jpg";
import { useParams } from "react-router-dom";
import { axiosInstanceBookService } from "../../../utils/axiosInstanceBookService";

const DetailsSectionComponent = () => {
  const { id } = useParams();
  const [bookDetails, setBookDetails] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  // console.log(id);
  useEffect(() => {
    axiosInstanceBookService
      .get(`/${id}`)
      .then((resp) => {
        const data = resp.data;
        setBookDetails(data);
        console.log(data);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  return (
    <section className="detail-section-container">
      <div className="container">
        <div className="flex-container">
          <div className="book-img-container">
            <img src={bookDetails?.imgUrl} alt="Book" />
          </div>

          <div className="book-detail-container">
            <h2>{bookDetails?.title}</h2>
            <p className="text-primary">{bookDetails?.author}</p>
            <p className="book-description">{bookDetails?.description}</p>
            <p>
              <b>Language</b> : {bookDetails?.language}
            </p>
            <p>
              <b>Book Length</b> : {bookDetails?.pageLength}
            </p>
            <h3>
              <b>Status</b> : {bookDetails?.status}
            </h3>

            <a href="#" className="button-primary">
              Add to My Shelf
            </a>
          </div>
        </div>
      </div>
    </section>
  );
};

export default DetailsSectionComponent;
