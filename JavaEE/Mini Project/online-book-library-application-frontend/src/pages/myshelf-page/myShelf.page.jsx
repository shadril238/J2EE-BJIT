import React from "react";
import "./myShelf.page.css";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import MyShelfComponent from "../../components/layouts/my-shelf/myShelf.component";

const MyShelfPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <MyShelfComponent />
      <FooterComponent />
    </section>
  );
};

export default MyShelfPage;
