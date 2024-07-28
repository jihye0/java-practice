package P2748;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static long[] d;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		d=new long[N+1];
		Arrays.fill(d, -1);
		d[0]=0;
		d[1]=1;
		System.out.println(f(N));

	}
	
	static long f(int n) {
		if(n==0) return 0;
		else if(n==1) return 1;
		if(d[n]!=-1) return d[n];
		return d[n]=f(n-1)+f(n-2);
	}

}
