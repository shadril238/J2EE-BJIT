import FooterComponent from "../../components/layouts/footer/footer.component";
import ShowcaseComponent from "../../components/layouts/showcase/showcase.component";
import ProductListingComponent from "../../components/layouts/product-listing/productListing.component";

const HomePage = () => {
  return (
    <section>
      <ShowcaseComponent />
      <ProductListingComponent />
      <FooterComponent />
    </section>
  );
};

export default HomePage;
