import React from "react";
import "./signUp.page.css";
import AuthBgImg from "../../assets/auth-bg.jpg";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import SignUpFormComponent from "../../components/layouts/forms/signup-form/signUpForm.component";

const SignUpPage = () => {
  return (
    <React.Fragment>
      <NavbarComponent darkText={true} />
      <section className="signup-container">
        <div className="signup-img-container">
          <img src={AuthBgImg} alt="authentication-background" />
        </div>

        <div className="signup-content-container">
          <div className="container">
            <div className="content-wrapper">
              <h2>Sign Up</h2>
              <p>Create a new account by providing your details.</p>

              <SignUpFormComponent />
            </div>
          </div>
        </div>
      </section>
    </React.Fragment>
  );
};

export default SignUpPage;
