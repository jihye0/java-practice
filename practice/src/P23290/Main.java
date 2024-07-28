package P23290;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

class Fish{
	int fy;
	int fx;
	int d;
	@Override
	public String toString() {
		return "Fish [fy=" + fy + ", fx=" + fx + ", d=" + d + "]";
	}
	public Fish(int fy, int fx, int d) {
		super();
		this.fy = fy;
		this.fx = fx;
		this.d = d;
	}
	
}

public class Main {
	static int M; //물고기의 수
	static int S; //상어가 법을 연습한 횟수 
	static ArrayList<Integer>[][] fish; 
	static int[][] smell; //물고기 냄새 
	static int sx; //상어의 위치 
	static int sy; //상어의 위치
	static ArrayList<Fish> copy_fish;
	static int[] dx= {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy= {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] sdx= {0, 0, -1, 0, 1};
	static int[] sdy= {0, -1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		S=Integer.parseInt(st.nextToken());
		fish=new ArrayList[5][5];
		for(int i=1; i<5; i++) {
			for(int j=1; j<5; j++) {
				fish[i][j]=new ArrayList<Integer>();
			}
		}
		smell=new int[5][5];
		copy_fish=new ArrayList<Fish>();

		//값 입력받기
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int fx=Integer.parseInt(st.nextToken());
			int fy=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			fish[fx][fy].add(d);
		}

		st=new StringTokenizer(br.readLine());
		sy=Integer.parseInt(st.nextToken());
		sx=Integer.parseInt(st.nextToken());
		
		
		for(int magic=1; magic<=S; magic++) {
			//물고기 복제 마법 copy_fish안에 현재 map에 있는 fish 넣어두기 
			copy_fish.clear();
			for(int i=1; i<5; i++) {
				for(int j=1; j<5; j++) {
					if(fish[i][j].size()>0) {
						for(int k=0; k<fish[i][j].size(); k++) {
							copy_fish.add(new Fish(i, j, fish[i][j].get(k)));
						}
					}
				}
			}
	
			/////물고기 이동
			ArrayList<Fish> move_fish=new ArrayList<>();
			for(int i=1; i<5; i++) {
				for(int j=1; j<5; j++) {
					if(fish[i][j].size()>0) {
						for(int k=0; k<fish[i][j].size(); k++) {
							int dir=fish[i][j].get(k);
							boolean isMove=false;
							for(int d=0; d<=7; d++) {
								int ndir=dir-d;
								if(ndir<=0) ndir=ndir+8;
								int ny=i+dy[ndir];
								int nx=j+dx[ndir];
								if(nx<=0 || nx>=5 || ny<=0 || ny>=5) continue;
								else if(ny==sy && nx==sx) continue;
								else if(smell[ny][nx]>0) continue;
								else {
									isMove=true;
									move_fish.add(new Fish(ny, nx, ndir));
									break;
								}
							}
							if(!isMove) move_fish.add(new Fish(i, j, dir));
						}
					}
				}
			}
			fish=new ArrayList[5][5];
			for(int i=1; i<5; i++) {
				for(int j=1; j<5; j++) {
					fish[i][j]=new ArrayList<Integer>();
				}
			}
			for(int i=0; i<move_fish.size(); i++) {
				Fish nf=move_fish.get(i);
				fish[nf.fy][nf.fx].add(nf.d);
			}
			//상어 이동
			max_arr=new int[3];
			arr=new int[3];
			fish_max=-1;
			visited=new boolean[5][5];
			dfs(0, 0, sx, sy);
			//System.out.println(Arrays.toString(max_arr));
			for(int i=0; i<3; i++) {
				int sdir=max_arr[i];
				sx=sx+sdx[sdir];
				sy=sy+sdy[sdir];
				if(fish[sy][sx].size()>0) {
					fish[sy][sx].clear();
					smell[sy][sx]=magic;
				}
				
			}
			//물고기 냄새 사라지게 하기
			for(int i=1; i<5; i++) {
				for(int j=1; j<5; j++) {
					if(smell[i][j]>0 && smell[i][j]==magic-2) smell[i][j]=0;
				}
			}
			//복제 마법 발동 
			for(int i=0; i<copy_fish.size(); i++) {
				Fish nf=copy_fish.get(i);
				fish[nf.fy][nf.fx].add(nf.d);
			}

		}
//		System.out.println("smell--------");
//		for(int i=1; i<5; i++) {
//			for(int j=1; j<5; j++) {
//				System.out.print(smell[i][j]);
//				System.out.print(' ');
//			}
//			System.out.print('\n');
//		}
//		System.out.println("------------");
//		System.out.println("fish--------");
//		for(int i=1; i<5; i++) {
//			for(int j=1; j<5; j++) {
//				System.out.print(fish[i][j].size());
//				System.out.print(' ');
//			}
//			System.out.print('\n');
//		}
//		System.out.println("------------");
		int result=0;
		for(int i=1; i<5; i++) {
			for(int j=1; j<5; j++) {
				result+=fish[i][j].size();
			}
		}
		System.out.println(result);
	}
	static int fish_max;
	static int[] max_arr;
	static int[] arr;
	static boolean[][] visited;
	static void dfs(int depth, int num, int x, int y) {
		if(depth==3){

			if(num>fish_max) {
				fish_max=num;
				max_arr[0]=arr[0];
				max_arr[1]=arr[1];
				max_arr[2]=arr[2];
			}
			return;
		}
		for(int i=1; i<=4; i++) {
			int nx=x+sdx[i];
			int ny=y+sdy[i];
			if(nx<=0 || nx>=5 || ny<=0 || ny>=5) continue;
			if(!visited[ny][nx]) {
				arr[depth]=i;
				visited[ny][nx]=true;
				int p_fish=fish[ny][nx].size();
				dfs(depth+1, num+p_fish, nx, ny);
				visited[ny][nx]=false;
			}
			else {
				arr[depth]=i;
				dfs(depth+1, num, nx, ny);
			}
//			arr[depth]=i;
//			int p_fish=fish[ny][nx].size();
//			visited[ny][nx]=true;
//			dfs(depth+1, num+p_fish, nx, ny);
//			visited[ny][nx]=false;
		}
	}

}
