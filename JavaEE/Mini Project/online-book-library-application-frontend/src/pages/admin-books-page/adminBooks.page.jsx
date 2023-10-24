import React from "react";
import "./adminBooks.page.css";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import AdminItemListingCardComponent from "../../components/layouts/cards/admin-item-listing-card/adminItemListingCard.component";
import AdminBooksComponent from "../../components/layouts/admin-books/adminBooks.component";

const AdminBooksPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <AdminBooksComponent />
      <FooterComponent />
    </section>
  );
};

export default AdminBooksPage;
