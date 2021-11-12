package Backend;

import java.util.ArrayList;

public interface IPuzzleSolver {
    public boolean solvePuzzle(String initialState, String methodOfSearch);
    public int getCost();
    public ArrayList<String> getPath ();
    public long getRunningTime();
    public ArrayList<String> getExpandedStates();
    public int getSearchDepth();
}
