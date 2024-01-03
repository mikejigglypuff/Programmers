import java.util.*;

class Solution {
        //내 풀이
    private static int[][] dir = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
    
    public int solution(int[] arrows) {
        Map<List<Integer>, Dot> graph = new HashMap<>();
        Dot start = new Dot(0, 0);
        graph.put(start.get_Id(), start);
        int answer = 0;
        
        for(int i : arrows) {
            for(int j = 0; j < 2; j++) {
                int[] cur_dir = dir[i];
                List<Integer> id = start.get_Id();
                List<Integer> newId = Arrays.asList(id.get(0) + cur_dir[0], id.get(1) + cur_dir[1]);
                
                if(!graph.containsKey(newId)) {
                    graph.put(newId, new Dot(newId.get(0), newId.get(1)));
                } else if(!start.get_ConnectedDots().contains(newId)) {
                    answer++;
                }
                
                Dot newDot = graph.get(newId);
                start.add_ConnectedDots(newId);
                newDot.add_ConnectedDots(id);
                start = newDot;
            }
        }
        
        
        return answer;
    }
    
    class Dot {
        private int x;
        private int y;
        private List<Integer> id;
        private Set<List<Integer>> connectedDots;
        
        public Dot(int x, int y) { 
            this.x = x; this.y = y; 
            this.id = id();
            this.connectedDots = new HashSet<>();
        }
        
        public List<Integer> id() { return Arrays.asList(this.x, this.y); }
        
        public int get_X() { return this.x; }
        public int get_Y() { return this.y; }
        public List<Integer> get_Id() { return this.id; }
        public Set<List<Integer>> get_ConnectedDots() { return this.connectedDots; }

        public void add_ConnectedDots(List<Integer> id) { this.connectedDots.add(id); }
    }
    
    
    /*
    private static class Vertex {
        public final int x;
        public final int y;
        public final String id;
        public final Set<String> connectedVertices;
        
        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
            this.id = id(x, y);
            this.connectedVertices = new HashSet<>();
        }
        
        public static String id(int x, int y) {
            return String.format("(%d, %d)", x, y);
        }
    }
    
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public int solution(int[] arrows) {
        int count = 0;
        
        Map<String, Vertex> vertices = new HashMap<>();
        
        Vertex v = new Vertex(0, 0);
        vertices.put(v.id, v);
        for(int d : arrows) {
            for(int i = 0; i < 2; i++) {
                int x = v.x + dx[d];
                int y = v.y + dy[d];
                String id = Vertex.id(x, y);
                System.out.println(v.id + " " + id);
                
                if(!vertices.containsKey(id)) {
                    vertices.put(id, new Vertex(x, y));
                } else if(!v.connectedVertices.contains(id)) {
                    System.out.println(id + " : " + !v.connectedVertices.contains(id));
                    count++;
                }
                
                Vertex u = vertices.get(id);
                v.connectedVertices.add(u.id);
                u.connectedVertices.add(v.id);
                v = u;
            }
        }
        
        return count;
    }
    */
}