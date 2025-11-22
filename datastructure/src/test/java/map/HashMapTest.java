package test.java.map;

import main.java.map.HashMap;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class HashMapTest {
    @Test
    public void testHashMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        assertThat(map.get("a"), equalTo(1));
        assertThat(map.get("b"), equalTo(2));
        assertThat(map.get("c"), equalTo(3));

        map.put("b", 4);
        assertThat(map.get("b"), equalTo(4));

        map.delete("a");
        assertThat(map.get("a"), nullValue());
        Optional.ofNullable(map.get("b")).stream().map(String::valueOf).findFirst();

    }
}
