package P1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] node;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		node=new int[26][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char v = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);
			node[v-'A'][0]=l-'A';
			node[v-'A'][1]=r-'A';
		}
//		for(int i=0; i<26; i++) {
//			System.out.println(Arrays.toString(node[i]));
//		}
		pre_order(0);
		System.out.print("\n");
		in_order(0);
		System.out.print("\n");
		post_order(0);
		//int ans='.'-'A';
		//System.out.println(ans);
	}

	static void pre_order(int x) {
		if(x<0) return;
		char ans=(char) (x+'A');
		System.out.print(ans);
		pre_order(node[x][0]);
		pre_order(node[x][1]);
	}
	
	static void in_order(int x) {
		if(x<0) return;
		in_order(node[x][0]);
		char ans=(char) (x+'A');
		System.out.print(ans);
		in_order(node[x][1]);
	}
	
	static void post_order(int x) {
		if(x<0) return;
		post_order(node[x][0]);
		post_order(node[x][1]);
		char ans=(char) (x+'A');
		System.out.print(ans);
	}
}
