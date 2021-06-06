import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch extends InformedSearch {

    public BestFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);

        runAlgorithm(map, start, goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateFOfN(Coord coord, Coord goal, double pathCost) {
        return heuristicDistance(coord, goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void successorAdder(StacsNode node, PriorityQueue<StacsNode> frontier, Coord goal,
                               ArrayList<Coord> nextStates, ArrayList<Coord> frontierStates,
                               ArrayList<Coord> exploredStates, ArrayList<StacsNode> successors) {
        for (Coord state : nextStates) {
            if ((!exploredStates.contains(state)) && (!frontierStates.contains(state))) {
                StacsNode nd = makeNode(state, goal, node, node.getPathCost()+1);
                successors.add(nd);
            }
        }
    }
}
