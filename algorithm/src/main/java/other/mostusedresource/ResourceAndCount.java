package main.java.other.mostusedresource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResourceAndCount implements Comparable<ResourceAndCount> {
    private String resourceId;
    private int count;

    @Override
    public int compareTo(ResourceAndCount o) {
        return Integer.compare(count, o.getCount());
    }
}
