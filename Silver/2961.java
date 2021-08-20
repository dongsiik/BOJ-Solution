// 제목 : 도영이가 만든 맛있는 음식
// 티어 : 실버1
// 링크 : https://www.acmicpc.net/problem/2961
// 메모리(kb) : 14040
// 실행시간(ms) : 140

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        //재료 수를 입력받아서 재료를 저장할 배열 만들기
        int n = Integer.parseInt(br.readLine());
        int[][] ingredients = new int[n][2];
        
        //재료의 번호별로 신맛, 쓴맛 정보 저장
        for(int i=0;i<n;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	ingredients[i][0] = Integer.parseInt(st.nextToken());
        	ingredients[i][1] = Integer.parseInt(st.nextToken());
        }
        
        //쓴맛과 신맛의 최소값. 비교하며 줄일 예정이므로 MAX_VALUE로 설정
        int answer=Integer.MAX_VALUE;
        //비트마스킹을 이용하여 공집합이 아닌 모든 부분집합에 대하여 비교
        for(int i=1;i<1<<n;i++) {
        	int sour=1;
        	int bitter=0;
        	for(int j=0;j<n;j++) {
        		if((i & 1<<j)!=0) {
        			sour*= ingredients[j][0];
        			bitter+= ingredients[j][1];
        		}
        	}
        	answer = Math.min(answer, Math.abs(sour-bitter));
        }
        
        //출력
        System.out.println(answer);
    }
}