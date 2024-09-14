package P2606;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int V;
	static int ans;
	static boolean[][] edges;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		V=Integer.parseInt(br.readLine());
		edges=new boolean[N+1][N+1];
		visited=new boolean[N+1];
		for(int i=0; i<V; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			edges[a][b]=true;
			edges[b][a]=true;
		}
		//체크인
		
		//목적지인가?
		//연결된 곳을 순회
		//갈 수 있는가
		//간다
		//체크아
		ans=0;
		dfs(1);
		for(int i=1; i<=N; i++) {
			if(visited[i]) {
				ans++;
			}
		}
		System.out.println(ans-1);
		
	}
	
	static void dfs(int n) {
		visited[n]=true;
		for(int i=1; i<=N; i++) {
			if(edges[n][i]==true && visited[i]==false) {
				dfs(i);
			}
		}
		//visited[n]=false;
	}
	

}
