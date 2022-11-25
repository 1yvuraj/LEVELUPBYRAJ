// Number of Islands
//https://leetcode.com/problems/number-of-islands/
class Solution {
    public int numIslands(char[][] grid) {
        int [][]dir={{-1,0},{0,1},{0,-1},{1,0}};
        int c=0;
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid[0].length;j++)
            {
               
                if(grid[i][j]=='1'){
                dfs(grid,i,j,dir);
                     c++;
                }
            }
        }
        return c;
    }
    public void dfs(char[][] grid,int sr,int sc,int[][]dir) {
        grid[sr][sc]='0';
        for(int d=0;d<dir.length;d++)
        {
            
            int r=sr+dir[d][0];
            int c=sc+dir[d][1];
            if(r>=0 && c>=0 && r<grid.length && c<grid[0].length && grid[r][c]=='1'){
             dfs(grid,r,c,dir);
            }
        }
    }
}
//Island Perimeter
//https://leetcode.com/problems/island-perimeter/
//There is exactly one island in grid. likha hai
class Solution {

    public int islandPerimeter(int[][] grid) {
        int one = 0;
        int nbr = 0;
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    one++;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
                            nbr++;
                        }
                    }
                }
            }
        }
        return 4 * one - nbr;
    }
}

//Max Area of Island
//https://leetcode.com/problems/max-area-of-island/
class Solution {
    int max=-(int)1e9;
    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };
        int c = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                   
                    int size =dfs(grid, i, j, dir);
                    max=Math.max(max,size);
                }
            }
        }
        return max==-(int)1e9?0:max;
    }
    public int dfs(int[][] grid, int sr, int sc, int[][] dir) {
        grid[sr][sc] = 0; int size=0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
                
                size+=dfs(grid, r, c, dir);
            }
        }
        return size+1;
    }
}
//Surrounded Regions
class Solution {
    public void solve(char[][] grid) {
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {
                    if (grid[i][j] == 'O') {
                        help(grid, i, j);
                    }
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'v') {
                    grid[i][j] = 'O';
                } else {
                    grid[i][j] = 'X';
                }
            }
        }
    }

    public void help(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != 'O') {
            return;
        }
        grid[row][col] = 'v';
        help(grid, row + 1, col);
        help(grid, row - 1, col);
        help(grid, row, col - 1);
        help(grid, row, col + 1);
    }
}
//rotten orange
//idx=r*m+c m=col hai
//r=idx/m;
//c=idx%m;
class Solution {
    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        int time = 0;
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                }
                if (grid[i][j] == 2) {
                    list.addLast(i * m + j);
                }
            }
        }
        if (fresh == 0) return 0;
        while (list.size() > 0) {
            int size = list.size();

            while (size-- > 0) {
                int rem = list.removeFirst();
                int sr = rem / m;
                int sc = rem % m;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        list.addLast(r * m + c);
                        fresh--;
                        if (fresh == 0) {
                            return time + 1;
                        }
                    }
                }
            }
            time++;
        }
        return -1;
    }
}
//Shortest Path in Binary Matrix
lass Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        LinkedList<Integer> list = new LinkedList<>();
        int m = grid[0].length;
        int n = grid.length;
        list.add(0);
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1;
        }

        int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
        int time = 1;
        while (list.size() > 0) {
            int size = list.size();
            while (size-- > 0) {
                int rem = list.removeFirst();
                int sr = rem / m;
                int sc = rem % m;
                if (sr == n - 1 && sc == m - 1) {
                    return time;
                }
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 0) {
                        grid[r][c] = 1;
                        list.add(r * m + c);
                    }
                }
            }
            time++;
        }
        return -1;
    }
}
//01 Matrix
//boi a zero se kitna dur hai
class Solution {
    public int[][] updateMatrix(int[][] grid) {
        LinkedList<Integer> list = new LinkedList<>();
        int[][] visit = new int[grid.length][grid[0].length];
        int m = grid[0].length;
        int n = grid.length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    list.add(i * m + j);
                    visit[i][j] = 2;
                }
            }
        }

        int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        int time = 1;
        while (list.size() > 0) {
            int size = list.size();
            while (size-- > 0) {
                int rem = list.removeFirst();
                int sr = rem / m;
                int sc = rem % m;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && visit[r][c] != 2) {
                        grid[r][c] = grid[sr][sc] + 1;
                        visit[r][c] = 2;
                        list.add(r * m + c);
                    }
                }
            }
        }
        return grid;
    }
}
//kahs hi hai algo
//bas dekh level kar is se kya fayda ak question dekhte hai
//jase kuch machine hai series me chale ge to 10 rs pr head and parrell to jitne marji chala lo 10 hi 
//to ab level same wale ko bas ab same level walo ko parrell me or akale to series maan lo
//ans level * 10 hota
//directed graph
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
//ArrayList me same level ko arraylist to
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
       ArrayList<ArrayList<Integer>>list=new ArrayList<>();
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
           ArrayList<Integer>Slist=new ArrayList<>();
           while(size-->0){
           int rem=queue.removeFirst();
           Slist.add(rem);
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
           list.add(Slist);
           level++;
       }
       if(idx==V){
           return ans;
       }
       return ans=new int[V];
    }
}
//kahans dfs se
//suno 0 matlb not visit
//1 mattlb visit
//2 mtalb backtrack
//agar 1 fir se aya matlb cycle return null otherwise arr wala ans
class Solution {
    int idx ;
    public int[] findOrder(int n, int[][] prerequisites) {
        idx=n-1;
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] arr : prerequisites) {
            graph[arr[1]].add(arr[0]);
        }
        int[] visit = new int[n];
        int[] arr = new int[n];
        boolean cycle = false;
        for (int i = 0; i < graph.length; i++) {
            if (visit[i] == 0) cycle = cycle || dfs(i, graph, visit, arr);
        }
        if(!cycle) return arr;
        return new int[0];
    }

    public boolean dfs(int src, ArrayList<Integer>[] graph, int[] visit, int[] arr) {
        visit[src] = 1;
        boolean icycle = false;
        for (int nbr : graph[src]) {
            if (visit[nbr] == 0) {
                icycle = icycle || dfs(nbr, graph, visit,arr);
            } else if (visit[nbr] == 1) {
                return true;
            }
        }
        visit[src] = 2;
        arr[idx--] = src;
        return icycle;
    }
}
//Longest Increasing Path in a Matrix
//boi jase start boi hoga jis ki indegree zero hogi 
//boi 2d ke 4 dir cal and check agar agar me bada hu to i and j ko plus plus
//9 9 4 
//6 6 8
//2 1 1
//jase 1 se start kuki degree zero hai ase hi karna hai or jis ki zero ho jay degree bo queue me dal do
//normal khans
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] ans = new int[n][m];
        LinkedList<Integer> q = new LinkedList<>();
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < matrix[0].length && matrix[r][c] < matrix[i][j]) {
                        ans[i][j]++;
                    }
                }
                if (ans[i][j] == 0) {
                    q.addLast(i * matrix[0].length + j);
                }
            }
        }
        int level = 0;
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int rem = q.removeFirst();
                int sr = rem / m;
                int sc = rem % m;
                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];
                    if (r >= 0 && c >= 0 && r < n && c < matrix[0].length && matrix[r][c] > matrix[sr][sc] && --ans[r][c] == 0) {
                        q.addLast(r * m + c);
                    }
                }
            }
            level++;
        }
        return level;
    }
}
//dsu
static int[] par, size;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public static void merge(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }
    //dsu
    // {{u,v,w}}
    //fing ki amotais log*n
    //overall V+E*log*n-->o(V+E);
    static int[] par, size;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public static void merge(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }
    public static void unionFind(int[][] Edges, int N) {
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        par = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] e : Edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                merge(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }

    // Redundant Connection
    class Solution {
        int[] arr;
        int[] rank;
        public int[] findRedundantConnection(int[][] edges) {
            arr = new int[edges.length + 1];
            rank = new int[edges.length + 1];
            for (int i = 0; i < edges.length; i++) {
                arr[i] = i;
                rank[i] = 1;
            }
            for (int[] nbr : edges) {
                int u = nbr[0];
                int v = nbr[1];
                int x = find(u);
                int y = find(v);
                if (x != y) {
                    union(x, y);
                } else {
                    return nbr;
                }
            }
            return null;
        }
    
        public int find(int x) {
            return arr[x] == x ? x : (arr[x] = find(arr[x]));
        }
    
        public void union(int x, int y) {
            if (rank[x] > rank[y]) {
                arr[y] = x;
            } else if (rank[x] > rank[y]) {
                arr[x] = y;
            } else {
                arr[x] = y;
                rank[x]++;
            }
        }
    }
