import { Link } from "react-router-dom";

const Header = () => {
  return (
    <div>
      {/* <div>Logo</div> */}

      <div style={{ display: "flex", justifyContent: "space-around" }}>
        <Link to="/">Home</Link>
        <Link to="/register">Register</Link>
        <Link to="/login">Login</Link>
      </div>
    </div>
  );
};

export default Header;
