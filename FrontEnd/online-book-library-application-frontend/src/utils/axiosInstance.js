import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: "http://localhost:8095/user",
  timeout: 3000,
});

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

// axiosInstance.interceptors.request.use((config) => {
//   const token = localStorage.getItem("token");

//   if (token) {
//     config.headers.Authorization = `Bearer ${token}`;
//   }

//   return config;
// });

// export const axiosInstanceProductService = axios.create({
//     baseURL: "https://localhost:3001",
//     timeout: 3000,
// });

export default axiosInstance;
