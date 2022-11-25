//Number of Islands
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    help(grid, i, j, visit);
                }
            }
        }
        return count;
    }
    public void help(char[][] grid, int sr, int sc, boolean[][] visit) {
        if (sr < 0 || sc < 0 || sr >= grid.length || sc >= grid[0].length || grid[sr][sc] == '0') return;
        grid[sr][sc] = '0';
        help(grid, sr + 1, sc, visit);
        help(grid, sr, sc + 1, visit);
        help(grid, sr - 1, sc, visit);
        help(grid, sr, sc - 1, visit);
    }
}
//number of enclaves
//bas boundery ttouch na ho
class Solution {
    int l = 0;
    boolean b=true;
    public int numEnclaves(int[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    l = 0;
                    b=true;
                    help(grid, i, j);
                    if(b)
                    count += l;
                }
            }
        }
        return count;
    }

    public void help(int[][] grid, int sr, int sc) {
        if (sr < 0 || sc < 0 || sr >= grid.length || sc >= grid[0].length) {
           b=false;

            return;
        }
        if(grid[sr][sc] == 0){
            return;
        }
        grid[sr][sc] = 0;
        l++;
        help(grid, sr + 1, sc);
        help(grid, sr, sc + 1);
        help(grid, sr - 1, sc);
        help(grid, sr, sc - 1);
    }
}

// tc v+c
//rooten orange
class Solution {

    public class pair {
        int x;
        int y;
        int t;

        pair(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public int orangesRotting(int[][] grid) {
        return help(grid);
    }

    public int help(int[][] grid) {
        int fresh = 0;
        LinkedList<pair> list = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                }
                if (grid[i][j] == 2) {
                    list.addLast(new pair(i, j, 0));
                }
            }
        }
        int time = 0;
        while (list.size() > 0) {
            pair rem = list.removeFirst();
            if (rem.x < 0 || rem.y < 0 || rem.x >= grid.length || rem.y >= grid[0].length || grid[rem.x][rem.y] == -1 || grid[rem.x][rem.y] == 0) {
                continue;
            }

            if (rem.t > time) {
                time = rem.t;
            }
            if (grid[rem.x][rem.y] == 1) {
                fresh--;
            }
            grid[rem.x][rem.y] = -1;
            list.addLast(new pair(rem.x + 1, rem.y, rem.t + 1));
            list.addLast(new pair(rem.x, rem.y + 1, rem.t + 1));
            list.addLast(new pair(rem.x - 1, rem.y, rem.t + 1));
            list.addLast(new pair(rem.x, rem.y - 1, rem.t + 1));
        }
        return fresh == 0 ? time : -1;
    }
}
//number of distict island
public static StringBuilder psf = new StringBuilder();
  public static int numDistinctIslands(int[][] grid) {
      int count = 0;
        HashSet<String>set=new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                psf = new StringBuilder();
                if (grid[i][j] == 1) {
                    psf.append('s');
                    help(grid, i, j);
                    set.add(psf.toString());
                    
                    
                }
            }
        }
       return set.size();
  }
    public static void help(int[][] grid, int sr, int sc) {
        if (sr < 0 || sc < 0 || sr >= grid.length || sc >= grid[0].length) {
            return;
        }
        if(grid[sr][sc] == 0){
            return;
        }
        grid[sr][sc] = 0;
        
        psf.append('D');
        help(grid, sr + 1, sc);
        psf.append('R');
        help(grid, sr, sc + 1);
        psf.append('L');
        help(grid, sr - 1, sc);
        psf.append('U');
        help(grid, sr, sc - 1);
        psf.delete(psf.length()-1,psf.length());
        
    }

}
//buses
//phale hum ki 0 to routes.length tak har point pe kon kon si bus ati hai
//fir use hashmap of integer ans hashset me put 
//boi normal bfs
class Solution {
    public class pair{
        int bus;
        int count;
        pair(int bus,int count){
            this.bus=bus;
            this.count=count;
        }
    }
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(target==source)return 0;
        HashMap<Integer,HashSet<Integer>>map=new HashMap<>();
        for(int i=0;i<routes.length;i++)
        {
            for(int stop:routes[i]){
                if(map.containsKey(stop)==false){
                    map.put(stop,new HashSet<>());
                }
                map.get(stop).add(i);
            }
        }
        LinkedList<pair>list=new LinkedList<>();
        for(int stop:map.get(source)){
            list.addLast(new pair(stop,1));
        }
        boolean[]visit=new boolean[routes.length];
        while(list.size()>0){
            pair rem=list.removeFirst();
            if(visit[rem.bus]==true)
            {
                continue;
            }
            visit[rem.bus]=true;
            for(int stop:routes[rem.bus])
            {
                if(stop==target)
                {
                    return rem.count;
                }
            }
            for(int stop:routes[rem.bus]){
                for(int bus:map.get(stop)){
                    if(visit[bus]==false){
                    list.addLast(new pair(bus,rem.count+1));
                    }
                }
            }
            
        }
        return -1;
    }
}
//Is Graph Bipartite?
class Solution {
    public class pair {
        int v;
        int colour;

        pair(int v, int colour) {
            this.v = v;
            this.colour = colour;
        }
    }
    public boolean isBipartite(int[][] graph) {
        int[] visit = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (visit[i] == 0) {
                boolean b = bfs(graph, visit, i);
                if (b == false) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean bfs(int[][] graph, int[] visit, int v) {
        LinkedList<pair> list = new LinkedList<>();
        list.addLast(new pair(v, 1));
        while (list.size() > 0) {
            pair rem = list.removeFirst();
            if (visit[rem.v] != 0) {
                int old = visit[rem.v];
                int n = rem.colour;
                if (old != n) {
                    return false;
                }
            }
            visit[rem.v] = rem.colour;
            for (int nbs : graph[rem.v]) {
                if (visit[nbs] == 0) list.addLast(new pair(nbs, rem.colour * -1));
            }
        }
        return true;
    }
}
//01 Matrix
class Solution {
    public class pair{
        int level;
        int i;
        int j;
        pair(int level,int i,int j)
        {
            this.level=level;
            this.i=i;
            this.j=j;
        }
    }
    public int[][] updateMatrix(int[][] mat) {
        LinkedList<pair>list=new LinkedList<>();
        int[][]arr=new int[mat.length][mat[0].length];
        boolean[][]visit=new boolean[mat.length][mat[0].length];
        for(int i=0;i<mat.length;i++)
        {
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==0)
                list.addLast(new pair(0,i,j));
            }
        }
        while(list.size()>0)
        {
            pair rem=list.removeFirst();
            if(rem.i<0 || rem.j<0 || rem.i>=mat.length || rem.j>=mat[0].length||visit[rem.i][rem.j]==true)
            {
                continue;
            }
            visit[rem.i][rem.j]=true;
            arr[rem.i][rem.j]=rem.level;
            list.addLast(new pair(rem.level+1,rem.i-1,rem.j));
            list.addLast(new pair(rem.level+1,rem.i,rem.j-1));
            list.addLast(new pair(rem.level+1,rem.i+1,rem.j));
            list.addLast(new pair(rem.level+1,rem.i,rem.j+1));
        }
        return arr;
    }
}
//As Far from Land as Possible
//boi 1 se max distance wala zero
class Solution {
    int max = 0;
    public class pair {
        int level;
        int i;
        int j;

