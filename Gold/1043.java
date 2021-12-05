
// 제목 : 거짓말
// 티어 : 골드 4
// 링크 : https://www.acmicpc.net/problem/1043
// 메모리(kb) : 14252
// 실행시간(ms) : 132

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	public static void main(String args[]) throws IOException {

		// 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 사람 수 ,파티 수
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 사람별로 참가하는 파티, 파티별로 참가하는 사람
		ArrayList<Integer>[] peopleToParty = new ArrayList[N + 1];
		ArrayList<Integer>[] partyToPeople = new ArrayList[M + 1];

		for (int i = 1; i <= N; i++) {
			peopleToParty[i] = new ArrayList<>();
		}

		for (int i = 1; i <= M; i++) {
			partyToPeople[i] = new ArrayList<>();
		}

		// 해당 사람과 파티를 확인했는지
		boolean[] checkedPeople = new boolean[N + 1];
		boolean[] checkedParty = new boolean[M + 1];

		// 진실을 아는 사람들의 큐
		Queue<Integer> q = new LinkedList<>();

		// 진실을 아는 사람 입력받기
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		for (int i = 0; i < num; i++) {
			int person = Integer.parseInt(st.nextToken());
			q.add(person);
			checkedPeople[person] = true;
		}

		// 파티 정보 입력받기
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				int person = Integer.parseInt(st.nextToken());
				partyToPeople[i].add(person);
				peopleToParty[person].add(i);
			}
		}

		// 거짓말을 할 수 있는 파티 수
		int answer = M;

		// 진실을 아는 사람들에 대해서
		while (!q.isEmpty()) {

			int person = q.poll();

			// 그 사람이 참가하는 파티들을 살펴봄
			for (int party : peopleToParty[person]) {
				// 살펴보지 않은 파티가 있다면
				if (!checkedParty[party]) {
					// 정답을 하나 줄이고, 이 파티에 참가하는 사람들을 진실을 아는 사람으로 취급해서 큐에 넣어줌
					answer--;
					checkedParty[party] = true;
					for (int partyPerson : partyToPeople[party]) {
						if (!checkedPeople[partyPerson]) {
							checkedPeople[partyPerson] = true;
							q.add(partyPerson);
						}
					}
				}
			}
		}

		// 정답 출력
		System.out.println(answer);

	}

}