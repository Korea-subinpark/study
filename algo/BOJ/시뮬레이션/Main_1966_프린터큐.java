import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1966_프린터큐 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int[][] q = new int[500][2];
			int front = 0;
			int rear = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());//문서의 수
			int M = Integer.parseInt(st.nextToken());//궁금한 문서의 위치
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++)
				q[rear++][0] = Integer.parseInt(st.nextToken());
			q[M][1] = 1;//원하는 문서 표시
			
			int ans = 1;
			while(true) {
				boolean flag = false;
				for(int i = front + 1; i < rear; i++) {//자신보다 높은 우선순위를 가진 문서가 있는지 검사
					if(q[front][0] < q[i][0]) {//우선순위가 더 높은 문서가 있는 경우
						int tmp = q[front][0];//dequeue
						int tmp2 = q[front++][1];
						q[rear][0] = tmp;//다시 enqueue
						q[rear++][1] = tmp2;
						i = front;//처음부터 다시 검사
					}
				}
				if(flag == false) {//우선순위가 더 높은 문서가 없는 경우
					int tmp = q[front++][1];//궁금한 문서가 맞는지 확인
					if(tmp == 1) {//맞는 경우
						System.out.println(ans);//출력된 문서 수 출력
						break;
					} else {//원하는 문서가 아닌 경우
						ans++;//출력된 문서 수 증가
					}
				}
			}
			
		}
	}

}
