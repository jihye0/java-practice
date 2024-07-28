package P11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static Edge[] edges;
	static long[] dist;
	static int INF = Integer.MAX_VALUE;

	static class Edge {
		int start;
		int end;
		int cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 버스의개수

		edges = new Edge[M + 1];

		dist = new long[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(A, B, C);
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M; j++) {
				if (dist[edges[j].start] != INF && dist[edges[j].end] > dist[edges[j].start] + edges[j].cost) {
					dist[edges[j].end] = dist[edges[j].start] + edges[j].cost;
				}
			}
		}
		boolean updated = false;
		for (int j = 0; j < M; j++) {
			if (dist[edges[j].start] != INF && dist[edges[j].end] > dist[edges[j].start] + edges[j].cost) {
				updated = true;
				break;
			}
		}

		if (updated) {
			System.out.println("-1");
		} else {
			for (int i = 2; i <= N; i++) {
				if (dist[i] == INF)
					System.out.println("-1");
				else
					System.out.println(dist[i]);
			}
		}

	}

}
