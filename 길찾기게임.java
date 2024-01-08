import java.util.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        int len = nodeinfo.length;
        
        Tree tree = new Tree(nodeinfo);
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        
        tree.pre(tree.nodes[0], pre);
        tree.post(tree.nodes[0], post);
        
        return new int[][] {
            pre.stream().mapToInt(Integer::intValue).toArray(),
            post.stream().mapToInt(Integer::intValue).toArray()
        };
    }
    
    class Node {
        public int value;
        public int x;
        public int y;
        public Node[] child;
        
        Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.child = new Node[2];
        }
        
        public void set_Child(Node node) {
            if(this.x > node.x) {
                if(this.child[0] != null) {
                    this.child[0].set_Child(node);
                } else {
                    this.child[0] = node;
                }
            } else {
                if(this.child[1] != null) {
                    this.child[1].set_Child(node);
                } else {
                    this.child[1] = node;
                }
            }
        }
        
    }
    
    class Tree {
        public Node[] nodes;
        
        Tree(int[][] nodeinfo) {
            int len = nodeinfo.length;
            nodes = new Node[len];
            for(int i = 0; i < len; i++) {
                nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
            }
            Arrays.sort(nodes, (a, b) -> { return b.y - a.y; });
            
            construct_Tree();
        }
        
        private void construct_Tree() {
            Node root = nodes[0];
            for(int i = 1; i < nodes.length; i++) {
                root.set_Child(nodes[i]);
            }
        }
        
        private void pre(Node node, List<Integer> visits) {
            if(node == null) { return; }
            
            visits.add(node.value);
            
            pre(node.child[0], visits);
            pre(node.child[1], visits);
        }
        
        private void post(Node node, List<Integer> visits) {
            if(node == null) { return; }
            
            post(node.child[0], visits);
            post(node.child[1], visits);
            
            visits.add(node.value);
        }
    }
}