package baekjoon.boj11727;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    int n;
    int[] table = new int[1000 + 2];

    public void solve(){
        getInput();
        table[1] = 1;
        table[2] = 3;
        for(int i = 3; i <= n; i++){
            table[i] = (table[i-1])%10007 + ((2*table[i-2])%10007);
        }
        System.out.println(table[n] % 10007);
    }

    public void getInput(){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
    }
}