//Lexicographically smallest equivalent string
//boi bas parent ko hi lexsograph me kar de ge
    
    public class Solution {
        static int []parent;
        public static String smallestString(String s, String t, String str) {
            parent=new int[26];
            for(int i=0;i<26;i++){
               parent[i]=i; 
            }
            for(int i=0;i<s.length();i++){
                int x=find(s.charAt(i)-'a');
                int y=find(t.charAt(i)-'a');
                parent[x]=Math.min(x,y);
                parent[y]=Math.min(x,y);
            }
            String ans="";
            for(int i=0;i<str.length();i++){
               int x=find(str.charAt(i)-'a');
               ans+=(char)(x+'a');
            }
            return ans;
        }
        public static int find(int x) {
            return parent[x]==x?x:(parent[x]=find(parent[x]));
        }
    }

//Similar String Groups
//boi simlar word ka set hai to kitne compand abne ge boi hai
//Input: strs = ["tars","rats","arts","star"]
//Output: 2
//https://leetcode.com/problems/similar-string-groups/
class Solution {
    int[]p;
    public int numSimilarGroups(String[] strs) {
        p=new int[strs.length];
        int c=strs.length;
        for(int i=0;i<p.length;i++){
            p[i]=i;
        }
       for(int i=0;i<strs.length;i++){
           for(int j=i+1;j<strs.length;j++){
               if(is(strs[i],strs[j])){
                   int x=find(i);
                   int y=find(j);
                   if(x!=y){
                       p[x]=y;
                       c--;
                   }
               }
           }
       }
        return c;
       
    }
    public boolean is(String s1,String s2) {
      int c=0;
      for(int i=0;i<s1.length();i++){
          if(s1.charAt(i)!=s2.charAt(i))++c;
      }
      //2 se hi kuki waha tha agar 1 swap me equal ho rhe to bhi
      
        return c<=2;
       
    }
    public int find(int x) {
       return p[x]==x?x:(p[x]=find(p[x]));
       
    }
}
// Count Sub Islands
//boi do grid hai or 2 grid me island 1 wale ke subisland me ate hai ya nhi
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

        int ans = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 1) {
                    if (dfs(i, j, dir, grid1, grid2)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public boolean dfs(int sr, int sc, int[][] dir, int[][] grid1, int[][] grid2) {
        boolean res = true;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < grid2.length && c < grid2[0].length && grid2[r][c] == 1) {
                grid2[r][c] = 0;

                res = dfs(r, c, dir, grid1, grid2) && res;
            }
        }
        return res && (grid1[sr][sc] == 1);
    }
}
//dsu se number of island
//n2
//to be prices n2*logamotas(*)(m*n);
class Solution {
    int[] p;
    int[] rank;

