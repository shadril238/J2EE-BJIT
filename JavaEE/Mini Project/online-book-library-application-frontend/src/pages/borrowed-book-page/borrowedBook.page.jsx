import BorrowedProductListingComponent from "../../components/layouts/borrowed-product-listing/borrowedProductListing.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";

const BorrowedBookPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <BorrowedProductListingComponent />
      <FooterComponent />
    </section>
  );
};

export default BorrowedBookPage;
