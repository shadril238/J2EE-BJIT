import React from "react";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import AdminDetailsSectionComponent from "../../components/layouts/admin-details-section/adminDetailsSection.component";

const AdminBookDetailsPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />

      <AdminDetailsSectionComponent />

      <FooterComponent />
    </section>
  );
};

export default AdminBookDetailsPage;
