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






