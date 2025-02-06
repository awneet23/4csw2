import java.util.*;

class Graph {
    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + source + " -> " + destination + ", weight=" + weight + ")";
        }
    }

    int vertices;
    List<List<Edge>> adjacencyList;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge(source, destination, weight));
        adjacencyList.get(destination).add(new Edge(destination, source, weight)); // For undirected graph
    }

    public void bfs(int startVertex) {
        Queue<Pair> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];

        queue.offer(new Pair(startVertex, String.valueOf(startVertex)));

        System.out.println("BFS Traversal:");
        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            if (visited[current.vertex]) {
                continue;
            }

            visited[current.vertex] = true;
            System.out.println(current.vertex + " via path: " + current.pathSoFar);

            List<Edge> neighbors = adjacencyList.get(current.vertex);
            for (int i = 0; i < neighbors.size(); i++) {
                Edge edge = neighbors.get(i);
                if (!visited[edge.destination]) {
                    queue.offer(new Pair(edge.destination, current.pathSoFar + " -> " + edge.destination));
                }
            }
        }
    }


    public void dfs(int startVertex) {
        Stack<Pair> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        stack.push(new Pair(startVertex, String.valueOf(startVertex))); // Use String.valueOf

        while (!stack.isEmpty()) {
            Pair current = stack.pop();

            if (visited[current.vertex]) {
                continue;
            }

            visited[current.vertex] = true;
            System.out.println(current.vertex + " via path: " + current.pathSoFar);

            // Get the neighbors *before* the loop
            List<Edge> neighbors = adjacencyList.get(current.vertex);

            // Push neighbors in *reverse* order for true DFS
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                Edge edge = neighbors.get(i);
                if (!visited[edge.destination]) {
                    stack.push(new Pair(edge.destination, current.pathSoFar + " -> " + edge.destination));
                }
            }
        }
    }


    // Helper class to track path
    static class Pair {
        int vertex;
        String pathSoFar;

        Pair(int vertex, String pathSoFar) {
            this.vertex = vertex;
            this.pathSoFar = pathSoFar;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int vertices = sc.nextInt();

        Graph graph = new Graph(vertices);

        System.out.println("Enter the number of edges:");
        int edges = sc.nextInt();

        System.out.println("Enter the edges (source, destination, weight):");
        for (int i = 0; i < edges; i++) {
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int weight = sc.nextInt();
            graph.addEdge(source, destination, weight);
        }

        System.out.println("\nGraph Adjacency List:");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + ": " + graph.adjacencyList.get(i));
        }

        System.out.println("\nEnter the starting vertex for BFS:");
        int startVertexBFS = sc.nextInt();
        graph.bfs(startVertexBFS);

        System.out.println("\nEnter the starting vertex for DFS:");
        int startVertexDFS = sc.nextInt();
        graph.dfs(startVertexDFS);


        sc.close();
    }
}