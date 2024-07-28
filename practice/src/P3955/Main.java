package P3955;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int t; // 테스트케이스 개수
	// static int k; //파티에 참가하는 사람의 수
	// static int c; //한 봉지에 들어있는 사의
	// X: 인당 나눠 줄 사탕의 수
	// Y: 사탕 봉지의 수
	// A* X +1 = b*Y
	// AX+BY=C의 형태로 변환
	// -AX+BY=1
	// A(-X)+BY=1
	static long A, B;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		// D = gcd(A, B)
		// Ax+By=C일때, C%D==0 이어야 해를 가질 수 있음: 베주 항등식.

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			if(B==1) {
				if(A>=1000000000) sb.append("IMPOSSIBLE").append("\n");
				else sb.append(A+1).append("\n");
				continue;
			}
			else if(A==1) {
				sb.append(1).append("\n");
				continue;
			}
			EGResult result = egcd(A, B);
			//System.out.println(result);
			if (result.r != 1) {
				sb.append("IMPOSSIBLE").append("\n");
			}
			else if(result.t>0) {
				if(result.t>1000000000) sb.append("IMPOSSIBLE").append("\n");
				else sb.append(result.t).append("\n");
			}
			else {
				long ans=result.t;
				while(ans<0) {
					ans+=A;
				}
				if(ans>1000000000) sb.append("IMPOSSIBLE").append("\n");
				else sb.append(ans).append("\n");
			}
//			else {
//				// x0=s*C/D
//				// y0=t*C/D
//				long x0 = result.s;
//				long y0 = result.t;
//
//				// 일반해공식
//				// x=x0+B/D*k
//				// y=y0-A/D*k
//
//				// x<0
//				// x0 + B*K <0
//				// k < -x0/B
//
//				// 0 < y <=1e9
//				// 0 < y0-A*k <=1e9
//				// -y0 < -A*k <= 1e0-y0
//				// ( y0 - 1e9 )/A <= k < y0 / A
//
//				long kFromY = (long) Math.ceil((double) y0 / (double) A) - 1;
//				long kFromX = (long) Math.ceil((double) -x0 / (double) B) - 1;
//				long k = Math.min(kFromX, kFromY);
//				long kLimitFromY = (long) Math.ceil((double) (y0 - 1e9) / (double) A);
//				if (kLimitFromY <= k) {
//					System.out.println(y0 - A * k);
//					sb.append(y0 - A * k).append("\n");
//				} else {
//					sb.append("IMPOSSIBLE").append("\n");
//				}
//
//			}

		}

		System.out.println(sb);

		// 해가 없음
	}

	// As+Bt=r을 만족하는 s, t, r을 찾는 과정

	static EGResult egcd(long a, long b) {
		long s0 = 1, t0 = 0, r0 = a;
		long s1 = 0, t1 = 1, r1 = b;

		long temp;
		while (r1 != 0) {
			long q = r0 / r1;

			temp = r0 - q * r1; // temp=r0%r1
			r0 = r1;
			r1 = temp;

			temp = s0 - q * s1;
			s0 = s1;
			s1 = temp;

			temp = t0 - q * t1;
			t0 = t1;
			t1 = temp;

		}
		return new EGResult(s0, t0, r0);
	}

}

class EGResult {
	long s;
	long t;
	long r;

	public EGResult(long s, long t, long r) {
		this.s = s;
		this.t = t;
		this.r = r;
	}

	@Override
	public String toString() {
		return "EGResult [s=" + s + ", t=" + t + ", r=" + r + "]";
	}

}