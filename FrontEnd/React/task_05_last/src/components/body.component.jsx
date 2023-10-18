import { useContext, useState } from 'react';
import './body.component.css';
import { ProductContext } from '../App';
import ButtonComponent from './button.component.jsx'; 

const BodyComponent = () => {
    const { data, loading } = useContext(ProductContext);
    const [selectedProduct, setSelectedProduct] = useState(null);

    const showDetails = (product) => {
        setSelectedProduct(product);
    };

    const hideDetails = () => {
        setSelectedProduct(null);
    };

    const handleProductDetailsClick = (product) => {
        alert(`Display details for product: ${product.title}`);
    };

    return (
        <div className="body">
            {loading ? (
                <p>Loading data...</p>
            ) : (
                <ul className="product-list">
                    {data.map((item) => (
                        <li key={item.id} className="product-box">
                            <div className="product-image">
                                <img
                                    src={item.thumbnail}
                                    alt={item.title}
                                    style={{ width: '200px', height: '200px' }}
                                />
                            </div>
                            <div className="product-details">
                                <h2 className="product-title">{item.title}</h2>
                                <p className="product-price">Price: ${item.price}</p>
                                <ButtonComponent
                                    isDetailsShown={selectedProduct === item}
                                    onShowDetails={() => showDetails(item)}
                                    onHideDetails={hideDetails}
                                    onProductDetailsClick={() => handleProductDetailsClick(item)}
                                />
                                {selectedProduct === item && (
                                    <div className="product-more-details">
                                        <p>Description: {item.description}</p>
                                        <p>Discount: {item.discountPercentage}%</p>
                                        <p>Rating: {item.rating}</p>
                                        <p>Stock: {item.stock}</p>
                                        <p>Brand: {item.brand}</p>
                                        <p>Category: {item.category}</p>
                                    </div>
                                )}
                            </div>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default BodyComponent;