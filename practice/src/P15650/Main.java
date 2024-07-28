package P15650;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int depth;
	static boolean[] visited;
	static int[] arr;
	static StringBuilder sb=new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		visited=new boolean[N+1];
		arr=new int[M];
		dfs(0, 0);
		System.out.print(sb);
	}
	
	static void dfs(int num, int depth) {
		if(depth==M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i]).append(' ');
			}
			sb.append('\n');
		}
		else {
			for(int i=num+1; i<=N; i++) {
				if(!visited[i]) {
					visited[i]=true;
					arr[depth]=i;
					dfs(i, depth+1);
					visited[i]=false;
				}
			}
		}
	}

}
