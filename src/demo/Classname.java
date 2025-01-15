package demo;

import java.util.ArrayList;
import java.util.Scanner;

public class Classname {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addInteger(integers, scanner);
                    break;
                case 2:
                    viewCount(integers);
                    break;
                case 3:
                    calculateSum(integers);
                    break;
                case 4:
                    findLargest(integers);
                    break;
                case 5:
                    displayAll(integers);
                    break;
                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nInteger Analysis Console Application");
        System.out.println("1. Add an integer");
        System.out.println("2. View the total count of integers");
        System.out.println("3. Calculate the sum of integers");
        System.out.println("4. Find the largest integer");
        System.out.println("5. Display all integers");
        System.out.println("6. Exit");
    }

    private static void addInteger(ArrayList<Integer> integers, Scanner scanner) {
        System.out.print("Enter an integer to add: ");
        try {
            int number = scanner.nextInt();
            integers.add(number);
            System.out.println(number + " added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next(); // Clear the invalid input
        }
    }

    private static void viewCount(ArrayList<Integer> integers) {
        System.out.println("Total count of integers: " + integers.size());
    }

    private static void calculateSum(ArrayList<Integer> integers) {
        int sum = 0;
        for (int num : integers) {
            sum += num;
        }
        System.out.println("Sum of integers: " + sum);
    }

    private static void findLargest(ArrayList<Integer> integers) {
        if (integers.isEmpty()) {
            System.out.println("No integers in the list.");
            return;
        }
        int largest = integers.get(0);
        for (int num : integers) {
            if (num > largest) {
                largest = num;
            }
        }
        System.out.println("Largest integer: " + largest);
    }

    private static void displayAll(ArrayList<Integer> integers) {
        if (integers.isEmpty()) {
            System.out.println("No integers to display.");
            return;
        }
        System.out.println("Integers: " + integers);
    }
}