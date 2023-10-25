import React from "react";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import UserProfileComponent from "../../components/layouts/user-profile/userProfile.component";

const UserProfilePage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <UserProfileComponent />
      <FooterComponent />
    </section>
  );
};

export default UserProfilePage;
