
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
/*
dfs방법으로 구현했으나 시간이 오래 걸려서 비트마스크 방법으로 변경하였다
모든 경우의 수를 체크하여 슈퍼키의 집합을 만들고
마지막에 최소성을 체크하여 후보키의 개수를 
*/
class Solution {
//  int row;
//  int col;
//  String[][] temp;
//  List<Integer> superkey;
//  int len;

    class SortedByTheNumberOfOne implements Comparator<Integer> {
        int count(int o) {
            int cnt = 0;
            while(o != 0) {
                if((o & 1) == 1)
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

    public boolean unique(String[][] relation, int row, int col, int bit) {//유일성 체크
        for(int i = 0; i < row - 1; i++) {
            for(int j = i + 1; j < row; j++) {
                boolean flag = true;
                for(int k = 0; k < col; k++) {
                    if((bit & (1 << k)) != 0) {
                        if(!relation[i][k].equals(relation[j][k])) {
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag) return false;
            }
        }
        return true;
    }

    public int solution(String[][] relation) {
        int answer = 0;
        int row = relation.length;
        int col = relation[0].length;
//        temp = new String[row][col];
        List<Integer> superkey = new LinkedList<>();

//        for(int i = 0; i < row; i++)
//          temp[i] = Arrays.copyOf(relation[i], row);


        for(int i = 1; i < 1 << col; i++) {
            if(unique(relation, row, col, i) == true)
                superkey.add(i);
        }

        Collections.sort(superkey, new SortedByTheNumberOfOne());

        while(!superkey.isEmpty()) {
            int a = superkey.remove(0);
            answer++;
            for(int i = 0; i < superkey.size(); i++) {
                if((a & superkey.get(i)) == a)
                    superkey.remove(i--);
            }
        }

        return answer;
    }
}


