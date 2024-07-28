package P1028;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int[][] a;
	static int[][] d;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// String filePath="/practice/src/P1028/input.txt";
		// FileReader fileReader=new FileReader(filePath);
		// BufferedReader br=new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		a = new int[R + 1][C + 1];
		d = new int[R + 1][C + 1];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			// System.out.println(line);
			for (int j = 0; j < C; j++) {
				char c = line.charAt(j);
				a[i + 1][j + 1] = c - '0';
			}
		}
//		for(int i=1; i<=R; i++) {
//			System.out.println(Arrays.toString(a[i]));
//		}
		int ans = 0;
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (i == 1 || j == C)
					d[i][j] = a[i][j];
				else if (d[i - 2][j] > 0 && d[i - 1][j - 1] > 0 && d[i - 1][j + 1] > 0) {
					d[i][j] = Math.min(d[i - 2][j], d[i - 1][j - 1]);
					d[i][j] = Math.min(d[i][j], d[i - 1][j + 1]) + 1;
					if(a[i][j]==1) ans=Math.max(ans, d[i][j]);
				}
				else d[i][j]=a[i][j];
			}
		}
//		for (int i = 1; i <= R; i++) {
//			System.out.println(Arrays.toString(d[i]));
//		}
		System.out.println(ans);

	}

}
