// 제목 : 거짓말
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1043
// 메모리(kb) : 14248
// 실행시간(ms) : 132

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;
  
class Main
{
	public static void main(String args[]) throws IOException
    {
		//빠른 입출력 도구
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//사람 수 N, 파티 수 N
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//진실을 알고, 참가하는 모든 파티가 확인된 사람을 확인하는 배열
		boolean[] checked = new boolean[N+1];
		//진실을 알고, 아직 참가하는 모든 파티가 확인되지 않은 사람
		ArrayDeque<Integer> truth = new ArrayDeque<>(N);
		
		//진실을 아는 사람 입력받기
		st = new StringTokenizer(br.readLine());
		int peopleWithTruth = Integer.parseInt(st.nextToken());
		for(int i=0;i<peopleWithTruth;i++) {
			int personWithTrue = Integer.parseInt(st.nextToken());
			truth.offer(personWithTrue);
			checked[personWithTrue] = true;
		}
		
		//파티 정보 입력받기
		LinkedList<int[]> parties = new LinkedList<>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int numOfPeopleInParty = Integer.parseInt(st.nextToken());
			int[] party = new int[numOfPeopleInParty];
			for(int j=0;j<numOfPeopleInParty;j++) {
				party[j] = Integer.parseInt(st.nextToken());
			}
			parties.add(party);
		}
		
		//진실을 아는 사람마다 그 사람이 참가하는 모든 파티를 찾고, 그런 파티에 참가하는 다른 사람들을 진실을 아는 사람으로 만듦
		while(!truth.isEmpty() && !parties.isEmpty()) {
			int person = truth.poll();
			for(int i=0;i<parties.size();i++) {
				int[] party = parties.get(i);
				if(isInParty(person,party)) {
					for(int individual : party) {
						if(!checked[individual])
							truth.add(individual);
					}
					parties.remove(i--);
				}
			}
		}
		
		//출력
		System.out.println(parties.size());
		
    }
	
	//어떤 사람이 파티에 참가했다면 true, 아니라면 false
	public static boolean isInParty(int person, int[] party) {
		for(int individual : party) {
			if(person==individual) return true;
		}
		return false;
	}
}