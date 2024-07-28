package P12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; //물품의 수
	static int K; //버틸 수 있는 무게 
	static int[] weights;
	static int[] values;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		dp=new int[K+1][N+1];
		weights=new int[N+1];
		values=new int[N+1];
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			weights[i]=Integer.parseInt(st.nextToken());
			values[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=K; i++) {
			for(int j=1; j<=N; j++) {
				if(i>=weights[j]) {
					dp[i][j]=Math.max(dp[i][j-1], dp[i-weights[j]][j-1]+values[j]);
				}
				else dp[i][j]=dp[i][j-1];
			}
		}
//		for(int i=1; i<=K; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		System.out.println(dp[K][N]);

	}

}
