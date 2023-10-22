import React from "react";
import { Route, Routes } from "react-router-dom";
import HomePage from "./pages/home-page/home.page";
import BooksPage from "./pages/books-page/books.page";
import BookdetailsPage from "./pages/book-details-page/bookdetails.page";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/books" element={<BooksPage />} />
        <Route path="/book-details/:id" element={<BookdetailsPage />} />
      </Routes>
    </div>
  );
}

export default App;
