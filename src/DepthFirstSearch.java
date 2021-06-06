import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class DepthFirstSearch extends UninformedSearch {

    public DepthFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);

        runAlgorithm(map, start, goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<StacsNode> insertNode(StacsNode node, LinkedList<StacsNode> frontier) {
        frontier.addFirst(node);
        return frontier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<StacsNode> insertAll(ArrayList<StacsNode> newNodes, LinkedList<StacsNode> frontier) {
        Collections.reverse(newNodes);

        for (StacsNode nd : newNodes) {
            frontier.addFirst(nd);
        }

        return frontier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StacsNode removeNode(LinkedList<StacsNode> frontier) {
        return frontier.removeFirst();
    }
}
