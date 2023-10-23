import axios from "axios";

export const axiosInstanceUsersService = axios.create({
  baseURL: "http://localhost:8095/users",
  timeout: 3000,
});

axiosInstanceUsersService.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

export default axiosInstanceUsersService;
