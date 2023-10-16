const ButtonComponent = ({ isDetailsShown, onShowDetails, onHideDetails }) => {
  return (
    <div className="details-button-container">
      {isDetailsShown ? (
        <div className="product-more-details">
          <button className="close-button" onClick={onHideDetails}>
            Close
          </button>
        </div>
      ) : (
        <button className="show-details-button" onClick={onShowDetails}>
          Show Details
        </button>
      )}
    </div>
  );
}

export default ButtonComponent;
