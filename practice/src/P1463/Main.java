package P1463;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr=new int[N+1];
		arr[1]=0;
		for(int i=2; i<=N; i++) {
			int min = Integer.MAX_VALUE;
			if (i % 3 == 0) {
				min = Math.min(arr[i/3] + 1, min);
			}
			if (i % 2 == 0) {
				min = Math.min(arr[i/2] + 1, min);
			}
			min = Math.min(arr[i - 1] + 1, min);
			arr[i]=min;
		}
		System.out.println(arr[N]);
	}

}
