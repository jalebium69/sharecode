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
