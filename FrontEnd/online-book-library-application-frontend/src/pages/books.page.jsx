import React, { useEffect, useState } from "react";
import { axiosInstanceBookService } from "../utils/axiosInstance";
import BookComponent from "../components/book.component";

const BooksPage = () => {
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
    <div>
      <h1>My Bookstore</h1>
      {bookList.length > 0 ? (
        <ul>
          {bookList.map((book) => (
            <li key={book.id}>
              <BookComponent bookData={book} />
            </li>
          ))}
        </ul>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default BooksPage;
