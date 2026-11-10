package terra;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    // Queue implementation
    static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addElements();
                    break;

                case 2:
                    displayQueue();
                    break;

                case 3:
                    generatePermutations();
                    break;

                case 4:
                    generateCombinations();
                    break;

                case 5:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // TODO 1: Accept elements and add them to the Queue
    static void addElements() {
        System.out.print("How many elements to add: ");
        int numofelements = sc.nextInt();
        sc.nextLine(); 
        
        for(int i = 1; i <= numofelements; i++) {
            String input = "";
            

            while (input.isEmpty()) {
                System.out.print("Enter character " + i + ": ");
                input = sc.nextLine();
            }
            
            String charInput = String.valueOf(input.charAt(0));
            queue.add(charInput);
        }
        System.out.println("Successfully added " + numofelements + " elements.");
    }

    // TODO 2: Display all elements in the Queue
    static void displayQueue() {
        if(queue.isEmpty()) {
            System.out.println("No queue inside");
        } else {
             System.out.println("Queue Contents: " + queue);
        }
    }

    // TODO 3: Generate all possible permutations
    static void generatePermutations() {
        if(queue.isEmpty()) {
            System.out.println("The queue is empty!");
            return;
        }
        
        List<String> elements = new LinkedList<>(queue);
        int n = elements.size();
        
        System.out.print("Enter the elements to select (r): ");
        int r = sc.nextInt();
        sc.nextLine();
        
        if(r < 1 || r > n) {
            System.out.println("Invalid Input!");
            return;
        }
        
        System.out.println("\nGenerating possible arrangements (n=" + n + ", r=" + r + "):");
        
        Queue<String> workingQueue = new LinkedList<>();
        int totalPermCount = 0;
        
        for(String element : elements) {
            workingQueue.add(element);
        }
        
        while(!workingQueue.isEmpty()) {
            String current = workingQueue.remove();
            
            if(current.length() == r) {
                System.out.println(current);
                totalPermCount++;
            } else {
                for(String nextElement : elements) {
                    if(!current.contains(nextElement)) {
                        workingQueue.add(current + nextElement);
                    }
                }
            }
        }
        
        System.out.println("\nTotal number of permutations: " + totalPermCount);
    }

    // TODO 4: Generate all possible combinations
    static void generateCombinations() {
        if(queue.isEmpty()) {
            System.out.println("The queue is empty!");
            return;
        }
        
        List<String> elements = new LinkedList<>(queue);
        int n = elements.size();

        System.out.print("Enter the elements to select (r): ");
        int r = sc.nextInt();
        sc.nextLine();
        
        if(r < 1 || r > n) {
            System.out.println("Invalid Input!");
            return;
        }
        
        System.out.println("\nGenerating possible selections (n=" + n + ", r=" + r + "):");
        
        Queue<String> workingQueue = new LinkedList<>();
        Queue<Integer> indexQueue = new LinkedList<>();
        int totalCombCount = 0;
        
        for (int i = 0; i < n; i++) {
            workingQueue.add(elements.get(i));
            indexQueue.add(i);
        }
        
        while(!workingQueue.isEmpty()) {
            String current = workingQueue.remove();
            int lastIndex = indexQueue.remove();
            
            if(current.length() == r) {
                System.out.println(current);
                totalCombCount++;
            } else {
                for(int i = lastIndex + 1; i < n; i++) {
                     workingQueue.add(current + elements.get(i));
                     indexQueue.add(i);
                }
            }
        }

        System.out.println("\nTotal number of combinations: " + totalCombCount);
    }
}
