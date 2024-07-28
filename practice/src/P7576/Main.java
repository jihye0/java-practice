package P7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class point{
	int y;
	int x;
	public point(int y, int x) {
		this.y = y;
		this.x = x;
	}	
}

public class Main {
	static int M; //가로칸의 수 
	static int N; //세로칸의 수
	static int[][] map;
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= {0, 1, 0, -1};
	static Queue<point> q=new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) q.add(new point(i, j));
			}
		}
		bfs();
//		for(int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		int ans=0;
		boolean fail=false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					fail=true;
				}
				ans=Math.max(ans, map[i][j]);
			}
		}
		if(fail) System.out.println(-1);
		else System.out.println(ans-1);
	}
	
	static void bfs() {
		//Queue<point> q=new ArrayDeque<>();
		//q.add(new point(y, x));
		while(!q.isEmpty()) {
			point now=q.poll();
			for(int i=0; i<4; i++) {
				int ny=now.y+dy[i];
				int nx=now.x+dx[i];
				if(ny<0 || nx<0) continue;
				if(ny>=N || nx>=M) continue;
				if(map[ny][nx]==-1 || map[ny][nx]==1) continue;
				else {
					if(map[ny][nx]==0) {
						map[ny][nx]=map[now.y][now.x]+1;
						q.add(new point(ny, nx));
					}
					else if(map[ny][nx]>map[now.y][now.x]+1) {
						map[ny][nx]=map[now.y][now.x]+1;
						q.add(new point(ny, nx));
					}
				}
			}
		}
	}

}
