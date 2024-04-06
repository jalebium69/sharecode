import java.util.*;

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class Pair{
    TreeNode node;
    int hd;
    Pair(TreeNode n, int h){
        this.node = n;
        this.hd = h;
    }
}

public class Tree{


    private static TreeNode build(String[] s){
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;

        while(!queue.isEmpty() && index < s.length){
            TreeNode curr = queue.poll();
            if(!s[index].equals("N")){
                curr.left = new TreeNode(Integer.parseInt(s[index]));
                queue.offer(curr.left);
            }
            index++;

            if(!s[index].equals("N")){
                curr.right = new TreeNode(Integer.parseInt(s[index]));
                queue.offer(curr.right);
            }
            index++;
        }

        return root;
    }

    private static void printVerticalOrder(TreeNode root){
        TreeMap<Integer,List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root,0));
        while(!queue.isEmpty()){
            Pair curr = queue.poll();
            int hd = curr.hd;
            TreeNode currNode = curr.node;

            if(!map.containsKey(hd)){
                map.put(hd,new ArrayList<>());
            }

            map.get(hd).add(currNode.val);

            if(currNode.left != null){
                queue.offer(new Pair(currNode.left,hd-1));
            }

            if(currNode.right != null){
                queue.offer(new Pair(currNode.right,hd+1));
            }
        }

        for(List<Integer> L : map.values()){
            for(int num : L){
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }

    private static void printHorizontalOrder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int cs = queue.size();
            for(int i=0; i<cs; i++){
                TreeNode curr = queue.poll();
                System.out.print(curr.val + " ");
                if(curr.left != null){
                    queue.offer(curr.left);
                }
                if(curr.right != null){
                    queue.offer(curr.right);
                }
            }
            System.out.println();
        }
    }

    private static void printTopOrder(TreeNode root){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root,0));
        while(!queue.isEmpty()){
            Pair curr = queue.poll();
            int hd = curr.hd;
            TreeNode currNode = curr.node;

            if(!map.containsKey(hd)){
                map.put(hd,currNode.val);
            }


            if(currNode.left != null){
                queue.offer(new Pair(currNode.left,hd-1));
            }

            if(currNode.right != null){
                queue.offer(new Pair(currNode.right,hd+1));
            }
        }

        for(Integer L : map.values()){
            System.out.println(L);
        }
    }

    private static void printBottomOrder(TreeNode root){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(root,0));
        while(!queue.isEmpty()){
            Pair curr = queue.poll();
            int hd = curr.hd;
            TreeNode currNode = curr.node;

            
            map.put(hd,currNode.val);


            if(currNode.left != null){
                queue.offer(new Pair(currNode.left,hd-1));
            }

            if(currNode.right != null){
                queue.offer(new Pair(currNode.right,hd+1));
            }
        }

        for(Integer L : map.values()){
            System.out.println(L);
        }
    }

    private static void leftView(TreeNode root) {
        if (root == null)
            return;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        int currentLevel = -1;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int level = current.hd;

            if (level > currentLevel) {
                System.out.print(node.val + " ");
                currentLevel = level;
            }
            if (node.left != null)
                queue.offer(new Pair(node.left, level + 1));

            if (node.right != null)
                queue.offer(new Pair(node.right, level + 1));
        }
    }

    public static void rightView(TreeNode root) {
        if (root == null)
            return;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        int currentLevel = -1;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int level = current.hd;

            if (level > currentLevel) {
                System.out.print(node.val + " ");
                currentLevel = level;
            }
            
            if (node.right != null)
                queue.offer(new Pair(node.right, level + 1));

            if (node.left != null)
                queue.offer(new Pair(node.left, level + 1));

        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        TreeNode root = build(s);
        printVerticalOrder(root);
        printHorizontalOrder(root);
        printTopOrder(root);
        printBottomOrder(root);
        leftView(root);
        rightView(root);
        sc.close();
    }
}


















===========================================================================================================================




