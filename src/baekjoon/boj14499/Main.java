package baekjoon.boj14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.solve();
    }

    int[][] board = new int[20+1][20+1];
    int[] inst = new int[1000+2];
    int N, M, K;
    int X, Y;

    public void solve() throws IOException {
        getInput();
        Dice dice = new Dice(X,Y, this.board);
        for (int i = 0; i < K; i++){
            int result = dice.moveDice(inst[i]);
            if(result == -1) continue;
            System.out.println(result);
        }
    }

    void getInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        N = Integer.parseInt(firstLine[0]);
        M = Integer.parseInt(firstLine[1]);
        X = Integer.parseInt(firstLine[2]);
        Y = Integer.parseInt(firstLine[3]);
        K = Integer.parseInt(firstLine[4]);

        for(int i = 0; i < N; i++){
            String[] newLine = br.readLine().split(" ");
            for(int j = 0 ; j < M; j++){
                board[i][j] = Integer.parseInt(newLine[j]);
            }
        }

        String[] inst = br.readLine().split(" ");
        for(int i = 0 ; i < K; i++){
            this.inst[i] = Integer.parseInt(inst[i]);
        }
    }

    class Dice{
        int curX;
        int curY;
        int[][] surface = new int[4][3];

//        surface = {
//            {0,2,0},
//            {4,1,3},
//            {0,5,0},
//            {0,6,0}
//        };

        int[][] board;

        public Dice(int curX, int curY, int[][] board) {
            this.curX = curX;
            this.curY = curY;
            this.board = board;
        }

        private final static int TOP_R = 1;
        private final static int TOP_C = 1;
        private final static int BOTTOM_R = 3;
        private final static int BOTTOM_C = 1;

        final int[] dx = {100,0,0,-1,1};
        final int[] dy = {100,1,-1,0,0};

        public void moveEast(){
            int temp = surface[3][1];
            surface[3][1] = surface[1][2];
            surface[1][2] = surface[1][1];
            surface[1][1] = surface[1][0];
            surface[1][0] = temp;
        }

        public void moveWest(){
            int temp = surface[3][1];
            surface[3][1] = surface[1][0];
            surface[1][0] = surface[1][1];
            surface[1][1] = surface[1][2];
            surface[1][2] = temp;
        }

        public void moveSouth(){
            int temp = surface[3][1];
            for(int i = 3; i >= 1; i--){
                surface[i][1] = surface[i-1][1];
            }
            surface[0][1] = temp;
        }

        public void moveNorth(){
            int temp = surface[0][1];
            for(int i = 0; i <= 2; i++){
                surface[i][1] = surface[i+1][1];
            }
            surface[3][1] = temp;
        }

        public int getTopValue(){
            return this.surface[TOP_R][TOP_C];
        }

        public int getBottomValue(){
          return this.surface[BOTTOM_R][BOTTOM_C];
        }

        public void setBottomValue(int value){
            this.surface[BOTTOM_R][BOTTOM_C] = value;
        }

        public int moveDice(int dir){
            if(!canMove(dir)) return -1;

            curX += dx[dir];
            curY += dy[dir];

            switch (dir){
                case 1:
                    moveEast();
                    break;
                case 2:
                    moveWest();
                    break;
                case 3:
                    moveNorth();
                    break;
                case 4:
                    moveSouth();
            }

            if(board[curX][curY] == 0){
                this.board[curX][curY] = getBottomValue();
            }else{
                setBottomValue(board[curX][curY]);
                this.board[curX][curY] = 0;
            }

            return getTopValue();
        }

        public boolean canMove(int dir){
            int nx = curX + dx[dir];
            int ny = curY + dy[dir];

            return nx >= 0 && nx < N && ny >= 0 && ny < M;
        }

    }
}

