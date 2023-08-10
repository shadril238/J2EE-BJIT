package teluskopractice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPI {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(4, 5, 6, 7, 8, 9, 1, -1);
        //Stream<Integer> data = nums.stream();
        //long count = data.count();
        //Stream<Integer> sortedValue = data.sorted();
        nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .sorted()
                .forEach(n -> System.out.println(n));
        //data.forEach(n -> System.out.println(n));
    }
}
