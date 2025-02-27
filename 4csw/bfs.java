import java.util.*;

public class WeightedGraphTraversals {
    // Class to represent an edge with source, destination vertices and weight
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of vertices
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        // Create an adjacency list representation of the weighted graph
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Get the number of edges
        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        // Get the edges with weights
        System.out.println("Enter the edges (format: source destination weight):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();

            // Validate the vertices
            if (source < 0 || source >= vertices || destination < 0 || destination >= vertices) {
                System.out.println("Invalid edge: (" + source + ", " + destination + "). Skipping...");
                continue;
            }

            // Add edge to the adjacency list (for undirected graph)
            graph.get(source).add(new Edge(source, destination, weight));
            graph.get(destination).add(new Edge(destination, source, weight)); // Remove this line for directed graph
        }

        // Get the starting vertex
        System.out.print("Enter the starting vertex: ");
        int startVertex = scanner.nextInt();

        // Perform BFS, Recursive DFS, and Iterative DFS
        System.out.println("\nBFS Traversal:");
        bfs(graph, startVertex, vertices);

        System.out.println("\nRecursive DFS Traversal:");
        recursiveDFS(graph, startVertex, vertices);

        System.out.println("\nIterative DFS Traversal:");
        iterativeDFS(graph, startVertex, vertices);

        scanner.close();
    }

    // BFS Implementation
    public static void bfs(ArrayList<ArrayList<Edge>> graph, int startVertex, int vertices) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];
        visited[startVertex] = true;
        queue.add(startVertex);
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1); // Initialize all parents to -1

        // Array to store the distance (sum of weights) from start to each vertex
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE); // Initialize all distances to infinity
        distance[startVertex] = 0; // Distance to starting vertex is 0

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            ArrayList<Edge> neighbors = graph.get(currentVertex);
            for (Edge edge : neighbors) {
                int neighbor = edge.destination;
                int weight = edge.weight;
                if (!visited[neighbor]) {
                    // Mark neighbor as visited
                    visited[neighbor] = true;

                    // Store the parent (to reconstruct the path later)
                    parent[neighbor] = currentVertex;

                    // Calculate and store the distance
                    distance[neighbor] = distance[currentVertex] + weight;

                    // Enqueue the neighbor
                    queue.add(neighbor);
                }
            }
        }

        // Print paths to all vertices
        printPaths(parent, vertices, startVertex, distance);
    }

    // Recursive DFS Implementation
    public static void recursiveDFS(ArrayList<ArrayList<Edge>> graph, int startVertex, int vertices) {
        boolean[] visited = new boolean[vertices];
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1); // Initialize all parents to -1
        
        dfsHelper(graph, startVertex, visited, parent);

        System.out.println("\nPaths from Recursive DFS:");
        printPaths(parent, vertices, startVertex, null); // Distance not calculated for DFS
    }

    private static void dfsHelper(ArrayList<ArrayList<Edge>> graph, int vertex, boolean[] visited, int[] parent) {
        visited[vertex] = true;
        for (Edge edge : graph.get(vertex)) {
            if (!visited[edge.destination]) {
                parent[edge.destination] = vertex;
                dfsHelper(graph, edge.destination, visited, parent);
            }
        }
    }

    // Iterative DFS Implementation
    public static void iterativeDFS(ArrayList<ArrayList<Edge>> graph, int startVertex, int vertices) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1); // Initialize all parents to -1

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();

            if (!visited[currentVertex]) {
                visited[currentVertex] = true;

                for (Edge edge : graph.get(currentVertex)) {
                    if (!visited[edge.destination]) {
                        parent[edge.destination] = currentVertex;
                        stack.push(edge.destination);
                    }
                }
            }
        }

        System.out.println("\nPaths from Iterative DFS:");
        printPaths(parent, vertices, startVertex, null); // Distance not calculated for DFS
    }

    // Utility to print paths
    static void printPaths(int[] parent, int vertices, int startVertex, int[] distance) {
        for (int i = 0; i < vertices; i++) {
            if (i != startVertex) {
                System.out.print("Path from " + startVertex + " to " + i + " is: ");
                printPath(parent, i);
                if (distance != null) {
                    System.out.println(" | Distance: " + distance[i]);
                } else {
                    System.out.println();
                }
            }
        }
    }

    static void printPath(int[] parent, int vertex) {
        if (vertex == -1) return;
        printPath(parent, parent[vertex]);
        System.out.print(vertex + " ");
    }
}
