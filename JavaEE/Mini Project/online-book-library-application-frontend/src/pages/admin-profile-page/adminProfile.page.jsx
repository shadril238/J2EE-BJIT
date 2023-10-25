import React from "react";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import AdminProfileComponent from "../../components/layouts/admin-profile/adminProfile.component";

const AdminProfilePage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <AdminProfileComponent />
      <FooterComponent />
    </section>
  );
};

export default AdminProfilePage;
