package other.mostusedresource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
class Log {
    private long seconds;
    private String userId;
    private String resourceId;
}