        pair(int level, int i, int j) {
            this.level = level;
            this.i = i;
            this.j = j;
        }
    }
    public int maxDistance(int[][] mat) {
        LinkedList<pair> list = new LinkedList<>();
        int[][] arr = new int[mat.length][mat[0].length];
        boolean[][] visit = new boolean[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) list.addLast(new pair(0, i, j));
            }
        }
        while (list.size() > 0) {
            pair rem = list.removeFirst();
            if (rem.i < 0 || rem.j < 0 || rem.i >= mat.length || rem.j >= mat[0].length || visit[rem.i][rem.j] == true) {
                continue;
            }
            visit[rem.i][rem.j] = true;
            arr[rem.i][rem.j] = rem.level;
            max = Math.max(max, rem.level);
            list.addLast(new pair(rem.level + 1, rem.i - 1, rem.j));
            list.addLast(new pair(rem.level + 1, rem.i, rem.j - 1));
            list.addLast(new pair(rem.level + 1, rem.i + 1, rem.j));
            list.addLast(new pair(rem.level + 1, rem.i, rem.j + 1));
        }
        return max == 0 ? -1 : max;
    }
}
//dakrtra
//https://pepcoding.com/resources/online-java-foundation/graphs/shortest-path-in-weights-official/ojquestion
public static class pair implements Comparable<pair>{
    int src;
    String path;
    int w;
    pair(int src,String path,int w)
    {
        this.src=src;
        this.path=path;
        this.w=w;
    }
    public int compareTo(pair o)
    {
        return this.w-o.w;
    }
    
 }
PriorityQueue<pair> q = new PriorityQueue<>();
      boolean[]visit=new boolean[vtces];
      q.add(new pair(src,src+"",0));
      while(q.size()>0)
      {
          pair rem=q.remove();
          if(visit[rem.src]==true)
          {
              continue;
          }
          visit[rem.src]=true;
          //1 via 01 @ 10
          System.out.println(rem.src + " via " + rem.path + " @ " + rem.w);
          for(Edge e:graph[rem.src])
          {
              if(visit[e.nbr]==false)
              q.add(new pair(e.nbr,rem.path+e.nbr,rem.w+e.wt));
          }
      }
    }
//778. Swim in Rising Water
//dekh wali baat min time me thik or normal bfs level wise sabko karta pr is me choose bhi hai konsa phale nickale
//is lia priority queue wala
//https://leetcode.com/problems/swim-in-rising-water/
class Solution {
    public class pair implements Comparable<pair> {
        int i;
        int j;
        int w;
        pair(int src, int j, int w) {
            this.i = i;
            this.j = j;
            this.w = w;
        }

        public int compareTo(pair o) {
            return this.w - o.w;
        }
    }
    public int swimInWater(int[][] grid) {
        PriorityQueue<pair> q = new PriorityQueue<>();
        boolean[][] visit = new boolean[grid.length][grid[0].length];
        q.add(new pair(0, 0, grid[0][0]));
        while (q.size() > 0) {
            pair rem = q.remove();

            if (visit[rem.i][rem.j] == true) {
                continue;
            }
            visit[rem.i][rem.j] = true;
            if (rem.i == grid.length - 1 && rem.j == grid[0].length - 1) {
                return rem.w;
            }
            help(rem.i - 1, rem.j, rem.w, visit, grid, q);
            help(rem.i, rem.j - 1, rem.w, visit, grid, q);
            help(rem.i + 1, rem.j, rem.w, visit, grid, q);
            help(rem.i, rem.j + 1, rem.w, visit, grid, q);
        }
        return -1;
    }
    public void help(int i, int j, int ot, boolean[][] visit, int[][] grid, PriorityQueue<pair> q) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visit[i][j] == true) {
            return;
        }
        q.add(new pair(i, j, Math.max(ot, grid[i][j])));
    }

}
//prince
//is me ye path jitna margi bada ho bas wt kaam lage
//https://leetcode.com/problems/coloring-a-border/
public static class pair implements Comparable<pair>{
    int src;
    String path;
    int w;
    pair(int src,String path,int w)
    {
        this.src=src;
        this.path=path;
        this.w=w;
    }
    public int compareTo(pair o)
    {
        return this.w-o.w;
    }
    
 }
