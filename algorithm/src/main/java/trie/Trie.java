package trie;

import java.util.Arrays;

public class Trie {
    private static final int ALPHABET_SIZE = 26;

    private Trie[] children;
    private boolean isEndOfWord;

    public Trie() {
        children = new Trie[ALPHABET_SIZE];
        isEndOfWord = false;
    }

    public void insert(String key) {
        if(key == null || key.isEmpty()) {
            return;
        }

        Trie p = this;
        int index;
        for(int i = 0; i < key.length(); i++) {
            index = key.charAt(i) - 'a';
            if(p.children[index] == null) {
                p.children[index] = new Trie();
            }
            p = p.children[index];
        }
        p.isEndOfWord = true;
    }

    public boolean search(String key) {
        if(key == null || key.isEmpty()) {
            return false;
        }

        Trie p = this;
        int index;
        for(int i = 0; i < key.length(); i++) {
            index = key.charAt(i) - 'a';
            if(p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }

        return p != null; //&& p.isEndOfWord;
    }

    public Trie[] getChildren() {
        return children;
    }

    public void setChildren(Trie[] children) {
        this.children = children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    @Override
    public String toString() {
        return "Trie{" +
                "children=" + Arrays.toString(children) +
                ", isEndOfWord=" + isEndOfWord +
                '}';
    }
}
