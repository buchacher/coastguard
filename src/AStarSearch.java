import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarSearch extends InformedSearch {

    public AStarSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);

        runAlgorithm(map, start, goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateFOfN(Coord coord, Coord goal, double pathCost) {
        return heuristicDistance(coord, goal) + pathCost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void successorAdder(StacsNode node, PriorityQueue<StacsNode> frontier, Coord goal,
                               ArrayList<Coord> nextStates, ArrayList<Coord> frontierStates,
                               ArrayList<Coord> exploredStates, ArrayList<StacsNode> successors) {
        for (Coord state : nextStates) {
            StacsNode nd = makeNode(state, goal, node, node.getPathCost()+1);
            if ((!exploredStates.contains(state)) && (!frontierStates.contains(state))) {
                successors.add(nd);
            }
            else if (frontierStates.contains(state)) {
                try {
                    for (StacsNode tn : frontier) {
                        if (tn.getCoord().equals(state)) {
                            if (tn.getPathCost() > nd.getPathCost()) {
                                frontier.remove(tn);
                                frontier.add(nd);
                            }
                        }
                    }
                } catch (Exception ignore) {
                    // TODO java.util.ConcurrentModificationException for Euclidean
                }

            }
        }
    }
}

