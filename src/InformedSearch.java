import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class InformedSearch extends Search {

    public InformedSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
    }

    /**
     * @return The cost function.
     */
    public abstract double calculateFOfN(Coord coord, Coord goal, double pathCost);

    /**
     * Called in the expand function to
     */
    public abstract void successorAdder(StacsNode node, PriorityQueue<StacsNode> frontier, Coord goal,
                                        ArrayList<Coord> nextStates, ArrayList<Coord> frontierStates,
                                        ArrayList<Coord> exploredStates, ArrayList<StacsNode> successors);

    /**
     * Runs the respective algorithm.
     */
    protected void runAlgorithm(Map map, Coord start, Coord goal) {
        Comparator<StacsNode> fCostSorter = Comparator.comparing(StacsNode::getFCost);
        PriorityQueue<StacsNode> frontier = new PriorityQueue<>(fCostSorter);
        ArrayList<StacsNode> explored = new ArrayList<>();
        StacsNode rootNode = makeNode(start, goal, null, 0);
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
    protected ArrayList<StacsNode> expand(StacsNode node, Map map, PriorityQueue<StacsNode> frontier,
                                          ArrayList<StacsNode> explored, Coord goal) {
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
        successorAdder(node, frontier, goal, nextStates, frontierStates, exploredStates, successors);

        return successors;
    }

    /**
     * @return A {@link StacsNode} with attributes passed as parameters.
     */
    protected StacsNode makeNode(Coord coord, Coord goal, StacsNode parent, double pathCost) {
        return new StacsNode(coord, parent, pathCost, goal, calculateFOfN(coord, goal, pathCost));
    }

    /**
     * @return The frontier with a {@link StacsNode} inserted.
     */
    protected PriorityQueue<StacsNode> insertNode(StacsNode node, PriorityQueue<StacsNode> frontier) {
        frontier.add(node);
        return frontier;
    }

    /**
     * @return The frontier with all {@link StacsNode}s inserted.
     */
    protected PriorityQueue<StacsNode> insertAll(ArrayList<StacsNode> newNodes, PriorityQueue<StacsNode> frontier) {
        frontier.addAll(newNodes);
        return frontier;
    }

    /**
     * @return The {@link StacsNode} removed from the frontier.
     */
    protected StacsNode removeNode(PriorityQueue<StacsNode> frontier) {
        return frontier.poll();
    }

    /**
     * @return The heuristic function.
     */
    protected double heuristicDistance(Coord curr, Coord goal) {
        int currR = curr.getR()*-1;
        int currC = curr.getC();
        int goalR = goal.getR()*-1;
        int goalC = goal.getC();

        int diffR = currR - goalR;
        int diffC = currC - goalC;

        int hDistance;

        if ((diffR >= 0 && diffC >= 0) ||(diffR < 0 && diffC < 0)) {
            hDistance = Math.abs(diffR) + Math.abs(diffC);
        }
        else {
            hDistance = Math.max(Math.abs(diffR), Math.abs(diffC));
        }

        return hDistance;
    }

    /**
     * Print function for the frontier for informed search.
     */
    protected void printFrontier(PriorityQueue<StacsNode> frontier) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int count = 0;
        for (StacsNode n : frontier) {
            sb.append("(");
            sb.append(n.getCoord().getR());
            sb.append(",");
            sb.append(n.getCoord().getC());
            sb.append(")");
            sb.append(":");
            sb.append(n.getFCost());
            count++;
            if (count != frontier.size()) {
                sb.append(", ");
            }
        }
        sb.append("]");

        System.out.println(sb);
    }
}
