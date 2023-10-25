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
import BorrowedBookPage from "./pages/borrowed-book-page/borrowedBook.page";
import SearchPage from "./pages/search-page/search.page";
import BorrowHistoryPage from "./pages/borrow-history-page/borrowHistory.page";
import AdminBooksPage from "./pages/admin-books-page/adminBooks.page";
import AdminAddBookPage from "./pages/admin-add-book-page/adminAddBook.page";
import AdminBookDetailsPage from "./pages/admin-book-details-page/adminBookDetails.page";
import AdminUpdateBookPage from "./pages/admin-update-book-page/adminUpdateBook.page";
import CreateReviewPage from "./pages/create-review-page/createReview.page";
import UserProfilePage from "./pages/user-profile-page/userProfile.page";
import AdminProfilePage from "./pages/admin-profile-page/adminProfile.page";
import AdminUsersPage from "./pages/admin-users-page/adminUsers.page";
import AdminUsersDetailsPage from "./pages/admin-users-page/adminUsersDetails.page";
import UpdateProfileComponent from "./components/layouts/update-profile/updateProfile.component";
import UpdateProfilePage from "./pages/update-profile-page/updateProfile.page";

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

          <Route element={<Authenticate requiredRole="ADMIN" />}>
            <Route path="/admin" element={<AdminBooksPage />} />
            <Route path="/admin/books" element={<AdminBooksPage />} />
            <Route path="/admin/users" element={<AdminUsersPage />} />
            <Route path="/admin/book/add" element={<AdminAddBookPage />} />
            <Route
              path="/admin/book-details/:id"
              element={<AdminBookDetailsPage />}
            />
            <Route
              path="/admin/book/update/:id"
              element={<AdminUpdateBookPage />}
            />
            <Route
              path="/admin/users/profile/:id"
              element={<AdminUsersDetailsPage />}
            />
            <Route path="/admin/profile" element={<AdminProfilePage />} />
            <Route
              path="/admin/user-profile/edit/:id"
              element={<UpdateProfilePage />}
            />
            <Route path="*" element={<NotFoundPage />} />
          </Route>

          <Route element={<Authenticate requiredRole="CUSTOMER" />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/books" element={<BooksPage />} />
            <Route path="/search" element={<SearchPage />} />
            <Route path="/book-details/:id" element={<BookdetailsPage />} />
            <Route path="/review/create/:id" element={<CreateReviewPage />} />
            <Route path="/my-shelf" element={<MyShelfPage />} />
            <Route
              path="/user-profile/edit/:id"
              element={<UpdateProfilePage />}
            />
            <Route path="/borrowed-books" element={<BorrowedBookPage />} />
            <Route path="/borrow-history" element={<BorrowHistoryPage />} />
            <Route path="/profile" element={<UserProfilePage />} />
            <Route path="*" element={<NotFoundPage />} />
          </Route>
        </Routes>
      </ShelfContext.Provider>
    </UserContext.Provider>
  );
}

export default App;