PriorityQueue<pair> q = new PriorityQueue<>();
      boolean[]visit=new boolean[vtces];
      q.add(new pair(0,-1+"",0));
      while(q.size()>0)
      {
          pair rem=q.remove();
          if(visit[rem.src]==true)
          {
              continue;
          }
          visit[rem.src]=true;
          //1 via 01 @ 10
          if(rem.path!="-1")
          System.out.println("["+rem.src + "-" + rem.path + "@" + rem.w+"]");
          for(Edge e:graph[rem.src])
          {
              if(visit[e.nbr]==false)
              q.add(new pair(e.nbr,e.src+"",e.wt));
          }
      }
      //Coloring A Border
      //bas ak colour dia hai uska bondery ke colour se change karna
      class Solution {
        public int[][] colorBorder(int[][] grid, int row, int col, int color) {
            boolean[][] visit = new boolean[grid.length][grid[0].length];
            help(grid, row, col, grid[row][col], color, visit);
    
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] < 0) grid[i][j] = color;
                }
            }
    
            return grid;
        }
    
        public void help(int[][] grid, int row, int col, int selfcolor, int color, boolean[][] visit) {
            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || visit[row][col] || grid[row][col] != selfcolor) {
                return;
            }
    
            grid[row][col] = -grid[row][col];
            visit[row][col] = true;
    
            help(grid, row - 1, col, selfcolor, color, visit);
            help(grid, row, col - 1, selfcolor, color, visit);
            help(grid, row + 1, col, selfcolor, color, visit);
            help(grid, row, col + 1, selfcolor, color, visit);
    
            if (
                row > 0 &&
                col > 0 &&
                row < grid.length - 1 &&
                col < grid[0].length - 1 &&
                Math.abs(grid[row - 1][col]) == selfcolor &&
                Math.abs(grid[row + 1][col]) == selfcolor &&
                Math.abs(grid[row][col + 1]) == selfcolor &&
                Math.abs(grid[row][col - 1]) == selfcolor
            ) {
                grid[row][col] = -grid[row][col];
            }
        }
    }
    //bfs
    class Solution {
        public class pair {
            int i;
            int j;
            boolean border;
    
            pair(int i, int j) {
                this.i = i;
                this.j = j;
            }
        }
    
        public int[][] colorBorder(int[][] grid, int row, int col, int color) {
            boolean[][] visit = new boolean[grid.length][grid[0].length];
            //help(grid, row, col, grid[row][col], color, visit);
    
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] < 0) grid[i][j] = color;
                }
            }
            int selfcolour = grid[row][col];
            LinkedList<pair> list = new LinkedList<>();
            ArrayList<pair> al = new ArrayList<>();
            pair ans = new pair(row, col);
            ans.border = check(grid, row, col);
            list.addLast(ans);
            while (list.size() > 0) {
                pair rem = list.removeFirst();
                if (visit[rem.i][rem.j] == true) {
                    continue;
                }
    
                al.add(rem);
                visit[rem.i][rem.j] = true;
                help(grid, rem.i - 1, rem.j, selfcolour, color, visit, list);
                help(grid, rem.i, rem.j - 1, selfcolour, color, visit, list);
                help(grid, rem.i + 1, rem.j, selfcolour, color, visit, list);
                help(grid, rem.i, rem.j + 1, selfcolour, color, visit, list);
            }
            for (pair p : al) {
                if (p.border == true) {
                    grid[p.i][p.j] = color;
                }
            }
            return grid;
        }
    
        public void help(int[][] grid, int row, int col, int selfcolor, int color, boolean[][] visit, LinkedList<pair> list) {
            if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || visit[row][col] || grid[row][col] != selfcolor) {
                return;
            }
            pair ans = new pair(row, col);
            ans.border = check(grid, row, col);
            list.addLast(ans);
        }
    
        public boolean check(int[][] grid, int row, int col) {
            if (row == 0) {
                return true;
            } else if (col == 0) {
                return true;
            } else if (row == grid.length - 1) {
                return true;
            } else if (col == grid[0].length - 1) {
                return true;
            } else {
                int ol = grid[row][col];
                if (grid[row - 1][col] != ol) {
                    return true;
                }
                if (grid[row + 1][col] != ol) {
                    return true;
                }
                if (grid[row][col - 1] != ol) {
                    return true;
                }
                if (grid[row][col + 1] != ol) {
                    return true;
                }
            }
            return false;
        }
    }
//Strongly Connected Components (Kosaraju's Algo) 
//phale dfs
//stack me post order
//trasnpose
//new graph se dfs or count kkar lo
//https://practice.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1/
    public int kosaraju(int v, ArrayList<ArrayList<Integer>> graph)
    {
     LinkedList<Integer>stack=new LinkedList<>();
     boolean []visit=new boolean[v];
     for(int i=0;i<v;i++)
     {
         if(!visit[i]){
             dfs(i,graph,visit,stack);
         }
     }
     ArrayList<ArrayList<Integer>>ngraph=new ArrayList<>();
     for(int i=0;i<v;i++)
     {
         ngraph.add(new ArrayList<>());
     }
      for(int i=0;i<v;i++)
     {
         for(int nbr:graph.get(i)){
           ngraph.get(nbr).add(i);
         }
     }
     visit=new boolean[v];
     int ans=0;
     while(stack.size()>0){
         int rem=stack.removeFirst();
         if(!visit[rem]){
            
             dfs1(rem,ngraph,visit);
             ans++;
         }
     }
     return ans;
    }
    public static void dfs(int src,ArrayList<ArrayList<Integer>>graph,boolean[]visit,LinkedList<Integer>stack) {
     visit[src]=true;
     for(int v:graph.get(src))
     {
         if(!visit[v]){
             dfs(v,graph,visit,stack);
         }
     }
     stack.addFirst(src);
  }
  public static void dfs1(int src,ArrayList<ArrayList<Integer>>graph,boolean[]visit) {
     visit[src]=true;
     for(int v:graph.get(src))
     {
         if(!visit[v]){
             dfs1(v,graph,visit);
         }
     }
  }
  //Mother Vertex
  class Solution
{
    //Function to find a Mother Vertex in the Graph.
    
    public int findMotherVertex(int V, ArrayList<ArrayList<Integer>>adj)
    {
       boolean[]visit=new boolean[V];
       int ans=-1;
       for(int i=0;i<V;i++)
       {
         if(visit[i]==false){
         dfs1(i,adj,visit);
         ans=i;
         }
           
       }
       Arrays.fill(visit,false);
       dfs1(ans,adj,visit);
       for(int i=0;i<visit.length;i++)
       {
         if(visit[i]==false)
         {
             return -1;
         }
           
       }
       return ans;
       
    }
  