    public int numIslands(char[][] grid) {
        p = new int[grid.length * grid[0].length];
        rank = new int[grid.length * grid[0].length];
        int max = -(int) 1e9;
        int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
            rank[i] = 1;
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] - '0' == 1) {
                    ans++;
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] - '0' == 1) {
                    int x = find(i * grid[0].length + j);
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] - '0' == 1) {
                            int y = find(r * grid[0].length + c);
                            if (x != y) {
                                p[y] = x;
                                ans--;
                                rank[x] += rank[y];
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }
}

//island 2
//tc k*logamotas(*)(m*n);
//bas is me position de rhaki hai ki is point me boom gire hai to after girne ke badd kitne components bache hai list me dana hai
public static List<Integer> numIslands2(int m, int n, int[][] p) {
    int []arr = new int[m * n];
    int []rank = new int[m * n];
    int[][]grid = new int[m][n];
    for(int i=0;i<arr.length;i++){
        arr[i]=i;
    }
    int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
    ArrayList<Integer>ans=new ArrayList<>();
    int count = 0;
    for (int[]nbs : p) {
      int i = nbs[0];
      int j = nbs[1];
    //   if(grid[i][j]==1){
    //       ans.add(count);
    //       continue;
    //   }
      grid[i][j] = 1;
      int x=find(i*grid[0].length+j,arr);
      count++;
      for (int d = 0; d < dir.length; d++) {
        int r = i + dir[d][0];
        int c = j + dir[d][1];
        if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
          int y = find(r * grid[0].length + c,arr);
          if ( x!= y) {
            arr[y] = x;
            count--;
            rank[x] += rank[y];
          }
        }
      }
      ans.add(count);
    }
    return ans;
  }
  //Arry sort for 2 d
//   Arrays.sort(arr,(a,b)->{
//     return a[2]-b[2];
 //       return b[2]-a[2];

//   });

//kuskal algo
//boi sare node connect with low cost wala
//mst jiska sum of wiegth is min jada ho or bo st(spanning tree hona chia)
//st different way jis me cycle naq ho 
// st connected  hona chkia
//cycle kahi nhi 
//ans kase kare ge== incresaing order me dsu
//summet sir wala
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

  // simple 2d me dia hai too to bas arry.sort se ho kai ga
 // yha alag se union kia island me is lia nhi kuki waha pta tha x ko bsna ka 4 direction ka leader
 static int[] par, size;

    public static int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public static void merge(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // {{u,v,w}}
    public static void unionFind(int[][] Edges, int N) {
        
        par = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] e : Edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if (p1 != p2) {
                merge(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }

    // kruskalAlgo
    public static void kruskalAlgo(int[][] edges, int N) {
        // {{u,v,w}}
        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        unionFind(edges, N);
    }

}
//Optimize Water Distribution
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
    ArrayList<pair>[]graph = new ArrayList[n+1];
    PriorityQueue<pair> pq = new PriorityQueue<>();
    for (int i = 0; i <= n; i++) {
      graph[i]=new ArrayList<>();
    }
    for (int i = 0; i < pipes.length; i++) {
      int u = pipes[i][0];
      int v = pipes[i][1];
      int wt = pipes[i][2];
      graph[u].add(new pair(v, wt));
      graph[v].add(new pair(u, wt));

    }

    for (int i = 1; i <= wells.length; i++) {

      graph[i].add(new pair(0, wells[i - 1]));
      graph[0].add(new pair(i, wells[i - 1]));

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
        for(pair e:graph[rem.nbr]){
            if(!visit[e.nbr]){
                pq.add(e);
            }
        }
      }
    }
    return ans;
}
//dsu
public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    ArrayList<int[]>graph=new ArrayList<>();
    p=new int[n+1];
    for(int i=0;i<=n;i++)p[i]=i;
    for(int[]a:pipes){
        graph.add(a);
    }
    for(int i=1;i<=wells.length;i++){
        graph.add(new int[]{0,i,wells[i-1]});
    }
    
    int cost=0;
    Collections.sort(graph,(a,b)->{
        return a[2]-b[2];
    });
    for(int[]ans:graph){
        int u=ans[0];
        int v=ans[1];
        int wt=ans[2];
        int x=find(u);
        int y=find(v);
        if(x!=y)
        {
            p[x]=y;
            cost+=wt;
        }
        
    }
    return cost;
    
 }
 public static int find(int x) {
  if(p[x]==x){
      return x;
  }
  int temp=find(p[x]);
  p[x]=temp;
  return temp;
 }

 //mrPresident
 //boi ki k hai me sari rooad connect karni hai and condition hai ki koi bhi rood ko thode ke super road bana sakte 
 //bas mentannis cost 1 rs hogi 
 //or ye bhi hai ki sara graph connect hona chia
 //https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/

 public int mrPresident() {
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();
    int M = scn.nextInt();
    long K = scn.nextLong();

    ArrayList<int[]> Edges = new ArrayList<>();
    for (int i = 0; i < M; i++) {
        int u = scn.nextInt(), v = scn.nextInt(), w = scn.nextInt();
        Edges.add(new int[] { u, v, w });
    }

    Collections.sort(Edges, (a, b) -> {
        return a[2] - b[2];
    });

    par = new int[N + 1];
    for (int i = 0; i <= N; i++)
        par[i] = i;

    long totalCost = 0;
    int conversions = 0;
    int components = N;
    ArrayList<Integer> costOfRoad = new ArrayList<>();
    for (int[] e : Edges) {
        int u = e[0], v = e[1], w = e[2];
        int p1 = findPar(u), p2 = findPar(v);

        if (p1 != p2) {
            par[p1] = p2;
            totalCost += w;
            costOfRoad.add(w);
            components--;
        }
    }

    if (components > 1)
        return -1;

    for (int i = costOfRoad.size() - 1; i >= 0; i--) {
        if (totalCost > K) {
            totalCost = totalCost - costOfRoad.get(i) + 1;
            conversions++;
        } else {
            break;
        }
    }

    return totalCost > K ? -1 : conversions;
}

