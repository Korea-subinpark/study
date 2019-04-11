import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2477_��������� {
	
	static int N, M, K, A, B;
	static int[] a;//�� ���� â������ �����ϴ� �� �ɸ��� �ð�
	static int[] b;//�� ���� â������ �����ϴ� �� �ɸ��� �ð�
	static Client[] clients;//�� ����
	
	static class Client {
		int aIdx;//�̿��� ����â�� ��ȣ
		int bIdx;//�̿��� ����â�� ��ȣ
		int arrive;//������ �ð�
		int remain;//�Ϸ���� ���� �ð�
		public Client(int arrive) {
			this.arrive = arrive;
			this.remain = -1;
		}
	}
	
	public static void init(BufferedReader br) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());//���� â���� ����
		M = Integer.parseInt(st.nextToken());//���� â���� ����
		K = Integer.parseInt(st.nextToken());//�湮�� �� ��
		A = Integer.parseInt(st.nextToken());//������ �ΰ� �� ���� �̿��� ���� â��
		B = Integer.parseInt(st.nextToken());//������ �ΰ� �� ���� �̿��� ���� â��
		
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
		Client[] ingA = new Client[N + 1];//�� ����â������ ���� �ް� �ִ� ��
		Client[] ingB = new Client[M + 1];//�� ����â������ ���� �ް� �ִ� ��
		int ClientIdx = 1;//�������� ������ �� ��ȣ
		Queue<Client> waitA = new LinkedList<>();//���� â�� ����� ���
		Queue<Client> waitB = new LinkedList<>();//���� â�� ����� ���
		while(true) {
			//���� â�� ����
			for(int i = 1; i <= M; i++) {
				if(ingB[i] != null) {//���� â���� ����� �ִ� ���
					if(ingB[i].remain == 1) {//���� â�� �Ϸ�
						ingB[i] = null;
						remain--;
					} else
						ingB[i].remain--;
				}
			}
			
			//���� â�� ����
			for(int i = 1; i <= N; i++) {
				if(ingA[i] != null) {//���� â���� ����� �ִ� ���
					if(ingA[i].remain == 1) {//���� â�� �Ϸ�
						waitB.add(ingA[i]);
						ingA[i] = null;
					} else
						ingA[i].remain--;
				}
			}
			
			//������ ��� ó��
			while(ClientIdx <= K && time == clients[ClientIdx].arrive) {
				waitA.add(clients[ClientIdx]);
				ClientIdx++;
			}
			
			
			//���� â�� ����� �й�
			for(int i = 1; i <= M; i++) {
				if(ingB[i] == null && !waitB.isEmpty()) {
					ingB[i] = waitB.poll();
					ingB[i].bIdx = i;
					ingB[i].remain = b[i];
				}
			}
			
			//���� â�� ����� �й�
			for(int i = 1; i <= N; i++) {
				if(ingA[i] == null && !waitA.isEmpty()) {
					ingA[i] = waitA.poll();
					ingA[i].aIdx = i;
					ingA[i].remain = a[i];
				}
			}
			
			if(remain == 0)//���� ���� ���� �� ����
				break;
			time++;//�ð� ����
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			init(br);
			
			simulation();
			
			int ans = 0;
			for(int i = 1; i <= K; i++)//������ �Ҿ���� �� �ĺ� ã��
				if(clients[i].aIdx == A && clients[i].bIdx == B)
					ans += i;
			
			if(ans != 0)
				System.out.println("#" + tc + " " + ans);
			else
				System.out.println("#" + tc + " " + -1);
		}
	}

}
