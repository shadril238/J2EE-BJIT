import axios from "axios";
import "./addBookForm.component.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstanceBookService from "../../../../utils/axiosInstanceBookService";

const AddBookFormComponent = () => {
  const navigate = useNavigate();

  const [bookTitle, setBookTitle] = useState("");
  const [bookAuthor, setBookAuthor] = useState("");
  const [bookDescription, setBookDescription] = useState("");
  const [bookStatus, setBookStatus] = useState("AVAILABLE");
  const [bookImgUrl, setBookImgUrl] = useState("");
  const [bookLanguage, setBookLanguage] = useState("");
  const [bookPages, setBookPages] = useState("");
  const [isActive, setIsActive] = useState(true);
  const [error, setError] = useState("");

  const handleBookAdd = (e) => {
    e.preventDefault();
    const bookData = {
      title: bookTitle,
      author: bookAuthor,
      description: bookDescription,
      status: bookStatus,
      imgUrl: bookImgUrl,
      language: bookLanguage,
      pageLength: bookPages,
      isActive: isActive,
    };

    axiosInstanceBookService
      .post("/create", bookData)
      .then((resp) => {
        console.log("Book Data - ", bookData);
        console.log("The Response - ", resp.data);
        navigate("/admin/books");
      })
      .catch((error) => {
        console.log("Error is ", error.response);
        setError(error.response.data.message);
      });
  };

  return (
    <section className="add-book-main-container">
      <h2 className="header-add-book">Add Book</h2>
      <form onSubmit={handleBookAdd}>
        <div className="add-book-container">
          <div className="form-group">
            <label htmlFor="title">Book Title</label>
            <input
              type="text"
              id="title"
              className="form-input"
              placeholder="Enter the Book Title"
              value={bookTitle}
              onChange={(e) => {
                setBookTitle(e.target.value);
              }}
            />
          </div>

          <div className="form-group">
            <label htmlFor="author">Book Author</label>
            <input
              type="text"
              id="author"
              className="form-input"
              placeholder="Enter the Book Author Name"
              value={bookAuthor}
              onChange={(e) => {
                setBookAuthor(e.target.value);
              }}
            />
          </div>

          <div className="form-group">
            <label htmlFor="description">Book Description</label>
            <input
              type="text"
              id="description"
              className="form-input"
              placeholder="Enter the Book Description"
              value={bookDescription}
              onChange={(e) => {
                setBookDescription(e.target.value);
              }}
            />
          </div>

          <div className="form-group">
            <label htmlFor="language">Book Language</label>
            <input
              type="text"
              id="language"
              className="form-input"
              placeholder="Enter the Book Language"
              value={bookLanguage}
              onChange={(e) => {
                setBookLanguage(e.target.value);
              }}
            />
          </div>

          <div className="form-group">
            <label htmlFor="pages">Book Pages</label>
            <input
              type="text"
              id="pages"
              className="form-input"
              placeholder="Enter the Book Pages"
              value={bookPages}
              onChange={(e) => {
                setBookPages(e.target.value);
              }}
            />
          </div>

          <div className="form-group">
            <label htmlFor="imgurl">Book Image URL</label>
            <input
              type="text"
              id="imgurl"
              className="form-input"
              placeholder="Enter the Book Image URL"
              value={bookImgUrl}
              onChange={(e) => {
                setBookImgUrl(e.target.value);
              }}
            />
          </div>
          <input type="hidden" value={bookStatus} />
          <input type="hidden" value={isActive} />

          <div className="form-group">
            <input type="submit" value="Add Book" className="button-primary" />
          </div>
        </div>
      </form>
    </section>
  );
};

export default AddBookFormComponent;
