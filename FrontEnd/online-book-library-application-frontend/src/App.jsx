import { Route, Routes } from "react-router-dom";
import FooterComponent from "./components/footer.component";
import NavbarComponent from "./components/navbar.component";
import AboutPage from "./pages/about.page";
import ContactPage from "./pages/contact.page";
import HomePage from "./pages/home.page";
import ServicesPage from "./pages/services.page";
import LoginPage from "./pages/login.page";
import NotFoundPage from "./pages/notfound.page";
import RegistrationPage from "./pages/registration.page";
import BooksPage from "./pages/books.page";

const App = () => {
  return (
    <div className="App">
      <NavbarComponent />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegistrationPage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/services" element={<ServicesPage />} />
        <Route path="/contact" element={<ContactPage />} />
        <Route path="/books" element={<BooksPage />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
      <FooterComponent />
    </div>
  );
};

export default App;
