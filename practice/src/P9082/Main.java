package P9082;

import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int N;
	static int[] arr;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			check = new boolean[N];
			Arrays.fill(check, false);
			String tmp = br.readLine();
			int result=0;
			for (int i = 0; i < N; i++) {
				arr[i] = tmp.charAt(i) - '0';
			}
			char[] map = br.readLine().toCharArray();
			
			for(int i=0; i<N; i++) {
				if(i==0) {
					if(arr[i]>0 && arr[i+1]>0) {
						arr[i]--;
						arr[i+1]--;
						result++;
					}
				}else if(i==(N-1)) {
					if(arr[i]>0 && arr[i-1]>0) {
						arr[i]--;
						arr[i-1]--;
						result++;
					}
				}else {
					if(arr[i]>0 && arr[i-1]>0 && arr[i+1]>0) {
						arr[i]--;
						arr[i-1]--;
						arr[i+1]--;
						result++;
					}
				}
			}

			//System.out.println(Arrays.toString(arr));
			//System.out.println(Arrays.toString(map));
			// System.out.println(Arrays.toString(arr));
			System.out.println(result);
		}
	}

}
