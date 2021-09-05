// 제목 : 문자열 폭발
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/9935
// 메모리(kb) : 56336
// 실행시간(ms) : 520

//아이디어 : 폭탄으로 완성될 여지가 있는 문자열들을 따로 모아놓다가, 폭탄이 안 될 때 남는 문자열에 넣어준다.
//폭탄이 완성된다면 격리된 문자열들 중에서 폭탄만큼을 날린다.
//사실 배열을 스택처럼 쓰면서 하나하나 집어넣다가, 
//폭발문자열 끝 글자가 들어오면 그 직전 문자열들이 폭탄인지 확인하고 그렇다면 인덱스를 이동하는 게 더 빠릅니다..

import java.util.*;
import java.io.*;

class Main
{
	public static void main(String args[]) throws Exception
	{
		//빠른 입출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		//문자열과 길이 입력받기
        String str = br.readLine();
        int len = str.length();
        //폭발 문자열 입력받기
        String bomb = br.readLine();
        
        //남은 문자열
        StringBuilder sb = new StringBuilder();
        //폭탄이 될 것 같아서 따로 보관하는 문자열
        ArrayDeque<Character> quarantine = new ArrayDeque<>(len);
        
        for(int i=0;i<len;i++){
        	//글자 하나 입력받기
        	char c = str.charAt(i);
        	//그 글자가 폭탄에 포함되는지 따져보기
            int cur = bomb.indexOf(c);
            int prev = -1;
            if(!quarantine.isEmpty()){
            	prev = bomb.indexOf(quarantine.peekLast());
            }
            //폭탄의 첫 글자이거나, 격리된 문자와 이어져서 폭탄이 될 것 같다면 격리하기
            if(cur==0 || prev+1==cur){
            	quarantine.offerLast(c);
            	//폭발 문자열이 완성되면 폭발시키기
                if(cur==bomb.length()-1){
                	for(int j=0;j<bomb.length();j++){
                    	quarantine.pollLast();
                    }
                }
            }
            //아니라면 격리소에 있는 문자열을 남는 문자열로 이동하고, 새로운 문자도 남는 문자열로 보냄
            else{
            	while(!quarantine.isEmpty()){
                	sb.append(quarantine.pollFirst());
                }
                sb.append(c);
            }

        }
        
        //격리소에 있던 남은 문자열 이동시키기
        while(!quarantine.isEmpty()){
        	sb.append(quarantine.pollFirst());
        }
        
        //남는 문자열이 없으면 FRULA, 아니라면 남는 문자열 출력하기
        if(sb.length()==0) sb.append("FRULA");
            
        System.out.println(sb);
	}
}