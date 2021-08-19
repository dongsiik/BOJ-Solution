// 제목 : 탑
// 티어 : 골드5
// 링크 : https://www.acmicpc.net/problem/2493
// 메모리(kb) : 85448
// 실행시간(ms) : 784

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main
{
    
    public static void main(String args[]) throws Exception
    {
    	//입출력 도우미들
    	BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	
    	//각각 탑의 갯수, 탑별 높이, 탑별 레이저 수신지
    	int n = Integer.parseInt(br.readLine());
    	int[] height = new int[n+1];
    	int[] dest = new int[n+1];
    	//왼쪽에 높은 탑들만 남겨놓기 위한 스택
    	Stack<Integer> localMax = new Stack<>();
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=1;i<=n;i++) {
    		//탑 높이 입력받기
    		height[i] = Integer.parseInt(st.nextToken());
    		//왼쪽에 탑이 없으면 스택에 집어넣음
    		if(localMax.isEmpty()) localMax.push(i);
    		//왼쪽에 탑이 있다면
    		else {
    			//자기보다 낮은 탑들은 다 지워버림
    			while(!localMax.isEmpty() && height[localMax.peek()]<height[i]) localMax.pop();
    			//남은 탑이 있다면, 그 탑이 레이저 수신지임
    			if(!localMax.isEmpty()) dest[i] = localMax.peek();
    			//자신을 집어 넣음
    			localMax.push(i);
    		}
    		//도착지 정보 출력문에 저장
    		sb.append(dest[i]).append(' ');
    	}
    	//출력문 끝에 있을 띄워쓰기 지우기
    	sb.setLength(sb.length()-1);
        
        //출력
        System.out.print(sb.toString());
    }
}
