import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

class Position implements Comparable<Position> {
    int x, y;
    Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        Position p = (Position)o;
        if(p.x == this.x && p.y == this.y)
            return true;
        return false;
    }
    
    @Override
    public int hashCode() {
        String s = x + "" + y + "";
        return Integer.parseInt(s);
    }
    
    @Override
    public int compareTo(Position p) {
        return p.y - this.y;
    }
}

class Solution {
    char[][] board;
    int m = 0, n = 0;
    
    HashSet<Position> positions;
    ArrayList<Position> list;
        
    //4개의 블록이 모였을 때, 블록을 없애고 재귀적으로 반복
    public int fourBlock(int cnt) {
        positions = new HashSet<>();
        for(int i = 0; i < m - 1; i++) {
            for(int j = 0; j < n - 1; j++) {
                char c = board[i][j];
                //4개의 블록이 모두 똑같은 경우 set에 블록 좌표를 add
                if(c != 'X' && c == board[i][j + 1] && c == board[i + 1][j] && c == board[i + 1][j + 1]) {
                    positions.add(new Position(i, j));
                    positions.add(new Position(i, j + 1));
                    positions.add(new Position(i + 1, j));
                    positions.add(new Position(i + 1, j + 1));
                }
            }
        }
        
        if(positions.isEmpty())//사라질 블럭이 없다면 종료
             return cnt;
        cnt += positions.size();//없어질 블록 개수 합산
        
        list = new ArrayList<>(positions);//좌표를 정렬하기 위해 ArrayList로 변환
        Collections.sort(list);
        
        for(int i = 0; i < list.size(); i++) {//블럭 없애고 X로 채우기
            Position temp = list.get(i);
            int x = temp.x;
            int y = temp.y;
            System.arraycopy(board[x], y + 1, board[x], y, n - (y + 1));//블록 끌어 내리기
            board[x][n - 1] = 'X';//빈공간을 X로 채우기
        }
        
        //다시 없어지는 블록을 확인하기 위해 재귀 호출
        return fourBlock(cnt);
    }
    
    public int solution(int row, int col, String[] temp) {
        board = new char[col][row];
        int idx = 0;
        for(int i = 0; i < col; i++) {//편의를 위해 board를 시계방향으로 90도 회전
            for(int j = row - 1; j >= 0; j--) {
                board[i][idx++] = temp[j].charAt(i);
            }
            idx = 0;
        }
        m = col;
        n = row;
        
        return fourBlock(0);
    }
}
