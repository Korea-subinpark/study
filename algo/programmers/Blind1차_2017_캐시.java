import java.util.List;
import java.util.LinkedList;

class Solution {
    List<String> list;
    
    public int find(int cacheSize, String city) {
        int curSize = list.size();
        for(int i = 0; i < curSize; i++) {
            if(list.get(i).equals(city)) {//캐시에서 찾은 경우
                list.remove(i);
                list.add(city);
                return 1;
            }
        }
        //캐시에서 못찾은 경우
        if(curSize < cacheSize) {//캐시에 여유공간이 있는 경우
            list.add(city);
            return 5;
        } else {//여유공간이 없는 경우
            list.remove(0);//가장 오래된 데이터 삭제
            list.add(city);
            return 5;
        }
    }
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        list = new LinkedList<>();
        if(cacheSize == 0)
            return cities.length * 5;


        for(int i = 0; i < cities.length; i++)
            answer += find(cacheSize, cities[i].toLowerCase());

        return answer;
  }
}
