import React from "react";
import "./adminAddBook.page.css";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import AddBookFormComponent from "../../components/layouts/forms/add-book-form/addBookForm.component";

const AdminAddBookPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <AddBookFormComponent />
      <FooterComponent />
    </section>
  );
};

export default AdminAddBookPage;
