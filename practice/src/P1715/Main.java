package P1715;

import java.io.*;
import java.util.*;


public class Main {
	
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr=new int[N];
		PriorityQueue pq=new PriorityQueue();
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(br.readLine());
			pq.add(arr[i]);
		}
		//PriorityQueue pg=new PriorityQueue();
		int ans=0;
		//System.out.println(pq.toString());
		while(pq.size()>1) {
			int a=(int) pq.poll();
			int b=(int) pq.poll();
			ans+=a;
			ans+=b;
			pq.add(a+b);
		}
		System.out.println(ans);
	}

}
