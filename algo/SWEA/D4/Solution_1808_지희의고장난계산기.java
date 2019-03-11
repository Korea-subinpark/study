import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1808_�����ǰ��峭���� {

	static boolean[] flag;
	static int[] cnt;
	static int X, len;
	
	public static void cntNum(int num, int d) {
		if(num > X || d > len)
			return;
		cnt[num] = d;
		
		for(int i = 0; i < 10; i++) {
			if(flag[i]) {
				cntNum(num * 10 + i, d + 1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String s = br.readLine();
			len = s.length();
			X = Integer.parseInt(s);
			cnt = new int[X + 1];
			
			flag = new boolean[10];
			for(int i = 0; i < 10; i++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					flag[i] = true;
				else
					flag[i] = false;
			}
			
			cntNum(0, 0);//���� �� �ִ� ���� ��ư �� ����
			if(X == 1 && flag[1]) {
				System.out.println("#" + tc + " " + 2);
				continue;
			}
			
			int ans = 0;
			int min = Integer.MAX_VALUE;
			int n = X;
			for(int i = n; i > 1; i--) {
				if(cnt[i] != 0 && n % i == 0) {
					ans = calc(n, i);//������ ���� ���� ������ ������ ����
					if(min > ans)//�ּڰ� ����
						min = ans;
				}
			}
			if(min != Integer.MAX_VALUE)//�ּڰ��� ���ŵǾ��� ���
				System.out.println("#" + tc + " " + min);
			else
				System.out.println("#" + tc + " " + "-1");
		}
		
	}
	
	public static int calc(int n, int i) {
		int ans = 0;
		for(int j = i; j > 1; j--) {//���� �ϳ��� �����Ѵ�
			while(cnt[j] > 0) {//��ư���� ���� �� �ִ� ���
				if(n % j == 0) {//������ �������� ���
					ans += cnt[j] + 1;//��ư �� ���ϱ�
					n /= j;
				} else
					break;
			}
			if(n == 1)//�� �������� ���
				break;
		}
		if(n == 1)//���ݱ��� ���� ��ư �� ����
			return ans;
		else
			return Integer.MAX_VALUE;
	}

}
