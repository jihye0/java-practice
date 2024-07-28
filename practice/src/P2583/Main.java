package P2583;
import java.util.*;
import java.io.*;

class Point{
	int y;
	int x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + "]";
	}
	
	
}

public class Main {
	static int M; //세로
	static int N; //가로 
	static int K; //직사각형의 개
	static int[][] map;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static List<Integer> ans=new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new int[M][N];
		visited=new boolean[M][N];
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine());
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			for(int y=y1; y<y2; y++) {
				for(int x=x1; x<x2; x++) {
					map[y][x]=1;
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0 && visited[i][j]==false) {
					ans.add(bfs(i, j));
				}
			}
		}
		Collections.sort(ans);
		StringBuilder sb=new StringBuilder();
		System.out.println(ans.size());
		for(int i=0; i<ans.size(); i++) {
			sb.append(ans.get(i)).append(" ");
		}
		System.out.println(sb);
	}
	
	static int bfs(int y, int x) {
		Queue q= new ArrayDeque<Point>();
		q.add(new Point(y, x));
		visited[y][x]=true;
		int size=0;
		
		while(!q.isEmpty()) {
			Point now_p=(Point) q.poll();
			size++;
			//int check=0;
			for(int i=0; i<4; i++) {
				int ny=now_p.y+dy[i];
				int nx=now_p.x+dx[i];
				if(ny<0 || ny>=M || nx<0 || nx>=N) continue;
				if(visited[ny][nx]==false && map[ny][nx]==0) {
					q.add(new Point(ny, nx));
					visited[ny][nx]=true;
					//check++;
				}
			}
			//if(check==0) break;
		}
		
		return size;
	}

}
