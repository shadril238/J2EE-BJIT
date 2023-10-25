import React from "react";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import UpdateProfileComponent from "../../components/layouts/update-profile/updateProfile.component";

const UpdateProfilePage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <UpdateProfileComponent />
      <FooterComponent />
    </section>
  );
};

export default UpdateProfilePage;
