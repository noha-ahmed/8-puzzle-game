package Backend.Heuristics;

import Backend.State.IState;

public class ManhattanHeuristic implements IHeuristic{
    static final int[] goalX = {0,0,0,1,1,1,2,2,2};
    static final int[] goalY = {0,1,2,0,1,2,0,1,2};
    int x;
    int y;
    @Override
    public int costH(IState state) {
        int cost = 0;
        String tempVal = state.getValue();
        for(int i = 0; i < tempVal.length(); i++){
            x = i / 3;
            y = i % 3;
            int index = Character.getNumericValue(tempVal.charAt(i));
            cost += (Math.abs(x - goalX[index]) + Math.abs(y - goalY[index]));
        }
        return cost;
    }
}
