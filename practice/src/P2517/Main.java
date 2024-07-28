package P2517;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static int N;
	static Player[] players;
	static int[] tree;
	static int S;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb=new StringBuilder();
		N = Integer.parseInt(br.readLine()); //선수의 수 
		players = new Player[N]; //실력을 저장한다 
		for (int i = 0; i < N; i++) {
			players[i] = new Player(i, Integer.parseInt(br.readLine()));
		}

		Arrays.sort(players, new Comparator<>() {
			public int compare(Player o1, Player o2) {
				if(o1.power>o2.power) return 1;
				else if(o1.power==o2.power) return 0;
				else return -1;
			}
		});

		for (int i = 0; i < N; i++) {
			players[i].power = i + 1;
		}

		Arrays.sort(players, new Comparator<>() {
			@Override
			public int compare(Player o1, Player o2) {
				return o1.index - o2.index;
			}
		});
		
		//인덱스 트리 만들
		S = 1;
		while (S < N) {
			S = S * 2;
		}
		tree = new int[2 * S];

		for (int i = 0; i < N; i++) {
			int power = (int) players[i].power;
			update(power);
			int pre_player=(i)-query(1, S, 1, 1, power-1)+1;
			sb.append(pre_player).append("\n");
		}
		System.out.println(sb);
	}
	
	//static int query2(int queryLeft, int queryRight, )
	
	static int query(int left, int right, int node, int queryLeft, int queryRight) {
		if(queryLeft>right || queryRight<left) return 0;
		else if(queryLeft<=left && right<=queryRight) {
			return tree[node];
		}else {
			int mid=(left+right)/2;
			return query(left, mid, node*2, queryLeft, queryRight)+
					query(mid+1, right, node*2+1, queryLeft, queryRight);
		}
	}

	static void update(int power) {
		int index=(power-1+S);
		tree[index]+=1;
		int parent=index/2;
		while(parent>0) {
			tree[parent]+=1;
			parent=parent/2;
		}
	}

	static class Player {
		int index;
		int power;

		public Player(int index, int power) {
			this.index = index;
			this.power = power;
		}

		@Override
		public String toString() {
			return "Player [index=" + index + ", power=" + power + "]";
		}

	}

}
