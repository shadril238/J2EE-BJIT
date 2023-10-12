import React from 'react';

const ButtonComponent = ({ btnText, handleOnClick }) => {
    return (
        <div>
        <button
          style={{ backgroundColor: "purple", color: "white" }}
          onClick={handleOnClick}
        >
          {btnText}
        </button>
      </div>
    );
}

export default ButtonComponent;
