import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: "https://reqres.in/api",
  timeout: 3000,
});

export const axiosInstanceProductService = axios.create({
  baseURL: "https://localhost:3001",
  timeout: 3000,
});

export default axiosInstance;
