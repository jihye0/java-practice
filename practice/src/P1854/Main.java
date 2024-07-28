package P1854;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k; //n 도시 개수 m 도시 간에 존재한 도로의 수 k번째
	static ArrayList<Edge>[] adj;
	//static ArrayList<Integer>[] dist;
	static int INF=Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge>{
		//다음 노드의 인덱스와 그 노드로 가는데 필요한 비용
		int end;
		int cost;
		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Edge [end=" + end + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost-o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()); //k번째 최단경
		adj = new ArrayList[n + 1];
		for(int i=1; i<=n; i++) {
			adj[i]=new ArrayList<Edge>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b, c));
		}
		
		PriorityQueue<Integer>[] dist=new PriorityQueue[n+1];
		Comparator<Integer> cp=new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		};
		for(int i=0; i<n+1; i++) {
			dist[i]=new PriorityQueue<Integer>(k, cp);
		}
		dist[1].add(0);

		PriorityQueue<Edge> pq=new PriorityQueue<Edge>();
		pq.add(new Edge(1,0));
		
		Edge now, next;
		while(!pq.isEmpty()) {
			now=pq.poll();
			
			for(int i=0; i<adj[now.end].size(); i++) {
				next=adj[now.end].get(i);
				if(dist[next.end].size()<k) {
					dist[next.end].add(now.cost+next.cost);
					pq.add(new Edge(next.end, now.cost+next.cost));
				}
				else if(dist[next.end].peek()>now.cost+next.cost) {
					dist[next.end].poll();
					dist[next.end].add(now.cost+next.cost);
					pq.add(new Edge(next.end, now.cost+next.cost));
				}
			}
		}
		for(int i=1; i<=n; i++) {
			if(dist[i].size()==k) {
				System.out.println(dist[i].peek());
			}
			else System.out.println(-1);
		}

	}

}
