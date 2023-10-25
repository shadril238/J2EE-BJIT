import React from "react";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import CreateReviewFormComponent from "../../components/layouts/forms/create-review-form/createReviewForm.component";

const CreateReviewPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <CreateReviewFormComponent />
      <FooterComponent />
    </section>
  );
};

export default CreateReviewPage;
