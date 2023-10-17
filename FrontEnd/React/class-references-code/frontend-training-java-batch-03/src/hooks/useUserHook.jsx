import axios from "axios";
import { useEffect, useState } from "react";

const useUserHook = () => {
  const [users, setUsers] = useState([]);
  const [errors, setErrors] = useState();

  useEffect(() => {
    axios
      .get("https://reqres.in/api/users")
      .then((resp) => {
        const data = resp.data;

        console.log("Data ", data.data);
        setUsers(data.data);
      })
      .catch((error) => {
        console.log(error);
        setErrors(errors);
      });
  }, []);

  const handleSubmit = () => {
    console.log("Submitting from custom hook");
  };

  return { users, handleSubmit, errors };
};

export default useUserHook;
