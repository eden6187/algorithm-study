package programmers.거리두기_확인하기;

import java.util.*;

class Solution {
    int[][][] boards = new int[5][5][5];

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int idx = 0; idx < 5; idx++) {
            answer[idx] = checkBoard(places[idx]);
        }
        return answer;
    }

    final int[] dx = {0, 0, 1, -1};
    final int[] dy = {1, -1, 0, 0};

    int checkBoard(String[] strBoard) {
        char[][] board = strArrToCharArr(strBoard);
        int isOk = 1;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (board[row][col] != 'P') continue;

                Queue<CorWithDist> queue = new LinkedList<>();
                boolean[][] isVisited = new boolean[5][5];

                queue.offer(new CorWithDist(row, col, 0));
                isVisited[row][col] = true;

                while (!queue.isEmpty()) {
                    CorWithDist corWithDist = queue.poll();
                    int curRow = corWithDist.x;
                    int curCol = corWithDist.y;
                    int dist = corWithDist.d;
                    if (dist == 2) continue;

                    for (int d = 0; d < 4; d++) {
                        int nx = curRow + dx[d];
                        int ny = curCol + dy[d];
                        if (oob(nx, ny)) continue; // out of bound
                        if (isVisited[nx][ny]) continue;
                        if (board[nx][ny] == 'X') continue;
                        if (board[nx][ny] == 'P') {
                            isOk = 0;
                            continue;
                        }
                        isVisited[nx][ny] = true;
                        queue.offer(new CorWithDist(nx, ny, dist + 1));
                    }
                }
            }
        }

        return isOk;
    }

    boolean oob(int row, int col) {
        return row < 0 || row >= 5 || col < 0 || col >= 5;
    }

    char[][] strArrToCharArr(String[] str) {
        char[][] charArr = new char[5][5];
        for (int i = 0; i < 5; i++) {
            charArr[i] = str[i].toCharArray();
        }
        return charArr;
    }

    class CorWithDist {
        int x;
        int y;
        int d;

        CorWithDist(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}


