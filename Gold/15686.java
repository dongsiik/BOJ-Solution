// 제목 : 치킨 배달
// 티어 : 골드 5
// 링크 : https://www.acmicpc.net/problem/15686
// 메모리(kb) : 14844
// 실행시간(ms) : 192

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main
{
	static int answer;	//정답 == 도시의 치킨 거리
	static int n, m, M;	//도시의 크기, 살아남을 치킨집 수, 전체 치킨집 수
	static ArrayList<Integer[]> chickens;	//치킨집들 배열
	static ArrayList<Integer[]> houses;		//집들 배열
	static int[] chosenChickens;			//next_permutation에 m개의 치킨집 조합을 테스트해볼 배열
	
	static boolean next_permutation(int[] arr) {
		int len = arr.length;
		
		int i = len -1;
		while(i>0 && arr[i-1]>= arr[i]) --i;
		if(i==0) return false;
		
		int j = len-1;
		while(arr[i-1]>=arr[j]) --j;
		
		swap(arr, i-1,j);
		
		int k = len-1;
		while(i<k) {
			swap(arr,i,k);
			i++;
			k--;
		}
		
		return true;
	}
	
	static void swap(int[]arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	//현재 치킨집 조합으로 도시의 치킨 거리를 구하고, 다른 조합에서 나온 최솟값과 비교하는 함수
	public static void cityDistance() {
		int cur_dis = 0;
		
		for(Integer[] house : houses) cur_dis += houseDistance(house);
		
		answer = Math.min(answer, cur_dis);
	}
	
	//한 집에서 남은 치킨집들과 치킨 거리를 구하는 함수
	public static int houseDistance(Integer[] house) {
		int distance = Integer.MAX_VALUE;
		
		for(int i=0;i<M;i++) {
			if(chosenChickens[i]==1) {
				Integer[] chicken = chickens.get(i);
				int cur_dis = Math.abs(house[0]-chicken[0])+Math.abs(house[1]-chicken[1]);
				distance = Math.min(cur_dis, distance);
			}
		}
		
		return distance;
	}
	
    public static void main(String args[]) throws Exception
    {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        //전역 초기화
        answer = Integer.MAX_VALUE;
        chickens = new ArrayList<>();
        houses = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        M = 0;
        
        //도시 지도를 읽으면서 집과 치킨집을 기록
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<n;j++) {
        		int a = Integer.parseInt(st.nextToken());
        		if(a==1) houses.add(new Integer[]{i,j});
        		if(a==2) {
        			chickens.add(new Integer[]{i,j});
        			++M;
        		}
        	}
        }

        //m개를 뽑는 조합을 위해 chosenChickens을 만들고, 1을 끝에서부터 m개 넣음
        chosenChickens = new int[M];
        for(int i=0; i<m;i++) chosenChickens[M-1-i]=1;
        
        //도시의 최소 치킨 거리 찾기
        do {
        	cityDistance();
        	
        }while(next_permutation(chosenChickens));
        
        //출력
        System.out.println(answer);
    }
}
