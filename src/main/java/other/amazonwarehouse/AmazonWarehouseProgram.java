package other.amazonwarehouse;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * // Amazon is trying to optimize its warehouse inventory for 2-hour delivery items.
 * // As part of that effort you are given a real-time stream of individual items ordered
 * // through Amazon.  Each entry in the stream includes a single item SKU, number
 * // of those items in the individual order, and a timestamp.
 * // Write a program, that will store the stream data. This program can also be
 * // queried, separately, at any time, to return the SKUs for all the items ordered in
 * // the last 6 hours as well as the combined, overall volume for each item.
 * //
 * // Example data:
 * // (“ITEM_1”, 5, 1620311721066)
 * // (“ITEM_2”, 2, 1620311782011)
 * // (“ITEM_3”, 12, 1620311822562)
 * // (“ITEM_1”, 14, 1620311851135)
 * // (“ITEM_4”, 15, 1620311893213)
 * // (“ITEM_2”, 10, 1620311926421)
 * // (“ITEM_3”, 6, 1620311979012)
 * //
 * // Output - [(“ITEM_1”, 19), (“ITEM_2”, 12), (“ITEM_3”, 18), (“ITEM_4”, 15)]
 */
public class AmazonWarehouseProgram {
    private Queue<Order> orders;
    private Map<String, Integer> itemCounts;
    private int windowSize;//In hours

    public AmazonWarehouseProgram(int windowSize) {
//        this.orders = new LinkedList<>();
//        this.itemCounts = new HashMap<>();
        this.orders = new LinkedBlockingQueue<>();
        this.itemCounts = new Hashtable<>();
        this.windowSize = windowSize;
    }

    public void stream(Order order) {
        orders.offer(order);
        itemCounts.put(order.getSku(), itemCounts.getOrDefault(order.getSku(), 0) + order.getCount());
    }

    public void cleanup() {
        while(!orders.isEmpty()) {
            Order order = orders.peek();
            long diff = System.currentTimeMillis() - order.getTimestamp();
            long hours = diff / (1000*60*60*60);
            if(hours <= 6) {
                break;
            }

            itemCounts.put(order.getSku(), itemCounts.get(order.getSku()) - order.getCount());
            orders.poll();
        }
    }

    public void printItems() {
        System.out.println(itemCounts);
    }

    public static void main(String...strs) {
        final AmazonWarehouseProgram warehouse = new AmazonWarehouseProgram(6);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            while(true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                warehouse.stream(new Order("SKU1", 10, System.currentTimeMillis()));
            }
        });
        executor.execute(() -> {
            while(true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                warehouse.stream(new Order("SKU2", 5, System.currentTimeMillis()));
                warehouse.stream(new Order("SKU1", 1, System.currentTimeMillis()));
            }
        });

        executor.execute(() -> {
            while(true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                warehouse.cleanup();
            }
        });

        while(true) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            warehouse.printItems();
        }
    }
}
