import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './css/Login.module.css';
import jwt_decode from 'jwt-decode';

const ProfilePage = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const token = localStorage.getItem('fungover');
  const decoded = jwt_decode(token);
  const userName = decoded.sub;
  const customerURL = 'http://localhost:8080/api/customer';
  const navigate = useNavigate();

  const handleOnClickMe = (e) => {
    postUpdateToCustomerAPI(customerURL);
  };

  const postUpdateToCustomerAPI = async (url) => {
    const settings = {
      headers: {
        Authorization: 'Bearer ' + localStorage.getItem('fungover'),
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      method: 'POST',
      body: JSON.stringify({
        userName,
        firstName,
        lastName,
      }),
    };
    try {
      let response = await fetch(url, settings);
      let data = await response.json();
      navigate('/profile');
    } catch (e) {
      console.log(e);
    }
  };
  const onFirstNameInputChange = (e) => {
    const { value } = e.target;
    setFirstName(value);
  };

  const onLastNameInputChange = (e) => {
    const { value } = e.target;
    setLastName(value);
  };
  return (
    <>
      <div className={styles.container}>
        <h1 className="text-white mt-5">Welcome {userName}!</h1>
        <h2 className="bg-dark text-white mt-5">Please Update Profile</h2>
        <div>
          <div className="ml-5">
            <div className="col-md-6 col-md-offset-3">
              <form>
                <div className="form-group">
                  <label htmlFor="firstName"></label>
                  <input
                    type="text"
                    id="firstName"
                    name="firstName"
                    className="form-control"
                    placeholder="First Name"
                    autoComplete="true"
                    value={firstName}
                    onChange={onFirstNameInputChange}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="lastName"></label>
                  <input
                    type="text"
                    id="lastName"
                    name="lastName"
                    className="form-control"
                    placeholder="Last Name"
                    value={lastName}
                    onChange={onLastNameInputChange}
                  />
                </div>
                <div className="form-group">
                  <div className="row">
                    <div className="col-sm-6 col-sm-offset-3">
                      <div className={styles.loginBtn}>
                        <input
                          type="button"
                          name="login-submit"
                          id="login-submit"
                          className="form-control btn btn-info"
                          value="Update Profile"
                          onClick={handleOnClickMe}
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ProfilePage;
