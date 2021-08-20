// 제목 : 롤케이크
// 티어 : 실버1
// 링크 : https://www.acmicpc.net/problem/16206
// 메모리(kb) : 14336
// 실행시간(ms) : 136

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//아이디어 : 길이가 10의 배수인 롤케이크는 자르다보면 길이가 20이 될 수 있고, 그러면 한 번 자르면 두 조각이 나온다.
//그러므로 길이가 10의 배수인 롤케이크, 아닌 롤 케이크로 나누어서 처리하고, 길이가 10의 배수인 롤케이크는 작은 롤케이크부터 처리하는 게 낫다.

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	//롤케이크 수
		int m = Integer.parseInt(st.nextToken());	//칼질 수
		ArrayList<Integer> cake10 = new ArrayList<>();	//10의 배수 롤케이크
		ArrayList<Integer> cake = new ArrayList<>();	//아닌 롤ㅋ케이크
		
		//롤케이크 정보를 입력받아서 10의 배수인지 분류 후 저장
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			int a = Integer.parseInt(st.nextToken());
			if(a%10==0) cake10.add(a);
			else cake.add(a);
		}
		//10의 배수 케이크는 한 번 썰어서 두 조각이 나오는 경우를 위해 오름차순으로 정렬
		cake10.sort(null);
		
		int answer=0;
		
		//10의 배수 케이크 자르기
		for(int i:cake10) {
			if(m==0) break;
			if(i==10) answer++;
			else if(i/10<=m+1) {
				answer += i/10;
				m -= i/10-1;
			}
			else {
				answer +=m;
				m=0;
			}
		}

		//나머지 케이크 자르기
		for(int i:cake) {
			if(m==0) break;
			if(i/10<=m) {
				answer += i/10;
				m -= i/10;
			}
			else {
				answer+=m;
				m=0;
			}
		}
		
		//출력
		System.out.println(answer);
	}
}
