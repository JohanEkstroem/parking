import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './css/Login.module.css';
const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const shouldRedirect = true;
  const navigate = useNavigate();
  const authURL = 'https://fungover.org/auth';
  const onUsernameInputChange = (e) => {
    const { value } = e.target;
    setUsername(value);
  };

  const onPasswordInputChange = (e) => {
    const { value } = e.target;
    setPassword(value);
  };

  const handleOnClickLogin = (e) => {
    postCredentialsToAuth(authURL);
  };

  const postCredentialsToAuth = async (url) => {
    const settings = {
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      method: 'POST',
      body: JSON.stringify({
        username,
        password,
      }),
    };
    try {
      let response = await fetch(url, settings);
      let data = await response.json();
      saveAccessTokenToLocalStorage(data.access_token);
      console.log(data.access_token);
      navigate('/profile');
    } catch (e) {
      console.log(e);
    }
  };

  const saveAccessTokenToLocalStorage = (data) => {
    localStorage.setItem('fungover', data);
  };

  return (
    <>
      <div className={styles.container}>
        <h2 className="bg-dark text-white mt-5"> Please Login</h2>
        <div>
          <div className="ml-5">
            <div className="col-md-6 col-md-offset-3">
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
                  <div className="row">
                    <div className="col-sm-6 col-sm-offset-3">
                      <div className={styles.loginBtn}>
                        <input
                          type="button"
                          name="login-submit"
                          id="login-submit"
                          className="form-control btn btn-info"
                          value="Login"
                          onClick={handleOnClickLogin}
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

export default Login;