//Min Cost to Connect All Points
//sare point ko connect kanra hai min cost me
class Solution {
    int[]p;
    public int minCostConnectPoints(int[][] points) {
        int n=points.length;
        ArrayList<int[]>graph=new ArrayList<>();
        p=new int[n];
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                graph.add(new int[]{distace(points,i,j),i,j});
                
            }
        }
        Collections.sort(graph,(a,b)->{
            return a[0]-b[0];
        });
        for(int i=0;i<p.length;i++)p[i]=i;
        int cost=0;
        for(int[]nbr:graph){
            int wt=nbr[0];
            int u=nbr[1];
            int v=nbr[2];
            int x=findPar(u);
            int y=findPar(v);
            if(x!=y){
                p[x]=y;
                cost+=wt;
            }
            
        }
        return cost;
            
        
    }
    public  int findPar(int u) {
        return p[u] == u ? u : (p[u] = findPar(p[u]));
    }

     public int distace(int[][] points,int i ,int j) {
        return Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1]);
        
    }
}
//ak spanning tree me kitne edge hote hai V-1;
//ye chz sab me kar sakte hai
class Solution {
    int[]p;
    public int minCostConnectPoints(int[][] points) {
        int n=points.length;
        ArrayList<int[]>graph=new ArrayList<>();
        p=new int[n];
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                graph.add(new int[]{distace(points,i,j),i,j});
                
            }
        }
        Collections.sort(graph,(a,b)->{
            return a[0]-b[0];
        });
        for(int i=0;i<p.length;i++)p[i]=i;
        int cost=0;
        int nooFedge=0;
        for(int[]nbr:graph){
            int wt=nbr[0];
            int u=nbr[1];
            int v=nbr[2];
            int x=findPar(u);
            int y=findPar(v);
            if(x!=y){
                p[x]=y;
                cost+=wt;
                nooFedge++;
            }
            if(nooFedge>=n)break;
            
        }
        return cost;
            
        
    }
    public  int findPar(int u) {
        return p[u] == u ? u : (p[u] = findPar(p[u]));
    }

     public int distace(int[][] points,int i ,int j) {
        return Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1]);
        
    }
}

//. Minimize Malware Spread
//boi ak array me infect de rhakhe hai hume btana kis arry ke infect se jada population bache gi
class Solution {
    int[]p;
    int[]s;
    public  int findPar(int u) {
        return p[u] == u ? u : (p[u] = findPar(p[u]));
    }

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n=graph.length;
        p=new int[n];
        s=new int[n];
        for(int i=0;i<p.length;i++){
            p[i]=i;
            s[i]=1;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(graph[i][j]==0 || i==j)continue;
                int x=findPar(i);
                int y=findPar(j);
                if(x!=y){
                    p[x]=y;
                    s[y]+=s[x];
                }
            }
        }
        int[]infected=new int[n];
        for(int p:initial){
            int x=findPar(p);
            infected[x]++;
        }
       Arrays.sort(initial);
        int ans=initial[0];
        int pop=0;
        for(int i=0;i<initial.length;i++){
            int x=findPar(initial[i]);
            if(infected[x]==1 && s[x]>pop){
                pop=s[x];
                ans=initial[i];
            }
        }
        return ans;
        
    }
}

//Regions Cut By Slashes
//boi / and \\ ko add karna or region btana kitne bane ge
class Solution {
    int[] p;
    int[] r;
    public int findPar(int u) {
        return p[u] == u ? u : (p[u] = findPar(p[u]));
    }
    public int union(int x, int y) {
        if (x != y) {
            if (r[x] > r[y]) {
                p[y] = x;
            } else if (r[x] < r[y]) {
                p[x] = y;
            } else {
                p[x] = y;
                r[x]++;
            }
            return 0;
        } else {
            return 1;
        }
    }
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int N = n + 1;
        p = new int[N * N];
        r = new int[N * N];
        int count = 1;
        for (int i = 0; i < p.length; i++) {
            if (i / N == 0 || i / N == N - 1 || i % N == 0 || i % N == N - 1) {
                p[i] = 0;
            } else {
                p[i] = i;
            }
            r[i] = 1;
        }
        for (int i = 0; i < grid.length; i++) {
            String s = grid[i];
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '/') {
                    count += union(findPar(i * N + j + 1), findPar((i + 1) * N + j));
                } else if (s.charAt(j) == '\\') {
                    count += union(findPar(i * N + j), findPar((i + 1) * N + j + 1));
                }
            }
        }
        return count;
    }
}
// Satisfiability of Equality Equations

class Solution {
    int[] p;
    public boolean equationsPossible(String[] equations) {
        p = new int[26];
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
        for (int i = 0; i < equations.length; i++) {
            String ans = equations[i];
            if (ans.charAt(1) == '=') {
                int x = find(ans.charAt(0) - 'a');
                int y = find(ans.charAt(3) - 'a');
                if (x != y) {
                    p[x] = y;
                }
            }
        }
        for (int i = 0; i < equations.length; i++) {
            String ans = equations[i];
            if (ans.charAt(1) == '!') {
                int x = find(ans.charAt(0) - 'a');
                int y = find(ans.charAt(3) - 'a');
                if (x == y) {
                    return false;
                }
            }
        }
        return true;
    }

