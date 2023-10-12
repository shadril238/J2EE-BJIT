import "./title.css";

const CustomTitle = ({ title, textColor = "green" }) => {
  return (
    <div>
      <h1
        className="title-class"
        style={{ color: textColor ? textColor : "green" }}
      >
        Title: {title}
      </h1>
    </div>
  );
};

export default CustomTitle;
