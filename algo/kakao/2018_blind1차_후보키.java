import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
/*
ó������ ���������� ������� �ʰ� class�� field�κп� relation�� ������ �迭�� row, col ���� �����ϰ�
��� �Լ����� �����ϸ� ����ߴµ� ��� �׽�Ʈ ���̽����� ��Ÿ�� ������ �߻��ߴ�
���������� ����ϰ� �Լ��� parameter�� �ѱ�� ������� �����Ͽ��� �� ��� �׽�Ʈ ���̽��� ������ �� �־���

dfs�� �����Ͽ��� ���� �ð��� ���� �ɷȱ� ������ bitmask ������� �ٲپ� �����Ͽ���
���ϼ��� üũ�� �� flag�� ������� �ʰ� StringBuilder�� �� Ʃ���� ���� �Ӽ����� ��� append�Ͽ� ���Ͽ��� ������ �ð��� ���� �ɷȴ�
*/
class Solution {

	class SortedByTheNumberOfOne implements Comparator<Integer> {//1�� ������ �������� �������� ����
		int count(int o) {//2������ ��ȯ���� �� 1�� ������ ���� �Լ�
			int cnt = 0;
			while (o != 0) {
				if ((o & 1) == 1)
					cnt++;
				o = o >> 1;
			}
			return cnt;
		}

		@Override
		public int compare(Integer o1, Integer o2) {
			return count(o1) - count(o2);
		}
	}

	public boolean unique(String[][] relation, int row, int col, int bit) {//���ϼ��� üũ�ϴ� �Լ�
		for (int i = 0; i < row - 1; i++) {//Ʃ�� i, j�� ��
			for (int j = i + 1; j < row; j++) {
				boolean flag = true;//�� Ʃ���� ������ ���θ� �����ϴ� flag
				for (int k = 0; k < col; k++) {
					if ((bit & (1 << k)) != 0) {//bit�� 1�� �Ӽ��� ��
						if (relation[i][k].equals(relation[j][k]) == false) {
							flag = false;//�� Ʃ���� �Ӽ��� �ٸ� ��� flag�� false�� ��ȯ
							break;//�Ӽ��� �ٸ� ��� �̹� ���ϼ��� �����߱� ������ �ٸ� �Ӽ��� üũ���� �ʾƵ� �ǹǷ� break
						}
					}
				}
				if (flag)//���⼭ flag�� true��� ���� �Ӽ����� ��� ���Ҵٴ� �ǹ��̹Ƿ� ���ϼ��� �������� �ʴ´�
					return false;
			}
		}
		return true;//��� �Ӽ��� �ٸ��ٴ� �ǹ̷� true�� ��ȯ
	}

	public int solution(String[][] relation) {
		int answer = 0;
		int row = relation.length;
		int col = relation[0].length;
		List<Integer> superkey = new LinkedList<>();//���ϼ��� üũ�ؼ� ��Ƶ� �Ӽ� ����

		for (int i = 1; i < 1 << col; i++) {
			if (unique(relation, row, col, i) == true)//���ϼ� üũ
				superkey.add(i);
		}

		Collections.sort(superkey, new SortedByTheNumberOfOne());//1�� ������ �������� ����

		while (!superkey.isEmpty()) {
			int a = superkey.remove(0);
			answer++;
			for (int i = 0; i < superkey.size(); i++) {
				if ((a & superkey.get(i)) == a)//&�����ڷ� �ּҼ��� �������� �ʴ� �� ����
					superkey.remove(i--);
			}
		}
		return answer;
	}
}