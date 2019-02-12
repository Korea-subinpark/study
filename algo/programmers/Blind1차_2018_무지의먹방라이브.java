import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
/*
brute force로는 효율성 테스트에서 점수를 받을 수 없다
음식별 시간을 오름차순으로 정렬한 후에 이전 시간과 현재 시간의 차이를 리스트의 길이만큼 곱해서 장애 시간에서 빼준다
뺀 값이 음수가 나온다면 멈추고 그 인덱스부터 마지막 인덱스까지를 다시 인덱스 오름차순으로 정렬한다
그 후에 장애시간을 남아있는 리스트의 길이만큼 나눈 나머지를 구한다
그 나머지를 인덱스로 하는 음식의 인덱스를 반환한다
for문을 탈출하게 된다면 아무 음식도 남아있지 않은 것이기 때문에 -1을 
*/
class Food {
    int idx;
    int sec;
    Food(int idx, int sec) {
        this.idx = idx;
        this.sec = sec;
    }
}

class CompareByIdx implements Comparator<Food> {
    @Override
    public int compare(Food f1, Food f2) {
        return f1.idx - f2.idx;
    }
}

class CompareBySec implements Comparator<Food> {
    @Override
    public int compare(Food f1, Food f2) {
        return f1.sec - f2.sec;
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        List<Food> list = new ArrayList<>();
        for(int i = 0; i < food_times.length; i++)
            list.add(new Food(i + 1, food_times[i]));
        
        Collections.sort(list, new CompareBySec());
        
        int preSec = 0;
        int i = 0;
        int len = list.size();
        for(Food f : list) {
            long height = f.sec - preSec;
            if(height != 0) {
                long Area = len * height;
                if(k >= Area) {
                    k -= Area;
                    preSec = f.sec;
                } else {
                    k %= len;
                    Collections.sort(list.subList(i, list.size()), new CompareByIdx());
                    return list.get(i + (int)k).idx;
                }
            }
            i++;
            len--;
        }
        return -1;
    }
}
