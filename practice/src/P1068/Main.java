package P1068;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] parent;
	static boolean[] visited;
	static boolean[] isLeaf;
	static int x;

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		parent=new int[N];
		visited=new boolean[N];
		Arrays.fill(visited, false);
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			parent[i]=Integer.parseInt(st.nextToken());
		}
		x=Integer.parseInt(br.readLine());
		dfs(x);
		int ans=0;

		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				for(int j=0; j<N; j++) {
					if(parent[j]==i && !visited[j]) {
						break;
					}
					if(j==(N-1)) ans++;
				}
			}
		}

		System.out.println(ans);
	}
	
	static boolean isLeaf(int n) {
		for(int i=0; i<N; i++) {
			if(parent[i]==n) {
				return false;
			}
		}
		return true;
	}
	
	static void dfs(int n) {
		visited[n]=true;
		if(isLeaf(n)) return;
		for(int i=0; i<N; i++) {
			if(parent[i]==n) {
				if(visited[i]==false) {
					dfs(i);
				}
			}
		}
	}

}
