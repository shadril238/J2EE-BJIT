// import axios from "axios";
// import React from "react";
// import { useNavigate, useParams } from "react-router-dom";
// import axiosInstanceBookService from "../utils/axiosInstanceBookService";

// const ReturnBookComponent = () => {
//   const { id } = useParams();
//   const navigate = useNavigate();
//   console.log(id);

//   axiosInstanceBookService
//     .get(`/${id}/return`)
//     .then((resp) => {
//       const data = resp.data;
//       console.log(data);
//       navigate("/borrowed-books");
//     })
//     .catch((error) => {
//       console.log("Error ", error);
//     });

//   return <div></div>;
// };

// export default ReturnBookComponent;
