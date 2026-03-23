import java.io.*;
import java.util.*;

/*
 * GraphLoader is a utility class that provides a method to load a graph from a file.
 */
public class GraphLoader {
    public static Graph loadGraphFromFile(String filename) {
        try (
            Scanner sc = new Scanner(new File(filename)) // Create a Scanner to read from the file given by filename
        ) { 
            // Scan the first two integers from the file
            int numAccounts = sc.nextInt(); // Represents the number of accounts in the graph
            int numFriendships = sc.nextInt(); // Represents the number of friendships (edges) in the graph

            // Create a new Graph object with the specified number of accounts
            Graph graph = new Graph(numAccounts);

            // Read the friendships from the file and add them to the graph
            for (int i = 0; i < numFriendships; i++) {
                int a = sc.nextInt(); // First person's ID number
                int b = sc.nextInt(); // Second person's ID number
                graph.addFriendship(a, b); // Add the friendship (edge) between accounts a and b in the graph
            }

            return graph; // Return the constructed graph after reading all friendships
        } catch (FileNotFoundException e) {
            // If the file is not found, print an error message and return null
            System.out.println("Error! File " + "'" + filename + "'" + " not found.");
            return null;
        }
    }
}