  public  void dfs1(int src,ArrayList<ArrayList<Integer>>graph,boolean[]visit) {
     visit[src]=true;   
    
     for(int v:graph.get(src))
     {
         if(!visit[v]){
             dfs1(v,graph,visit);
         }
     }
     
  }
}
//toplogical sort
// most imp chiz ye directed graph ke lia hi hota hai and cycle  bhi nhi honi chia kuki fir cycle me pta nhi chale ga ki phale kon aye ga cycle me ghumte rhe ge
//boi graph hai or line wise vertix ai ge like agar a ke baad b to b ana chia or starting ka bande ko sabse badi preority
//or ye jab lagana jab ordering and dependency ho
//mtlb mere nbr se me phale or mere nbr ke nbr se mere nbr phale
LinkedList<Integer>stack=new LinkedList<>();
      boolean[] visited = new boolean[v];
      for(int i = 0; i < v; i++){
         if(visited[i] == false){
            topological(graph, i, visited, stack);
         }
      }
       while(stack.size() > 0){
         System.out.println(stack.removeFirst());
      }
   }
  public static void topological(ArrayList<Edge>[] graph, int src, boolean[] visited, LinkedList<Integer>st) {
      visited[src] = true;
      for (Edge e : graph[src]) {
         if (!visited[e.nbr]) {
            topological(graph, e.nbr, visited, st);
         }
      }
      st.push(src);
   }
   
   //Kahn's Algorithm | Advanced Graphs
  // bas ye itertive hai toplogical sort ka
  //ye cycle ke bhi bta data mtlb ans always sai data hai
  class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> graph) 
    {
       int[]count=new int[V];
       int[]ans=new int[V];
       for(int i=0;i<V;i++)
       {
           for(int nbr:graph.get(i)){
              count[nbr]++; 
           }
       }
       LinkedList<Integer>queue=new LinkedList<>();
       for(int i=0;i<count.length;i++)
       {
           if(count[i]==0)
           {
               queue.addLast(i);
           }
       }
       int idx=0;
       while(queue.size()>0){
           int rem=queue.removeFirst();
           ans[idx]=rem;
           idx++;
           for(int nbr:graph.get(rem)){
               count[nbr]--;
               if(count[nbr]==0)
               {
                   queue.add(nbr);
               }
           }
       }
       if(idx==V){
           return ans;
       }
       return ans=new int[V];
    }
}
//Alien Dictionary
//char se int se easy ho jai ga
//boi hai ki string arrr hai us me string compare or jo phale word not
//equal unhe graph dal ke break ase sab string arr me karo
//or fir boi toplogical sort kar do
class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        ArrayList<Integer>[]graph=(ArrayList<Integer>[])new ArrayList[K];
        for(int i=0;i<K;i++)
        {
            graph[i]=new ArrayList<>();
        }
        int idx=0;
        for(int i=0;i<dict.length-1;i++)
        {
            for(int j=0;j<Math.min(dict[i].length(),dict[i+1].length());j++){
                char ch1=dict[i].charAt(j);
                char ch2=dict[i+1].charAt(j);
                if(ch1!=ch2)
                {
                    graph[ch1-'a'].add(ch2-'a');
                    break;
                }
            }
        }
        boolean[]visit=new boolean[K];
        LinkedList<Integer>list=new LinkedList<>();
        for(int i=0;i<K;i++)
        {
            if(!visit[i]){
                dfs(i,graph,list,visit);
            }
        }
        String ans="";
        while(list.size()>0)
        {
            int rem=list.removeFirst();
            ans+=(char)(rem+'a');
        }
        //System.out.println(ans);
        return ans;
        
    }
    public void dfs(int src,ArrayList<Integer>[]graph,LinkedList<Integer>list,boolean[]visit)
    {
      visit[src]=true;
      for(int nbr:graph[src]){
          if(!visit[nbr])
          dfs(nbr,graph,list,visit);
      }
       list.addFirst(src); 
        
    }
}
// Course Schedule II
//course hai order dana hai topological sort ka
class Solution {
    public int[] findOrder(int n, int[][] prerequisites) {
        ArrayList<Integer>[]graph=new ArrayList[n];
        for(int i=0;i<n;i++)
        {
            graph[i]=new ArrayList<>();
        }
        for(int[]arr:prerequisites){
            graph[arr[0]].add(arr[1]);
        }
        int[]arr=new int[n];
        LinkedList<Integer>q=new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            for(int nbr:graph[i]){
                arr[nbr]++;
            }
        }
        for(int i=0;i<n;i++)
        {
            if(arr[i]==0)
            {
                q.addLast(i);
            }
        }
        int idx=arr.length-1;
        int[]ans=new int[n];
        while(q.size()>0)
        {
            int rem=q.removeFirst();
            ans[idx]=rem;
            idx--;
           
            for(int nbr:graph[rem]){
                arr[nbr]--;
                if(arr[nbr]==0)
                {
                    q.addLast(nbr);
                }
            }
        }
        
        if(idx==-1){
            return ans;
            
        }
        return arr=new int[0];
        
    }
}
//kahs hi hai algo
//bas dekh level kar is se kya fayda ak question dekhte hai
//jase kuch machine hai series me chale ge to 10 rs pr head and parrell to jitne marji chala lo 10 hi 
//to ab level same wale ko bas ab same level walo ko parrell me or akale to series maan lo
//ans level * 10 hota
class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> graph) 
    {
       int[]count=new int[V];
       int[]ans=new int[V];
       for(int i=0;i<V;i++)
       {
           for(int nbr:graph.get(i)){
              count[nbr]++; 
           }
       }
       LinkedList<Integer>queue=new LinkedList<>();
       for(int i=0;i<count.length;i++)
       {
           if(count[i]==0)
           {
               queue.addLast(i);
           }
       }
       int idx=0;
       int level=0;
       while(queue.size()>0){
           int size=queue.size();
           while(size-->0){
           int rem=queue.removeFirst();
           ans[idx]=rem;
           idx++;
           for(int nbr:graph.get(rem)){
               count[nbr]--;
               if(count[nbr]==0)
               {
                   queue.add(nbr);
               }
           }
           }
           level++;
       }
       if(idx==V){
           return ans;
       }
       return ans=new int[V];
    }
}
//dsu
//jab question ho ki similar bando ki grouping karni ho
//2 optimstion first boi yar ki recusion me baar uper jane se acha ak bar jab jao yo sabko point kar to parent se
//find ki =amotise log(n)<=o(4)
//and overall v+E*amotise log(n)
public static int find(int x)
{
    if(arr[x]==x)
    {
        return x;

    }
    int temp=find(x);
    arr[x]=temp;
    return temp;
}
public static int uinon(int x,int y)
{
    int xp=find(x);
    int yp=find(x);
    if(xp!=yp)
    {
        if(rank[xp]<rank[yp])
        {
            part[yp]=xp;
        }else if(rank[xp]>rank[yp])
        {
            part[xp]=yp;
        }else{
            part[xp]=yp;
            rank[xp]++;
        }
    }
}
//dsu vs dfs
//agar static graph hai to dfs acha hai
//per agar dymanic grapg mtlb yar boi sare node hai or egdes nhi to dsu use karo

