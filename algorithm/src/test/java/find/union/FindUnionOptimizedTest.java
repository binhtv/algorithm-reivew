package test.java.find.union;

import main.java.find.union.FindUnion;
import main.java.find.union.FindUnionOptimized;
import org.junit.Test;

public class FindUnionOptimizedTest {
    @Test
    public void testFindUnion() {
        FindUnionOptimized findUnion = new FindUnionOptimized(10);
        findUnion.union(0, 1);
        findUnion.union(0, 2);
        findUnion.union(1, 3);
        findUnion.union(4, 8);
        findUnion.union(5, 7);
        findUnion.union(5, 6);

        System.out.println(findUnion.find(1) == findUnion.find(3));
        System.out.println(findUnion.find(4) == findUnion.find(7));
        System.out.println(findUnion.find(6) == findUnion.find(7));


        System.out.println(findUnion);
    }
}
