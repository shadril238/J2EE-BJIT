import React from "react";
import { Route, Routes } from "react-router-dom";
import HomePage from "./pages/home-page/home.page";
import BooksPage from "./pages/books-page/books.page";
import BookdetailsPage from "./pages/book-details-page/bookdetails.page";
import LoginPage from "./pages/login-page/login.page";
import SignUpPage from "./pages/signup-page/signUp.page";
import Authenticate from "./components/authenticate";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/login" element={<LoginPage />} />

        <Route element={<Authenticate requiredRole="CUSTOMER" />}>
          <Route path="/" element={<HomePage />} />
          <Route path="/books" element={<BooksPage />} />
          <Route path="/book-details/:id" element={<BookdetailsPage />} />
        </Route>

        <Route element={<Authenticate requiredRole="ADMIN" />}>
          {/* <Route path="/" element={<HomePage />} />
          <Route path="/books" element={<BooksPage />} /> */}
          {/* <Route path="/book-details/:id" element={<BookdetailsPage />} /> */}
        </Route>
      </Routes>
    </div>
  );
}

export default App;
