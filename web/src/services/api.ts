import axios from 'axios';
import { getToken } from './auth';

const api = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})

api.interceptors.request.use(async config => {
  const token = getToken();
  console.log(getToken())
  if (token) {
    // config.headers.access_token = `Bearer ${token}`;
    config.headers.Authorization=token
  }
  return config;
});

export default api;