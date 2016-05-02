import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class StreamsTest {

    @Test
    public void test_forEach() {
        List<String> filteredNames = new ArrayList<>();
        List<String> heroNames = Arrays.asList("Hei", "", "Luffy", "Naruto", "Ichigo", "", "BK-201");

        heroNames.forEach((name) -> {
            if (!name.isEmpty()) {
                filteredNames.add(name);
            }
        });

        assertThat(filteredNames).containsOnly("Hei", "Luffy", "Naruto", "Ichigo", "BK-201");
    }

    @Test
    public void test_filter() {
        List<String> heroNames = Arrays.asList("Hei", "", "Luffy", "Naruto", "Ichigo", "", "BK-201");
        List<String> filteredNames = heroNames.stream().filter(hero -> !hero.isEmpty()).collect(Collectors.toList());

        assertThat(filteredNames).containsOnly("Hei", "Luffy", "Naruto", "Ichigo", "BK-201");
    }

    @Test
    public void test_map() {
        List<String> heroNames = Arrays.asList("Hei", "Luffy", "Naruto", "Ichigo", "BK-201");

        List<String> filteredNames = heroNames.stream().map(String::toUpperCase).collect(Collectors.toList());

        assertThat(filteredNames).containsOnly("HEI", "LUFFY", "NARUTO", "ICHIGO", "BK-201");
    }

    @Test
    public void test_limit() {
        List<String> heroNames = Arrays.asList("Hei", "Luffy", "Naruto", "Ichigo", "BK-201");

        List<String> limitedList = heroNames.stream().limit(2).collect(Collectors.toList());

        assertThat(limitedList).containsOnly("Hei", "Luffy");
    }

    @Test
    public void test_sorted() {
        List<String> heroNames = Arrays.asList("Hei", "Luffy", "Naruto", "Ichigo", "BK-201");

        List<String> limitedList = heroNames.stream().sorted().collect(Collectors.toList());

        assertThat(limitedList).containsOnly("BK-201", "Hei", "Ichigo", "Luffy", "Naruto");
    }

    @Test
    public void test_collectors() {
        List<String> heroNames = Arrays.asList("Hello", "world");

        String oneJoinedString = heroNames.stream().collect(Collectors.joining(", "));

        assertThat(oneJoinedString).isEqualTo("Hello, world");
    }

    @Test
    public void test_summaryStatistics() {
        List<Integer> numbers = Arrays.asList(1, 2, 15, 0, 87, 5, 31);

        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(x -> x).summaryStatistics();

        assertThat(intSummaryStatistics.getMax()).isEqualTo(87);
        assertThat(intSummaryStatistics.getMin()).isEqualTo(0);
        assertThat(intSummaryStatistics.getAverage()).isEqualTo(20.142857142857142);
        assertThat(intSummaryStatistics.getCount()).isEqualTo(7);
        assertThat(intSummaryStatistics.getSum()).isEqualTo(141);
    }

    @Test
    public void test_parallelStream() {
        List<String> heroNames = Arrays.asList("Hei", "", "Luffy", "Naruto", "Ichigo", "", "BK-201");
        long count = heroNames.parallelStream().filter(String::isEmpty).count();

        assertThat(count).isEqualTo(2);
    }
}
