package P2618;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N; // 동서방향 도로의 개수
	static int W; // 처리해야하는 사건의 개수
	static int[][] dp;
	static Point[] points;
	static ArrayList<Integer> ps;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 동서방향 도로의 개
		W = Integer.parseInt(br.readLine()); // 처리 해야 하는 사건의 개
		points = new Point[W + 2];
		ps = new ArrayList<Integer>();
		points[0] = new Point(1, 1);
		points[1] = new Point(N, N);
		for (int i = 0; i < W; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[2 + i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		dp = new int[W + 2][W + 2];
		int ans = mem(0, 1);
		System.out.println(ans);
		path(0, 1);
	}

	static int mem(int f, int s) {
		if (f == W + 1 || s == W + 1)
			return 0;

		int ret = dp[f][s];
		if (ret != 0)
			return ret;
		int next = Math.max(f, s) + 1;
		int p1 = mem(next, s) + dist(points[f], points[next]);
		int p2 = mem(f, next) + dist(points[s], points[next]);
		ret = Math.min(p1, p2);
		return dp[f][s] = ret;
	}

	public static void path(int f, int s) {
		if (f == W + 1 || s == W + 1) {
			for (int i = 0; i < ps.size(); i++) {
				System.out.println(ps.get(i));
			}
			return;
		}
		int next = Math.max(f, s) + 1;
		int p1 = dp[next][s] + dist(points[f], points[next]);
		int p2 = dp[f][next] + dist(points[s], points[next]);
		if (p1 < p2) {
			ps.add(1);
			path(next, s);
		} else {
			ps.add(2);
			path(f, next);
		}
		return;
	}

	static int dist(Point point1, Point point2) {
		return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
	}

}
