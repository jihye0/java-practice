package P2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;

class Node{
	int x;
	int y;
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static String line;
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= {0, -1, 0, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		visited=new boolean[N][N];
		ArrayList answer=new ArrayList<>();
		for(int i=0; i<N; i++) {
			line=br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j]=line.charAt(j)-'0';
			}
		}
		
		//for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		
		//큐에서 꺼내옴
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1 && visited[i][j]==false) {
				answer.add(bfs(i, j));}
			}
		}
		Collections.sort(answer);
		System.out.println(answer.size());
		for(int i=0; i<answer.size(); i++) {
			System.out.println(answer.get(i));
		}
	}
	
	static int bfs(int x, int y) {
		Queue q=new ArrayDeque<Node>();
		q.add(new Node(x, y));
		visited[x][y]=true;
		int size=0;
		
		while(!q.isEmpty()) {
			Node now=(Node) q.poll();
			size++;
			
			for(int d=0; d<4; d++) {
				int nx=now.x+dx[d];
				int ny=now.y+dy[d];
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]==false && map[nx][ny]==1) {
					q.add(new Node(nx, ny));
					visited[nx][ny]=true;
				}
			}
		}
		
		return size;
	}

}
