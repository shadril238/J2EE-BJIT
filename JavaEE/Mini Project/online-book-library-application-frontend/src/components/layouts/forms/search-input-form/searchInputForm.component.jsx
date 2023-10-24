import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./searchInputForm.component.css";

const SearchInputFormComponent = ({ darkTheme }) => {
  const [searchField, setSearchField] = useState("");
  const navigate = useNavigate();

  return (
    <div
      className={`search-input-form-container ${
        darkTheme ? "dark-box-shadow" : "light-box-shadow"
      }`}
    >
      <input
        type="text"
        className="search-input"
        placeholder="Search Books"
        value={searchField}
        onChange={(e) => {
          setSearchField(e.target.value);
        }}
      />
      <button
        className="search-button"
        onClick={() => {
          navigate("/search", { state: searchField });
        }}
      >
        Search
      </button>
    </div>
  );
};

export default SearchInputFormComponent;
