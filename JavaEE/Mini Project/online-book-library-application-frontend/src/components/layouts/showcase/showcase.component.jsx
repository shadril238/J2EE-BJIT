import "./showcase.component.css";
import NavbarComponent from "../navbar/navbar.component";
import SearchInputFormComponent from "../forms/search-input-form/searchInputForm.component";

const ShowcaseComponent = () => {
  return (
    <section className="showcase-container">
      <NavbarComponent darkTheme={false} />

      <div className="overlay"></div>
      <div className="showcase-content">
        <h1>
          Best <span className="text-primary">Books</span> Available
        </h1>
        <p>Get lost in a good book</p>

        <SearchInputFormComponent darkTheme={true} />
      </div>
    </section>
  );
};

export default ShowcaseComponent;
