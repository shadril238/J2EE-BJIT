import React from "react";
import "./pageNotFound.component.css";

const PageNotFoundComponent = () => {
  return (
    <section>
      <div className="page-not-found-container">
        <h1>Not Found</h1>
        <p>The request URL was not found on this server.</p>
        <hr />
        <address>
          BookLibrary Version 1.0 - Server at localhost Port 5173
        </address>
      </div>
    </section>
  );
};

export default PageNotFoundComponent;
