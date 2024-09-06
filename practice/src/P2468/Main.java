package P2468;

import java.io.*;
import java.util.*;

class Node{
	int x;
	int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}
}

public class Main {

	static int N;
	static int max;
	static int ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= {0, -1, 0, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		visited=new boolean[N][N];
		max=0;
		ans=1;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				max=Math.max(max, map[i][j]);
			}
		}
		for(int n=1; n<=max; n++) {
			for(int i=0; i<N; i++) Arrays.fill(visited[i], false);
			int check=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]>n && !visited[i][j]) {
						bfs(n, i, j);
						check++;
					}
				}
			}
			ans=Math.max(ans,  check);
		}
		System.out.println(ans);
	}
	
	
	static void bfs(int n, int x, int y) {
		Queue q=new LinkedList<Node>();
		q.add(new Node(x, y));
		visited[x][y]=true;
		while(!q.isEmpty()) {
			Node now=(Node) q.poll();
			//큐에서 꺼낸다
			for(int i=0; i<4; i++) {
				int nx=now.x+dx[i];
				int ny=now.y+dy[i];
				//갈 수 있는가??
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]<=n) continue;
				//체크인(방문예정)
				visited[nx][ny]=true;
				q.add(new Node(nx, ny));
			}
		}
		
	}
	
}
