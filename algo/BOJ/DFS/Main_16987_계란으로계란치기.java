import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16987_������ΰ��ġ�� {
	static int[][] egg;//����� �������� ���� ����
	static boolean[] crashed;//���� ���: true
	static int max, N;
	
	public static void dfs(int cur, int cnt) {
		if(max < cnt)//�ִ� ������Ʈ
			max = cnt;
		if(cur >= N) return;//�� ������ ����� ������ ���� ����
		if(crashed[cur]) {//���� ����� ����� �����ִ� ��� ���� ������� �̵�
			dfs(cur + 1, cnt);
			return;
		}
		for(int i = 0; i < N; i++) {
			if(cur == i) continue;//�ڱ��ڽ� �ǳʶٱ�
			if(!crashed[i] && !crashed[cur]) {//����ִ� ����� ������ �ʾ��� ��
				if(egg[cur][0] <= egg[i][1]) {//��� �ִ� ����� ���� ���
					crashed[cur] = true;
					if(egg[i][0] <= egg[cur][1]) {//����ģ ����� ���� ���
						crashed[i] = true;
						dfs(cur + 1, cnt + 2);//���� �������
						crashed[cur] = false;
						crashed[i] = false;
					} else {//����ģ ����� ������ ���� ���
						egg[i][0] -= egg[cur][1];
						dfs(cur + 1, cnt + 1);
						egg[i][0] += egg[cur][1];
						crashed[cur] = false;
					}
				} else {//���� ��� �ִ� ����� ������ ���� ���
					egg[cur][0] -= egg[i][1];
					if(egg[i][0] <= egg[cur][1]) {//����ģ ����� ���� ���
						crashed[i] = true;
						dfs(cur + 1, cnt + 1);//���� �������
						crashed[i] = false;
						egg[cur][0] += egg[i][1];
					} else {//����ģ ����� ������ ���� ���
						egg[i][0] -= egg[cur][1];
						dfs(cur + 1, cnt);
						egg[i][0] += egg[cur][1];
						egg[cur][0] += egg[i][1];
					}
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		egg = new int[N + 1][2];
		crashed = new boolean[N + 1];
		crashed[N] = true;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			egg[i][0] = Integer.parseInt(st.nextToken());//������
			egg[i][1] = Integer.parseInt(st.nextToken());//����
		}
		
		dfs(0, 0);
		
		System.out.println(max);
	}

}
