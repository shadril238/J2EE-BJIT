import { Route, Routes } from "react-router-dom";
import { useState } from "react";
import { createContext, useEffect } from "react";
import HomePage from "./pages/home-page/home.page";
import BooksPage from "./pages/books-page/books.page";
import BookdetailsPage from "./pages/book-details-page/bookdetails.page";
import LoginPage from "./pages/login-page/login.page";
import SignUpPage from "./pages/signup-page/signUp.page";
import Authenticate from "./components/authenticate";
import MyShelfPage from "./pages/myshelf-page/myShelf.page";
import NotFoundPage from "./pages/not-found-page/notFound.page";
import axiosInstanceUserService from "./utils/axiosInstanceUserService";
import BorrowedProductListingComponent from "./components/borrowed-product-listing/borrowedProductListing.component";
import BorrowedBookPage from "./pages/borrowed-book-page/borrowedBook.page";
import BorrowHistoryPage from "./pages/borrow-history-page/borrowHistory.page";

export const UserContext = createContext({});
export const ShelfContext = createContext({});

function App() {
  const [userData, setUserData] = useState({});
  const [myShelfData, setMyShelfData] = useState({});
  useEffect(() => {
    axiosInstanceUserService.get("").then((resp) => {
      const data = resp.data;
      console.log("Current user data ", data);
      setUserData(data);
    });
  }, []);

  return (
    <UserContext.Provider value={userData}>
      <ShelfContext.Provider value={{ myShelfData, setMyShelfData }}>
        <Routes>
          <Route path="/signup" element={<SignUpPage />} />
          <Route path="/login" element={<LoginPage />} />

          <Route element={<Authenticate requiredRole="CUSTOMER" />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/books" element={<BooksPage />} />
            <Route path="/book-details/:id" element={<BookdetailsPage />} />
            <Route path="/my-shelf" element={<MyShelfPage />} />
            <Route path="/borrowed-books" element={<BorrowedBookPage />} />
            <Route path="/borrow-history" element={<BorrowHistoryPage />} />
            <Route path="*" element={<NotFoundPage />} />
          </Route>

          <Route element={<Authenticate requiredRole="ADMIN" />}>
            <Route path="*" element={<NotFoundPage />} />
            {/* <Route path="/" element={<HomePage />} />
          <Route path="/books" element={<BooksPage />} /> */}
            {/* <Route path="/book-details/:id" element={<BookdetailsPage />} /> */}
          </Route>
        </Routes>
      </ShelfContext.Provider>
    </UserContext.Provider>
  );
}

export default App;
