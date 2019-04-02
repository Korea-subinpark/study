import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_5672_���������û� {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			char[] c = new char[N];
			
			for(int i = 0; i < N; i++)
				c[i] = br.readLine().charAt(0);
			
			char[] result = new char[N];//����� ���ڸ� ����
			
			int s = -1;// ���ʿ��� ���ڸ� ���� �� �ֵ��� index
			int e = N;// ���ʿ������� ����Ű�� index
			
			for (int i = 0; i < N; i++) {
				if(c[s + 1] < c [e - 1]) {
					result[i] = c[++s];
				} else if(c[s + 1] > c[e - 1]) {
					result[i] = c[--e];
				} else {//���� ���
					int j;
					for (j = 1; s + j < e - j && c[s + j] == c[e - j] ; j++);
					if(c[s + j] < c[e - j])
						result[i] = c[++s];
					else if(c[s + j] > c[e - j])
						result[i] = c[--e];
					else//�ƹ��ų� �ִ´�
						result[i] = c[++s];
				}
			}
			
			System.out.println("#" + tc + " " + new String(result));
			
		}
		
	}

}
