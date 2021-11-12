package Backend.SearchAgents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import Backend.State.*;

public class BFSAgent extends SearchAgent {



    @Override
    public boolean search(IState initialState) {
        Queue<IState> frontier = new LinkedList<>();
        Set<String> frontierOrExplored = new HashSet<>();
        frontier.add(initialState);
        frontierOrExplored.add(initialState.getValue());
        IState state;

        while(!frontier.isEmpty()){
            state = frontier.poll();
            statesExpanded.add(state.getValue());
            this.searchDepth  =Math.max(this.searchDepth, state.getDepth());
            if(state.isGoal()){
                this.finalState = state;
                return true;
            }
            ArrayList<IState> neighbours = state.getNeighbours();
            for(int i=0; i<neighbours.size(); i++){
                if(!frontierOrExplored.contains(neighbours.get(i).getValue())){
                    neighbours.get(i).setParent(state);
                    frontier.add(neighbours.get(i));
                    frontierOrExplored.add(neighbours.get(i).getValue());
                }
            }
        }
        return false;
    }
}
