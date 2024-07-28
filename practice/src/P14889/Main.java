package P14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] S;
	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = Integer.MAX_VALUE;
		dfs(0, 0);
		System.out.println(result);

	}

	static void dfs(int idx, int depth) {
		// 1. 체크인
		// 목적지인가
		if (depth == N / 2) {
			int sum1 = 0;
			int sum2 = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					if (visited[i] && visited[j] && i != j) {
						sum1 += S[i][j];
					} else if (!visited[i] && !visited[j] && i != j) {
						sum2 += S[i][j];
					}
			}

			result = Math.min(result, Math.abs(sum1 - sum2));
			//Arrays.fill(isTeam1, false);
			return;
		}
		// 연결된 곳을 순회
		for (int i = idx; i < N; i++) {
			// 갈 수 있는가
			if (!visited[i]) {
				visited[i] = true;
				dfs(i+1, depth + 1);
				visited[i] = false;
			}
			// 간다
		}

	}

}
