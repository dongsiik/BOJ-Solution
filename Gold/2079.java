
// 제목 : 팰린드롬
// 티어 : 골드 2
// 링크 : https://www.acmicpc.net/problem/2079
// 메모리(kb) : 33804
// 실행시간(ms) : 264

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	//입력받은 문자열과 그 길이
	static String inputString;
	static int N;
	// checkPalindromeTable[start][end]는 start부터 end까지 부분문자열이 팰린드롬이면 2, 아니면 1, 아직 모르면 0
	static int[][] checkPalindromeTable;
	//minimumPartitionTable[start]는 start부터 시작하는 부분 문자열의 팰린드롬 분할 최솟값
	static int[] minimumPartitionTable;
	
	public static void main(String[] args) throws IOException {

		//초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		inputString = br.readLine();
		N = inputString.length();
		checkPalindromeTable = new int[N][N];
		minimumPartitionTable = new int[N];
		
		
		System.out.println(minimumPartition(0));
		
	}

	//start부터 시작하는 부분 문자열의 팰린드롬 분할 최솟값 구하기
	private static int minimumPartition(int start) {
		
		//이미 구했다면 구한 값 리턴
		if(minimumPartitionTable[start]!=0) return minimumPartitionTable[start];
		
		//부분문자열이 팰린드롬이라면 1 리턴
		if(checkPalindrome(start, N-1)==2) return 1;
		
		//문자열의 최대 길이가 2500이므로, 초기값을 3000으로 두고 비교하면서 줄일 예정
		int result = 3000;
		for(int i=start;i<N;i++) {
			//start부터 i까지가 팰린드롬이라면, start~i, i+1 ~ end로 나누어 뒷부분 팰린드롬 분할 개수를 구함
			if(checkPalindrome(start,i)==2)
				result = Math.min(result, 1+ minimumPartition(i+1));
		}
		
		//결과 리턴
		return minimumPartitionTable[start] = result;
	}

	//start부터 end까지의 부분문자열이 팰린드롬이라면 2, 아니라면 1, 모르면 0 리턴
	private static int checkPalindrome(int start, int end) {
		//이 경우는 연속된 두 글자가 같을 때, 다시 한 번 재귀를 호출한 경우, 한 글자인 경우이므로 2 리턴
		if(start>=end) {
			return 2;
		}
		
		//이미 처리했다면 처리한 결과 리턴
		if(checkPalindromeTable[start][end]!=0) return checkPalindromeTable[start][end];
		
		//양 끝이 다르다면 팰린드롬이 아니므로 1 리턴
		if(inputString.charAt(start)!=inputString.charAt(end)) {
			return checkPalindromeTable[start][end] = 1;
		}
		
		//양 끝이 같다면, 양 끝을 빼고 팰린드롬인지 확인
		return checkPalindromeTable[start][end]=checkPalindrome(start+1, end-1);
	}
	
}