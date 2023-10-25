import { useState } from "react";
import "./createReviewForm.component.css";
import { useNavigate, useParams } from "react-router-dom";
import axiosInstanceBookService from "../../../../utils/axiosInstanceBookService";

const CreateReviewFormComponent = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [reviewRating, setReviewRating] = useState("");
  const [reviewComment, setReviewComment] = useState("");

  const handleCreateReview = (e) => {
    e.preventDefault();
    console.log("Creating Review");
    const reviewData = {
      rating: reviewRating,
      review: reviewComment,
    };
    axiosInstanceBookService
      .post(`${id}/reviews/create`, reviewData)
      .then((resp) => {
        const data = resp.data;
        console.log("Response from creating review ", data);
        navigate(`/book-details/${id}`);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <section className="create-review-main-container">
      <h2 className="header-create-review">Review Book</h2>
      <form onSubmit={handleCreateReview}>
        <div className="create-review-container">
          <div className="form-group">
            <label htmlFor="rating">Review Rating</label>
            <input
              type="text"
              id="rating"
              className="form-input"
              placeholder="Enter the Review Rating"
              value={reviewRating}
              onChange={(e) => {
                setReviewRating(e.target.value);
              }}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="review">Book Author</label>
            <input
              type="text"
              id="review"
              className="form-input"
              placeholder="Enter the Review Comment"
              value={reviewComment}
              onChange={(e) => {
                setReviewComment(e.target.value);
              }}
              required
            />
          </div>
          <div className="form-group">
            <input
              type="submit"
              value="Create Review"
              className="button-primary"
            />
          </div>
        </div>
      </form>
    </section>
  );
};

export default CreateReviewFormComponent;
