import java.util.Scanner;

public class Solution_6781_���Ʈ���ð���_�ڼ��� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int[][] card = new int[9][2];
			char colTemp = 0;
			String temp = "";

			temp = sc.next();

			for (int i = 0; i < 9; i++)
				card[i][0] = temp.charAt(i) - '0';

			temp = sc.next();

			for (int i = 0; i < 9; i++) {
				colTemp = temp.charAt(i);
				if (colTemp == 'R')//R�� 0���� ����
					card[i][1] = 0;
				else if (colTemp == 'G')//G�� 1�� ����
					card[i][1] = 1;
				else
					card[i][1] = 2;//B�� 2�� ����
			}

			int[] RCnt = new int[11];// +2 ����
			int[] GCnt = new int[11];
			int[] BCnt = new int[11];

			for (int i = 0; i < 9; i++) {//�� ���� ī�� ī����
				if(card[i][1] == 0)
					RCnt[card[i][0]-1]++;
				else if(card[i][1] == 1)
					GCnt[card[i][0]-1]++;
				else
					BCnt[card[i][0]-1]++;
				
			}

			int chk = 0;// ��Ʈ �ϼ� ����

			for (int i = 0; i < 9; i++) {
				if(RCnt[i] >= 1 && RCnt[i+1] >= 1 && RCnt[i+2] >= 1) {//���� ���� ī��
					chk++;
					RCnt[i]--;
					RCnt[i+1]--;
					RCnt[i+2]--;
					i--;
                    continue;
				}
				
				if(GCnt[i] >= 1 && GCnt[i+1] >= 1 && GCnt[i+2] >= 1) {//�ʷ� ���� ī��
					chk++;
					GCnt[i]--;
					GCnt[i+1]--;
					GCnt[i+2]--;
					i--;
                    continue;
				}
	
				
				if(BCnt[i] >= 1 && BCnt[i+1] >= 1 && BCnt[i+2] >= 1) {//�Ķ� ���� ī��
					chk++;
					BCnt[i]--;
					BCnt[i+1]--;
					BCnt[i+2]--;
					i--;
                    continue;
				}
			
				
				if(RCnt[i] >= 3) {//���� ���� ī��
					chk++;
					RCnt[i] -= 3;
					i--;
                    continue;
				}

				
				if(GCnt[i] >= 3) {//���� ���� ī��
					chk++;
					GCnt[i] -= 3;
					i--;
                    continue;
				}
			
				
				if(BCnt[i] >= 3) {//���� ���� ī��
					chk++;
					BCnt[i] -= 3;
					i--;
                    continue;
				}
			
			}

			if (chk == 3)
				System.out.println("#" + tc + " Win");
			else
				System.out.println("#" + tc + " Continue");
		}

		sc.close();
	}

}
