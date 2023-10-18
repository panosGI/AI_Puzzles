import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class A_star{
    private ArrayList<stateNode> frontier;
    private HashSet<stateNode> closedSet;
    private int k;


    A_star(int k)
    {
        this.frontier = new ArrayList<>();
        this.closedSet = new HashSet<>();
        this.k=k;


    }


    stateNode run(stateNode initialState, int f)
    {
        if(initialState.isFinal()) return initialState;
        // step 1: put initial state in the frontier.
        this.frontier.add(initialState);

        // step 2: check for empty frontier.
        int steps =0;
        while(this.frontier.size() > 0  && steps<=k)
        {
            // step 3: get the first node out of the frontier.
            stateNode currentState = this.frontier.remove(0);

            // step 4: if final state, return.
            if(currentState.isFinal()) return currentState;

            // step 5: if the node is not in the closed set, put the children at the frontier.
            // else go to step 2.
            if(!this.closedSet.contains(currentState))
            {
                this.closedSet.add(currentState);
                this.frontier.addAll(currentState.getChildren());// step 6: sort the frontier based on the heuristic score to get best as first
                Collections.sort(this.frontier); // sort the frontier to get best as first
            }
            steps++;

        }
        return null;
    }
}
