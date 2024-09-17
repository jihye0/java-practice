package P1459;

import java.io.*;
import java.util.*;

public class Main {

	static long X;
	static long Y;
	static long W; // 가로나 세로로 한 블
	static long S; // 대각선으로 한블

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
		if (2 * W > S) {
			nowX = Math.min(X, Y);
			nowY = Math.min(X, Y);
			ans += S * Math.min(X, Y);
		}
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
