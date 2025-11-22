import lombok.NonNull;
import org.junit.Test;

public class TestMe {
    @Test
    public void testme() {
        sum(null, 1);
    }

    private int sum(@NonNull Integer a, @NonNull Integer b) {
        return a + b;
    }
}
