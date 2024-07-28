package P1328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R;
	static long[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		dp=new long[N+1][L+1][R+1];
		dp[1][1][1]=1;
		for(int n=2; n<=N; n++) {
			for(int l=1; l<=L; l++) {
				for(int r=1; r<=R; r++) {
					dp[n][l][r]+=dp[n-1][l-1][r]%1000000007;
					dp[n][l][r]+=dp[n-1][l][r-1]%1000000007;
					dp[n][l][r]+=dp[n-1][l][r]*(n-2)%1000000007;
				}
			}
		}
		System.out.println(dp[N][L][R]%1000000007);

	}

}
