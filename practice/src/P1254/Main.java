package P1254;

import java.util.*;
import java.io.*;

public class Main {
	
	static String str;
	static boolean find;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		str=br.readLine();
		int len=str.length();
		
		for(int i=0; i<=len; i++) {
//			if(isPalindrome(str.substring(0, i))) {
//				System.out.println(str.substring(0, i));
//				System.out.println(len+len-i);
//				break;
//			}
			if(isPalindrome(str.substring(i, len))) {
				//System.out.println(str.substring(i, len));
				System.out.println(len+i);
				break;
			}
		}
	}

	static boolean isPalindrome(String str) {
		int mid=str.length()/2;
		for(int i=0; i<mid; i++) {
			if(str.charAt(i)!=str.charAt(str.length()-i-1)) {
				return false;
			}
		}
		return true;
	}

}
