import React from "react";
import "./navbar.component.css";
import { Link, Navigate, useNavigate } from "react-router-dom";

const NavbarComponent = ({ darkTheme, darkText }) => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  return (
    <section
      className={`navbar-container ${
        darkTheme ? "background-dark relative" : "background-transparent"
      }`}
    >
      <div className="container flex justify-between align-center">
        <a href="#" className="logo">
          Book
          <span className="text-primary">Library</span>
        </a>
        <nav className="nav-links-container">
          {!token && (
            <>
              <Link
                to="/signup"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Sign up
              </Link>
              <Link
                to="/login"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Login
              </Link>
            </>
          )}

          {token && role === "CUSTOMER" && (
            <>
              <Link
                to="/"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Home
              </Link>
              <Link
                to="/books"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Books
              </Link>
              <Link
                to="/my-shelf"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                My Shelf
              </Link>
              <Link
                to="/borrowed-books"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Borrowed Books
              </Link>
              <Link
                to="/borrow-history"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Borrow History
              </Link>

              <Link
                to="/profile"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Profile
              </Link>
            </>
          )}

          {token && role === "ADMIN" && (
            <>
              <Link
                to="/admin/users"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Users
              </Link>
              <Link
                to="/admin/books"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Books
              </Link>
              <Link
                to="/admin/book/add"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Add Book
              </Link>
              <Link
                to="/admin/profile"
                className={`${darkText ? "nav-links-dark" : "nav-links"}`}
              >
                Profile
              </Link>
            </>
          )}

          {token && (
            <Link
              to="/login"
              onClick={() => {
                localStorage.removeItem("token");
                localStorage.removeItem("role");
                navigate("/login");
              }}
              className={`${darkText ? "nav-links-dark" : "nav-links"}`}
            >
              Logout
            </Link>
          )}
        </nav>
      </div>
    </section>
  );
};

export default NavbarComponent;
