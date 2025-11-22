public class Child extends FileSystem {
    public static void staticFunction() {
        System.out.println("child");
    }
    private int someValue() {
        return 1;
    }
    public Double randomVal(int ratio) {
        return Math.random() * ratio;
    }
}