//Number Of Island 2
//boi dsu se hi hai
public static List<Integer> numIslands2(int m, int n, int[][] p) {
    int []arr=new int[m*n];
    int []rank=new int[m*n];
    int count=0;
    Arrays.fill(arr, -1);
    int[][] dir =  { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    ArrayList<Integer>ans=new ArrayList<>();
    for(int[] N : p)
    {
        int row=N[0];
        int col=N[1];
        arr[row*n+col]=row*n+col;
        rank[row*n+col]++;
        count++;
        for(int d=0;d<dir.length;d++){
            int r=row+dir[d][0];
            int c=col+dir[d][1];
            if (r < 0 || r >= m || c < 0 || c >= n || arr[r*n+c] == -1) {
             continue;
            }
            int nbr=find(r*n+c,arr);
            int parentnbr=find(row*n+col,arr);
            if(nbr!=parentnbr){
                if(rank[nbr]>rank[parentnbr])
                {
                    arr[parentnbr]=nbr;
                }else if(rank[nbr]<rank[parentnbr])
                {
                    arr[nbr]=parentnbr;
                }else{
                     arr[nbr]=parentnbr;
                     rank[nbr]++;
                }
                count--;
            }
            
        }
        ans.add(count);
    }
    return ans;
}
public static int find(int m, int []arr) {
    if(arr[m]==m)
    {
        return m;
    }
    int temp=find(arr[m],arr);
    arr[m]=temp;
    return temp;
}
}
// Redundant Connection
//are first konsi edge remove pe tree baan jai ga
//to jab edge u and v sme group ko point kar rhe boi remove pr tree baan jai ga
//ase ku kuki 1 to n egde hai to 1 to n-1 cycle nhi bana sakti to bachi n wale jo check ho rhi
class Solution {
    int[] arr;
    int[] rank;
    public int[] findRedundantConnection(int[][] edges) {
        arr = new int[edges.length+1];
        rank = new int[edges.length+1];
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=i;
        }
        for (int[] c : edges) {
            int row = c[0];
            int col = c[1];
            int src1 = find(row);
            int src2 = find(col);
            if (src1 != src2) {
                union(src1, src2);
            } else {
                
                return c;
            }
        }
        
