package P1761;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge {
	int end=0;
	int cost=0;

	public Edge(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Edge [end=" + end + ", cost=" + cost + "]";
	}

}

public class Main {
	static int N;
	static int M;
	static int K;
	static boolean[] visited;
	static int[] depth;
	static Edge[][] parent;
	static ArrayList<Edge>[] adj;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<Edge>();
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
		}
		K=0;
		int n=N-1;
		while(n>0) {
			n/=2;
			K++;
		}
		//System.out.println(K);
		parent=new Edge[K][N+1];
		for(int i=0; i<K; i++) Arrays.fill(parent[i], new Edge(0, 0));
		parent[0][0]=new Edge(0, 0);
		parent[0][1]=new Edge(0, 0);
		dfs(1, 0);
		//System.out.println(Arrays.toString(parent[0]));
		for(int k=1; k<K; k++) {
			for(int v=2; v<N+1; v++) {
				//System.out.println(parent[k][v]);
				int end=parent[k-1][parent[k-1][v].end].end;
				if(end==0) continue;
				parent[k][v]=new Edge(end, parent[k-1][v].cost+parent[k-1][parent[k-1][v].end].cost);
				//System.out.println(parent[k][v]);
				//parent[k][v].end=parent[k-1][parent[k-1][v].end].end;
				//parent[k][v].cost=parent[k-1][v].cost+parent[k-1][parent[k-1][v].end].cost;
			}
		}
//		for(int i=0; i<K; i++) {
//			System.out.println(parent[i][12]);
//		}
//		for(int i=0; i<K; i++) {
//			System.out.println(parent[i][15]);
//		}
		M = Integer.parseInt(br.readLine());
		///////////////////
		//System.out.println(Arrays.toString(depth));
		//////////////////////////
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			System.out.println(lca(a, b));
		}

	}
	
	static int lca(int a, int b) {
		int result=0;
		if(depth[a]<depth[b]) {
			int tmp=a;
			a=b;
			b=tmp;
		}
		int diff=depth[a]-depth[b];
		//System.out.println("diff");
		//System.out.println(diff);
		for(int i=K-1; i>=0; i--) {
			if(diff>=(1<<i)) {
				//System.out.println(a);
				result+=parent[i][a].cost;
				//System.out.println(result);
				a=parent[i][a].end;
				//System.out.println(a);
				diff=depth[a]-depth[b];
			}
		}
		if(a==b) return result;
		//System.out.println(a);
		
		for(int i=K-1; i>=0; i--) {
			if(parent[i][a].end!=parent[i][b].end) {
				///System.out.println(parent[i][a]);
				//System.out.println(parent[i][b]);
				result+=parent[i][a].cost+parent[i][b].cost;
				//System.out.println(i);
				
				//System.out.println(result);
				a=parent[i][a].end;
				b=parent[i][b].end;
			}
			
		}
		result+=parent[0][a].cost+parent[0][b].cost;
		return result;
		
	}

	static void dfs(int node, int d) {
		//체크인 
		visited[node]=true;
		depth[node]=d;
		//목적지인가?
		//순회 
		for(int i=0; i<adj[node].size(); i++) {
			if(visited[adj[node].get(i).end]==false) {
				parent[0][adj[node].get(i).end]=new Edge(node, adj[node].get(i).cost);
				dfs(adj[node].get(i).end, d+1);
			}
		}
		//갈 수 있는가?
		//간다 
		//체크아
		visited[node]=false;
	}
}
