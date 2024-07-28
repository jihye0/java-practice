package P2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N; //수의 개수
	static int M; //수의 변경이 일어나는 횟수
	static int K; //구간 합을 구하는 횟
	static int S; //리프 노드의 개
	static long[] nums;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		nums=new long[N];
		for(int i=0; i<N; i++) {
			nums[i]=Long.parseLong(br.readLine());
		}
		S=1;
		while(true) {
			S=S*2;
			if(S>=N) break;
		}
		tree=new long[2*S];
		for(int i=0; i<N; i++) {
			tree[i+S]=nums[i]; //leaf node 채우
		}
		init(1, S, 1);
//		for(int i=S-1; i>0; i--) {
//			tree[i]=tree[2*i]+tree[2*i+1];
//		}
		//System.out.println(Arrays.toString(tree));
		for(int i=0; i<K+M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			//long c=Integer.parseInt(st.nextToken());
			if(a==1) {
				long c=Long.parseLong(st.nextToken());
				long diff=c-tree[b+S-1];
				update(1, S, 1, b, diff);
			}
			else if(a==2) {
				//System.out.println("dfd");
				int c=Integer.parseInt(st.nextToken());
				System.out.println(query(1, S, 1, b, c));
			}
		}
		
	}
	
	static long init(int left, int right, int node) {
		if(node<S) {
			//내부노드
			int mid=(left+right)/2;
			return tree[node]=init(left, mid, 2*node)+init(mid, right, 2*node+1);
		}else {
			//외부노
			return tree[node]; 
		}
	}
	static void update(int left, int right, int index, int target, long diff) {
		if(left<=target && target<=right) {
			int mid=(left+right)/2;
			tree[index]+=diff;
			if(index<S) {
				update(left, mid, index*2, target, diff);
				update(mid+1, right, index*2+1, target, diff);
			}
			else return;
		}
		else return;
	}
	
	static void update2 (int b, long c) {
		int index=b+S-1;
		tree[index]=c;
		int parent=index/2;
		while(true) {
			tree[parent]=tree[2*parent]+tree[2*parent+1];
			if(parent==1) break;
			parent=parent/2;
		}
		//System.out.println(Arrays.toString(tree));
	}
	
	static long query(int left, int right, int index, int queryLeft, int queryRight) {
		if(right<queryLeft || left>queryRight) return 0;
		
		if(queryLeft<=left && queryRight>=right) {
			return tree[index];
		}
		else{
			int mid=(left+right)/2;
			return query(left, mid, index*2, queryLeft, queryRight)+
					query(mid+1, right, index*2+1, queryLeft, queryRight);
		}
	}
	
	static long query2(int left, int right) {
		int l=left+S-1;
		int r=right+S-1;
		long result=0;
		while(l<=r) {
			//System.out.println(result);
			if(l%2==0) l=l/2;
			else {
				result+=tree[l];
				l=(l+1)/2;
			}
			if(r%2==0) {
				result+=tree[r];
				r=(r-1)/2;
			}
			else r=r/2;
		}
		return result;
	}

}
