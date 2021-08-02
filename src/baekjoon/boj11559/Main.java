package baekjoon.boj11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    char[][] board = new char[12+1][6+1];
    boolean[][] visited = new boolean[12+1][6+1];

    public void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int r =0 ;r < 12; r++){
            String str = br.readLine();
            board[r] = str.toCharArray();
        }
    }

    public void solve() throws IOException{
        int ys = 0;
        getInput();
        while (true){
            List<Cor> corToDelete = new LinkedList<>();
            clearVisited();
            for(int r = 0;r < 12; r++){
                for(int c = 0; c <6; c++){
                    Cor cor = new Cor(r,c);
                    List<Cor> connectedCor = bfs(cor);
                    if(connectedCor.size() >= 4) corToDelete.addAll(connectedCor);
                }
            }
            if(corToDelete.isEmpty()) break;
            delete(corToDelete);
            down();
            ys++;
        }
        System.out.println(ys);
    }

    private void down() {
        Queue<Character> q = new LinkedList<>();
        for(int c = 0 ; c < 6; c++){
            for(int r = 11; r >= 0; r--){
                if(board[r][c] != '.') q.offer(board[r][c]);
                board[r][c] = '.';
            }
            int r = 12-1;
            while (!q.isEmpty()){
                board[r][c] = q.poll();
                r--;
            }
        }
    }

    private void delete(List<Cor> corToDelete) {
        for (Cor cor : corToDelete) {
            board[cor.getX()][cor.getY()] = '.';
        }
    }

    public void clearVisited(){
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }
    }

    public List<Cor> bfs(Cor cor){ // 좌표와 연결된 책들을 List 형태로 묶어서 반환합니다.
        final int[] dx = {0,0,1,-1};
        final int[] dy = {1,-1,0,0};

        List<Cor> corList = new LinkedList<>();

        if(board[cor.getX()][cor.getY()] == '.') return corList;

        Queue<Cor> queue = new LinkedList<>();
        queue.offer(cor);
        corList.add(cor);
        visited[cor.x][cor.y] = true;

        while (!queue.isEmpty()){
            Cor curCor = queue.poll();
            int curX = curCor.getX();
            int curY = curCor.getY();
            int curChar = board[curX][curY];

            for(int dir = 0 ; dir < 4; dir++){
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                if(oob(nx,ny)) continue;
                if(visited[nx][ny]) continue;
                if(curChar != board[nx][ny]) continue;

                queue.offer(new Cor(nx,ny));
                visited[nx][ny] = true;
                corList.add(new Cor(nx,ny));
            }
        }

        return corList;
    }

    public boolean oob(int nx, int ny){
        return nx < 0 || nx >= 12 || ny < 0 || ny >= 6;
    }

    class Cor{
        private final int x, y;
        public Cor(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
