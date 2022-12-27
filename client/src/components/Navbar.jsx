import React from 'react';
import styles from './css/Navbar.module.css';
import { Link } from 'react-router-dom';
const Navbar = () => {
  return (
    <>
      <div className={styles.topnav}>
        <Link className={styles.navMenu} to="/">
          Home
        </Link>
        <Link className={styles.navMenu} to="/login">
          Login
        </Link>
        <Link className={styles.navMenu} to="/createUser">
          Create User
        </Link>
        <Link className={styles.navMenu} to="/car">
          Car
        </Link>
        <Link className={styles.navMenu} to="/profile">
          Profile
        </Link>
      </div>
    </>
  );
};

export default Navbar;
