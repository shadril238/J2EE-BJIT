import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "./adminBooks.component.css";
import axiosInstanceBookService from "../../../utils/axiosInstanceBookService";
import AdminItemListingCardComponent from "../cards/admin-item-listing-card/adminItemListingCard.component";
import SearchInputFormComponent from "../forms/search-input-form/searchInputForm.component";

const AdminBooksComponent = () => {
  const [bookList, setBookList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    axiosInstanceBookService
      .get("/all")
      .then((resp) => {
        console.log(resp.data);
        setBookList(resp.data);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  return (
    <section className="product-listing-all-container">
      <h1 className="product-listing-all-heading">
        Here you can manage <span className="status">Books</span> for the
        Library...
      </h1>
      <div className="container">
        <Link to="/admin/book/add" className="button-primary">
          Add Book
        </Link>
        <div className="grid-container">
          {bookList.map((book) => {
            return (
              <div className="grid-item" key={book?.id}>
                <AdminItemListingCardComponent bookData={book} />
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default AdminBooksComponent;
