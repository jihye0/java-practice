package P13460;

import java.io.*;
import java.util.*;

class Point{
	int red_y;
	int red_x;
	int blue_y;
	int blue_x;
	int move;
	public Point(int red_y, int red_x, int blue_y, int blue_x, int move) {
		this.red_y = red_y;
		this.red_x = red_x;
		this.blue_y = blue_y;
		this.blue_x = blue_x;
		this.move = move;
	}
	@Override
	public String toString() {
		return "Point [red_y=" + red_y + ", red_x=" + red_x + ", blue_y=" + blue_y + ", blue_x=" + blue_x + ", move="
				+ move + "]";
	}
	
	
}



public class Main {

	static int N;
	static int M;
	static int[][] map;
	static int hole_x;
	static int hole_y;
	
	static Point left(Point p) {
		Point ans = new Point(0, 0, 0, 0, 0);
		ans.move=p.move+1;
		ans.blue_y=p.blue_y;
		ans.red_y=p.red_y;
		if(p.red_x==1) ans.red_x=1;
		else {
			boolean check=false;
			for(int i=p.red_x-1; i>=1; i--) {
				if(map[p.red_y][i]==-3) {
					ans.red_x=i+1;
					check=true;
					break;
				}
				if(map[p.red_y][i]==-2) {
					ans.red_x=i;
					check=true;
					break;
				}
			}
			if(!check) ans.red_x=1;
		}
		if(p.blue_x==1) ans.blue_x=1;
		else {
			boolean check=false;
			for(int i=p.blue_x-1; i>=1; i--) {
				if(map[p.blue_y][i]==-3) {
					ans.blue_x=i+1;
					check=true;
					break;
				}
				if(map[p.blue_y][i]==-2) {
					ans.blue_x=i;
					check=true;
					break;
				}
			}
			if(!check) ans.blue_x=1;
		}
		if(ans.red_x == ans.blue_x && ans.red_y == ans.blue_y
				&&map[ans.red_y][ans.red_x]==-1) {
			if(p.red_x<p.blue_x) {
				ans.blue_x++;
			}else {
				ans.red_x++;
			}
		}
		
		return ans;
	}
	
	static Point right(Point p) {
		Point ans = new Point(0, 0, 0, 0, 0);
		ans.blue_y=p.blue_y;
		ans.red_y=p.red_y;
		ans.move=p.move+1;
		if(p.red_x==M-2) ans.red_x=M-2;
		else {
			boolean check=false;
			for(int i=p.red_x+1; i<=M-2; i++) {
				if(map[p.red_y][i]==-3) {
					ans.red_x=i-1;
					check=true;
					break;
				}
				if(map[p.red_y][i]==-2) {
					ans.red_x=i;
					check=true;
					break;
				}
			}
			if(!check) ans.red_x=M-2;
		}
		if(p.blue_x==M-2) ans.blue_x=M-2;
		else {
			boolean check=false;
			for(int i=p.blue_x+1; i<=M-2; i++) {
				if(map[p.blue_y][i]==-3) {
					ans.blue_x=i-1;
					check=true;
					break;
				}
				if(map[p.blue_y][i]==-2) {
					ans.blue_x=i;
					check=true;
					break;
				}
			}
			if(!check) ans.blue_x=M-2;
		}
		if(ans.red_x == ans.blue_x && ans.red_y == ans.blue_y
				&&map[ans.red_y][ans.red_x]==-1) {
			if(p.red_x<p.blue_x) {
				ans.red_x--;
			}else {
				ans.blue_x--;
			}
		}
		
		return ans;
	}
	
	static Point up(Point p) {
		Point ans = new Point(0, 0, 0, 0, 0);
		ans.blue_x=p.blue_x;
		ans.red_x=p.red_x;
		ans.move=p.move+1;
		if(p.red_y==1) ans.red_y=1;
		else {
			boolean check=false;
			for(int i=p.red_y-1; i>=1; i--) {
				if(map[i][p.red_x]==-3) {
					ans.red_y=i+1;
					check=true;
					break;
				}
				if(map[i][p.red_x]==-2) {
					ans.red_y=i;
					check=true;
					break;
				}
			}
			if(!check) ans.red_y=1;
		}
		if(p.blue_y==1) ans.blue_y=1;
		else {
			boolean check=false;
			for(int i=p.blue_y-1; i>=1; i--) {
				if(map[i][p.blue_x]==-3) {
					ans.blue_y=i+1;
					check=true;
					break;
				}
				if(map[i][p.blue_x]==-2) {
					ans.blue_y=i;
					check=true;
					break;
				}
			}
			if(!check) ans.blue_y=1;
		}
		if(ans.red_x == ans.blue_x && ans.red_y == ans.blue_y
				&&map[ans.red_y][ans.red_x]==-1) {
			if(p.red_y<p.blue_y) {
				ans.blue_y++;
			}else {
				ans.red_y++;
			}
		}
		
		return ans;
	}
	
