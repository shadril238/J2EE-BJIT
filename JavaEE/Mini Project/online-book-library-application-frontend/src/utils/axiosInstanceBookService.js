import axios from "axios";

export const axiosInstanceBookService = axios.create({
  baseURL: "http://localhost:8095/books",
  timeout: 3000,
});

axiosInstanceBookService.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

export default axiosInstanceBookService;
