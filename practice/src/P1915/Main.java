package P1915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] nums;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		dp=new int[n+1][m+1];
		nums=new int[n+1][m+1];
		
		for(int i=1; i<=n; i++) {
			String tmp = br.readLine();
			for(int j=1; j<=m; j++) {
				nums[i][j]=tmp.charAt(j-1)-'0';
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(nums[i][j]==1) {
					dp[i][j]=Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
				}
				else
					dp[i][j]=0;
			}
		}

		
		//for(int i=0; i<=n; i++) System.out.println(Arrays.toString(nums[i]));
		//for(int i=0; i<=n; i++) System.out.println(Arrays.toString(dp[i]));
		
		int max=0;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				max=Math.max(dp[i][j], max);
			}
		}
		System.out.println(max*max);

	}


}