    public int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }
}

//Dakstra
//single source algo
//negative edge not work
//src to any point tak minimum wt me karta hai 
//jo kusagar tha ya mst us me bo sare point ko connect karta tha minimum cost me

public static class pair implements Comparable<pair>{
       int src = 0;
        int par = 0;
        int w = 0;
        int wsf = 0;
    pair(int src,int par,int w,int wsf)
    {
        this.src=src;
        this.par=par;
        this.w=w;
        this.wsf=wsf;
    }
    public int compareTo(pair o)
    {
        return this.wsf-o.wsf;
    }
    
 }
 public static void dijikstra(ArrayList<Edge>[] graph, int V, int src) {
    ArrayList<Edge>[] mygraph = new ArrayList[V];
    for (int i = 0; i < V; i++)
        graph[i] = new ArrayList<>();

    boolean[] vis = new boolean[V];
    PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
        return a.wsf - b.wsf;
    });

    pq.add(new pair(src, -1, 0, 0));
    while (pq.size() != 0) {
        pair p = pq.remove();
        if (vis[p.src])
            continue;

        

        vis[p.src] = true;
        for (Edge e : graph[p.src]) {
            if (!vis[e.v])
                pq.add(new pair(e.v, p.src, e.w, p.wsf + e.w));
        }
    }
}

//prise
public static class pair implements Comparable<pair>{
    int src = 0;
     int par = 0;
     int w = 0;
     int wsf = 0;
 pair(int src,int par,int w,int wsf)
 { 
     this.src=src;
     this.par=par;
     this.w=w;
     this.wsf=wsf;
 }
 public int compareTo(pair o)
 {
     return this.w-o.w;
 }
 
}
public static void priseclass Solution {
    public class pair implements Comparable<pair> {
        int src;
        int dest;
        int wt;
        pair(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
        public int compareTo(pair o) {
            return this.wt - o.wt;
        }
    }
   
 
 boolean[] vis = new boolean[V];
 PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
     return a.wsf - b.wsf;
 });

 pq.add(new pair(src, -1, 0, 0));
 while (pq.size() != 0) {
     pair p = pq.remove();
     if (vis[p.src])
         continue;

     

     vis[p.src] = true;
     for (Edge e : graph[p.src]) {
         if (!vis[e.v])
             pq.add(new pair(e.v, p.src, e.w, p.wsf + e.w));
     }
 }
}

//kuskal and prine same hi bas do way to solve problem
//kuskal and price not used in directed graph but dakstra ued in both graph
// Network Delay Time
//distace arry is lia kuki jis se pta chal jai sare node pe gya hai na ki nhi or max nickalne ke lia
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] i : times) {
            graph[i[0]].add(new int[] { i[1], i[2] });
        }
        return help(graph, k);
    }

    public int help(ArrayList<int[]>[] graph, int src) {
        int[] distace = new int[graph.length];
        boolean[] visit = new boolean[graph.length];
        for (int i = 0; i < distace.length; i++) {
            distace[i] = (int) 1e9;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> {
                return a[1] - b[1];
            }
        );
        pq.add(new int[] { src, 0 });
        while (pq.size() > 0) {
            int[] arr = pq.remove();
            int s = arr[0], wsf = arr[1];
            if (visit[s]) continue;
            visit[s] = true;
            distace[s] = wsf;
            for (int[] nbr : graph[s]) {
                if (!visit[nbr[0]]) {
                    pq.add(new int[] { nbr[0], wsf + nbr[1] });
                }
            }
        }
        int max = -(int) 1e9;
        for (int i = 1; i < distace.length; i++) {
            if (distace[i] == (int) 1e9) return -1;
            max = Math.max(max, distace[i]);
        }
        return max;
    }
}
//balmanfort
//ki negative cycle hai ki nhi kuki negtive cycle ho to ans nhi nickal sakte
//single source algo
// {{src,dest,weight}}
public static void bellmanFord(int[][] edges, int N, int src) {
    int[] prev = new int[N];
    Arrays.fill(prev, (int) 1e9);
    prev[src] = 0;

    boolean isNegativeCycle = false;
    for (int edgeCount = 1; edgeCount <= N; edgeCount++) {
        int[] curr = new int[N];
        for (int i = 0; i < N; i++)
            curr[i] = prev[i];

        boolean isAnyUpdate = false;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (prev[u] + w < curr[v]) {
                curr[v] = prev[u] + w;
                isAnyUpdate = true;
            }
        }

        if (edgeCount == N && isAnyUpdate)
            isNegativeCycle = true;

        if (!isAnyUpdate)
            break;
    }
}

