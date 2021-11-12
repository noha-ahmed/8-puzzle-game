package Backend.SearchAgents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import Backend.State.*;

public class DFSAgent extends SearchAgent {

    @Override
    public boolean search(IState initialState){ 
        Stack<IState> frontier = new Stack<>();
        Set<String> frontierAndExplored = new HashSet<>();
        frontier.push((State)initialState);
        frontierAndExplored.add(initialState.getValue());
        IState state;
        ArrayList<IState> neighbourStates;

        while( !frontier.isEmpty() ){
            state = frontier.pop();
            statesExpanded.add(state.getValue());
            this.searchDepth = Math.max(this.searchDepth, state.getDepth());

            if( state.isGoal() ){
                finalState = state;
                return true;
            }

            neighbourStates = state.getNeighbours();

            for( IState childState : neighbourStates ){
                if( ! frontierAndExplored.contains(childState.getValue()) ){
                    childState.setParent(state);
                    frontierAndExplored.add(childState.getValue());
                    frontier.push(childState);
                }
            }

        }
        return false;
    }

    public static void main( String[] args ){
        IState initialState = new State("432650781");
        SearchAgent agent = new DFSAgent();
        agent.search(initialState);
        System.out.println(agent.finalState);
        agent.finalState.printState();
        ArrayList<String> path = agent.getPath();
        for(String s : path ){
            System.out.println(s);
        }
    }
}
    