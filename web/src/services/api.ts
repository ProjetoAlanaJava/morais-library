import axios from 'axios';

const api = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})

api.interceptors.request.use(async config => {
  const token = 'token';
  if (token) {
    config.headers.access_token = `Bearer ${token}`;
  }
  return config;
});

export default api;