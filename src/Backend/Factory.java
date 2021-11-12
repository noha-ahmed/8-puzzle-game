package Backend;

import Backend.Heuristics.*;
import Backend.SearchAgents.*;
import Backend.State.*;


public class Factory {
    /*
    * it takes a string type of agent(search method)
    * returns the correct agent
    */
    public SearchAgent createAgent(String type){
        if(type.compareToIgnoreCase(SearchAgent.BFS) == 0)
            return new BFSAgent();
        else if(type.compareToIgnoreCase(SearchAgent.DFS) == 0)
            return new DFSAgent();
        else if(type.compareToIgnoreCase(SearchAgent.ASTAR_EUCLIDEAN) == 0)
            return new AStarAgent(new EuclideanHeuristic());
        else if(type.compareToIgnoreCase(SearchAgent.ASTAR_MANHATTAN) == 0)
            return new AStarAgent(new ManhattanHeuristic());
        
        return null;
    }
    /*
     * it takes a string type of agent(search method)
     * returns the correct state
     * normal state in case of BFS/DFS
     * Astate in case of A*
     */
    public IState createState(String state, String methodType){
        if(methodType.compareToIgnoreCase(SearchAgent.BFS) == 0 || methodType.compareToIgnoreCase(SearchAgent.DFS) == 0)
            return new State(state);
        if(methodType.compareToIgnoreCase(SearchAgent.ASTAR_EUCLIDEAN) == 0 ||
         methodType.compareToIgnoreCase(SearchAgent.ASTAR_MANHATTAN) == 0)
            return new AState(state, 0);
        return null;
    }

}
