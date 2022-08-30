import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ShortTest {

    @Test
    void shouldPrintOnlyFirstCreedLine() {
        short first = 1;
        short second = 1;
        var summ = first + second;
        assertThat(((Object) summ).getClass().getName()).isEqualTo("java.lang.Integer");
    }
}
