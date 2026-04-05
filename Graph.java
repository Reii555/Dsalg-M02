import java.util.*;

/*
 * Graph represents a social network graph where nodes are accounts and edges are friendships.
 * It provides methods to add friendships, display friend lists, and find connections between accounts.
 */
public class Graph {
    private int numAccounts; // Number of accounts (nodes)
    private ArrayList<Integer>[] adjacencyList; // Adjacency list to store friendships (edges)

    // Constructor: set up adjacency list
    @SuppressWarnings("unchecked") // Generic array creation warning is suppressed since we are creating an array of ArrayLists
    public Graph(int numAccounts) {
        this.numAccounts = numAccounts; // Initialize the number of accounts
        adjacencyList = new ArrayList[numAccounts]; // Create an array of ArrayLists to represent the adjacency list
        // Initialize each entry in the adjacency list with an empty ArrayList
        for (int i = 0; i < numAccounts; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    // Add friendship (bi-directional edge)
    // This method is used by GraphLoader to construct the graph from the input file
    public void addFriendship(int a, int b) {
        adjacencyList[a].add(b); // Add b to a's friend list
        adjacencyList[b].add(a); // Add a to b's friend list
    }

    // [1] Display friend list
    public void getFriendList(int id) {
        // Implemntation here...
            // - Check that the ID is valid (within range) (with error message if not)
            // - Consider the case where the person has no friends (with message if so)
            // - Check Sample Run on Page 5 of the specs for the expected output format

            if(id < 0 || id >= numAccounts) {
                // Error message for invalid ID
                System.out.println("Invalid account ID.");
            } else if (adjacencyList[id].isEmpty()) {
                // Message for no friends
                System.out.println("Account has no friends :(");
            } else {
                // Display friend list in the expected format
                System.out.println("Person " + id + " has " + adjacencyList[id].size() + " friends!");
                
                System.out.print("List of friends:");

                for (int friend : adjacencyList[id]) {
                    System.out.print(" " + friend); // Print each friend's ID
                }
                System.out.println();
            }
    }

    // [2] Display connection
    public void getConnection(int start, int end) {
        // Implementation here...
            // - Check that both IDs are valid (within range) (with error message if not)
            // - Check in case the user enters the same ID for both start and end (with error message if so)
            // - Check Sample Run on Page 5 of the specs for the expected output format
    }

}
