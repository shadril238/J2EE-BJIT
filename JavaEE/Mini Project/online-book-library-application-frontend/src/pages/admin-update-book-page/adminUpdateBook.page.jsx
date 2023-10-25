import { useState, useEffect, useContext } from "react";
import { useParams } from "react-router-dom";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import axiosInstanceBookService from "../../utils/axiosInstanceBookService";
import { UserContext } from "../../App";
import AdminUpdateBookComponent from "../../components/layouts/admin-update-book/adminUpdateBook.component";

const AdminUpdateBookPage = () => {
  const { id } = useParams();
  console.log(id);

  const [bookDetails, setBookDetails] = useState();
  const [isLoading, setIsLoading] = useState(false);

  const user = useContext(UserContext);

  useEffect(() => {
    axiosInstanceBookService
      .get(`/${id}`)
      .then((resp) => {
        const data = resp.data;
        setBookDetails(data);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);
  console.log("book details", bookDetails);
  return (
    <section>
      <NavbarComponent darkTheme={true} />
      <AdminUpdateBookComponent bookData={bookDetails} />
      <FooterComponent />
    </section>
  );
};

export default AdminUpdateBookPage;
