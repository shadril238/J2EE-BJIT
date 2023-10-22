import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import SearchInputFormComponent from "../../components/layouts/forms/search-input-form/searchInputForm.component";
import "./books.page.css";
import FooterComponent from "../../components/layouts/footer/footer.component";
import ProductListingAllComponent from "../../components/layouts/product-listing-all/productListingAll.component";

const BooksPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />

      <div className="search-container">
        <h2>
          Find the <span className="text-primary">Books</span> that you want
        </h2>

        <SearchInputFormComponent darkTheme={false} />
      </div>

      <ProductListingAllComponent />

      <FooterComponent />
    </section>
  );
};

export default BooksPage;
