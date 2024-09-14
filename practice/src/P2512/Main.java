package P2512;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;
	static int total;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr=new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		int check=0;
		int max=0;
		int min=Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
			check+=arr[i];
			max=Math.max(max,  arr[i]);
			min=Math.min(min, arr[i]);
		}
		total=Integer.parseInt(br.readLine());
		if(check<=total) {
			System.out.println(max);
		}else if(min*N>total) {
			int left=0;
			int right=min;
			while(left<=right) {
				int mid=(left+right)/2;
				long budget=0;
				for(int i=0; i<N; i++) {
					if(arr[i]>mid) budget+=mid;
					else budget+=arr[i];
				}
				if(budget<=total) {
					left=mid+1;
				}else {
					right=mid-1;
				}
			}
			System.out.println(right);
		}else {
			int left=min;
			int right=max;
			while(left<=right) {
				int mid=(left+right)/2;
				long budget=0;
				for(int i=0; i<N; i++) {
					if(arr[i]>mid) budget+=mid;
					else budget+=arr[i];
				}
				if(budget<=total) {
					left=mid+1;
				}else {
					right=mid-1;
				}
			}
			System.out.println(right);
		}
	}

}
