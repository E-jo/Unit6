package org.unitsix;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;

public class Scenario {
    private final ArrayList<Customer> customers;
    private static DressingRooms dressingRooms;

    public Scenario(int numRooms, int numCustomer) {
        customers = new ArrayList<>();
        dressingRooms = new DressingRooms(numRooms);
        for (int i = 0; i < numCustomer; i++) {
            Customer customer = new Customer( 0, i + 1 );
            customers.add(customer);
        }
    }

    public void runScenario() throws InterruptedException, ExecutionException {
        if (customers.isEmpty()) {
            System.out.println("No customers in line");
            return;
        }

        int numCustomers = customers.size();
        long startTime = System.currentTimeMillis();

        ExecutorService executorService =
                Executors.newFixedThreadPool( numCustomers );
        List<Future<HashMap<String, Long>>> results =
                executorService.invokeAll( customers );

        executorService.shutdown();

        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;

        long totalWaitingTime = 0, totalUsingTime = 0, totalItems = 0,
        maxWaitingTime = 0, maxUsingTime = 0;

        for (Future<HashMap<String, Long>> future : results) {
            HashMap<String, Long> result = future.get();
            long waitingTime = result.getOrDefault("waitingTime", 0L );
            totalWaitingTime += waitingTime;
            maxWaitingTime = Math.max(maxWaitingTime, waitingTime);
            long usingTime = result.getOrDefault("usingTime", 0L );
            totalUsingTime += usingTime;
            maxUsingTime = Math.max(maxUsingTime, usingTime);
            totalItems += result.getOrDefault( "numItems", 0L );
        }

        double avgWaitingTime = totalWaitingTime / (double) numCustomers;
        double avgUsingTime = totalUsingTime / (double) numCustomers;
        double avgItems = totalItems / (double) numCustomers;

        DecimalFormat decimalFormat = new DecimalFormat("##.00");

        System.out.println("\nLongest waiting time: "
                + maxWaitingTime + " minutes");
        System.out.println("Longest using time: "
                + maxUsingTime + " minutes");

        System.out.println("Average waiting time: "
                + decimalFormat.format(avgWaitingTime) + " minutes");
        System.out.println("Average using time: "
                + decimalFormat.format(avgUsingTime) + " minutes");
        System.out.println("Average item count: "
                + decimalFormat.format(avgItems));

        System.out.println("Total simulated runtime: "
                + runTime + " minutes\n");
    }

    public static DressingRooms getDressingRooms() {
        return dressingRooms;
    }

}
