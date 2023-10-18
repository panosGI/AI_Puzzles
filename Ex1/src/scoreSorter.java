
import java.util.Comparator;

public class scoreSorter implements Comparator<stateNode>
{
    @Override
    public int compare(stateNode o1, stateNode o2) {
        if(o1.getScore()<=o2.getScore()){
            return o1.getScore();
        }else {
            return o2.getScore();
        }
    }
}