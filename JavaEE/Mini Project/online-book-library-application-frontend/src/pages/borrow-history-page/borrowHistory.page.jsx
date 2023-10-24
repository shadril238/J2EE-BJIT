import React from "react";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import BorrowHistoryComponent from "../../components/layouts/borrow-history-listing/borrowHistory.component";
import FooterComponent from "../../components/layouts/footer/footer.component";

const BorrowHistoryPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <BorrowHistoryComponent />
      <FooterComponent />
    </section>
  );
};

export default BorrowHistoryPage;
