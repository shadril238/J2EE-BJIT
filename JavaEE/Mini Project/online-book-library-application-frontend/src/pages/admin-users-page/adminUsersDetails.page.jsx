import React from "react";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import AdminUserProfileComponent from "../../components/layouts/admin-users/adminUserProfile.component";
const AdminUsersDetailsPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <AdminUserProfileComponent />
      <FooterComponent />
    </section>
  );
};

export default AdminUsersDetailsPage;
