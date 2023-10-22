import React from "react";
import AuthBgImg from "../../assets/auth-bg.jpg";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import LoginFormComponent from "../../components/layouts/forms/login-form/loginForm.component";
import "./login.page.css";

const LoginPage = () => {
  return (
    <React.Fragment>
      <NavbarComponent darkText={true} />
      <section className="signin-container">
        <div className="signin-img-container">
          <img src={AuthBgImg} alt="authentication-background" />
        </div>

        <div className="signin-content-container">
          <div className="container">
            <div className="content-wrapper">
              <h2>Login</h2>
              <p>Sign in with your email and password.</p>

              <LoginFormComponent />
            </div>
          </div>
        </div>
      </section>
    </React.Fragment>
  );
};

export default LoginPage;