        return null;
    }

    public int find(int x) {
        if (arr[x] == x) {
            return x;
        }
        int temp = find(arr[x]);
        arr[x] = temp;
        return temp;
    }

    public void union(int x, int y) {
        if (rank[x] > rank[y]) {
            arr[y] = x;
        } else if (rank[x] < rank[y]) {
            arr[x] = y;
        } else {
            arr[x] = y;
            rank[x]++;
        }
    }
}
//Minimum Wire Required To Connect All Pcs
static int[]arr;
  static int[]rank;
  public static void kuskal(ArrayList<Edge>[] graph) {

    PriorityQueue<Edge>pq = new PriorityQueue<>();
    for (int i = 0; i < graph.length; i++)
    {

      for (Edge e : graph[i]) {
        pq.add(e);
      }
    }
    arr=new int[graph.length];
    rank=new int[graph.length];
    for(int i=0;i<arr.length;i++)
    {
        arr[i]=i;
    }
    while(pq.size()>0)
    {
        Edge e=pq.remove();
        int src1=find(e.src);
        int src2=find(e.nbr);
        if(src1!=src2)
        {
          System.out.println(e.nbr + "@"+e.src+"@"+e.wt);
          union(src1,src2);
        }
    }


  }
  public static void union(int x,int y) {
   if(rank[x]>rank[y])
   {
       arr[y]=x;
   }else if(rank[x]<rank[y]){
       arr[x]=y;
   }else{
       arr[x]=y;
       rank[x]++;
   }

  }
  public static int find(int x) {
   if(arr[x]==x){
       return x;
   }
   int temp=find(arr[x]);
   arr[x]=temp;
   return temp;
   

  }
  //Regions Cut By Slashes
  //boi phale boundery condition ko connect then normal arr ko or agar find me dono not equal aye 
  //matlb ak group me hai or cycle bane rhi to ak count increase
  class Solution {
    int[] arr;
    int[] rank;
    int count;
    public int regionsBySlashes(String[] grid) {
        count = 1;
        int n = grid.length;
        arr = new int[(n + 1) * (n + 1)];
        rank = new int[(n + 1) * (n + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            rank[i] = 1;
        }
        for (int i = 0; i <= grid.length; i++) {
            for (int j = 0; j <= grid.length; j++) {
                if (i == 0 || j == 0 || i == grid.length || j == grid.length) {
                    int cellno = i * (grid.length + 1) + j;
                    if (cellno != 0) {
                        help(0, cellno);
                    }
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            char[] ch = grid[i].toCharArray();
            for (int j = 0; j < grid.length; j++) {
                if (ch[j] == '/') {
                    int cell1 = i * (grid.length + 1) + j + 1;
                    int cell2 = (i + 1) * (grid.length + 1) + j;
                    help(cell1, cell2);
                } else if (ch[j] == '\\') {
                    int cell1 = i * (grid.length + 1) + j;
                    int cell2 = (i + 1) * (grid.length + 1) + j + 1;
                    help(cell1, cell2);
                }
            }
        }
        return count;
    }

    public void help(int x, int y) {
        int x1 = find(x);
        int y1 = find(y);
        if (x1 != y1) {
            union(x1, y1);
        } else {
            count++;
        }
    }

    public int find(int x) {
        if (arr[x] == x) {
            return x;
        }
        int temp = find(arr[x]);
        arr[x] = temp;
        return temp;
    }

    public void union(int x, int y) {
        if (rank[x] > rank[y]) {
            arr[y] = x;
        } else if (rank[x] < rank[y]) {
            arr[x] = y;
        } else {
            arr[x] = y;
            rank[x]++;
        }
    }
}
//Satisfiability of Equality Equations
//boi equation hai equal and unequal to btana ki sab sai bta rhi kya hai kya 
//https://leetcode.com/problems/satisfiability-of-equality-equations/
class Solution {
    int[] arr;
    int[] rank;
    public boolean equationsPossible(String[] equations) {
        arr = new int[26];
        rank = new int[26];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            rank[i] = 1;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                union(str.charAt(0) - 'a', str.charAt(3) - 'a');
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int x = find(str.charAt(0) - 'a');
                int y = find(str.charAt(3) - 'a');
                if (x == y) {
                    return false;
                }
            }
        }
        return true;
    }
    public void union(int x1, int y1) {
        int x = find(x1);
        int y = find(y1);
        if (rank[x] > rank[y]) {
            arr[y] = x;
        } else if (rank[x] < rank[y]) {
            arr[x] = y;
        } else {
            arr[x] = y;
            rank[x]++;
        }
    }

    public int find(int x) {
        if (arr[x] == x) {
            return x;
        }
        int temp = find(arr[x]);
        arr[x] = temp;
        return temp;
    }
}
//Articulation Point
static int time = 0;

  private static void AP_Bridges(ArrayList<ArrayList<Integer>> graph, int[] disc, int[] low, int[] parent,
                                 boolean[] Apoint, boolean[] visited, int src) {
    disc[src] = low[src] = ++time;
    int counter = 0;
    visited[src] = true;
    for (int e = 0; e < graph.get(src).size(); e++) {
      int nbr = graph.get(src).get(e);
      if (parent[src] == nbr) {
        continue;
      } else if (visited[nbr] == true) {
        low[src] = Math.min(low[src], disc[nbr]);
      } else {
        parent[nbr] = src;
        AP_Bridges(graph, disc, low, parent, Apoint, visited, nbr);
        low[src] = Math.min(low[src], low[nbr]);
        if (parent[src] == -1) {
          counter++;
          if (counter >= 2) {
            Apoint[src] = true;
          }
        } else {
          if (low[nbr] >= disc[src]) {
            Apoint[src] = true;
          }
        }

      }
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner scn = new Scanner(System.in);


    int v = scn.nextInt();
    int e = scn.nextInt();
    ArrayList<ArrayList<Integer>> g = new ArrayList<>();
    for (int i = 0; i < v; i++) {
      g.add(new ArrayList<>());
    }
    for (int i = 0; i < e; i++) {
      int v1 = scn.nextInt();
      int v2 = scn.nextInt();
      g.get(v1 - 1).add(v2 - 1);
      g.get(v2 - 1).add(v1 - 1);
    }

    boolean A_point[] = new boolean[v];
    int[] parent = new int[v];
    parent[0] = -1;
    AP_Bridges(g, new int[v], new int[v], parent, A_point, new boolean[v], 0);
    int count = 0;
    for (int i = 0; i < v; i++) {
      if (A_point[i]) {
        count++;
      }
    }
    System.out.println(count);



  }
  // Minimize Malware Spread
  //arry me infection bande hai to btana konsa infact hata de too kaam faale ga
  //logic boi jis me 1 infect us me jo biigest size wala bo ans
  //https://leetcode.com/problems/minimize-malware-spread/
  class Solution {
    int[]p;
    int[]size;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        p=new int[graph.length];
        size=new int[graph.length];
        for(int i=0;i<p.length;i++){
            p[i]=i;
            size[i]=1;
        }
        for(int i=0;i<graph.length;i++)
        {
            for(int j=0;j<graph.length;j++)
            {
                if(i!=j && graph[i][j]==1){
                    int x=find(i);
                    int y=find(j);
                    union(x,y);
                }
            }
        }
        int[]componentSize=new int[graph.length];
        for(int i:initial){
            int x=find(i);
            componentSize[x]++;
        }
        Arrays.sort(initial);
        int ans_i=initial[0];
        int ans_size=0;
        
        for(int i:initial){
            int x=find(i);
            if(componentSize[x]==1 && size[x]>ans_size){
                ans_size=size[x];
                ans_i=i;
            }
        }
        return ans_i;
    }
    public int find(int x) {
        return p[x]==x?x:(p[x]=find(p[x]));
    }
    public void union(int x,int y) {
        if(size[x]>size[y]){
            p[y]=x;
        }else if(size[x]<size[y]){
             p[x]=y;
        }else{
             p[y]=x;
             size[y]++;
        }
    }
}
//Similar String Groups
//boi word ka array hai ab jo word me do character unequal hai uska pair bana or last me kitne pair hai bo btana
class Solution {
    HashMap<String,String> p;
    HashMap<String,Integer> r;

    public int numSimilarGroups(String[] strs) {
        p=new HashMap<>();
        r=new HashMap<>();
        
        for (String str : strs) {
            p.put(str, str);
            r.put(str, 1);
        }
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                String s1 = strs[i];
                String s2 = strs[j];
                if (similar(s1, s2)) {
                    String x = find(s1);
                    String y = find(s2);
                    if(!(x.equals(y)))
                    union(x, y);
                }
            }
        }
        int count=0;
        for(String key:p.keySet()) {
           if(p.get(key).equals(key)){
               count++;
           }
            
        }
        return count;
    }

    public String find(String x) {
        if (p.get(x) == x) {
            return x;
        }
        String pos = p.get(x);
        String ans = find(pos);
        p.put(x, ans);
        return ans;
    }

    public void union(String x, String y) {
        if (r.get(x) > r.get(y)) {
            p.put(y, x);
        } else if (r.get(x) < r.get(y)) {
            p.put(x, y);
        } else {
            p.put(x, y);
            r.put(x, r.get(x) + 1);
        }
    }

    public boolean similar(String x, String y) {
        int res = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) != y.charAt(i)) {
                res++;
            }
        }
        return res == 2;
    }
}
//Redundant Connection 2
static int[] parent;
  static int[] rank;

  public static int[] findRedundantDirectedConnection(int[][] edges) {
    int n = edges.length;
    int[] indegree = new int[n + 1];
    Arrays.fill(indegree, -1);
    int bl1 = -1;
    int bl2 = -1;
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      int u = edge[0];
      int v = edge[1];

      if (indegree[v] == -1) {
        indegree[v] = i;
      } else {
        bl1 = i;
        bl2 = indegree[v];
      }
    }
    parent = new int[n + 1];
    rank = new int[n + 1];
    for (int i = 1; i < parent.length; i++) {
      parent[i] = i;
      rank[i] = 1;
    }
    for (int i = 0; i < edges.length; i++) {
      if (bl1 == i) {
        continue;
      }
      int[] edge = edges[i];
      int u = edge[0];
      int v = edge[1];
      boolean flag = union(u, v);
      if (flag == true) {
        if (bl1 == -1) {
          return edge;
        } else {
          return edges[bl2];
        }
      }
    }
    return edges[bl1];
  }

  public static int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    int temp = find(parent[x]);
    parent[x] = temp;
    return temp;
  }

  public static boolean union(int x, int y) {
    int lx = find(x);
    int ly = find(y);

    if (lx == ly) {
      return true;
    }

    if (rank[lx] > rank[ly]) {
      parent[ly] = lx;
    } else if (rank[lx] < rank[ly]) {
      parent[lx] = ly;
    } else {
      parent[lx] = ly;
      rank[ly]++;
    }

    return false;
  }

}
//minimum swap to sort the arry
// ss-1 dekho
class Solution
{
    //Function to find the minimum number of swaps required to sort the array.
    public class pair implements Comparable<pair>{
        int val;
        int idx;
        pair(int val,int idx){
            this.val=val;
            this.idx=idx;
        }
        public int compareTo(pair o){
            return this.val-o.val;
        }
    }
    public int minSwaps(int nums[])
    {
       pair[]arr=new pair[nums.length]; 
       for(int i=0;i<nums.length;i++)
       {
           arr[i]=new pair(nums[i],i);
       }
       Arrays.sort(arr);
       boolean[] visit=new boolean[nums.length];
       int ans=0;
       for(int i=0;i<nums.length;i++){
           if(visit[i]==true||arr[i].idx==i){
               continue;
           }
           int j=i;
           int len=0;
          
           while(visit[j]==false){
               visit[j]=true;
               j=arr[j].idx;
               len++;
           }
           ans+=len-1;
       }
       return ans;
    }
}
//Minimum Swaps To Make Identical Array
//ss-2

