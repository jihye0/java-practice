package P10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String st = br.readLine();
			for (int j = 0; j < N; j++) {
				if (st.charAt(j) == 'R') {
					map[i][j] = 0;
				} else if (st.charAt(j) == 'G') {
					map[i][j] = 1;
				} else {
					map[i][j] = 2;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}

		for (int i = 0; i < N; i++)
			Arrays.fill(visited[i], false);
		int count2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs2(i, j);
					count2++;
				}
			}
		}

		System.out.print(count);
		System.out.print(' ');
		System.out.print(count2);
		// for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));

	}

	static void bfs(int x, int y) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.add(new Node(x, y));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Node now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] == map[x][y]) {
					q.add(new Node(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}

	static void bfs2(int x, int y) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.add(new Node(x, y));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Node now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (map[x][y] == 2) {
					if (map[nx][ny] == 2) {
						q.add(new Node(nx, ny));
						visited[nx][ny] = true;
					}
				} else {
					if (map[nx][ny] != 2) {
						q.add(new Node(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}

}
