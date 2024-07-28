package P2342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer> a;
	static int[][][] d;
	static int[][] s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = new ArrayList<>(); //발 위치 담는 배열 
		a.add(0);
		while (true) {
			int num = Integer.parseInt(st.nextToken());
			if (num == 0)
				break;
			a.add(num);
		}
		
		s = new int[5][5]; //i에서 j로 가는 데 드는 힘을 담은 배열 
		for (int i = 0; i < 5; i++)
			s[i][i] = 1;
		for (int i = 1; i < 5; i++) {
			s[0][i] = 2;
			s[i][0] = 2;
		}
		s[1][2] = 3;
		s[2][1] = 3;
		s[1][4] = 3;
		s[4][1] = 3;
		s[2][3] = 3;
		s[3][2] = 3;
		s[3][4] = 3;
		s[4][3] = 3;
		s[1][3] = 4;
		s[3][1] = 4;
		s[2][4] = 4;
		s[4][2] = 4;

		//int step_num=step.size()-1;
		d = new int[a.size()][5][5];
		d[0][0][0]=0;
		d[1][0][a.get(1)]=s[0][a.get(1)];
		d[1][a.get(1)][0]=s[a.get(1)][0];
		int left, right;
		if(d[1][0][a.get(1)] > d[1][a.get(1)][0]) {
			left=a.get(1);
			right=0;
		}else {
			left=0;
			right=a.get(1);
		}
		for(int i=2; i<a.size(); i++) {
			d[i][a.get(i)][right]=d[i-1][left][right]+s[left][a.get(i)];
			d[i][left][a.get(i)]=d[i-1][left][right]+s[right][a.get(i)];
			if(d[i][a.get(i)][right]>d[i][left][a.get(i)]) {
				right=a.get(i);
			}
			else {
				left=a.get(i);
			}
		}
		System.out.println(d[a.size()-1][left][right]);
	}

}
