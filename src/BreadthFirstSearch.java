import java.util.ArrayList;
import java.util.LinkedList;

public class BreadthFirstSearch extends UninformedSearch {

    public BreadthFirstSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);

        runAlgorithm(map, start, goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<StacsNode> insertNode(StacsNode node, LinkedList<StacsNode> frontier) {
        frontier.add(node);
        return frontier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<StacsNode> insertAll(ArrayList<StacsNode> newNodes, LinkedList<StacsNode> frontier) {
        frontier.addAll(newNodes);
        return frontier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StacsNode removeNode(LinkedList<StacsNode> frontier) {
        return frontier.remove();
    }
}
