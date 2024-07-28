package P1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int V;
	static ArrayList<Integer>[] adj;
	static boolean[] visited, visited2;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		V= Integer.parseInt(st.nextToken());
		adj=new ArrayList[N+1];
		visited=new boolean[N+1];
		visited2=new boolean[N+1];
		for(int i=1; i<=N; i++) {
			adj[i]=new ArrayList<> ();
		}
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		for(int i=1; i<=N; i++) {
			//System.out.println(adj[i]);
			Collections.sort(adj[i]);
		}
		dfs(V);
		System.out.print("\n");
		q=new LinkedList<>();
		q.add(V);
		visited2[V]=true;
		bfs();
}
	
	static void dfs(int v){
		//체크인
		//System.out.println(Arrays.toString(visited));
		visited[v]=true;
		System.out.print(v+" ");
		//목적지인가
		if(adj[v].size()==0) return;
		//연결된 곳을 순회
		for(int i=0; i<adj[v].size(); i++) {
			//갈수있는가
			//간다
			//체크아
			if(visited[adj[v].get(i)]==false) {
				dfs(adj[v].get(i));
			}
		}
		//visited[v]=false;
	}
	
	static void bfs() {
		//큐에서 꺼내옴
		while(true) {
//			System.out.println(Arrays.toString(visited2));
//			System.out.println(q);
			if(q.isEmpty()) break;	
			int v=q.poll();
			System.out.print(v+" ");
			//목적지인가
			if(adj[v].size()==0) return;
			//연결된 곳을 순회
			for(int i=0; i<adj[v].size(); i++) {
			//갈수있는가
			//체크인 방문예
			//큐에 넣는
				if(visited2[adj[v].get(i)]==false) {
					visited2[adj[v].get(i)]=true;
					q.add(adj[v].get(i));
				}
			}
			
		}
		
	}

}
