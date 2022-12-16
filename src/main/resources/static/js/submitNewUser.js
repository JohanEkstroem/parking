const username = document.getElementById('username');
const password = document.getElementById('password');
const signupURl = 'https://fungover.org/sign-up';
const submitButton = document.querySelector('#login-submit');

submitButton.addEventListener('click', (e) => {
  e.preventDefault();
  postNewUser(signupURl);
});

const postNewUser = async (url) => {
  const settings = {
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    method: 'POST',
    body: JSON.stringify({
      username: username.value,
      password: password.value,
      scope: 'read write',
    }),
  };
  try {
    await fetch(url, settings);
    location.replace('http://localhost:8080/login');
  } catch (e) {
    console.log(e);
  }
};
