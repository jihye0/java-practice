package P23034;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int s;
	int e;
	int c;
	public Edge(int s, int e, int c) {
		this.s = s;
		this.e = e;
		this.c = c;
	}
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.c-o.c;
	}
}

public class Main {
	static int N, M;
	static int Q;
	static int x, y;
	static int[] parent;
	static Edge[] edge;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		//parent=new int[N+1];
		edge=new Edge[M];
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			edge[i]=new Edge(s, e, c);
		}
		Arrays.sort(edge, new Comparator<>(){
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.c-o2.c;
			}	
		});
		Q=Integer.parseInt(br.readLine());
		for(int q=0; q<Q; q++) {
			int result=0;
			st=new StringTokenizer(br.readLine());
			x=Integer.parseInt(st.nextToken());
			y=Integer.parseInt(st.nextToken());
			parent=new int[N+1];
			for(int i=1; i<=N; i++) parent[i]=i;
			int count=0;
			for(int i=0; i<M; i++) {
				if(find(edge[i].s)==x && find(edge[i].e)==y) continue;
				if(find(edge[i].s)==y && find(edge[i].e)==y) continue;
				else {
					union(edge[i].s, edge[i].e);
					result+=edge[i].c;
					count++;
				}
				if(count==N-2) break;
			}
			System.out.println(result);
		}
	}
	
	static void union(int a, int b) {
		int aRoot=find(a);
		int bRoot=find(b);
		if(aRoot==bRoot) return;
		else {
			if(aRoot==x || aRoot==y) parent[bRoot]=aRoot;
			else if(bRoot==x || bRoot==y) parent[aRoot]=bRoot;
			else if(aRoot<bRoot) parent[aRoot]=bRoot;
			else parent[bRoot]=aRoot;
		}
	}
	
	static int find(int a) {
		if(parent[a]==a) return a;
		else return parent[a]=find(parent[a]);
	}
	
	
}
