import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public abstract class UninformedSearch extends Search {

    public UninformedSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
    }

    /**
     * @return The frontier with a {@link StacsNode} inserted.
     */
    public abstract LinkedList<StacsNode> insertNode(StacsNode node, LinkedList<StacsNode> frontier);

    /**
     * @return The frontier with all {@link StacsNode}s inserted.
     */
    public abstract LinkedList<StacsNode> insertAll(ArrayList<StacsNode> newNodes, LinkedList<StacsNode> frontier);

    /**
     * @return The {@link StacsNode} removed from the frontier.
     */
    public abstract StacsNode removeNode(LinkedList<StacsNode> frontier);

    /**
     * Runs the respective algorithm.
     */
    protected void runAlgorithm(Map map, Coord start, Coord goal) {
        LinkedList<StacsNode> frontier = new LinkedList<>();
        ArrayList<StacsNode> explored = new ArrayList<>();
        StacsNode rootNode = makeNode(start, null, 0);
        insertNode(rootNode, frontier);
        printFrontier(frontier);
        boolean goalFound = false;

        while (!frontier.isEmpty()) {
            StacsNode nd = removeNode(frontier);
            explored.add(nd);

            if (goalTest(nd.getCoord(), goal)) {

                ArrayList<StacsNode> ancestors = new ArrayList<>();
                getAncestry(nd, ancestors);
                Collections.reverse(ancestors);
                printPath(ancestors);

                goalFound = true;
                System.out.println(nd.getPathCost());
                System.out.println(explored.size()); // Equivalent to a visited counter
                break;
            }
            else {
                ArrayList<StacsNode> newNodes = expand(nd, map, frontier, explored, goal);
                insertAll(newNodes, frontier);
                printFrontier(frontier);
            }
        }

        if (!goalFound) {
            System.out.println("fail");
        }
    }

    /**
     * @return A a {@link StacsNode}s successors, provided they are not in the frontier or were already explored.
     */
    protected ArrayList<StacsNode> expand(StacsNode node, Map map, LinkedList<StacsNode> frontier, ArrayList<StacsNode> explored, Coord goal) {
        ArrayList<Coord> nextStates = successorFunction(node, map);

        ArrayList<Coord> frontierStates = new ArrayList<>();
        for (StacsNode fn : frontier) {
            frontierStates.add(fn.getCoord());
        }

        ArrayList<Coord> exploredStates = new ArrayList<>();
        for (StacsNode en : explored) {
            exploredStates.add(en.getCoord());
        }

        ArrayList<StacsNode> successors = new ArrayList<>();
        for (Coord state : nextStates) {
            if ((!exploredStates.contains(state)) && (!frontierStates.contains(state))) {
                StacsNode nd = makeNode(state, node, node.getPathCost()+1);
                successors.add(nd);
            }
        }

        return successors;
    }

    /**
     * @return A {@link StacsNode} with attributes passed as parameters.
     */
    protected StacsNode makeNode(Coord coord, StacsNode parent, double pathCost) {
        return new StacsNode(coord, parent, pathCost);
    }

    /**
     * Print function for the frontier for uninformed search.
     */
    protected void printFrontier(LinkedList<StacsNode> frontier) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int count = 0;
        for (StacsNode n : frontier) {
            sb.append("(");
            sb.append(n.getCoord().getR());
            sb.append(",");
            sb.append(n.getCoord().getC());
            sb.append(")");
            count++;
            if (count != frontier.size()) {
                sb.append(", ");
            }
        }
        sb.append("]");

        System.out.println(sb);
    }
}
