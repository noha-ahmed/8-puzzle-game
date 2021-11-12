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
    private PuzzleSolver(){}

    public static PuzzleSolver getInstance(){
        return solver;
    }

    public boolean solvePuzzle(String initialState, String methodOfSearch){
        agent = factory.createAgent(methodOfSearch);
        IState state = factory.createState(initialState, methodOfSearch);
        agent.setMethodOfSearch(methodOfSearch);
        long start = System.nanoTime();
        boolean isSolvable = agent.search(state);
        long end = System.nanoTime();
        runningTime = ( end-start)/1000;
        agent.constructPath();
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
    
    public void test(){
        System.out.println("Cost = " + getCost());
        System.out.println("Time = " + getRunningTime());
        System.out.println("Depth = " + getSearchDepth());
        System.out.println("Path: " + Arrays.toString(getPath().toArray()));
    }

    public static void main( String[] args ){
        PuzzleSolver puzzleSolver = PuzzleSolver.getInstance();
        String initialState = "125340678";
        String method1 = SearchAgent.BFS;
        String method2 = SearchAgent.DFS;
        String method3 = SearchAgent.ASTAR_EUCLIDEAN;
        System.out.println("BFS");
        System.out.println(puzzleSolver.solvePuzzle(initialState, method1));
        puzzleSolver.test();
        System.out.println("A*");
        System.out.println(puzzleSolver.solvePuzzle(initialState, method3));
        puzzleSolver.test();
        System.out.println("DFS");
        System.out.println(puzzleSolver.solvePuzzle(initialState, method2));
        puzzleSolver.test();

    }


}
