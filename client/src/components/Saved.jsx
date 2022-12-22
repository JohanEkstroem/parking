import React from 'react';
import styles from './css/Form.module.css';
import { Link } from 'react-router-dom';
const Saved = () => {
  return (
    <div class={styles.container}>
      <h2>Parking started..</h2>
      <a id="return_to_homepage" href="/">
        <Link className={styles.returnToParking} to="/">
          Back to Parking
        </Link>
      </a>
    </div>
  );
};

export default Saved;