public static int minimumSwaps(int n, int[] a, int[] b) {
    // Write your code here.
    HashMap<Integer,Integer>m=new HashMap<>();
    for(int i=0;i<b.length;i++)
    {
        m.put(b[i],i);
    }
    int ans=0;
    
    boolean[]visit=new boolean[n];
    for(int i=0;i<n;i++){
        if(visit[i]==true||m.get(b[i])==m.get(a[i]))
        {
            continue;
        }
        
        int len=0;
        int j=i;
        while(visit[j]==false){
            visit[j]=true;
            j=m.get(a[j]);
            len++;
        }
        ans+=len-1;
    }
   return ans; 
}
//Optimize Water Distribution
//boi bas ak zero node banya us se sab connect hai or mtlb is question me ye mtlb hai ki koi bhi ak kuue me se pani nicklna hai pr sab se mini me pani chala jai sab ko

public static class pair implements Comparable<pair> {

    int nbr;
    int wt;
    pair(int nbr, int wt) {
      this.nbr = nbr;
      this.wt = wt;
    }
    public int compareTo(pair o) {
      return this.wt - o.wt;
    }
  }
  public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    ArrayList<ArrayList<pair>>graph = new ArrayList<>();
    PriorityQueue<pair> pq = new PriorityQueue<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < pipes.length; i++) {
      int u = pipes[i][0];
      int v = pipes[i][1];
      int wt = pipes[i][2];
      graph.get(u).add(new pair(v, wt));
      graph.get(v).add(new pair(u, wt));

    }

    for (int i = 1; i <= wells.length; i++) {

      graph.get(i).add(new pair(0, wells[i - 1]));
      graph.get(0).add(new pair(i, wells[i - 1]));

    }
    boolean[]visit=new boolean[n+1];
    pq.add(new pair(0, 0));
    int ans = 0;
    while (pq.size() > 0) {
      int size = pq.size();
      while (size-- > 0) {
        pair rem = pq.remove();
       
        if(visit[rem.nbr]){
            continue;
        }
         ans += rem.wt;
        visit[rem.nbr]=true;
        for(pair e:graph.get(rem.nbr)){
            if(!visit[e.nbr]){
                pq.add(e);
            }
        }
      }
    }
    return ans;
//Job Sequencing Problem
//ss-3
//boi dsu hi hai sort kia jase hi or fir prent nickal la ss3 dekho
class Solution
{
    //Function to find the maximum profit and the number of jobs done.
    int[]parent;
    int[] JobScheduling(Job arr[], int n)
    {
        Arrays.sort(arr,(a,b)->{
            return b.profit-a.profit;
        });
        int count=0;
        int ans=0;
        parent=new int[101];
        for(int i=0;i<parent.length;i++){
            parent[i]=i;
        }
        for(Job j:arr){
            int jobp=find(j.deadline);
            if(jobp>0){
                count++;
                ans+=j.profit;
                parent[jobp]=find(jobp-1);
            }
            
        }
        return new int[]{count,ans};
        
    }
    public int find(int x){
        return parent[x]==x?x:(parent[x]=find(parent[x]));
    }
}
//minimum cost to connect all the city
class GFG {
    public static class pair implements Comparable<pair> {
      int vtx;
      int wt;
      pair(int vtx,int wt){
      this.vtx=vtx;
      this.wt=wt;
      }
      public  int compareTo(pair o){
      return this.wt-o.wt;
      }
    }
	public static int findcost (int n,int [][]arr) {
	    
	    ArrayList<ArrayList<pair>>graph=new ArrayList<>();
	    for(int i=0;i<n;i++)
	    {
	      graph.add(new ArrayList<>());
	    }
	    for(int i=0;i<n;i++){
	    for(int j=0;j<n;j++){
	       if(arr[i][j]!=0){
	        graph.get(i).add(new pair(j,arr[i][j]));
	        graph.get(j).add(new pair(i,arr[i][j]));
	        }
	        
	    }
	    }
	    PriorityQueue<pair> pq = new PriorityQueue<>();
	    pq.add(new pair(0,0));
	    int ans=0;
	    boolean[]visit=new boolean[n+1];
	    while(pq.size()>0){
	      int size=pq.size();
	      while(size-->0){
	      pair rem=pq.remove();
	      if(visit[rem.vtx]==true){
	      continue;
	      }
	      ans+=rem.wt;
	      visit[rem.vtx]=true;
	      for(pair e:graph.get(rem.vtx)){
	        if(visit[e.vtx]==false){
	           pq.add(e);
	        }
	      }
          }
	    
	    
	}
	return ans;
	}

//Little Alawn's Puzzle
//ss-4
//bas kuch nhi karna jss-4
public class Main {
    public static void main(String[] args){
        
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();

        while(t-- > 0){
            int n = scn.nextInt();

            int[] r1 = new int[n];
            int[] r2 = new int[n];

            for(int i = 0; i < n; i++){
                r1[i] = scn.nextInt();
            }

            for(int i = 0; i < n; i++){
                r2[i] = scn.nextInt();
            }

            solve(r1, r2);
        }
    }

