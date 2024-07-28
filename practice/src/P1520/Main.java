package P1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int N;
	static int[][] map;
	static int[][] dp;
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= {0, -1, 0, 1};
	static StringBuilder sb=new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		map=new int[M][N];
		dp=new int[M][N];
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<M; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(mem(0, 0));
//		System.out.println(Arrays.toString(dx));
//		System.out.println(Arrays.toString(dy));
//		for(int i=0; i<M; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
	}
	
	static int mem(int i, int j) {
		if(i==M-1 && j==N-1) return 1;
		if(dp[i][j]!=-1) return dp[i][j];
		dp[i][j]=0;
		for(int k=0; k<4; k++) {
			int y=i+dy[k];
			int x=j+dx[k];
			if(y<0 || x<0 || x>N-1 || y>M-1) continue;
			if(map[y][x]<map[i][j]) {
				dp[i][j]+=mem(y, x);
			}	
		}
		return dp[i][j];
	}

}
