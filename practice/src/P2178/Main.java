package P2178;

import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N;
	static int M;
	static int ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		visited[0][0] = true;
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0));
		while (!q.isEmpty()) {
			// 큐에서 꺼낸
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			// 목적지인가
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				// 갈 수 있는가
				if (nx < 0 || nx >= M || ny < 0 || ny >= N)
					continue;
				if (map[ny][nx] == 0)
					continue;
				if (visited[ny][nx] == true)
					continue;
				// 해당 노드를 처음 방문하는 경우에
				map[ny][nx] = map[y][x] + 1;
				visited[ny][nx] = true;
				q.offer(new Node(nx, ny));
			}
		}
		return map[N - 1][M - 1];
	}

}
