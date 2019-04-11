import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2477_차량정비소 {
	
	static int N, M, K, A, B;
	static int[] a;//각 접수 창구에서 접수하는 데 걸리는 시간
	static int[] b;//각 정비 창구에서 정비하는 데 걸리는 시간
	static Client[] clients;//고객 정보
	
	static class Client {
		int aIdx;//이용한 접수창구 번호
		int bIdx;//이용한 정비창구 번호
		int arrive;//도착한 시간
		int remain;//완료까지 남은 시간
		public Client(int arrive) {
			this.arrive = arrive;
			this.remain = -1;
		}
	}
	
	public static void init(BufferedReader br) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());//접수 창구의 개수
		M = Integer.parseInt(st.nextToken());//정비 창구의 개수
		K = Integer.parseInt(st.nextToken());//방문한 고객 수
		A = Integer.parseInt(st.nextToken());//지갑을 두고 간 고객이 이용한 접수 창구
		B = Integer.parseInt(st.nextToken());//지갑을 두고 간 고객이 이용한 정비 창구
		
		a = new int[N + 1];
		b = new int[M + 1];
		clients = new Client[K + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= M; i++) b[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= K; i++) clients[i] = new Client(Integer.parseInt(st.nextToken()));
	}
	
	public static void simulation() {
		int remain = K;
		int time = 0;
		Client[] ingA = new Client[N + 1];//각 접수창구에서 서비스 받고 있는 고객
		Client[] ingB = new Client[M + 1];//각 정비창구에서 서비스 받고 있는 고객
		int ClientIdx = 1;//다음으로 도착할 고객 번호
		Queue<Client> waitA = new LinkedList<>();//접수 창구 대기자 명단
		Queue<Client> waitB = new LinkedList<>();//정비 창구 대기자 명단
		while(true) {
			//정비 창구 진행
			for(int i = 1; i <= M; i++) {
				if(ingB[i] != null) {//현재 창구에 사람이 있는 경우
					if(ingB[i].remain == 1) {//정비 창구 완료
						ingB[i] = null;
						remain--;
					} else
						ingB[i].remain--;
				}
			}
			
			//접수 창구 진행
			for(int i = 1; i <= N; i++) {
				if(ingA[i] != null) {//현재 창구에 사람이 있는 경우
					if(ingA[i].remain == 1) {//정비 창구 완료
						waitB.add(ingA[i]);
						ingA[i] = null;
					} else
						ingA[i].remain--;
				}
			}
			
			//도착한 사람 처리
			while(ClientIdx <= K && time == clients[ClientIdx].arrive) {
				waitA.add(clients[ClientIdx]);
				ClientIdx++;
			}
			
			
			//정비 창구 대기자 분배
			for(int i = 1; i <= M; i++) {
				if(ingB[i] == null && !waitB.isEmpty()) {
					ingB[i] = waitB.poll();
					ingB[i].bIdx = i;
					ingB[i].remain = b[i];
				}
			}
			
			//접수 창구 대기자 분배
			for(int i = 1; i <= N; i++) {
				if(ingA[i] == null && !waitA.isEmpty()) {
					ingA[i] = waitA.poll();
					ingA[i].aIdx = i;
					ingA[i].remain = a[i];
				}
			}
			
			if(remain == 0)//남은 고객이 없을 때 종료
				break;
			time++;//시간 증가
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			init(br);
			
			simulation();
			
			int ans = 0;
			for(int i = 1; i <= K; i++)//지갑을 잃어버린 고객 후보 찾기
				if(clients[i].aIdx == A && clients[i].bIdx == B)
					ans += i;
			
			if(ans != 0)
				System.out.println("#" + tc + " " + ans);
			else
				System.out.println("#" + tc + " " + -1);
		}
	}

}
