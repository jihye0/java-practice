package P14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; //날짜 수 
	static int[] T;
	static int[] P;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		T=new int[N];
		P=new int[N];
		dp=new int[N+1];
		for(int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			T[i]=Integer.parseInt(st.nextToken());
			P[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			if(i+T[i]<=N) {
				dp[i+T[i]]=Math.max(dp[i+T[i]], dp[i]+P[i]);
			}
			dp[i+1]=Math.max(dp[i+1], dp[i]);
		}
		System.out.println(dp[N]);
	}

}
