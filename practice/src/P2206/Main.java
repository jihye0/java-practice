package P2206;

import java.util.*;
import java.io.*;

class Node{
	int x;
	int y;
	boolean canBreak;
	
	public Node(int x, int y, boolean canBreak){
		this.x=x;
		this.y=y;
		this.canBreak=canBreak;
	}

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + ", canBreak=" + canBreak + "]";
	}
	
}

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][][] step;
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		step=new int[N][M][2];

		for(int i=0; i<N; i++) {
			String str=br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}
		//최단거리를 구하기 불가능하면 -1
		//만약 짧아진다면 한개의 벽 부수기 가능
		step[0][0][1]=0;
		bfs(0, 0, true);

	}
	
	static void bfs(int x, int y, boolean canBreak) {
		//for(int i=0; i<N; i++) System.out.println(Arrays.toString(step[i]));
		Queue<Node> q=new LinkedList<Node>();
		q.add(new Node(x, y, canBreak));
		while(!q.isEmpty()) {
			
			Node now=q.poll();
			int nextStep=0;
			if(now.canBreak==true) {
				nextStep=step[now.x][now.y][0]+1;
			}else {
				nextStep=step[now.x][now.y][1]+1;
			}
			
			//목적지인가??
			if(now.x==(N-1) && now.y==(M-1)) {
				System.out.println(nextStep);
				return;
			}
			for(int i=0; i<4; i++) {
				int nx=now.x+dx[i];
				int ny=now.y+dy[i];
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				
				if(map[nx][ny]==0) {
					//벽이 아니면
					if(now.canBreak && step[nx][ny][0]==0) {
						q.add(new Node(nx, ny, true));
						step[nx][ny][0]=nextStep;
					}
					else if(!now.canBreak && step[nx][ny][1]==0) {
						q.add(new Node(nx, ny, false));
						step[nx][ny][1]=nextStep;
					}
					
				}else if(map[nx][ny]==1) {
					if(now.canBreak) {
						q.add(new Node(nx, ny, false));
						step[nx][ny][1]=nextStep;
					}
				}
			}
			
		}
		System.out.println(-1);
	}

}
