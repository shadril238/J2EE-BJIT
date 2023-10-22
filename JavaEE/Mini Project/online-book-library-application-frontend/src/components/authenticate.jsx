import React from "react";
import { Navigate, Outlet } from "react-router-dom";

const Authenticate = ({ requiredRole }) => {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  if (!token || role !== requiredRole) {
    return <Navigate to="/login" />;
  }

  return <Outlet />;
};

export default Authenticate;
