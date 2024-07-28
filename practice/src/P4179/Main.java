package P4179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int y;
	int x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	static int R;
	static int C;
	static int[][] map;
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		Queue<Point> q=new ArrayDeque<>();
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map=new int[R][C];
		for(int i=0; i<R; i++) {
			String str=br.readLine();
			for(int j=0; j<C; j++) {
				if(str.charAt(j)=='#') map[i][j]=-3;
				else if(str.charAt(j)=='.') map[i][j]=-2;
				else if(str.charAt(j)=='J') {
					map[i][j]=0;
					//q.add(new Point(i, j));
				}
				else if(str.charAt(j)=='F') {
					map[i][j]=-1;
					//q.add(new Point(i, j));
				}
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]==-1) q.add(new Point(i, j));
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]==0) q.add(new Point(i, j));
			}
		}
//		for(int i=0; i<R; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		boolean isPossible=false;
		int ans=0;
		while(!q.isEmpty()) {
			Point now=q.poll();
			//목적지인가?
			if(map[now.y][now.x]>=0) {
				if(now.y==0 || now.y==(R-1) || now.x==0 || now.x==(C-1)) {
					isPossible=true;
					ans=map[now.y][now.x];
					break;
				}
			}
			for(int i=0; i<4; i++) {
				int ny=now.y+dy[i];
				int nx=now.x+dx[i];
				if(ny<0 || ny>=R) continue;
				if(nx<0 || nx>=C) continue;
				if(map[ny][nx]==-3) continue;
				if(map[now.y][now.x]>=0) {
					if(map[ny][nx]==-2) {
						map[ny][nx]=map[now.y][now.x]+1;
						q.add(new Point(ny, nx));
					}
				}
				else if(map[now.y][now.x]==-1) {
					if(map[ny][nx]==-2) {
						map[ny][nx]=-1;
						q.add(new Point(ny, nx));
					}
				}
				
			}
		}
//		for(int i=0; i<R; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		if(isPossible) System.out.println(ans+1);
		else System.out.println("IMPOSSIBLE");
		

	}

}
