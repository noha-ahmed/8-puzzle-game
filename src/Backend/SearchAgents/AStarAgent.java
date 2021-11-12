package Backend.SearchAgents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import Backend.Heuristics.*;
import Backend.State.*;

public class AStarAgent extends SearchAgent {
    private IHeuristic heuristicFn;
    private HashMap<String, AState> visited = new HashMap<String, AState>();
    private PriorityQueue<AState> frontier = new PriorityQueue();
    public AStarAgent (IHeuristic heuristic){
        this.heuristicFn = heuristic;
    }
    @Override
    public boolean search(IState initialState) {
        AState state = (AState) initialState;
        state.setCostH(heuristicFn.costH(state));
        state.setCostF();
        frontier.add(state);
        while (!frontier.isEmpty()){
            state = frontier.poll();
            visited.put(state.getValue(),state);
            this.statesExpanded.add(state.getValue());
            //making the search depth max depth reached till now
            this.searchDepth = Math.max(this.searchDepth, state.getDepth());

            if(state.isGoal()) {
                this.finalState = state;
                return true;
            }
            for(IState neighbour : state.getNeighbours()){
                AState temp = (AState) neighbour;
                temp.setParent(state);
                temp.setCostH(heuristicFn.costH(temp));
                temp.setCostF();
                if(!(frontier.contains(temp) || visited.containsKey(temp.getValue()))){
                    frontier.add(temp);
                }else if(frontier.contains(temp)){
                    // decrease key
                    int newCostG = state.getCostG() + 1;
                    int newCostF = newCostG + temp.getCostH();
                    if(newCostF < temp.getCostF()){
                        temp.setCostG(newCostG);
                        temp.setCostF();
                        temp.setParent(state);
                    }
                }
            }

        }
        return false;
    }
}

