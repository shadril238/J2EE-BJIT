import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapDemo {
    public static void main(String[] args) {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, List.of("Item A", "Item B")));
        orderList.add(new Order(2, List.of("Item C")));
        orderList.add(new Order(3, List.of("Item D", "Item E", "Item F")));

        List<String> allItems = orderList.stream()
                .flatMap(order -> order.getItems().stream())
                .toList();

        System.out.println("All Items: " + allItems);


        //--------------------------------------------------------------//

        List<Optional<Integer>> numbers = Arrays.asList(
                Optional.of(1),
                Optional.empty(),
                Optional.of(3),
                Optional.empty(),
                Optional.of(5)
        );

        List<Integer> nonEmptyValues = numbers.stream()
                .flatMap(Optional::stream)
                .toList();

        System.out.println("Non Empty Values: " + nonEmptyValues);
    }
}
