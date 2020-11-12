import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import { Provider } from 'react-redux';

import Navbar from './components/Navbar';

import Routes from './routes';

import store from './store';

import './assets/styles/global.css';

function App() {
  return (
    <Provider store={store}>
        <BrowserRouter>
          <Navbar />
          <Routes />
          <ToastContainer autoClose={3000} position="top-center"/>
        </BrowserRouter>
    </Provider>
  );
}

export default App;
