package P1459;

import java.io.*;
import java.util.*;

public class Main {

	static long X;
	static long Y;
	static long W; // 가로나 세로로 한 블럭 
	static long S; // 대각선으로 한 블럭 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		long nowX = 0;
		long nowY = 0;
		long ans = 0;
		
		//1. 대각선으로 한번 가는게 가로,세로 거쳐서 가는 것 보다 빠르면
		//갈 수 있는 데까지 대각선으로 가기 
		if (2 * W > S) {
			nowX = Math.min(X, Y);
			nowY = Math.min(X, Y);
			ans += S * Math.min(X, Y);
		}
		
		//2. 가로로 더 가야한다면 
		//대각선 두번으로 가는게 더 빠를 수도 있으니까 체크해서 확인 
		//단, 대각선 두번으로 간다면 무조건 짝수여야함
		if (X > nowX) {
			if (W > S) {
				if ((X - nowX) % 2 == 0) {
					ans += (X - nowX) * S;
				} else {
					ans += (X - nowX - 1) * S;
					ans += W;
				}
			} else {
				ans += (X - nowX) * W;
			}
		}
		//세로로 더 가야한다면.. 위랑 같
		if (Y > nowY) {
			if (W > S) {
				if ((Y - nowY) % 2 == 0) {
					ans += (Y - nowY) * S;
				} else {
					ans += (Y - nowY - 1) * S;
					ans += W;
				}
			} else {
				ans += (Y - nowY) * W;
			}
		}
		System.out.println(ans);

	}

}
