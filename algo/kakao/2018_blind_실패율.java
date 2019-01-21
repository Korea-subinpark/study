import java.util.ArrayList;
import java.util.Collections;
/*
각 스테이지에 머물고 있는 플레이어 수를 입력 받았을 때
스테이지별 실패율(스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어 수)를 구하여
실패율이 높은 순으로 정렬된 스테이지 배열을 반환하는 문제이다

먼저 Fail이라는 객체를 사용했다
이 객체는 field 값으로 stage와 실패율을 가지고 있다
실패율을 계산하여 ArrayList에 객체를 추가하고
Comparable 인터페이스를 상속하여 compareTo 함수를 Overriding 하였다
이렇게 하면 객체를 정렬하는 기준을 정할 수 있다
먼저 실패율을 비교하고 실패율이 같으면 스테이지를 비교하는 것으로 compareTo 메소드를 
*/
class Fail implements Comparable<Fail>{
    public int stage;
    public double probability;
    public Fail(int stage, double probability){
        this.stage = stage;
        this.probability = probability;
    }
    @Override
    public int compareTo(Fail other) {
        Double d1 = this.probability;
        Double d2 = other.probability;
        int result = d2.compareTo(d1);//실패율을 먼저 비교
        if(result == 0) {//실패율이 같을 경우 스테이지를 비교
            return this.stage - other.stage;
        }
        return result;
    }
}
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] stageCnt = new int[N + 2];
        ArrayList<Fail> list = new ArrayList<>();
        for(int i = 0; i < stages.length; i++)
            stageCnt[stages[i]]++;//각 스테이지별 인원수 카운트

        for(int i = N; i >= 1; i--) {
            int denominator = stageCnt[i] + stageCnt[i + 1];//스테이지에 도달한 플레이어 수
            if(denominator == 0)
                list.add(new Fail(i, 0));//도달한 플레이어가 없을 경우 실패율 0
            else {
                int numerator = stageCnt[i];//스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수
                stageCnt[i] = denominator;//카운트 값을 누적합으로 변경
                list.add(new Fail(i, (double)numerator/(double)denominator));
            }
        }
        Collections.sort(list);
        for(int i = 0; i < N; i++)
            answer[i] = list.get(i).stage;
        return answer;
    }
}
