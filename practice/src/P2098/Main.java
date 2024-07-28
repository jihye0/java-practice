package P2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] a;
	static int[][] d;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		a = new int[N][3];
		d = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a[i][0] = Integer.parseInt(st.nextToken());
			a[i][1] = Integer.parseInt(st.nextToken());
			a[i][2] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			Arrays.fill(d[i], -1);
		}
		d[0][0] = a[0][0];
		d[0][1] = a[0][1];
		d[0][2] = a[0][2];
		int max = Math.max(find(N - 1, 0), find(N - 1, 1));
		max = Math.max(max, find(N - 1, 2));
		System.out.println(max);
		

		for (int i = 0; i < N; i++) {
			Arrays.fill(d[i], -1);
		}
		d[0][0] = a[0][0];
		d[0][1] = a[0][1];
		d[0][2] = a[0][2];
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(d[i]));
//		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(a[i]));
//		}
		int min = Math.min(find_min(N - 1, 0), find_min(N - 1, 1));
		min = Math.min(min, find_min(N - 1, 2));
		System.out.println(min);
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(d[i]));
//		}

	}

	static int find(int i, int j) {
		if (i == 0)
			return d[i][j];
		if (d[i][j] != -1)
			return d[i][j];
		if (j == 0) {
			return d[i][j] = Math.max(find(i - 1, 1) + a[i][j], find(i - 1, 0) + a[i][j]);
		} else if (j == 1) {
			d[i][j] = Math.max(find(i - 1, 1) + a[i][j], find(i - 1, 0) + a[i][j]);
			return d[i][j] = Math.max(d[i][j], find(i - 1, 2) + a[i][j]);
		} else {
			return d[i][j] = Math.max(find(i - 1, 1) + a[i][j], find(i - 1, 2) + a[i][j]);
		}
	}

	static int find_min(int i, int j) {
		if (i == 0)
			return d[i][j];
		if (d[i][j] != -1)
			return d[i][j];
		if (j == 0) {
			return d[i][j] = Math.min(find_min(i - 1, 1) + a[i][j], find_min(i - 1, 0) + a[i][j]);
		} else if (j == 1) {
			d[i][j] = Math.min(find_min(i - 1, 1) + a[i][j], find_min(i - 1, 0) + a[i][j]);
			return d[i][j] = Math.min(d[i][j], find_min(i - 1, 2) + a[i][j]);
		} else {
			return d[i][j] = Math.min(find_min(i - 1, 1) + a[i][j], find_min(i - 1, 2) + a[i][j]);
		}
	}

}
