// 제목 : 암호 만들기
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/1759
// 메모리(kb) : 14208
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//아이디어 : 재귀로 조합을 만드는 것과 비슷하게 만들고, 추가적으로 자음수와 모음수만 따로 넘겨준다.

public class Main {

	static StringBuilder sb; 	//재귀를 돌며 값을 저장하기 위해 StringBuilder를 static 멤버 변수로 선언함
	static char[] chars;		//후보가 되는 전체 문자 배열
	static char[] selected;		//선택받은 l개의 문자 배열
	static int l, c;			//암호문의 길이, 후보 문자의 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//l과 c 초기화
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		//후보 문자를 입력받고, 사전순으로 정렬
		st = new StringTokenizer(br.readLine());
		chars = new char[c];
		for(int i=0;i<c;i++) chars[i] = st.nextToken().charAt(0);
		Arrays.sort(chars);
		
		//선택받은 문자들의 배열 초기화
		selected = new char[l];
		
		//재귀로 진행
		recursiveMatching(0,0,0,0);
		
		System.out.print(sb.toString());
	}
	
	//어떤 문자가 모음인지 확인하는 함수
	public static boolean isVowel(char c) {
		if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u') return true;
		else return false;
	}

	//start = 이 문자를 포함하여 이후 문자들만 후보로 삼음, count = 현재까지 선택한 문자 수, NumOfVowle = 지금까지 모음 수, NumOfConsonant = 지금까지 자음 수
	public static void recursiveMatching(int start, int count, int NumOfVowel, int NumOfConsonant){
		//l개를 다 뽑은 상태
		if(count==l) {
			//자음과 모음이 조건을 만족한다면
			if(NumOfVowel>=1 && NumOfConsonant >=2) {
				//출력문에 저장
				for(char selectedC : selected) sb.append(selectedC);
				sb.append('\n');
			}
			return;
		}
		
		//l개를 다 뽑지 않았다면
		for(int i=start;i<c;i++) {
			//안 뽑은 후보 중에 하나를 골라서 넣고
			char currentC = chars[i];
			selected[count] = currentC;
			
			//자음, 모음 여부에 따라 재귀를 다르게 실행함
			if(isVowel(currentC)) recursiveMatching(i+1,count+1,NumOfVowel+1, NumOfConsonant);
			else recursiveMatching(i+1,count+1,NumOfVowel, NumOfConsonant+1);
		}
	}
}