//
// 490
//ball hai start to dest me jana hai pr ball wall se tacra ki hi ruke gi
public boolean hasPath(int[][] maze, int[] start, int[] destination) {

    int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
    LinkedList<Integer> que = new LinkedList<>();
    boolean[][] vis = new boolean[n][m];
    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    que.add(sr * m + sc);
    vis[sr][sc] = true;

    while (que.size() != 0) {
        int size = que.size();
        while (size-- > 0) {
            int idx = que.removeFirst(), i = idx / m, j = idx % m;
            if (i == er && j == ec)
                return true;
            for (int[] d : dir) {

                int r = i, c = j;
                while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                    r += d[0];
                    c += d[1];
                }

                r -= d[0];
                c -= d[1];

                if (vis[r][c])
                    continue;

                vis[r][c] = true;
                que.addLast(r * m + c);
                
            }

        }
    }

    return false;
}
//the maze 11
//steps btane hai kitne minimum me pocha
public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    int[][] dis = new int[n][m];
    for (int[] d : dis)
        Arrays.fill(d, (int) 1e8);

    PriorityQueue<pair> que = new PriorityQueue<>();
    que.add(new pair(sr, sc, 0));
    dis[sr][sc] = 0;

    while (que.size() != 0) {
        pair p = que.remove();
        if (p.r == er && p.c == ec)
            return p.steps;
        for (int[] d : dir) {
            int r = p.r, c = p.c, steps = p.steps;
            while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0) {
                r += d[0];
                c += d[1];
                steps++;
            }

            r -= d[0];
            c -= d[1];
            steps--;

            if (steps >= dis[r][c])
                continue;

            que.add(new pair(r, c, steps));
            dis[r][c] = steps;
        }
    }

    return -1;
}
//the maze 111
// 499
public static class pair implements Comparable<pair> {
    int r = 0, c = 0, steps = 0;
    String psf = "";

    pair(int r, int c, int steps, String psf) {
        this.r = r;
        this.c = c;
        this.steps = steps;
        this.psf = psf;
    }

    @Override
    public int compareTo(pair o) {
        if (this.steps != o.steps)
            return this.steps - o.steps;
        else
            return this.psf.compareTo(o.psf);
    }
}

public String findShortestWay(int[][] maze, int[] start, int[] destination) {
    int n = maze.length, m = maze[0].length, sr = start[0], sc = start[1], er = destination[0], ec = destination[1];
    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    String[] dirS = { "d", "u", "r", "l" };
    pair[][] dis = new pair[n][m];
    for (int i = 0; i < n * m; i++)
        dis[i / m][i % m] = new pair(i / m, i % m, (int) 1e8, "");

    PriorityQueue<pair> que = new PriorityQueue<>();
    pair src = new pair(sr, sc, 0, "");

    que.add(src);
    dis[sr][sc] = src;

    while (que.size() != 0) {
        pair p = que.remove();
        for (int i = 0; i < 4; i++) {
            int[] d = dir[i];

            int r = p.r, c = p.c, steps = p.steps;
            while (r >= 0 && c >= 0 && r < n && c < m && maze[r][c] == 0 && !(r == er && c == ec)) { // !(r == er &&
                r += d[0];
                c += d[1];
                steps++;                                                                                    // c == ec) ==
                                                                                                     // (r != er ||
                                                                                                      // c != ec)
                
            }

            if (!(r == er && c == ec)) { // why it is necc. ???
                r -= d[0];
                c -= d[1];
                steps--;
            }

            pair np = new pair(r, c, steps, p.psf + dirS[i]);
            if (steps > dis[r][c].steps || dis[r][c].compareTo(np) <= 0) // why this kind of check ???
                continue;

            que.add(np);
            dis[r][c] = np;
        }
    }

    pair ans = dis[er][ec];
    return ans.steps != (int) 1e8 ? ans.psf : "impossible";
}


// 2359. Find Closest Node to Given Two Nodes

// You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.

// The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.

// You are also given two integers node1 and node2.

// Return the index of the node that can be reached from both node1 and node2, such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return the node with the smallest index, and if no possible answer exists, return -1.

// Note that edges may contain cycles.

class Solution {

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] == -1) {} else {
                graph.get(i).add(edges[i]);
            }
        }
        int[] A = new int[n];
        int[] B = new int[n];
        Arrays.fill(A, -1);
        Arrays.fill(B, -1);
        distace(graph, node1, node2, node1, A);
        distace(graph, node1, node2, node2, B);

        int min = (int) 1e9;

        int minDist = Integer.MAX_VALUE, resNode = -1;
        for (int i = 0; i < A.length; i++) {
            // node is not reacheable so discard
            System.out.println(B[i]);
            if (A[i] == -1 || B[i] == -1) continue;
            int tempDist = Math.max(A[i], B[i]);
            // take the node with min dist from node1 and node2
            if (tempDist < minDist) {
                minDist = tempDist;
                resNode = i;
            }
        }
        return resNode;
    }

    public void distace(ArrayList<ArrayList<Integer>> graph, int node1, int node2, int srs, int[] distace) {
        int n = graph.size();
        boolean[] visit = new boolean[n];
        LinkedList<Integer> q = new LinkedList<>();

        q.add(srs);
        int dist = 0;
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int rem = q.removeFirst();
                if (visit[rem]) continue;
                visit[rem] = true;
                distace[rem] = dist;
                for (int nbr : graph.get(rem)) {
                    if (!visit[nbr]) {
                        q.addLast(nbr);
                    }
                }
            }
            dist++;
        }
    }
}
//same graph nhi bana
class Solution {

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] A = new int[n];
        int[] B = new int[n];
        distace(edges, node1, node2, node1, A);
        distace(edges, node1, node2, node2, B);

        int min = (int) 1e9;

        int minDist = Integer.MAX_VALUE, resNode = -1;
        for (int i = 0; i < A.length; i++) {
            // node is not reacheable so discar
            int tempDist = Math.max(A[i], B[i]);
            // take the node with min dist from node1 and node2
            if (tempDist < minDist) {
                minDist = tempDist;
                resNode = i;
            }
        }
        return resNode;
    }

    public int[] distace(int[] edges, int node1, int node2, int srs, int[] distace) {
        int n = edges.length;
        PriorityQueue<int[]> q = new PriorityQueue<>(
            (a, b) -> {
                return a[1] - b[1];
            }
        );
        Arrays.fill(distace, Integer.MAX_VALUE);
        q.add(new int[] { srs, 0 });
        int dist = 0;
        while (q.size() > 0) {
            int size = q.size();
            while (size-->0) {
                int[] rem = q.remove();
                if (visit[rem[0]]) continue;
                visit[rem[0]] = true;
                distace[rem[0]] = rem[1];
                int nbr = edges[rem[0]];
                if (nbr != -1 && distace[nbr]==Integer.MAX_VALUE) {
                    q.add(new int[] { nbr, rem[1] + 1 });
                }
            }
        }
        return distace;
    }
}
//Longest Cycle in a Graph

