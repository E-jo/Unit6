package org.unitsix;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;

public class Customer extends Thread implements Callable<HashMap<String, Long>> {
    private final int numberOfItems;
    private final int itemLimit = 6;

    // turning on verbose will enable much more detailed info
    // about individual thread operations as they happen
    private final boolean verbose = false;

    private final int customerId;

    public Customer(int numItems, int id) {
        this.customerId = id;

        // 0 for numItems param signifies random item count,
        // per the instructions
        if (numItems == 0) {
            Random r = new Random();
            this.numberOfItems = r.nextInt(1, itemLimit + 1);
        } else {
            this.numberOfItems = numItems;
        }
    }

    @Override
    public HashMap<String, Long> call() {
        HashMap<String, Long> results = new HashMap<>();
        results.put("numItems", (long) numberOfItems );
        long startTime = System.currentTimeMillis();
        boolean hasRoom = false;
        while (!hasRoom) {
            try {
                hasRoom = Scenario.getDressingRooms().requestRoom();
            } catch (InterruptedException ignored) {}
        }
        long endTime = System.currentTimeMillis();
        results.put("waitingTime", endTime - startTime );
        if (verbose) {
            System.out.println( "Customer " + customerId + " wait time: " +
                    (endTime - startTime) + " minutes." );
        }
        startTime = System.currentTimeMillis();
        Random r = new Random();

        if (verbose) {
            System.out.println( "Customer " + customerId + " trying on " +
                    numberOfItems + " items." );
        }

        long st, et;
        for (int i = 0; i < numberOfItems; i++) {
            try {
                st = System.currentTimeMillis();
                int tryOnTime = r.nextInt(1, 4);
                if (verbose) {
                    System.out.println( "Customer " + customerId + " trying on item " +
                            (i + 1) + " for " + tryOnTime + " minutes." );
                }
                sleep(tryOnTime);
                et = System.currentTimeMillis();
                long tt = et - st;
                if (verbose) {
                    System.out.println( "Customer " + customerId + " tried on item " +
                            (i + 1) + " for " + tt + " minutes." );
                }
            } catch (InterruptedException e) {
                throw new RuntimeException( e );
            }
        }
        Scenario.getDressingRooms().leaveRoom();
        endTime = System.currentTimeMillis();
        results.put("usingTime", endTime - startTime );
        if (verbose) {
            System.out.println( "Customer " + customerId + " total time in room: " +
                    (endTime - startTime) + " minutes." );
        }
        return results;
    }
}
