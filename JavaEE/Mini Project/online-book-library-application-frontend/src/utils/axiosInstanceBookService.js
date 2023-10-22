import axios from "axios";

export const axiosInstanceBookService = axios.create({
  baseURL: "http://localhost:8095/books",
  timeout: 3000,
});

export default axiosInstanceBookService;
