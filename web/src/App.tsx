import React from 'react';
import { BrowserRouter } from 'react-router-dom';
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
        </BrowserRouter>
    </Provider>
  );
}

export default App;
