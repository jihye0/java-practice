package 	P1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static int length;
	static int[][] C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // "a"의 개수
		M = Integer.parseInt(st.nextToken()); // "z"의 개수
		K = Integer.parseInt(st.nextToken()); // k번째 문자열
		length = N + M;
		C = new int[length + 1][length + 1];
		for (int i = 0; i <= length; i++) {
			C[0][i] = 1;
			C[i][0] = 1;
			C[i][i] = 1;
		}
		for (int i = 1; i <= length; i++) {
			C[0][i] = 0;
		}
		for (int i = 1; i <= length; i++) {
			for (int j = 1; j < i; j++) {
				C[i][j] = Math.min(C[i - 1][j - 1] + C[i - 1][j], 1000000001);
			}
		}
//		for (int i = 0; i <= length; i++) {
//			System.out.println(Arrays.toString(C[i]));
//		}
		String result = "";
		int a_count=N;
		int z_count=M;
		int order = K;
		//int S2=S;

		if (C[length][M] < order)
			System.out.println(-1);
		else { 
			for (int index = 1; index <= length; index++) {
				//System.out.println("K2 "+K2);
				//System.out.println(C[length-index][M]);
				if(z_count==0) {
					a_count--;
					result+="a";
					continue;
				}
				else if(a_count==0) {
					z_count--;
					result+="z";
					continue;
				}
				else if (order <= C[a_count+z_count-1][z_count]) {
					a_count--;
					result += "a";
				} else {
					order = order - C[a_count+z_count-1][z_count];
					z_count--;
					//System.out.println("K2 "+K2);
					result += "z";
				}
				//S2--;
				
			}
			System.out.println(result);
		}
	}
}