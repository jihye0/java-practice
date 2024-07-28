package P5719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // N은 장소의 수 M은 도로의 수
	static int S, D; // S는 시작점 D는 도착점
	static ArrayList[] adj; //간선리스트

	static class Edge implements Comparable<Edge> {
		int e;
		int c;

		public Edge(int e, int c) {
			this.e = e;
			this.c = c;
		}
		
		@Override
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break;
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			adj = new ArrayList[N];
			for (int i = 0; i < N; i++)
				adj[i] = new ArrayList<Edge>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				adj[U].add(new Edge(V, P));
			}
			
		}
		
		
		
			

		

	}

}
