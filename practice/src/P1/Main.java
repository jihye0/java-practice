import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Player{
	int x; 
	int y;
	int d; //방향 
	int s; //초기능력치 
	int g; //가지고 있는 총의 공격력 
	public Player(int x, int y, int d, int s, int g) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
		this.s = s;
		this.g = g;
	}
	@Override
	public String toString() {
		return "Player [x=" + x + ", y=" + y + ", d=" + d + ", s=" + s + ", g=" + g + "]";
	}
	
}

public class Main {
	static int n; //격자의 크기
	static int m; //플레이어의 수
	static int k; //라운드의 수 
	static int[] point; //각 플레이어의 얻은 포인트 
	static Player[] player; //각 플레이어의 정보를 담은 배열 
	static PriorityQueue<Integer>[][] gun; //총의 지도 
	static int[][] pmap; //플레이어의 위치를 표시한다 
	static int[] dx= {0, 1, 0, -1}; // 0 위 1 오 2 아 3 왼 
	static int[] dy= {-1, 0, 1, 0};
	

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		point=new int[m+1];
		player=new Player[m+1];
		gun=new PriorityQueue[n+1][n+1];
		pmap=new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				gun[i][j]=new PriorityQueue<Integer>(Collections.reverseOrder());
			}
		}
		
		for(int i=1; i<=n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				gun[i][j].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=1; i<=m; i++) {
			st=new StringTokenizer(br.readLine());
			int ty=Integer.parseInt(st.nextToken());
			int tx=Integer.parseInt(st.nextToken());
			int td=Integer.parseInt(st.nextToken());
			int ts=Integer.parseInt(st.nextToken());
			player[i]=new Player(tx, ty, td, ts, 0);
			pmap[ty][tx]=i;
		}
//		for(int i=1; i<=n; i++) {
//			System.out.println(Arrays.toString(gun[i]));
//		}
		for(int round=1; round<=k; round++) {
			//k번의 라운드 진행 
			for(int p=1; p<=m; p++) {
				///////////
				//플레이어가 갈 위치를 정하기!!
				int nx=player[p].x+dx[player[p].d];
				int ny=player[p].y+dy[player[p].d];
				if(nx<=0 || nx>n || ny<=0 || ny>n) {
					//플레이어의 방향을 바꾼다 
					if(player[p].d<2) {
						player[p].d+=2;
					}else player[p].d-=2;
					nx=player[p].x+dx[player[p].d];
					ny=player[p].y+dy[player[p].d];
				}
				if(gun[ny][nx].isEmpty() && pmap[ny][nx]==0) {
					//총도 없고 플레이어도 없다면
					//그냥 이동만 함..이동했으면 pmap update 해야겠지? 
					pmap[player[p].y][player[p].x]=0;
					player[p].x=nx;
					player[p].y=ny;
					pmap[ny][nx]=p;
					//System.out.println(Arrays.toString(player));
					continue;
				}
				if(pmap[ny][nx]==0 && !gun[ny][nx].isEmpty()) {
					//플레이어가 없고..
					//이동하려고 하는 곳 총이 있다.
					if(player[p].g>0) {
						//내가 총을 가지고 있디
						//내 총보다 이 총이 더 크다
						int check=gun[ny][nx].peek();
						if(check>player[p].g) {
							int tmp=player[p].g;
							player[p].g=gun[ny][nx].poll();
							gun[ny][nx].add(tmp);
						}else {
							//내 총이 더 크다 그러면 아무것도 안함 
						}
					}else {
						//총을 가지고 있지 않다!
						player[p].g=gun[ny][nx].poll();
					}
					//플레이어 이동함!
					pmap[player[p].y][player[p].x]=0;
					player[p].x=nx;
					player[p].y=ny;
					pmap[ny][nx]=p;
					//System.out.println(Arrays.toString(player));
					continue;
				}
				if(pmap[ny][nx]>0) {
					//이동하려고 하는 곳에 플레이어가 있다.
					pmap[player[p].y][player[p].x]=0;
					player[p].x=nx;
					player[p].y=ny;
					//싸운다
					int e=pmap[ny][nx]; //적의 넘버 
					int p1=player[p].s+player[p].g; //나의 능력치 
					int p2=player[e].s+player[e].g; //적의 능력치
					///
					//ystem.out.println(p1);
					//System.out.println(p2);
					//
					int winner, loser;
					if(p1>p2) {
						winner=p;
						loser=e;
					}else if(p2>p1) {
						winner=e;
						loser=p;
					}else {
						///
						//System.out.println("same");
						//
						if(player[p].s>player[e].s) {
							winner=p;
							loser=e;
						}else {
							winner=e;
							loser=p;
						}
					}
					//이긴 플레이어 포인트 획득
					pmap[ny][nx]=winner;
					point[winner]+=Math.abs(p1-p2);
					//진 플레이어: 총 격자에
					gun[ny][nx].add(player[loser].g);
					player[loser].g=0;
					//한칸 이동
					//static int[] dx= {0, 1, 0, -1}; // 0 위 1 오 2 아 3 왼 
					//static int[] dy= {-1, 0, 1, 0};
					//int nd=player[loser].d; //나의 원래 방
					for(int i=0; i<4; i++) {
						//근데 만약에 갈 수 있는 곳이 없으면 어떡하지????..
						int nd=player[loser].d+i;
						if(nd>3) nd=nd-4;
						int cx=player[loser].x+dx[nd];
						int cy=player[loser].y+dy[nd];
						if(cx<=0 || cx>n || cy<=0 || cy>n) continue;
						if(pmap[cy][cx]>0) continue;
						if(pmap[cy][cx]==0) {
							//내가 이동할 수 있는 칸 발견! 
							if(!gun[cy][cx].isEmpty()) {
								//총이 있음 
								//가장 공격력 높은 총 획득한다 
								player[loser].g=gun[cy][cx].poll();
							}
							player[loser].x=cx;
							player[loser].y=cy;
							pmap[cy][cx]=loser;
							player[loser].d=nd;
							break;
						}
					}
					//이긴 플레이어는 센 총 획득 나머지 격자에 
					if(player[winner].g>=gun[ny][nx].peek()) {
						
					}else {
						int tmp=player[winner].g;
						player[winner].g=gun[ny][nx].poll();
						gun[ny][nx].add(tmp);
					}
				}
//				else {
//					//플레이어 이동함!
//					pmap[player[p].y][player[p].x]=0;
//					player[p].x=nx;
//					player[p].y=ny;
//					pmap[ny][nx]=p;
//				}
				//System.out.println(Arrays.toString(player));
			}
		}
		//System.out.println(Arrays.toString(player));
		//System.out.println(Arrays.toString(point));
		StringBuilder sb=new StringBuilder(); 
		for(int i=1; i<=m; i++) {
			sb.append(point[i]).append(' ');
		}
		System.out.println(sb);

	}

}
