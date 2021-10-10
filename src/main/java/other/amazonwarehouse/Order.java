package other.amazonwarehouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private String sku;
    private int count;
    private long timestamp;
}
