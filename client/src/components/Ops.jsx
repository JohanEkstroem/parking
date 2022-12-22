import React from 'react';
import styles from './css/Form.module.css';
import { Link } from 'react-router-dom';

const Ops = () => {
  return (
    <div className={styles.container}>
      <h2>Ops.. Something went wrong. Try again. </h2>
      <Link className={styles.returnToParking} to="/">
        Back to Parking
      </Link>
    </div>
  );
};

export default Ops;
