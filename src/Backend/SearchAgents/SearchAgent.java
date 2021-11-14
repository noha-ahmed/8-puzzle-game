package Backend.SearchAgents;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
    protected String methodOfSearch;

    /*
    * searched for the path to a goal
    * if the goal could be reached it returns true
    * if not (unsolvable) it returns false
    */
    abstract public boolean  search(IState initialState);

    /*
    * this method constructs the path to the goal by iterating over the parents
    * starting from the gaol state and going up
    */
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

    /*
    * returns an arrayList of the states in the path
    * and adds the states to a file
    */
    public ArrayList<String> getPath(){
        try {
            String initialState = this.path.get(0);
            if(this.methodOfSearch.equalsIgnoreCase(ASTAR_MANHATTAN))
                this.methodOfSearch = "A_star (Manhattan)";
            else if(this.methodOfSearch.equalsIgnoreCase(ASTAR_EUCLIDEAN))
                this.methodOfSearch = "A_star (Euclidean)";
            FileWriter pathNodes = new FileWriter("src\\Path states_"+initialState+"_"+this.methodOfSearch+".txt");
            for(int i = 0; i < this.path.size(); i++){
                pathNodes.write("State " + i +" : "+path.get(i) + '\n');
            }
            pathNodes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.path;
    }
    /*
    * returns the cost of the path
    */
    public int getCost(){
        return this.cost;
    }
    /*
    * returns an arraylist of states expanded
    * and writes the states to a file
    */
    public ArrayList<String> getStatesExpanded(){
        try {
            String initialState = this.path.get(0);
            if(this.methodOfSearch.equalsIgnoreCase(ASTAR_MANHATTAN))
                this.methodOfSearch = "A_star (Manhattan)";
            else if(this.methodOfSearch.equalsIgnoreCase(ASTAR_EUCLIDEAN))
                this.methodOfSearch = "A_star (Euclidean)";
            FileWriter expandedNodes = new FileWriter("src\\Expanded states_"+initialState+"_"+this.methodOfSearch+".txt");
            for(int i = 0; i < this.statesExpanded.size(); i++){
                expandedNodes.write("State " + i +" : "+statesExpanded.get(i) + '\n');
            }
            expandedNodes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.statesExpanded;
    }
    /*
    * returns the max search depth the algorithm reached
    */
    public int getSearchDepth(){
        return this.searchDepth;
    }
    public void setMethodOfSearch(String method){
        this.methodOfSearch = method;
    }
}
