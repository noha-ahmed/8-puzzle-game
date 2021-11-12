package Backend.Heuristics;

import Backend.State.IState;

public interface IHeuristic {
    int costH(IState state);
}
