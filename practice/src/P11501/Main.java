package P11501;

import java.util.*;
import java.io.*;

public class Main {
	static int T;
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		T=Integer.parseInt(br.readLine());
		for(int test=1; test<=T; test++) {
			N=Integer.parseInt(br.readLine());
			arr=new int[N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			
			long max=0;
			long ans=0;
			for(int i=N-1; i>=0; i--) {
				if(max<arr[i]) {
					max=arr[i];
				}else {
					ans+=max-arr[i];
				}
			}
			sb.append(ans);
			sb.append('\n');
		}
		System.out.println(sb);
		
	}

}
