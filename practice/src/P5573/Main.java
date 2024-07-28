package P5573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int H;
	static int W;
	static int N;
	static int[][] a;
	static int[][] dp;
	// static int[][] b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		a = new int[N + 1][W + 1];
		dp = new int[N + 1][W + 1];
		for (int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= W; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[1][1] = N - 1;

		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				if (i == 1 && j == 1) {
					dp[i][j] = N - 1;
					continue;
				}
				dp[i][j] = dp[i - 1][j] / 2 + dp[i][j - 1] / 2;

				if (dp[i - 1][j] % 2 == 1 && a[i - 1][j] == 0)
					dp[i][j]++;
				if (dp[i][j - 1] % 2 == 1 && a[i][j - 1] == 1)
					dp[i][j]++;

			}
		}
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				if (dp[i][j] % 2 == 1) {
					if (a[i][j] == 1)
						a[i][j] = 0;
					else
						a[i][j] = 1;
				}
			}
		}

		int y = 1;
		int x = 1;
		while (true) {
			if (a[y][x] == 1)
				x++;
			else
				y++;
			if (x == W + 1)
				break;
			if (y == H + 1)
				break;
		}
		System.out.print(y + " ");
		System.out.print(x);

	}
}
