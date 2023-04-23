import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    private static final int RETURN_FOR_EXCEPTION = 0;

    public static int splitAndSum(String text) throws RuntimeException {

        if (Objects.isNull(text) || text.isEmpty()) {
            return RETURN_FOR_EXCEPTION;
        }

        String[] tokens = tokenize(text);

        if (checkNegative(tokens)) {
            throw new RuntimeException("cannot process negative value");
        }

        return Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private static boolean checkNegative(String[] tokens) {
        return Arrays.stream(tokens).mapToInt(Integer::parseInt).anyMatch(t -> t < 0);
    }

    private static String[] tokenize(String text) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);

        if (m.find()) {
            String customDelimiter = m.group(1);
            return m.group(2).split(customDelimiter);
        }

        return text.split(",|:");
    }

}
