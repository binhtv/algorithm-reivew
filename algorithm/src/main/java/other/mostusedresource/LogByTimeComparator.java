package main.java.other.mostusedresource;

import java.util.Comparator;

public class LogByTimeComparator implements Comparator<Log> {
    @Override
    public int compare(Log log1, Log log2) {
        return Long.compare(log1.getSeconds(), log2.getSeconds());
    }
}
