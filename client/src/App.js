import './App.css';
import Header from './components/Header.jsx';
import Navbar from './components/Navbar.jsx';
import Login from './components/Login.jsx';
import Car from './components/Car.jsx';
import Ops from './components/Ops.jsx';
import ProfilePage from './components/ProfilePage';

import CreateUser from './components/CreateUser';
import { useState, useEffect } from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
  NavLink,
  Router,
} from 'react-router-dom';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route exact path="/head" element={<Header />} />
          <Route exact path="/login" element={<Login />} />
          <Route exact path="/createUser" element={<CreateUser />} />
          <Route exact path="/car" element={<Car />} />
          <Route exact path="/ops" element={<Ops />} />
          <Route exact path="/profile" element={<ProfilePage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
//this works for now! :)

export default App;
