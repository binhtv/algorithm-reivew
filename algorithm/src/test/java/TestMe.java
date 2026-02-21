// Test package - files in src/test/java root directory

import lombok.NonNull;
import org.junit.Test;

public class TestMe {
    @Test
    public void testme() {
        sum(1, 2);
    }

    private int sum(@NonNull Integer a, @NonNull Integer b) {
        return a + b;
    }
}