import java.util.*;

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class RecoverTheBST {

    static TreeNode FIN = null, SIN = null, prevNode = new TreeNode(Integer.MIN_VALUE);

    private static TreeNode build(String[] s){
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;

        while(!queue.isEmpty() && index < s.length){
            TreeNode curr = queue.poll();
            if(!s[index].equals("N")){
                curr.left = new TreeNode(Integer.parseInt(s[index]));
                queue.offer(curr.left);
            }
            index++;

            if(!s[index].equals("N")){
                curr.right = new TreeNode(Integer.parseInt(s[index]));
                queue.offer(curr.right);
            }
            index++;
        }

        return root;
    }

    private static void insertBSTNodes(TreeNode root,String s){
        if(s.equals("N")) return;

        int val = Integer.parseInt(s);

        if(root == null){
            root = new TreeNode(val);
        }else{
            if(val < root.val){
                if(root.left == null){
                    root.left = new TreeNode(val);
                }else{
                    insertBSTNodes(root.left, s);
                }
            }else{
                if(root.right == null){
                    root.right = new TreeNode(val);
                }else{
                    insertBSTNodes(root.right, s);
                }
            }
        }

    }

    private static void inorderBST(TreeNode root){
        if(root == null) return;
        inorderBST(root.left);
        System.out.print(root.val);
        inorderBST(root.right);
    }

    private static TreeNode buildBST(String[] s){
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));

        for(int i=1; i<s.length; i++){
            insertBSTNodes(root,s[i]);
        }

        return root;
    }

    private static void inorderRBST(TreeNode root){
        if(root == null) return;

        inorderRBST(root.left);

        if(FIN == null && root.val <= prevNode.val){
            FIN = prevNode;
        }else if(FIN != null && root.val <= prevNode.val){
            SIN = root;
        }
        prevNode = root;
        inorderRBST(root.right);


    }

    private static void recoverTheBST(TreeNode root){
        inorderRBST(root);

        int t = FIN.val;
        FIN.val = SIN.val;
        SIN.val = t;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split(" ");
        // TreeNode root = buildBST(s);
        // inorderBST(root);

        TreeNode wrongBSTRoot = build(s);
        System.out.println("Wrong BST Order: ");
        inorderBST(wrongBSTRoot);
        recoverTheBST(wrongBSTRoot);
        System.out.println("Right BST Order");
        inorderBST(wrongBSTRoot);
        sc.close();
    }
}



import java.util.*;

public class Graph {

    private static void DFS(List<List<Integer>> adjList, Boolean[] visited, int startNode){
        
        visited[startNode] = true;
        System.out.print(startNode + " ");

        List<Integer> n = adjList.get(startNode);
        for(int v : n){
            if(!visited[v]){
                DFS(adjList, visited, v);
            }
        }

    }

    private static void topSort(List<List<Integer>> adjList, int[] indeg){

        Queue<Integer> queue = new LinkedList<>();
        for(int i : indeg){
            if(i == 0){
                queue.offer(i);
            }
        }



        while(!queue.isEmpty()){
            int u = queue.poll();
            System.out.print(u+ " ");

            for(int i : adjList.get(u)){
                indeg[i]--;

                if(indeg[i] == 0){
                    queue.offer(i);
                }
            }
        }
    }

    private static void BFS(List<List<Integer>> adjList, Boolean[] visited, int startNode){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        visited[startNode] = true;

        while(!queue.isEmpty()){
            int curr = queue.poll();

            System.out.print(curr + " ");
            for(int n : adjList.get(curr)){
                if(!visited[n]){
                    visited[n] = true;
                    queue.offer(n);
                }
            }
        }
    }

    //private static void topSort(List<List<Integer>> adjList, )

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> adjList = new ArrayList<>();
        int i;
    
        for(i=0; i<n; i++){
            adjList.add(new ArrayList<>());
        }
    
        int e = sc.nextInt();
        for(i=0; i<e; i++){
            adjList.get(sc.nextInt()).add(sc.nextInt());
        }

        Boolean[] visited = new Boolean[n];
        for(i=0; i<n; i++){
            visited[i] = false;
        }
        
        DFS(adjList,visited,0);
        for(i=0; i<n; i++){
            visited[i] = false;
        }
        System.out.println();
        BFS(adjList, visited, 0);

        int[] indeg = new int[n];

        for(i=0; i<n; i++){
            for(int v : adjList.get(i)){
                indeg[v]++;
            }
        }
        System.out.println();

        topSort(adjList, indeg);
        sc.close();
    }
}


import java.util.*;

public class BellmanFord {
    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices, edges;
        List<Edge> edgeList;

        Graph(int vertices, int edges) {
            this.vertices = vertices;
            this.edges = edges;
            edgeList = new ArrayList<>();
        }

        void addEdge(int source, int destination, int weight) {
            edgeList.add(new Edge(source, destination, weight));
        }

        void bellmanFord(int source) {
            int[] distance = new int[vertices];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[source] = 0;

            // Relax all edges |V| - 1 times
            for (int i = 0; i < vertices - 1; i++) {
                for (Edge edge : edgeList) {
                    if (distance[edge.source] != Integer.MAX_VALUE &&
                        distance[edge.source] + edge.weight < distance[edge.destination]) {
                        distance[edge.destination] = distance[edge.source] + edge.weight;
                    }
                }
            }

            // Check for negative weight cycles
            for (Edge edge : edgeList) {
                if (distance[edge.source] != Integer.MAX_VALUE &&
                    distance[edge.source] + edge.weight < distance[edge.destination]) {
                    System.out.println("Graph contains negative weight cycle!");
                    return;
                }
            }

            // Print shortest distances
            System.out.println("Shortest distances from source " + source + ":");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Vertex " + i + ": " + distance[i]);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        int edges = 8;
        int source = 0;

        Graph graph = new Graph(vertices, edges);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        graph.bellmanFord(source);
    }
}








