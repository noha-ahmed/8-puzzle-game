package Backend.SearchAgents;

import java.util.ArrayList;
import java.util.Collections;

import Backend.State.*;

public abstract class SearchAgent {
    public static final String DFS = "DFS";
    public static final String BFS = "BFS";
    public static final String ASTAR_MANHATTAN = "A* (Manhattan)";
    public static final String ASTAR_EUCLIDEAN = "A* (Euclidean)";
    protected IState finalState;
    protected int cost;
    protected int searchDepth = 0;
    protected ArrayList<String> statesExpanded = new ArrayList<>();
    protected ArrayList<String> path = new ArrayList<>();
    

    abstract public boolean  search(IState initialState);

    public void constructPath(){
        if(this.finalState == null){
            return;
        }
        IState state = finalState;
        do{
            path.add(state.getValue());
            state = state.getParent();
        }while( state != null );
        this.cost = this.path.size() - 1;
        Collections.reverse(this.path);
    }

    public ArrayList<String> getPath(){
        return this.path;
    }
    public int getCost(){
        return this.cost;
    }
    public ArrayList<String> getStatesExpanded(){
        return this.statesExpanded;
    }
    public int getSearchDepth(){
        return this.searchDepth;
    }
}
