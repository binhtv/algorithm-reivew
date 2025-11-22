package most.visited.website;

import java.util.*;

public class MostVisitedWebsite {
    static class Visit implements Comparable<Visit> {
        String username;
        int timestamp;
        String website;

        public Visit(String u, int t, String w) {
            username = u;
            timestamp = t;
            website = w;
        }

        @Override
        public int compareTo(Visit o) {
            return this.timestamp - o.timestamp;
        }
    }

    private static List<Visit> buildListOfVisit(String[] username, int[] timestamp, String[] website) {
        List<Visit> visits = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {
            visits.add(new Visit(username[i], timestamp[i], website[i]));
        }
        Collections.sort(visits);

        return visits;
    }

    private static Map<String, List<String>> buildMapOfWebsite(List<Visit> visits) {
        Map<String, List<String>> map = new HashMap<>();
        for (Visit visit : visits) {
            List<String> list = map.getOrDefault(visit.username, new ArrayList<>());
            list.add(visit.website);
            map.put(visit.username, list);
        }

        return map;
    }

    private static Set<String> getAllCombination(List<String> websites) {
        Set<String> result = new HashSet<>();
        if (websites.size() == 0) {
            return result;
        }

        for (int i = 0; i < websites.size() - 2; i++) {
            for (int j = i + 1; j < websites.size() - 1; j++) {
                for (int k = j + 1; k < websites.size(); k++) {
                    StringBuilder sb = new StringBuilder(websites.get(i));
                    sb.append(",");
                    sb.append(websites.get(j));
                    sb.append(",");
                    sb.append(websites.get(k));
                    result.add(sb.toString());
                }
            }
        }

        return result;
    }

    private static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int maxCount = 0;
        Map<String, Integer> counts = new HashMap<>();
        Map<String, List<String>> map = buildMapOfWebsite(buildListOfVisit(username, timestamp, website));
        String result = "";
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            Set<String> sequences = getAllCombination(entry.getValue());
            for (String sequence : sequences) {
                int count = counts.getOrDefault(sequence, 0) + 1;
                counts.put(sequence, count);
                if (count > maxCount) {
                    maxCount = count;
                    result = sequence;
                } else if (count == maxCount) {
                    result = sequence.compareTo(result) < 0 ? sequence : result;
                }
            }
        }

        return Arrays.asList(result.split(","));
    }

    public static void main(String... strings) {
        String[] username = new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] website = new String[]{"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"};

        System.out.println(mostVisitedPattern(username, timestamp, website));
    }
}
