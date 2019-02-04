import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
/*
처음에는 지역변수를 사용하지 않고 class의 field부분에 relation을 복사한 배열과 row, col 값을 선언하고
모든 함수에서 공유하며 사용했는데 몇몇 테스트 케이스에서 런타임 에러가 발생했다
지역변수로 사용하고 함수에 parameter로 넘기는 방식으로 구현하였을 때 모든 테스트 케이스를 만족할 수 있었다

dfs로 구현하였을 때는 시간이 오래 걸렸기 때문에 bitmask 방식으로 바꾸어 구현하였고
유일성을 체크할 때 flag를 사용하지 않고 StringBuilder에 각 튜플의 비교할 속성값을 모두 append하여 비교하였을 때에도 시간이 많이 걸렸다
*/
class Solution {

	class SortedByTheNumberOfOne implements Comparator<Integer> {//1의 개수를 기준으로 오름차순 정렬
		int count(int o) {//2진수로 변환했을 때 1의 개수를 세는 함수
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

	public boolean unique(String[][] relation, int row, int col, int bit) {//유일성을 체크하는 함수
		for (int i = 0; i < row - 1; i++) {//튜플 i, j를 비교
			for (int j = i + 1; j < row; j++) {
				boolean flag = true;//두 튜플이 같은지 여부를 저장하는 flag
				for (int k = 0; k < col; k++) {
					if ((bit & (1 << k)) != 0) {//bit가 1인 속성만 비교
						if (relation[i][k].equals(relation[j][k]) == false) {
							flag = false;//두 튜플의 속성이 다를 경우 flag를 false로 변환
							break;//속성이 다를 경우 이미 유일성을 만족했기 때문에 다른 속성을 체크하지 않아도 되므로 break
						}
					}
				}
				if (flag)//여기서 flag가 true라는 것은 속성값이 모두 같았다는 의미이므로 유일성을 만족하지 않는다
					return false;
			}
		}
		return true;//모든 속성이 다르다는 의미로 true를 반환
	}

	public int solution(String[][] relation) {
		int answer = 0;
		int row = relation.length;
		int col = relation[0].length;
		List<Integer> superkey = new LinkedList<>();//유일성만 체크해서 모아둔 속성 집합

		for (int i = 1; i < 1 << col; i++) {
			if (unique(relation, row, col, i) == true)//유일성 체크
				superkey.add(i);
		}

		Collections.sort(superkey, new SortedByTheNumberOfOne());//1의 개수를 기준으로 정렬

		while (!superkey.isEmpty()) {
			int a = superkey.remove(0);
			answer++;
			for (int i = 0; i < superkey.size(); i++) {
				if ((a & superkey.get(i)) == a)//&연산자로 최소성을 만족하지 않는 것 제거
					superkey.remove(i--);
			}
		}
		return answer;
	}
}