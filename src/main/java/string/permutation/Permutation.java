package string.permutation;

public class Permutation {
    static void permutate(String str, String pref) {
        if(str.isEmpty()) {
            System.out.println(pref);
        }

        for(int i = 0; i < str.length(); i++) {
            String rem = str.substring(0, i) + str.substring(i + 1);
            permutate(rem, pref + str.charAt(i));
        }
    }

    public static void main(String...strings) {
        permutate("abc", "");
    }
}
