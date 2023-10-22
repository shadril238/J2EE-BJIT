import axios from "axios";

export const axiosInstanceUserService = axios.create({
  baseURL: "http://localhost:8095/user",
  timeout: 3000,
});

export default axiosInstanceUserService;
