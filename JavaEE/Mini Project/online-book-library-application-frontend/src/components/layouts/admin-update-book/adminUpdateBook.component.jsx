import { useState, useContext, useEffect } from "react";
import "./adminUpdateBook.component.css";
import axiosInstanceBookService from "../../../utils/axiosInstanceBookService";
import { useNavigate } from "react-router-dom";

const AdminUpdateBookComponent = ({ bookData }) => {
  //   console.log("book data from component", bookData);\
  const navigate = useNavigate();

  const [bookId, setBookId] = useState("");
  const [bookTitle, setBookTitle] = useState("");
  const [bookAuthor, setBookAuthor] = useState("");
  const [bookDescription, setBookDescription] = useState("");
  const [bookStatus, setBookStatus] = useState("");
  const [bookImgUrl, setBookImgUrl] = useState("");
  const [bookLanguage, setBookLanguage] = useState("");
  const [bookPages, setBookPages] = useState("");
  const [isActive, setIsActive] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    setBookId(bookData?.id);
    setBookTitle(bookData?.title);
    setBookAuthor(bookData?.author);
    setBookDescription(bookData?.description);
    setBookStatus(bookData?.status);
    setBookImgUrl(bookData?.imgUrl);
    setBookLanguage(bookData?.language);
    setBookPages(bookData?.pageLength);
    setIsActive(bookData?.isActive);
  }, [bookData]);

  const handleUpdateBook = (e) => {
    e.preventDefault();

    const bookData = {
      id: bookId,
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
      .post("/update", bookData)
      .then((resp) => {
        console.log("Book Data - ", bookData);
        console.log("The Response - ", resp.data);
        navigate(`/admin/book-details/${bookId}`);
      })
      .catch((error) => {
        console.log("Error is ", error.response);
        setError(error.response.data.message);
      });
  };
  return (
    <section className="add-book-main-container">
      <h2 className="header-add-book">Update Book</h2>
      <form onSubmit={handleUpdateBook}>
        <input type="hidden" value={bookId} />

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

          <div className="form-group">
            <label htmlFor="status">Book Status</label>
            <input
              type="text"
              id="status"
              className="form-input"
              placeholder="Enter the Status"
              value={bookStatus}
              onChange={(e) => {
                setBookStatus(e.target.value);
              }}
            />
          </div>

          <input type="hidden" value={isActive} />

          <div className="form-group">
            <input
              type="submit"
              value="Update Book"
              className="button-primary"
            />
          </div>
        </div>
      </form>
    </section>
  );
};

export default AdminUpdateBookComponent;
