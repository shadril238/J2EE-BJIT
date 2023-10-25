import React, { useEffect, useState, useContext } from "react";
import "./detailsSection.component.css";
import BookDetailImg from "../../../assets/showcase-bg.jpg";
import { Link, useNavigate, useParams } from "react-router-dom";
import { axiosInstanceBookService } from "../../../utils/axiosInstanceBookService";
import { ShelfContext, UserContext } from "../../../App";

const DetailsSectionComponent = () => {
  const { id } = useParams();
  const [bookDetails, setBookDetails] = useState();
  const [bookReviews, setBookReviews] = useState();
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

  useEffect(() => {
    axiosInstanceBookService.get(`/${id}/reviews`).then((resp) => {
      const data = resp.data;
      console.log("book reviews : ", data);
      setBookReviews(data);
    });
  }, []);

  // console.log(`Book Reviews : ${bookReviews}`);

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
              <b>Book Page Length</b> : {bookDetails?.pageLength}
            </p>
            <h3>
              <b>Status</b> : {bookDetails?.status}
            </h3>

            <Link onClick={handleAddToMyShelf} className="button-shelf">
              Add to My Shelf
            </Link>
          </div>
        </div>
      </div>
      <h2 className="reviews-container-header">
        <u>Reviews</u>
      </h2>
      <div className="reviews-container">
        {bookReviews?.map((review) => {
          console.log("Review ", review?.user?.firstName);
          return (
            <div key={review?.id}>
              <div className="reviews-container-user">
                <h3>
                  {/* User :{" "} */}
                  {review?.user?.firstName + " " + review?.user?.lastName}
                </h3>
                <p>
                  <i>Created at : {review?.date}</i>
                </p>
              </div>
              <div className="reviews-container-review">
                <h4>Book Rating : {review?.rating}</h4>
                <p>
                  <b>Comment : </b>
                  {review?.review}
                </p>
              </div>
            </div>
          );
        })}
      </div>
    </section>
  );
};

export default DetailsSectionComponent;
