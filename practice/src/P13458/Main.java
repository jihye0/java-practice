package P13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int B;
	static int C;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		arr=new int[N];
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		B=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		long ans=0;
		for(int i=0; i<N; i++) {
			int num=1;
			arr[i]=arr[i]-B;
			if(arr[i]>0) {
				num+=arr[i]/C;
				if(arr[i]%C!=0) num++;
					
			}
			ans+=num;
		}
		System.out.println(ans);

	}

}
