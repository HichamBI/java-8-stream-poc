import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class OneLineFileTransformerTest {

    @Test
    public void test_file_to_one_line() throws Exception {
        String line = OneLineFileTransformer.transform(getClass().getResource("data.txt").getPath());

        assertThat(line).isEqualTo("Naruto_is_a_good_guy_But_Hei_is_the_best");
    }
}
