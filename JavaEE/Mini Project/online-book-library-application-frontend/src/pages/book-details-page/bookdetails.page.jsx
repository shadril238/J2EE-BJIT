import DetailsSectionComponent from "../../components/layouts/details-section/detailsSection.component";
import FooterComponent from "../../components/layouts/footer/footer.component";
import NavbarComponent from "../../components/layouts/navbar/navbar.component";

const BookdetailsPage = () => {
  return (
    <section>
      <NavbarComponent darkTheme={true} />

      <DetailsSectionComponent />

      <FooterComponent />
    </section>
  );
};

export default BookdetailsPage;
