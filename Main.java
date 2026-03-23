import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner object to read user input

        // Prompt the user to enter the file path for the graph data
        System.out.print("Input file path: ");
        String filename = sc.nextLine();

        // Attempt to load the graph from the specified file
        Graph graph = GraphLoader.loadGraphFromFile(filename);
        if (graph == null) {
            System.out.println("Failed to load graph. Exiting...");
            return;
        } else {
            System.out.println("Graph loaded!");
        }

        boolean running = true; // Controls the main menu loop
        while (running) {
            System.out.println();
            System.out.println("MAIN MENU");
            System.out.println("[1] Get friend list");
            System.out.println("[2] Get connection");
            System.out.println("[3] Exit");
            System.out.println();            
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1; // Set to an invalid choice to trigger the error message
            }

            if (choice == 1) { // Display friend list
                System.out.println();
                System.out.print("Enter ID of person: ");
                int id = Integer.parseInt(sc.nextLine());
                graph.getFriendList(id);
            } else if (choice == 2) { // Display connection
                System.out.println();
                System.out.print("Enter ID of first person: ");
                int id1 = Integer.parseInt(sc.nextLine());
                System.out.print("Enter ID of second person: ");
                int id2 = Integer.parseInt(sc.nextLine());
                graph.getConnection(id1, id2);
            } else if (choice == 3) { // Exit the program
                System.out.println("Exiting program...");
                running = false; // Exit the loop and end the program
            } else if (choice != -1) {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}