import React from "react";

const BookComponent = ({ bookData }) => {
  return (
    <div>
      <table>
        <tbody>
          <tr>
            <td>{bookData.id}</td>
            <td>{bookData.title}</td>
            <td>{bookData.author}</td>
            <td>{bookData.status}</td>
            <td>{bookData.isActive ? "Yes" : "No"}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default BookComponent;
