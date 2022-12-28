import './App.css';
import Navbar from './components/Navbar.jsx';
import Login from './components/Login.jsx';
import Car from './components/Car.jsx';
import Ops from './components/Ops.jsx';
import ProfilePage from './components/ProfilePage';
import CreateUser from './components/CreateUser';
import { useLocation } from 'react-router-dom';
import {
  BrowserRouter,
  Routes,
  Route,
  NavLink,
  Router,
} from 'react-router-dom';
import Home from './components/Home';
import Parking from './components/Parking';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/login" element={<Login />} />
          <Route exact path="/createUser" element={<CreateUser />} />
          <Route exact path="/car" element={<Car />} />
          <Route exact path="/ops" element={<Ops />} />
          <Route exact path="/profile" element={<ProfilePage />} />
          <Route exact path="/parking" element={<Parking />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
//this works for now! :)

export default App;
