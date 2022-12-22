import React, { useState } from 'react';
import styles from './css/Login.module.css';

const Car = () => {
  const [carNum, setCarNum] = useState('');
  const [error, setError] = useState('');
  const postCarNumUrl = 'http://localhost:8080/api/customer/1/car';
  const handleCarNum = async () => {
    if (!carNum) {
      setError('noCarNum');
    }

    const settings = {
      headers: {
        Accept: 'application/json',
        Authorization: 'Bearer ' + localStorage.getItem('fungover'),
        'Content-Type': 'application/json',
      },
      method: 'POST',
      body: JSON.stringify({
        registrationNumber: carNum,
      }),
    };
    try {
      const response = await fetch(postCarNumUrl, settings);
      console.log(response.status);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <>
      {error === 'noCarNum' && (
        <h1 className={styles.error}>You should enter the car number</h1>
      )}
      <div className={styles.container}>
        <h2>Register your car</h2>
        <form>
          <div className="form-group">
            <label htmlFor="username"></label>
            <input
              type="text"
              id="username"
              name="username"
              className="form-control"
              autoFocus="autofocus"
              placeholder="Username"
              value={carNum}
              onChange={(e) => {
                setCarNum(e.target.value);
                setError('');
              }}
            />
          </div>
          <div className={styles.loginBtn}>
            <input
              type="button"
              name="login-submit"
              id="login-submit"
              className="form-control btn btn-info"
              value="Register Car number"
              onClick={handleCarNum}
            />
          </div>
        </form>
      </div>
      ;
    </>
  );
};

export default Car;
