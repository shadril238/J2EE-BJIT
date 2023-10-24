import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import axiosInstanceBookService from "../../utils/axiosInstanceBookService";
import "./search.page.css";
import ProductListingSearchComponent from "../../components/layouts/product-listing-search/productListingSearch.component";

const SearchPage = () => {
  const [bookList, setBookList] = useState([]);
  const location = useLocation();

  useEffect(() => {
    let searchValue = [];

    axiosInstanceBookService.get("/all").then((resp) => {
      searchValue = resp.data.filter((data) =>
        data.title.toLowerCase().includes(location.state.toLowerCase())
      );
      setBookList(searchValue);
    });

    console.log(location.state);
  }, [location.state]);
  console.log("Search value : ", bookList);
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <div className="search-result-container">
        <div className="container">
          <h2>Your Search Result</h2>
          <ProductListingSearchComponent bookList={bookList} />
        </div>
      </div>
      <FooterComponent />
    </section>
  );
};

export default SearchPage;
