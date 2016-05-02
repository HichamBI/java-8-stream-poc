import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OneLineFileTransformer {

    public static String transform(String filePath) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().map((line) -> line.replace(" ", "_")).collect(Collectors.joining("_"));
        }
    }
}
