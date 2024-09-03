package P21921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, X;
	static int[] num;
	static int[] sum;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		num=new int[N];
		sum=new int[N+1];
		st=new StringTokenizer(br.readLine());
		int val=0;
		sum[0]=0;
		for(int i=0; i<N; i++) {
			num[i]=Integer.parseInt(st.nextToken());
			val+=num[i];
			sum[i+1]=val;
		}
		//System.out.println(Arrays.toString(num));
		//System.out.println(Arrays.toString(sum));
		
		//각 구간에서 최댓값 구하기
		int max=0;
		int how=0;
		for(int i=0; i<=N-X; i++) {
			int cal=sum[i+X]-sum[i];
			if(cal>max) {
				max=cal;
				how=1;
			}else if(cal==max) {
				how++;
			}
		}
		if(max==0) {
			System.out.println("SAD");
		}else {
			System.out.println(max);
			System.out.println(how);
		}
		//System.out.println(max);
		
	}

}
