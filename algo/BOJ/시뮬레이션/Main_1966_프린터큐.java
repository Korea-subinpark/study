import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1966_������ť {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int[][] q = new int[500][2];
			int front = 0;
			int rear = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());//������ ��
			int M = Integer.parseInt(st.nextToken());//�ñ��� ������ ��ġ
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++)
				q[rear++][0] = Integer.parseInt(st.nextToken());
			q[M][1] = 1;//���ϴ� ���� ǥ��
			
			int ans = 1;
			while(true) {
				boolean flag = false;
				for(int i = front + 1; i < rear; i++) {//�ڽź��� ���� �켱������ ���� ������ �ִ��� �˻�
					if(q[front][0] < q[i][0]) {//�켱������ �� ���� ������ �ִ� ���
						int tmp = q[front][0];//dequeue
						int tmp2 = q[front++][1];
						q[rear][0] = tmp;//�ٽ� enqueue
						q[rear++][1] = tmp2;
						i = front;//ó������ �ٽ� �˻�
					}
				}
				if(flag == false) {//�켱������ �� ���� ������ ���� ���
					int tmp = q[front++][1];//�ñ��� ������ �´��� Ȯ��
					if(tmp == 1) {//�´� ���
						System.out.println(ans);//��µ� ���� �� ���
						break;
					} else {//���ϴ� ������ �ƴ� ���
						ans++;//��µ� ���� �� ����
					}
				}
			}
			
		}
	}

}
