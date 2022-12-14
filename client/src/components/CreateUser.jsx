import { useState, useEffect } from 'react';
import { redirect } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

import styles from './css/Login.module.css';

const CreateUser = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const signUpURL = 'https://fungover.org/sign-up';
  const newCustomerURL = 'http://localhost:8080/api/customer';
  const navigate = useNavigate();

  const onUsernameInputChange = (e) => {
    const { value } = e.target;
    setUsername(value);
  };

  const onPasswordInputChange = (e) => {
    const { value } = e.target;
    setPassword(value);
  };

  const handleOnClickCreateUser = (e) => {
    e.preventDefault();
    postNewUser(signUpURL);
    redirect('/login');
  };

  const postNewUser = async (url) => {
    const settings = {
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      method: 'POST',
      body: JSON.stringify({
        username,
        password,
        scope: 'read write',
      }),
    };
    try {
      await fetch(url, settings);
      navigate('/login');
    } catch (e) {
      console.log(e);
    }
  };
  return (
    <>
      <div className={styles.container}>
        <h2 className="bg-dark text-white mt-5"> Create User </h2>
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
              value={username}
              onChange={onUsernameInputChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="password"></label>
            <input
              type="password"
              id="password"
              name="password"
              className="form-control"
              placeholder="Password"
              autoComplete="true"
              value={password}
              onChange={onPasswordInputChange}
            />
          </div>
          <div className="form-group">
            <div className={styles.loginBtn}>
              <input
                type="button"
                name="login-submit"
                id="login-submit"
                className="form-control btn btn-info"
                value="Create User"
                onClick={handleOnClickCreateUser}
              />
            </div>
          </div>
        </form>
      </div>
    </>
  );
};

export default CreateUser;
