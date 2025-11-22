package main.java.bit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * <p>
 * Each LED represents a zero or one, with the least significant bit on the right.
 * For example, the above binary watch reads "3:25".
 * <p>
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 * <p>
 * Example:
 * <p>
 * Input: n = 1
 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
 * The order of output does not matter.
 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryClock {

    public static List<String> solve(int num) {
        List<String> result = new ArrayList<>();
        for (int hh = 0; hh < 12; hh++) {
            for (int mm = 0; mm < 60; mm++) {
                if (aux(hh, mm, num)) {
                    if (mm < 10) {
                        result.add(String.format("%d:0%d", hh, mm));
                    } else {
                        result.add(String.format("%d:%d", hh, mm));
                    }
                }
            }
        }

        return result;
    }

    private static boolean aux(int hh, int mm, int num) {
        int tmp = 0;
        while (hh != 0 || mm != 0) {
            if (hh != 0) {
                tmp += hh % 2;
                hh /= 2;
            }
            if (mm != 0) {
                tmp += mm % 2;
                mm /= 2;
            }
        }

        return tmp == num;
    }

    private boolean filterNonNullTitle(JsonElement e) {
        JsonObject obj = e.getAsJsonObject();
        return Objects.nonNull(obj.get("title")) || Objects.nonNull(obj.get("story_title"));
    }

    private String getTitle(JsonElement e) {
        JsonObject obj = e.getAsJsonObject();
        if(!obj.get("title").isJsonNull()) {
            return obj.get("title").getAsString();
        } else if(!obj.get("story_title").isJsonNull()) {
            return obj.get("story_title").getAsString();
        }

        return null;
    }

    public String[] getTitles(String author) {
//        String response;
        int startPage = 1;
        int totalPages = Integer.MAX_VALUE;
        List<String> titles = new ArrayList<>();
        while (startPage <= totalPages) {
            try {
                URL obj = new URL("https://jsonmock.hackerrank.com/api/articles?author=" + author + "&page=" + startPage);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String response = in.lines().collect(Collectors.joining());
//                while ((response = in.readLine()) != null) {
//                }
                {
                    JsonObject convertedObject = new Gson().fromJson(response, JsonObject.class);
                    totalPages = convertedObject.get("total_pages").getAsInt();
                    JsonArray data = convertedObject.getAsJsonArray("data");
                    titles.addAll(StreamSupport.stream(data.spliterator(), false).map(this::getTitle).filter(Objects::nonNull).collect(Collectors.toList()));
                }
                in.close();
                startPage++;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
//        Collections.sort(titles);

        return titles.toArray(new String[0]);
    }

    public static void main(String... strings) {
        long start = System.currentTimeMillis();
//        new BinaryClock().getTitles("coloneltcb");
        new BinaryClock().getTitles("saintamh");
        System.out.println(System.currentTimeMillis() - start);
    }
}
