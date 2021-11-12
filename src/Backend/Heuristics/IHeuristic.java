package Backend.Heuristics;

import Backend.State.IState;

public interface IHeuristic {
    public static final String MANHATTAN = "MANHATTAN";
    int costH(IState state);
}
