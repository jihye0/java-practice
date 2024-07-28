package P14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
	static int n;
	static int m;
	static int[][] map;
	static boolean[][] visited;
	static int[][] result;
	static int[] dx= {0, 0, -1, 1};
	static int[] dy= {-1, 1, 0, 0};
	static int ex;
	static int ey;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		visited=new boolean[n][m];
		result=new int[n][m];
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					ex=i;
					ey=j;
				}
			}
		}
		bfs(ex, ey);
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==1 && result[i][j]==0) result[i][j]=-1;
				sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	static void bfs(int x, int y) {
		Queue<Node> q=new ArrayDeque<Node>();
		q.add(new Node(x, y));
		visited[x][y]=true;
		while(!q.isEmpty()) {
			Node now=q.poll();
			for(int i=0; i<4; i++) {
				int nx=now.x+dx[i];
				int ny=now.y+dy[i];
				if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==1) {
					q.add(new Node(nx, ny));
					visited[nx][ny]=true;
					result[nx][ny]=result[now.x][now.y]+1;
				}
			}
		}
		
	}
}