	static Point down(Point p) {
		Point ans = new Point(0, 0, 0, 0, 0);
		ans.move=p.move+1;
		ans.blue_x=p.blue_x;
		ans.red_x=p.red_x;
		if(p.red_y==N-2) ans.red_y=N-2;
		else {
			boolean check=false;
			for(int i=p.red_y+1; i<=N-2; i++) {
				if(map[i][p.red_x]==-3) {
					ans.red_y=i-1;
					check=true;
					break;
				}
				if(map[i][p.red_x]==-2) {
					ans.red_y=i;
					check=true;
					break;
				}
			}
			if(!check) ans.red_y=N-2;
		}
		if(p.blue_y==N-2) ans.blue_y=N-2;
		else {
			boolean check=false;
			for(int i=p.blue_y+1; i<=N-2; i++) {
				if(map[i][p.blue_x]==-3) {
					ans.blue_y=i-1;
					check=true;
					break;
				}
				if(map[i][p.blue_x]==-2) {
					ans.blue_y=i;
					check=true;
					break;
				}
			}
			if(!check) ans.blue_y=N-2;
		}
		if(ans.red_x == ans.blue_x && ans.red_y == ans.blue_y
				&&map[ans.red_y][ans.red_x]==-1) {
			if(p.red_x<p.blue_x) {
				ans.red_x--;
			}else {
				ans.blue_x--;
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		//String str = br.readLine();
		Point first=new Point(0, 0, 0, 0, 0);
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
					if (str.charAt(j) == '.')
						map[i][j] = -1;
					else if (str.charAt(j) == '#')
						map[i][j] = -3;
					else if (str.charAt(j) == 'O') {
						map[i][j] = -2;
						hole_y=i;
						hole_x=j;
					}
						
					else if (str.charAt(j) == 'R') {
						map[i][j] = 0;
						first.red_x=j;
						first.red_y=i;
					}
						
					else if (str.charAt(j) == 'B') {
						map[i][j] = 1;
						first.blue_x=j;
						first.blue_y=i;
					}
			}

		}

		Queue<Point> q=new ArrayDeque<Point>();
		q.add(first);
		int result=0;
		while(!q.isEmpty()) {
			//꺼낸다
			Point check=q.poll();
			//목적지인가?
			if(check.red_x==hole_x && check.red_y==hole_y) {
				if(check.blue_x==hole_x && check.blue_y==hole_y)
				{
					result=100;
					break;
				}
				result=check.move;
				break;
			}else if(check.move==10) {
				result=100;
				break;
			}
			//갈 수 있는가??
			//큐에 넣는다
			Point left_check=left(check);
			if(left_check.red_x==left_check.blue_x 
					&& left_check.blue_y==left_check.blue_x) {}
			else if(left_check.blue_y==hole_y && left_check.blue_x==hole_x) {}
			else q.add(left_check);
			
			Point right_check=right(check);
			if(right_check.red_x==right_check.blue_x 
					&& right_check.blue_y==right_check.blue_x) {}
			else if(right_check.blue_y==hole_y && right_check.blue_x==hole_x) {}
			else q.add(right_check);
			
			Point up_check=up(check);
			if(up_check.red_x==up_check.blue_x 
					&& up_check.blue_y==up_check.blue_x) {}
			else if(up_check.blue_y==hole_y && up_check.blue_x==hole_x) {}
			else q.add(up_check);
			
			Point down_check=down(check);
			if(down_check.red_x==down_check.blue_x 
					&& down_check.blue_y==down_check.blue_x) {}
			else if(down_check.blue_y==hole_y && down_check.blue_x==hole_x) {}
			else q.add(down_check);
		}
		if(result==100)System.out.println(-1);
		else System.out.println(result);
		
	}

}
