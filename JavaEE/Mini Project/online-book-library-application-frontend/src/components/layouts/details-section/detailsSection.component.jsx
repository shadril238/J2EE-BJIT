import React, { useEffect, useState, useContext } from "react";
import "./detailsSection.component.css";
import BookDetailImg from "../../../assets/showcase-bg.jpg";
import { useNavigate, useParams } from "react-router-dom";
import { axiosInstanceBookService } from "../../../utils/axiosInstanceBookService";
import { ShelfContext, UserContext } from "../../../App";

const DetailsSectionComponent = () => {
  const { id } = useParams();
  const [bookDetails, setBookDetails] = useState();
  const [isLoading, setIsLoading] = useState(false);

  const user = useContext(UserContext);
  // console.log("Current user : ", user);
  const { myShelfData, setMyShelfData } = useContext(ShelfContext);

  const navigate = useNavigate();

  useEffect(() => {
    axiosInstanceBookService
      .get(`/${id}`)
      .then((resp) => {
        const data = resp.data;
        setBookDetails(data);
        // console.log(bookDetails);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  const handleAddToMyShelf = () => {
    if (user) {
      console.log("Adding book to My Shelf");
      setMyShelfData(bookDetails);
      console.log("My Shelf data : ", myShelfData);
      alert(`The Book ${bookDetails?.title} is added to the My Shelf`);
      navigate("/my-shelf");
    } else {
      // for security purpose
      localStorage.removeItem("token");
      localStorage.removeItem("role");
      navigate("/login");
      alert("Please Login or Sign up first...");
    }
  };
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

            <a onClick={handleAddToMyShelf} className="button-primary">
              Add to My Shelf
            </a>
          </div>
        </div>
      </div>
    </section>
  );
};

export default DetailsSectionComponent;