class Solution {
    int max = -1;
    public int longestCycle(int[] edges) {
        max = -(int) 1e9;
        boolean[] visit = new boolean[edges.length];
        for (int i = 0; i < edges.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            if (edges[i] == -1 || visit[i]) continue;
            dfs(edges, i, 0, map, visit);
        }
        return max == -(int) 1e9 ? -1 : max;
    }

    public void dfs(int[] edges, int src, int dist, HashMap<Integer, Integer> map, boolean[] visit) {
        if (src == -1) return;
        if (visit[src]) {
            if (!map.containsKey(src)) return;
            max = Math.max(max, dist - map.get(src));
            return;
        }
        visit[src] = true;
        map.put(src, dist);
        dfs(edges, edges[src], dist + 1, map, visit);
    }
}
//Number of Provinces
//dekh is me tha i and j jha 1 hai bo connect hai to parent [n] ka tha pr in island waqle me n*m kuki us me starting wali 
//condition nhi boli thi
class Solution {
    int[] parent;
    
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int m = isConnected[0].length;
        parent=new int[n+1];
       
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            
        }
        int count=n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    int x = findPar(i);
                    int y = findPar(j);
                    if (x != y) {
                        parent[x]=y;
                        count--;
                    }
                }
            }
        }
        return count;
    }

    public int findPar(int u) {
        return parent[u] == u ? u : (parent[u] = findPar(parent[u]));
    }

    // Minimum Number of Vertices to Reach All Nodes
    //jis node pe ak bhi edge nhi a rhi boi ans hai
    class Solution {
        public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
            int[] count = new int[n];
            for (List<Integer> nbr : edges) {
                int u = nbr.get(0);
                int v = nbr.get(1);
    
                count[v]++;
            }
    
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (count[i] == 0) {
                    ans.add(i);
                }
            }
            return ans;
        }
    }

//Keys and Rooms
//boi yar agar count list ke equal ho gya to mtlb same key mil hi gyi hai
 //Input: rooms = [[1],[2],[3],[]]
// Output: true
// Explanation: 
// We visit room 0 and pick up key 1.
// We then visit room 1 and pick up key 2.
// We then visit room 2 and pick up key 3.
// We then visit room 3.
// Since we were able to visit every room, we return true.
    class Solution {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            LinkedList<Integer> q = new LinkedList<>();
            boolean[] visit = new boolean[rooms.size()];
            q.add(0);
            int count = 0;
            while (q.size() > 0) {
                int size = q.size();
                while (size-- > 0) {
                    int rem = q.removeFirst();
                    if (visit[rem]) continue;
                    count++;
                    visit[rem] = true;
                    for (int nbr : rooms.get(rem)) {
                        if (!visit[nbr]) {
                            q.addLast(nbr);
                        }
                    }
                }
            }
            return count == rooms.size();
        }
    }
    //1791. Find Center of Star Graph
    //are boi centre node btana hai 
    //to jo node sab me hai boi ans hoga na
    class Solution {
        public int findCenter(int[][] edges) {
            int x=edges[0][0];
            int y=edges[0][1];
            return x==edges[1][0] ||x==edges[1][1]?x:y ;
        }
    }
    //Find if Path Exists in Graph
    //source and dest dia hai poch sakte ki nhi btana hai
    class Solution {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            if (edges.length == 0) return true;
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < edges.length; i++) {
                graph.get(edges[i][0]).add(edges[i][1]);
                graph.get(edges[i][1]).add(edges[i][0]);
            }
            LinkedList<Integer> q = new LinkedList<>();
            boolean[] visit = new boolean[n];
            q.addLast(source);
            while (q.size() > 0) {
                int size = q.size();
                while (size-- > 0) {
                    int rem = q.removeFirst();
                    if (visit[rem]) continue;
                    visit[rem] = true;
                    if (rem == destination) {
                        System.out.println(rem);
                        return true;
                    }
                    for (int nbr : graph.get(rem)) {
                        if (!visit[nbr]) {
                            q.addLast(nbr);
                        }
                    }
                }
            }
            return false;
        }
    }
    //dsu se upper wali
    class Solution {
        int[] parent;
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            parent = new int[n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            for (int[] nbr : edges) {
                int x = findPar(nbr[0]);
                int y = findPar(nbr[1]);
                if (x != y) {
                    parent[x] = y;
                }
            }
    c
            return findPar(source) == findPar(destination);
        }
    
        public int findPar(int u) {
            return parent[u] == u ? u : (parent[u] = findPar(parent[u]));
        }
    }
    //dfs
    class Solution {
        public boolean validPath(int n, int[][] edges, int source, int destination) {
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            if (edges.length == 0) return true;
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < edges.length; i++) {
                graph.get(edges[i][0]).add(edges[i][1]);
                graph.get(edges[i][1]).add(edges[i][0]);
            }
            boolean[] visit = new boolean[n];
    
            visit = new boolean[n];
            dfs(graph, source, visit);
            if (visit[destination]) return true;
    
            return false;
        }
    
        public void dfs(ArrayList<ArrayList<Integer>> graph, int src, boolean[] visit) {
            visit[src] = true;
            for (int nbr : graph.get(src)) {
                if (!visit[nbr]) {

                    dfs(graph, nbr, visit);
                }
            }
        }
    }

