package P14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int fr;
	static int fc;
	static int fd;
	static int result;
	static int[][] map;

	static int[][] dx = { { -1, 0, 1, 0 }, { 0, -1, 0, 1 }, { 1, 0, -1, 0 }, { 0, 1, 0, -1 } }; // {-1, 0, 1, 0};
	static int[][] dy = { { 0, 1, 0, -1 }, { -1, 0, 1, 0 }, { 0, -1, 0, 1 }, { 1, 0, -1, 0 } }; // {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		fr = Integer.parseInt(st.nextToken());
		fc = Integer.parseInt(st.nextToken());
		fd = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = 0;
		clean(fr, fc, fd);
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		// System.out.println(r);
		// System.out.println(c);
		// System.out.println(d);
		System.out.println(result);

	}

	static void clean(int r, int c, int d) {
//		System.out.println(r);
//		System.out.println(c);
//		System.out.println(d);
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println('\n');
		if (map[r][c] == 0) {
			map[r][c] = 2;
			result++;
		}
		// boolean isFull = true;
		boolean isFind = false;
		int ny;
		int nx;
		int nd;
		int ay=-1, ax=-1, ad=-1;
		for (int i = 0; i < 4; i++) {
			ny = r + dy[d][i];
			nx = c + dx[d][i];
			nd = d;
			if (ny < 0 || ny >= N || nx < 0 || nx >= M)
				continue;
			if (map[ny][nx] == 0) {
				// isFull = false;
				// d = (d - i - 1) % 3;
				// r = ny;
				// c = nx;
				ay = ny;
				ax = nx;
				ad = d-i-1;
				if(ad<0) ad=ad+4;
				isFind = true;
				break;
			}
		}
		if (!isFind) {
			ny = r + dy[d][1];
			nx = c + dx[d][1];
			if (ny >= 0 && ny < N && nx >= 0 && nx < M && map[ny][nx] != 1) {
				isFind=true;
				ay=ny;
				ax=nx;
				ad=d;
				//clean(ny, nx, d);
			} 
			else return;
		}
		if(isFind) clean(ay, ax, ad);

	}

}
