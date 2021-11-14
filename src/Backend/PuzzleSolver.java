package Backend;

import java.util.ArrayList;
import java.util.Arrays;
import Backend.SearchAgents.*;
import Backend.State.*;

public class PuzzleSolver implements IPuzzleSolver {
    private static PuzzleSolver solver = new PuzzleSolver();
    private Factory factory = new Factory();
    private SearchAgent agent;
    private Long runningTime;
    /*
    * private constructor so only one instance is created
    */
    private PuzzleSolver(){}

    /*
    * returns the instance of the class
    */
    public static PuzzleSolver getInstance(){
        return solver;
    }
    /*
    * takes the initial state as string and type of search
    * returns true if the goal is reached, false if not
    */
    public boolean solvePuzzle(String initialState, String methodOfSearch){
        agent = factory.createAgent(methodOfSearch);
        IState state = factory.createState(initialState, methodOfSearch);
        agent.setMethodOfSearch(methodOfSearch);
        // calculating running time of the method
        long start = System.nanoTime();
        boolean isSolvable = agent.search(state);
        long end = System.nanoTime();
        runningTime = ( end-start)/1000;
        // constructing the oath to goal
        agent.constructPath();
        // if it's solvable write expanded states in file
        if(isSolvable)
            getExpandedStates();
        return isSolvable;
    }

    public int getCost(){
        return agent.getCost();
    }

    public ArrayList<String> getPath (){
        return agent.getPath();
    }

    public long getRunningTime(){
        return runningTime;
    }

    public ArrayList<String> getExpandedStates(){
        return agent.getStatesExpanded();
    }

    public int getSearchDepth(){
        return agent.getSearchDepth();
    }
}