//Find the Town Judge
//If the town judge exists, then:
// The town judge trusts nobody.
// Everybody (except for the town judge) trusts the town judge.
// There is exactly one person that satisfies properties 1 and 2.

    class Solution {
        public int findJudge(int n, int[][] trust) {
            int[] count = new int[n + 1];
            for (int[] nbr : trust) {
                count[nbr[0]]--;
                count[nbr[1]]++;
            }
            for (int i = 1; i < count.length; i++) {
                if (count[i] == n - 1) {
                    return i;
                }
            }
            return -1;
        }
    }
//All Paths From Source to Target

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] edges) {
        List<List<Integer>>ans=new ArrayList<>();
        ArrayList<Integer>a=new ArrayList<>();
        a.add(0);
        dfs(edges,ans,a,0);
        return ans;
    }

    public void dfs(int[][]graph, List<List<Integer>>main,ArrayList<Integer>ans,int start) {
        if(start==graph.length-1){
            ArrayList<Integer>a=new ArrayList<>(ans);
            main.add(a);
            return;
        }
        for(int i:graph[start]){
            ans.add(i);
            dfs(graph,main,ans,i);
            ans.remove(ans.size()-1);
        }
    }
}

//Number of Operations to Make Network Connected
//connect karna hai or minimum no of wire connect se sab ko connect kar sakte hai
//n*log*n

class Solution {
    int[] parent;
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int c = 0;
        for (int[] nbr : connections) {
            int x = findPar(nbr[0]);
            int y = findPar(nbr[1]);
            if (x != y) {
                parent[x] = y;
            }
        }

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                c++;
            }
        }
        return c - 1;
    }

    public int findPar(int u) {
        return parent[u] == u ? u : (parent[u] = findPar(parent[u]));
    }
}
//choclate journy
public static void dijikstra(int src, ArrayList<int[]>[] graph, int[] dis) {

    // {vtx,dis}
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        return a[1] - b[1];
    });

    pq.add(new int[] { src, 0 });
    dis[src] = 0;
    while (pq.size() != 0) {
        int[] p = pq.remove();
        int u = p[0], cost = p[1];

        if (dis[u] < cost)
            continue;

        for (int[] e : graph[u]) {
            int v = e[0], c = e[1];
            if (cost + c < dis[v]) {
                dis[v] = cost + c;
                pq.add(new int[] { v, cost + c });
            }
        }
    }
}

public static void chocolateJourney() throws IOException {
    Reader scn = new Reader();
    int n = scn.nextInt();
    int m = scn.nextInt();
    int k = scn.nextInt();
    int x = scn.nextInt();

    boolean[] chocolates = new boolean[n + 1];
    for (int i = 0; i < k; i++)
        chocolates[scn.nextInt()] = true;

    ArrayList<int[]>[] graph = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++)
        graph[i] = new ArrayList<>();

    while (m-- > 0) {
        int u = scn.nextInt(), v = scn.nextInt(), w = scn.nextInt();
        graph[u].add(new int[] { v, w });
        graph[v].add(new int[] { u, w });
    }

    int src = scn.nextInt(), dest = scn.nextInt();

    int[] disSrc = new int[n + 1];
    Arrays.fill(disSrc, (int) 1e9);
    dijikstra(src, graph, disSrc);

    int[] disDest = new int[n + 1];
    Arrays.fill(disDest, (int) 1e9);
    dijikstra(dest, graph, disDest);

    int ans = (int) 1e9;
    for (int i = 1; i <= n; i++) {
        if (chocolates[i]) {
            if (disDest[i] < x && disSrc[i] != (int) 1e9) {
                ans = Math.min(ans, disDest[i] + disSrc[i]);
            }
        }
    }

    if (ans != (int) 1e9)
        System.out.println(ans);
    else
        System.out.println(-1);
}
//parent wala bas dfs(undirected) me hota hia  directed me khans
//longest cycle wale me likha tha ki 1 ane pr -1 ana hai is lia khusagra lag gya 
//or cycle in directed graph me khusagra is lia nhi lag sakti kuki itself loop hoga to ye nhi chale ga
//bfs work cycle in undiorected but not in directed


//kosaraju algo(strongly connected compound)(directed)
//jase ak cycle hai pr ussme jitne bhi node un sab se hum ussi pr wapas a sakte ho uss strongly connected compound kahte hai
//3 points to be remember
//typologoical sort
//graph ka inverse
//dfs
/// ase ku dekh trologcal se pta hai kase chalna hai
//or jab inverse kare ge to cycle ko koi farak nhi pade ga pr jis me cycle nhi hai unki direct opposite
//bo jai gi to me cycle se bhaar nhi ja pau ga
class Solution
{
    //Function to find number of strongly connected components in the graph.
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
}

//Mother Vertex
//ans me is lia i me jo hoga last uske baadk koi nhi aya kuki sab visit kar dia or ak bar confirm ke lia us
//se dfs chala dia 
//or i se phale wale is lia nhi ho sakte kuki agar hota to hum i pr nhi pochte na
//https://practice.geeksforgeeks.org/problems/mother-vertex/1
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

//Aticulation point ->mtlb kis point ko hatane se number of compounds jothe usse increase ho jai to



    
    
    
    
















