import React from "react";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import AdminUsersComponent from "../../components/layouts/admin-users/adminUsers.component";

const AdminUsersPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <AdminUsersComponent />
      <FooterComponent />
    </section>
  );
};

export default AdminUsersPage;
