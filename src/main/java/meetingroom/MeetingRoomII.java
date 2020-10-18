package meetingroom;

import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.*;

public class MeetingRoomII {
    public static List<String> food(List<List<String>> itemAssociation) {
        Map<String, List<String>> graph = new HashMap<>();
        for(List<String> item : itemAssociation) {
            List<String> adj = graph.getOrDefault(item.get(0), new ArrayList<>());
            adj.add(item.get(1));
            graph.put(item.get(0), adj);

            List<String> adj1 = graph.getOrDefault(item.get(1), new ArrayList<>());
            adj1.add(item.get(0));
            graph.put(item.get(1), adj1);
        }

        Set<String> visited= new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        List<List<String>> components = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : graph.entrySet()) {
            String v = entry.getKey();
            List<String> comp = new ArrayList<>();
            if(!visited.contains(v)) {
                queue.offer(v);
                visited.add(v);
                comp.add(v);
                while (!queue.isEmpty()) {
                    String curr = queue.poll();
                    for(String adj : graph.get(curr)) {
                        if(!visited.contains(adj)) {
                            queue.offer(adj);
                            visited.add(adj);
                            comp.add(adj);
                        }
                    }
                }
            }
            if(!comp.isEmpty()) {
                Collections.sort(comp);
                components.add(comp);
            }
        }

        Queue<List<String>> priorityQueue = new PriorityQueue<>((l1, l2) -> {
           if(l1.size() == l2.size()) {
               return l1.get(0).compareTo(l2.get(0));
           } else {
               return l2.size() - l1.size();
           }
        });

        priorityQueue.addAll(components);

        return priorityQueue.peek();
    }

    public static void main(String...strings) {
        List<List<String>> items = new ArrayList<>();
        items.add(Arrays.asList("item1", "item2"));
        items.add(Arrays.asList("item3", "item4"));
        items.add(Arrays.asList("item4", "item5"));
        System.out.println(food(items));
    }
}
