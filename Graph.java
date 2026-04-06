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

    // helper method for bubble sorting
    private void bubbleSort(List<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Swap list[j] and list[j+1]
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    // helper method for insertion sorting
    private void insertionSort(List<Integer> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            int key = list.get(i);
            int j = i - 1;
            // Move elements of list[0..i-1], that are greater than key, to one position ahead of their current position
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }

    // Helper method to get unique friends (removes duplicates)
    private List<Integer> getUniqueFriends(List<Integer> friends) {
        // Use LinkedHashSet to maintain order while removing duplicates
        Set<Integer> uniqueSet = new LinkedHashSet<>(friends);
        return new ArrayList<>(uniqueSet);
    }

    // [1] Display friend list
    public void getFriendList(int id) {
            if(id < 0 || id >= numAccounts) {
                // Error message for invalid ID
                System.out.println("Invalid account ID.");
            } else if (adjacencyList[id].isEmpty()) {
                // Message for no friends
                System.out.println("Account has no friends :(");
            } else {
                 // Get unique friends first (remove duplicates from the data file)
                List<Integer> friends = getUniqueFriends(adjacencyList[id]);
                int k = friends.size();
                
                // Sort based on list size
                if (k <= 10) {
                    bubbleSort(friends);
                } else if (k <= 50) {
                    insertionSort(friends);
                } else {
                    Collections.sort(friends);
                }
                
                System.out.println("Person " + id + " has " + k + " friends!");
                System.out.print("List of friends:");
                for (int friend : friends) {
                    System.out.print(" " + friend);
                }
                System.out.println();
            }
    }

    // [2] Display connection
    public void getConnection(int start, int end) {
        // Check that both IDs are valid (within range)
        if (start < 0 || start >= numAccounts) {
            System.out.println("Invalid account ID: " + start);
            return;
        }
        if (end < 0 || end >= numAccounts) {
            System.out.println("Invalid account ID: " + end);
            return;
        }
        
        // Check if user entered the same ID for both start and end
        if (start == end) {
            System.out.println("Start and end accounts are the same. Please enter two different accounts.");
            return;
        }
        
        // Perform BFS to find the shortest path from start to end
        boolean[] visited = new boolean[numAccounts];
        int[] previous = new int[numAccounts]; // To reconstruct the path
        
        // Initialize previous array with -1 (no parent)
        for (int i = 0; i < numAccounts; i++) {
            previous[i] = -1;
        }
        
        // BFS using a queue
        Queue<Integer> queue = new LinkedList<>();
        
        // Start BFS from 'start'
        visited[start] = true;
        queue.add(start);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            // If we reached the target, stop BFS
            if (current == end) {
                break;
            }
            
            // Explore all neighbors
            for (int neighbor : adjacencyList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    previous[neighbor] = current;
                    queue.add(neighbor);
                }
            }
        }
        
        // Check if a connection was found
        if (!visited[end]) {
            System.out.println("Cannot find a connection between " + start + " and " + end);
            return;
        }
        
        // Reconstruct the path from start to end
        ArrayList<Integer> path = new ArrayList<>();
        int current = end;
        while (current != -1) {
            path.add(current);
            current = previous[current];
        }
        
        // Print the connection path
        System.out.println("There is a connection from " + start + " to " + end + "!");
        
        // Print the connection path line by line
        for (int i = path.size() - 1; i > 0; i--) {
            int node = path.get(i);
            int next = path.get(i - 1);
            System.out.println(node + " is friends with " + next);
        }
    }
}
