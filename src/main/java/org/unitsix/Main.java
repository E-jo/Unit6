package org.unitsix;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        /*
        String cont = "y";
        Scanner sc = new Scanner(System.in);
        while (cont.equalsIgnoreCase( "y" )) {
            System.out.print("Number of rooms: ");
            int numRooms = sc.nextInt();
            System.out.print("Number of customers: ");
            int numCustomers = sc.nextInt();
            runHelper( numRooms, numCustomers );
            System.out.print("Continue (y/n): ");
            cont = sc.next();
        }

         */

        scenario01();
        scenario02();
        scenario03();
        scenario04();
        scenario05();
        scenario06();
        scenario07();
        scenario08();
        scenario09();
        scenario10();
        scenario11();
        scenario12();
        scenario13();
        scenario14();
        scenario15();
    }

    private static void runHelper(int numRooms, int numCustomers) {
        System.out.println("\nRooms: " + numRooms + "\nCustomers: " + numCustomers);
        Scenario scenario = new Scenario( numRooms, numCustomers );
        try {
            scenario.runScenario();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException( e );
        }
    }

    private static void scenario01() {
        runHelper( 1, 10 );
    }

    private static void scenario02() {
        runHelper( 1, 20 );
    }

    private static void scenario03() {
        runHelper( 1, 30 );
    }

    private static void scenario04() {
        runHelper( 2, 10 );
    }

    private static void scenario05() {
        runHelper( 2, 20 );
    }

    private static void scenario06() {
        runHelper( 2, 30 );
    }

    private static void scenario07() {
        runHelper( 3, 10 );
    }

    private static void scenario08() {
        runHelper( 3, 20 );
    }

    private static void scenario09() {
        runHelper( 3, 30 );
    }

    private static void scenario10() {
        runHelper( 4, 10 );
    }

    private static void scenario11() {
        runHelper( 4, 20 );
    }

    private static void scenario12() {
        runHelper( 4, 30 );
    }

    private static void scenario13() {
        runHelper( 5, 10 );

    }

    private static void scenario14() {
        runHelper( 5, 20 );

    }

    private static void scenario15() {
        runHelper( 5, 30 );
    }

}