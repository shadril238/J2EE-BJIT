import { useState } from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import axiosInstanceUsersService from "../../../utils/axiosInstanceUsersService";
import AdminUserListingCardComponent from "../cards/admin-user-listing-card/adminUserListingCard.component";

const AdminUsersComponent = () => {
  const [usersList, setUsersList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    axiosInstanceUsersService
      .get("")
      .then((resp) => {
        console.log(resp.data);
        setUsersList(resp.data);
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);
  console.log("users list", usersList);
  return (
    <section className="product-listing-all-container">
      <h1 className="product-listing-all-heading">
        Here you can manage <span className="status">Users</span> for the
        Library...
      </h1>
      <div className="container">
        {/* <Link to="/admin/user/add" className="button-primary">
          Add Book
        </Link> */}
        <div className="grid-container">
          {usersList.map((user) => {
            return (
              <div className="grid-item" key={user?.id}>
                <AdminUserListingCardComponent userData={user} />
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
};

export default AdminUsersComponent;
