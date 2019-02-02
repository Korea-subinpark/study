import java.util.Scanner;

public class Solution_1215_SW�����ذ�⺻3����_ȸ��1_�ڼ��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int N = sc.nextInt();
			int cnt = 0;
			char[][] arr = new char[8][8];

			for (int i = 0; i < 8; i++) {
				String str = sc.next();
				for (int j = 0; j < 8; j++) {
					arr[j][i] = str.charAt(j);//��� ���� �ٲ㼭 ����
				}
				for(int j = 0; j < 8 - N + 1; j++) {
					StringBuilder sb = new StringBuilder();
					for(int k = j; k < j + N; k++)
						sb.append(str.charAt(k));
					String temp = sb.toString();
					String sbReverse = sb.reverse().toString();
					if(temp.equals(sbReverse))
						cnt++;
				}
			}
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8 - N + 1; j++) {
					StringBuilder sb = new StringBuilder();
					for(int k = j; k < j + N; k++)
						sb.append(arr[i][k]);
					
					String temp = sb.toString();
					String sbReverse = sb.reverse().toString();
					if(temp.equals(sbReverse))
						cnt++;
				}

			}
			
			System.out.println("#" + tc + " " + cnt);
			cnt = 0;
		}
		sc.close();
	}

}
