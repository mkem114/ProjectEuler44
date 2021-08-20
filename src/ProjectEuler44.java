import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ProjectEuler44 {
    public static void main(String[] args) {
        Long recordDifference = Long.MAX_VALUE;

        // The smallest difference should be near the start given that pentagonal numbers grow faster
        // than N. Just relies on the scale of the problem being just within reach of computers.
        List<Long> range = LongStream.rangeClosed(1L, 10_000L)
                .boxed().collect(Collectors.toList());

        LinkedHashSet<Long> pentoganalNumbers = range
                .stream()
                .map(ProjectEuler44::toPentagonNumber)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        for (Long outer: pentoganalNumbers) {
            for (Long inner: pentoganalNumbers) {
                Long difference = Math.abs(outer - inner);
                if (difference < recordDifference) {
                    if (pentoganalNumbers.contains(difference)) {
                        if (pentoganalNumbers.contains(outer + inner)) {
                            recordDifference = difference;
                        }
                    }
                }
            }
        }

        System.out.println(recordDifference);
    }

    private static Long toPentagonNumber(Long integer) {
        return integer * (3 * integer - 1) / 2;
    }
}
