import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public  static void main(String[]args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Give me the number of missionaries and cannibals : ");
        int N = s.nextInt();
        System.out.println("Give me the number of maximum people in boat : ");
        int m = s.nextInt();
        System.out.println("Give me the maximum steps of the algorithm : ");
        int maxSteps=s.nextInt();
        stateNode initialState = new stateNode(N, N, m, 0, 0, 1);
        initialState.setg(0);
        A_star alg = new A_star(maxSteps);
        long start = System.currentTimeMillis();
        stateNode terminalState = alg.run(initialState, initialState.getScore());
        long end = System.currentTimeMillis();
        if (terminalState == null) System.out.println("Could not find a solution.");
        else {
            // print the path from beggining to start.
            stateNode temp = terminalState; // begin from the end.
            ArrayList<stateNode> path = new ArrayList<>();
            path.add(terminalState);
            while (temp.getFather() != null) // if father is null, then we are at the root.
            {
                path.add(temp.getFather());
                temp = temp.getFather();
            }
            // reverse the path and print.
            Collections.reverse(path);
            for (stateNode item : path) {
                item.print();
            }
            System.out.println();
            System.out.println("Algorithm running time in ms : " + (end-start));

        }

    }
}
