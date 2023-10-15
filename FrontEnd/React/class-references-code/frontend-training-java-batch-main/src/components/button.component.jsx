const Button = ({ btnText = "click", handleOnClick }) => {
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
};

export default Button;
