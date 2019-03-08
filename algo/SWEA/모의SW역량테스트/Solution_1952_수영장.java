import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_������ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int[] price = new int[4];//1��, 1��, 3��, 1�� ���
			for(int i = 0; i < 4; i++)
				price[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			int[] plan = new int[13];//�̿� ��ȹ
			for(int i = 1; i < 13; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] min = new int[13];//�� �ޱ����� �ּ� ���
			for(int i = 1; i < 13; i++) {
				if(i >= 3)//3�� �̻���ʹ� 3�޿�ݱ��� ����Ѵ�
					min[i] = Math.min(min[i - 1] + Math.min(price[0] * plan[i], price[1]), min[i - 3] + price[2]);
				else//1���� 2���� 1��, 1�� ��ݸ� ����Ѵ�
					min[i] = min[i - 1] + Math.min(price[0] * plan[i], price[1]);
			}
			//�������� 1�� ��ݰ� ���Ͽ� �ּڰ��� ���
			System.out.println("#" + tc + " " + Math.min(min[12], price[3]));
		}
	}

}
