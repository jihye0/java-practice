package P1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int T;
	static int M;
	static int N;
	static int K;
	static int[][] map;
	static boolean[][] visited;
	static int result;
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= {0, -1, 0, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int test=1; test<=T; test++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			map=new int[N][M];
			//visited=new boolean[N][M];
			for(int i=0; i<K; i++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				map[y][x]=1;
			}
			result=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==1) {
						bfs(i, j);
						result++;
					}
				}
			}
			System.out.println(result);
		}

	}
	
	static void bfs(int i, int j) {
		Queue<Node> q=new ArrayDeque<>();
		q.add(new Node(i, j));
		map[i][j]=0;
		while(!q.isEmpty()) {
			Node now=q.poll();
			//visited[now.x][now.y]=true;
			map[now.x][now.y]=0;
			for(int k=0; k<4; k++) {
				int nx=now.x+dx[k];
				int ny=now.y+dy[k];
				if(nx<0 || nx >=N ||ny<0 || ny>=M) continue;
				if(map[nx][ny]==1) {
					q.add(new Node(nx, ny));
					map[nx][ny]=0;
				}
			}
		}
	}

}