    public static void solve(int[] r1, int[] r2){
        parent = new int[r1.length+1];
        rank = new int[r1.length+1];
        for(int i = 0; i <parent.length; i++){
            parent[i] = i;
            rank[i] = 1;
        }

        for(int i = 0; i < r1.length; i++){
            int x=find(r1[i]);
            int y=find(r2[i]);
            if(x!=y){
                union(x,y);
            }
        }

        int count = 0;
        for(int i = 1; i <parent.length; i++){
            if(parent[i] == i){
                count++;
            }
        }

        int MOD = 1000000007;
        int ans = 1;

        for(int i = 1; i <= count; i++){
            ans = ((ans % MOD) * (2 % MOD)) % MOD;
        }

        System.out.println(ans);
    }

    static int[] parent;
    static int[] rank;

    static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y){
        if(rank[x] < rank[y]){
            parent[x] = y;
        } else {
            parent[y] = x;

            if(rank[x] == rank[y]){
                rank[x]++;
            }
        }
    }
}

//journey to the moon
//friend pairing bhi yhi hai
//
class Result {

    public static  long find(int src, List<Integer>[]graph,boolean[]v) {
      v[src]=true;
      long size=1;
      for(int nbr:graph[src]){
          if(!v[nbr]){
              size+=find(nbr,graph,v);
              
          }
      }
      return size;
         
    }
    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
     List<Integer>[]graph=new ArrayList[n];
     for(int i=0;i<n;i++){
         graph[i]=new ArrayList<>();
     }
     
         for(List<Integer> nbr:astronaut){
             graph[nbr.get(0)].add(nbr.get(1));
             graph[nbr.get(1)].add(nbr.get(0));
         }
     
     boolean []visit=new boolean[n];
     int ans=0,sum=0;
     for(int i=0;i<n;i++){
         if(!visit[i]){
             long size=find(i,graph,visit);
             
             ans+=size*sum;
             sum+=size;
             System.out.println(sum);
         }
     }
     return ans;
    }

}
////Euler Circuit in an Undirected Graph 
//Euler Circuit me sab ki degree 2 hogi
//Euler ppath me sab degree 2 pr start and end ki one hogi
//Undirected Graph hoga
class Solution
{
    public boolean isEularCircuitExist(int V, ArrayList<ArrayList<Integer>> adj)
    {
        int count=0;
        for(int i=0;i<V;i++){
            if(adj.get(i).size()%2==1){
                count++;
            }
        }
        return count==0;
    }
    
}
//Eulerian Path in an Undirected Graph
//Euler ppath me sab degree 2 pr start and end ki one hogi ya sari even ho to bhi 
//Undirected Graph  hoga
class Solution{
    static int eulerPath(int N, int graph[][]){
        int count=0;
        int size=0;
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                if(i!=j && graph[i][j]==1){
                    size++;
                }
            }
            if(size%2==1){
                count++;
                size=0;
            }
        }
        if(count==0 || count==2){
            return 1;
        }
        return 0;
    }
}
//Possible Path 
//boi ak point se last point ho undirected graph ho to lagta hai
class Solution
{
    public int isPossible(int[][] graph)
    {
         int count=0;
        int size=0;
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                if(i!=j && graph[i][j]==1){
                    size++;
                }
            }
            if(size%2==1){
                count++;
                size=0;
            }
        }
        if(count==0){
            return 1;
        }
        return 0;
    }
}

// dsu why ony in undurected graph
//becouse us me dono direction hoti hai matlb same set
//per directesd me ak taraf direction hoyi hai matlb    same set me nhi

//K-Similar Strings
//ss-5
class Solution {
    public int kSimilarity(String s1, String s2) {
        HashSet<String> visit = new HashSet<>();
        LinkedList<String> list = new LinkedList<>();
        list.add(s1);
        int level = 0;
        while (list.size() > 0) {
            int size = list.size();
            while (size-- > 0) {
                String rem = list.removeFirst();
                if (visit.contains(rem)) {
                    continue;
                }
                visit.add(rem);
                if (rem.equals(s2)) {
                    return level;
                }
                for (String s : nbr(s2, rem)) {
                    if (!visit.contains(s)) {
                        list.add(s);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public ArrayList<String> nbr(String s2, String rem) {
        int idx = -1;
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) != rem.charAt(i)) {
                idx = i;
                break;
            }
        }
        for (int i = idx + 1; i < s2.length(); i++) {
            if (s2.charAt(idx) == rem.charAt(i)) {
                String str = help(rem, idx, i);
                ans.add(str);
            }
        }
        return ans;
    }

    public String help(String s1, int i, int j) {
        StringBuilder sb = new StringBuilder(s1);
        sb.setCharAt(i, s1.charAt(j));
        sb.setCharAt(j, s1.charAt(i));
        return sb.toString();
    }
}
//Word Ladder
//A transformation sequence from word beginWord to word endWord using a dictionary 
public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> set = new HashSet<>(wordList);
    LinkedList<String> q = new LinkedList<>();
    q.addLast(beginWord);
    int level = 0;
    while (q.size() > 0) {
        int size = q.size();
        while (size-- > 0) {
            String rem = q.removeFirst();
            if (rem.equals(endWord)) {
                return level + 1;
            }
            for (int i = 0; i < rem.length(); i++) {
                char[] ch = rem.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    ch[i] = j;
                    String check = new String(ch);
                    if (!rem.equals(check) && set.contains(check)) {
                        q.addLast(check);
                        set.remove(check);
                    }
                }
            }
        }
        level++;
    }
    return 0;
}
    
    

                                






