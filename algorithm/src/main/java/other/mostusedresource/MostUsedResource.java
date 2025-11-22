package main.java.other.mostusedresource;

import java.util.*;

import static java.util.Collections.reverseOrder;

/**
 * Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID.
 * <p>
 * The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.
 * <p>
 * Example:
 * logs1 = [
 * ["58523", "user_1", "resource_1"],
 * ["62314", "user_2", "resource_2"],
 * ["54001", "user_1", "resource_3"],
 * ["200", "user_6", "resource_5"],
 * ["215", "user_6", "resource_4"],
 * ["54060", "user_2", "resource_3"],
 * ["53760", "user_3", "resource_3"],
 * ["58522", "user_22", "resource_1"],
 * ["53651", "user_5", "resource_3"],
 * ["2", "user_6", "resource_1"],
 * ["100", "user_6", "resource_6"],
 * ["400", "user_7", "resource_2"],
 * ["100", "user_8", "resource_6"],
 * ["54359", "user_1", "resource_3"],
 * ]
 * <p>
 * Example 2:
 * logs2 = [
 * ["300", "user_1", "resource_3"],
 * ["599", "user_1", "resource_3"],
 * ["900", "user_1", "resource_3"],
 * ["1199", "user_1", "resource_3"],
 * ["1200", "user_1", "resource_3"],
 * ["1201", "user_1", "resource_3"],
 * ["1202", "user_1", "resource_3"]
 * ]
 * <p>
 * Example 3:
 * logs3 = [
 * ["300", "user_10", "resource_5"]
 * ]
 * <p>
 * Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window, together with how many accesses it saw.
 * <p>
 * Expected Output:
 * most_requested_resource(logs1) # => ('resource_3', 3)
 * Reason: resource_3 is accessed at 53760, 54001, and 54060
 * <p>
 * most_requested_resource(logs2) # => ('resource_3', 4)
 * Reason: resource_3 is accessed at 1199, 1200, 1201, and 1202
 * <p>
 * most_requested_resource(logs3) # => ('resource_5', 1)
 * Reason: resource_5 is accessed at 300
 * <p>
 * Complexity analysis variables:
 * <p>
 * n: number of logs in the input
 */
public class MostUsedResource {
    private static ResourceAndCount findMax(Map<String, Integer> windowMap) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(windowMap.entrySet());
        Collections.sort(entries, Comparator.comparing(Map.Entry::getValue, reverseOrder()));
        return new ResourceAndCount(entries.get(0).getKey(), entries.get(0).getValue());
    }

    private static ResourceAndCount mostUsedResource(List<Log> logs, int windowSize) {
        List<ResourceAndCount> allLocalMax = new ArrayList<>();
        for (int i = 0; i < logs.size(); i++) {
            long start = logs.get(i).getSeconds();
            long end = start + windowSize;
            Map<String, Integer> windowMap = new HashMap<>();
            int j = i;
            while (j < logs.size() && logs.get(j).getSeconds() <= end) {
                windowMap.put(logs.get(j).getResourceId(), windowMap.getOrDefault(logs.get(j).getResourceId(), 0) + 1);
                j++;
            }
            allLocalMax.add(findMax(windowMap));
        }

        return Collections.max(allLocalMax);
    }

    public static void main(String... args) {
        int windowSize = 5 * 60;//300 seconds
        List<Log> logs = new ArrayList<>();
        logs.add(new Log(58523, "user_1", "resource_1"));
        logs.add(new Log(62314, "user_2", "resource_2"));
        logs.add(new Log(54001, "user_1", "resource_3"));
        logs.add(new Log(200, "user_6", "resource_5"));
        logs.add(new Log(215, "user_6", "resource_4"));
        logs.add(new Log(54060, "user_2", "resource_3"));
        logs.add(new Log(53760, "user_3", "resource_3"));
        logs.add(new Log(58522, "user_22", "resource_1"));
        logs.add(new Log(53651, "user_5", "resource_3"));
        logs.add(new Log(2, "user_6", "resource_1"));
        logs.add(new Log(100, "user_6", "resource_6"));
        logs.add(new Log(400, "user_7", "resource_2"));
        logs.add(new Log(100, "user_8", "resource_6"));
        logs.add(new Log(54359, "user_1", "resource_3"));

        Collections.sort(logs, new LogByTimeComparator());

        System.out.println(mostUsedResource(logs, windowSize));
    }
}
