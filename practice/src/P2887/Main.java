package P2887;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
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
		return this.c - o.c;
	}

	@Override
	public String toString() {
		return "Edge [s=" + s + ", e=" + e + ", c=" + c + "]";
	}
	
	
}

class xyz implements Comparable<xyz>{
	int index;
	int cost;
	public xyz(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}
	@Override
	public int compareTo(xyz o) {
		// TODO Auto-generated method stub
		return this.cost-o.cost;
	}
	@Override
	public String toString() {
		return "xyz [index=" + index + ", cost=" + cost + "]";
	}
	
}

public class Main {
	static int N;
	//static Planet[] planets;
	static PriorityQueue<Edge> edges;
	static int[] parents;
	static PriorityQueue<xyz> x;
	static PriorityQueue<xyz> y;
	static PriorityQueue<xyz> z;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//planets = new Planet[N];
		parents = new int[N];
		x=new PriorityQueue<xyz>();
		y=new PriorityQueue<xyz>();
		z=new PriorityQueue<xyz>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x.add(new xyz(i, Integer.parseInt(st.nextToken())));
			y.add(new xyz(i, Integer.parseInt(st.nextToken())));
			z.add(new xyz(i, Integer.parseInt(st.nextToken())));
		}
//		System.out.println(x);
//		System.out.println(y);
//		System.out.println(z);
		edges = new PriorityQueue();
		xyz x1=x.poll();
		xyz y1=y.poll();
		xyz z1=z.poll();
		while(true) {
			xyz x2=x.poll();
			edges.add(new Edge(x1.index, x2.index, Math.abs(x1.cost-x2.cost)));
			x1=x2;
			xyz y2=y.poll();
			edges.add(new Edge(y1.index, y2.index, Math.abs(y1.cost-y2.cost)));
			y1=y2;
			xyz z2=z.poll();
			edges.add(new Edge(z1.index, z2.index, Math.abs(z1.cost-z2.cost)));
			z1=z2;
			if(x.isEmpty()) break;
		}
		//System.out.println(edges);
		

		for (int i = 0; i < N; i++)
			parents[i] = i;

		int count = 0;
		int cost = 0;
		while (true) {
			Edge check = edges.poll();
			if (find(check.s) != find(check.e)) {
				union(check.s, check.e);
				count++;
				cost += check.c;
			}
			if (count == N - 1)
				break;
		}
		System.out.println(cost);
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		parents[aRoot] = bRoot;
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		else
			return parents[a] = find(parents[a]);
	}

}
