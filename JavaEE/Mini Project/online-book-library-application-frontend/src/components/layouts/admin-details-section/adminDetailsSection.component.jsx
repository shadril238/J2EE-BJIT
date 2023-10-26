import React, { useContext, useState, useEffect } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import "./adminDetailsSection.component.css";
import axiosInstanceBookService from "../../../utils/axiosInstanceBookService";
import { UserContext } from "../../../App";

const AdminDetailsSectionComponent = () => {
  const { id } = useParams();
  const [bookDetails, setBookDetails] = useState();
  const [isLoading, setIsLoading] = useState(false);

  const user = useContext(UserContext);
  // console.log("Current user : ", user);
  //   const { myShelfData, setMyShelfData } = useContext(ShelfContext);

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

            <Link
              to={`/admin/book/update/${bookDetails?.id}`}
              className="button-primary-details"
            >
              Update Book Details
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AdminDetailsSectionComponent;
