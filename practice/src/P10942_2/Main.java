package P10942_2;

import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] arr;
	static boolean[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		N=Integer.parseInt(br.readLine());
		arr=new int[N+1];
		dp=new boolean[N+1][N+1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=N; i++) Arrays.fill(dp[i], false);
		for(int i=1; i<=N; i++) {
			dp[i][i]=true;
		}
		for(int i=1; i<N; i++) {
			if(arr[i]==arr[i+1]) dp[i][i+1]=true;
		}
		for(int d=2; d<N; d++) {
			for(int i=1; (i+d)<=N; i++) {
				if(arr[i]==arr[i+d] && dp[i+1][i+d-1]) {
					dp[i][i+d]=true;
				}
			}
		}
		M=Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int S=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			if(dp[S][E]) sb.append("1\n");
			else sb.append("0\n");
		}
		System.out.println(sb);
	}

}
