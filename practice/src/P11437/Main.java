package P11437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static ArrayList<Integer>[] adj;
	static int[] depths;
	static boolean[] visited;
	static int[][] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		adj=new ArrayList[N+1];
		depths=new int[N+1];
		visited=new boolean[N+1];
		//int v=1;
		//int count=1;
		//int tmp=1;
		K=0;
		int n=N-1;
		while(n>0) {
			n/=2;
			K++;
		}
		parent=new int[K+1][N+1];
		
		for(int i=1; i<=N; i++) adj[i]=new ArrayList<Integer>();
		for(int i=0; i<N-1; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		parent[0][1]=0;
		dfs(1, 0);
		//System.out.println(Arrays.toString(depths));
		//System.out.println(Arrays.toString(parent[0]));
		for(int k=1; k<=K; k++) {
			for(int v=1; v<=N; v++) {
				parent[k][v]=parent[k-1][parent[k-1][v]];
			}
		}
//		for(int k=0; k<=K; k++) {
//			System.out.println(Arrays.toString(parent[k]));
//		}
		int M=Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			System.out.println(lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
	}
	
	static int lca(int a, int b) {
		if(depths[a]<depths[b]) {
			int tmp=a;
			a=b;
			b=tmp;
		}
		int diff=depths[a]-depths[b];
		for(int k=K; k>=0; k--) {
			if(diff>=(1<<k)) {
				a=parent[k][a];
				diff=depths[a]-depths[b];
			}
		}
		
		if(a==b) return a;
		//int result=0;

		for(int k=K; k>=0; k--) {
			if(parent[k][a]!=parent[k][b]) {
				a=parent[k][a];
				b=parent[k][b];
			}
		}
		return parent[0][a];
	}
	
	static void dfs(int node, int depth) {
		//체크인
		visited[node]=true;
		depths[node]=depth;
		//목적지인가
		//순회
		for(int i=0; i<adj[node].size(); i++) {
			if(visited[adj[node].get(i)]==false) {
				parent[0][adj[node].get(i)]=node;
				dfs(adj[node].get(i), depth+1);
			}
		}
		//갈 수있는가
		//간다
		//체크아
		visited[node]=false;
	}

}
