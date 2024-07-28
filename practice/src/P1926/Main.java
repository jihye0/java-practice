package P1926;
import java.io.*;
import java.util.*;

class Point{
	int n;
	int m;
	
	public Point(int n, int m) {
		this.n = n;
		this.m = m;
	}

	@Override
	public String toString() {
		return "Node [n=" + n + ", m=" + m + "]";
	}
	
}

public class Main {
	static int n; //세로크기 
	static int m; //가로크기 
	static int[][] map;
	static boolean[][] visited;
	static int count=0;
	static int[] dn= {-1, 0, 1, 0};
	static int[] dm= {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		visited=new boolean[n][m];
		boolean zero=false;
		//Queue q=new ArrayDeque<Point>();
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					zero=true;
					//q.add(new Point(i, j));
				}
			}
		}
		List<Integer> ans=new ArrayList<>();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				count=0;
				if(map[i][j]==1 && !visited[i][j]) {
					count++;
					bfs(i, j);
					ans.add(count);
				}
			}
		}
//		while(!q.isEmpty()) {
//			Point check=(Point) q.poll();
//			
//			if(visited[check.n][check.m]==false) {
//				for(int i=0; i<4; i++) {
//					int new_n=check.n+dn[i];
//					int new_m=check.m+dm[i];
//					if(new_n<0 || new_m<0 || new_n>n-1 || new_m>m-1) continue;
//					if(map[new_n][new_m]==1 && visited[new_n][new_m]==false) {
//						q.add(new Point(new_n, new_m));
//						visited[new_n][new_m]=true;
//					}
//				}
//			}
//		}
		
		//큐에서꺼내옴 
		//목적지인가 
		//연결된 곳을 순회 
		//갈 수 있는가
		//체크인 방문예정 
		//큐에 넣
		//////////////////////////////
		Collections.sort(ans);
		if(!zero) System.out.println(0);
		else System.out.println(ans.size());
		if(!zero) System.out.println(0);
		else System.out.println(ans.get(ans.size()-1));
		//System.out.println(sb);
	}
	
	static void bfs(int y, int x) {
		visited[y][x]=true;
		Queue<Point> q=new ArrayDeque<>();
		q.add(new Point(y, x));
		while(!q.isEmpty()) {
			Point check=q.poll();
			int nowY=check.n;
			int nowX=check.m;
			for(int i=0; i<4; i++) {
				int nY=nowY+dn[i];
				int nX=nowX+dm[i];
				if(nY<0 || nY>=n || nX<0 || nX>=m) continue;
				if(map[nY][nX]==1 && !visited[nY][nX]) {
					count++;
					visited[nY][nX]=true;
					q.add(new Point(nY, nX));
				}
			}
		}
	}

}
