package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchWord {
    public static void main(String... strings) {
        String[] keys = new String[] {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";

        List<List<String>> result = new ArrayList<>();
        Trie trie = new Trie();
        for(String key : keys) {
            trie.insert(key);
        }
        System.out.println(Arrays.toString("a b c".split(" ", 2)));
        System.out.println(trie.search(searchWord));
    }
}
