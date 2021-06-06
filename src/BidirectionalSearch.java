import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BidirectionalSearch extends BreadthFirstSearch {

    public BidirectionalSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);

        runAlgorithm(map, start, goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runAlgorithm(Map map, Coord start, Coord goal) {

        LinkedList<StacsNode> frontierForward = new LinkedList<>();
        LinkedList<StacsNode> frontierBackward = new LinkedList<>();

        ArrayList<StacsNode> exploredForward = new ArrayList<>();
        ArrayList<StacsNode> exploredBackward = new ArrayList<>();

        StacsNode rootNode = makeNode(start, null, 0);
        StacsNode goalNode = makeNode(goal, null, 0);

        insertNode(rootNode, frontierForward);
        insertNode(goalNode, frontierBackward);

        printFrontier(frontierForward);
        printFrontier(frontierBackward);

        boolean intersect = false;

        while (!frontierForward.isEmpty() || !frontierBackward.isEmpty()) {

            StacsNode nf = removeNode(frontierForward);
            StacsNode nb = removeNode(frontierBackward);
            exploredForward.add(nf);
            exploredBackward.add(nb);

            if (intersectionTest(frontierForward, frontierBackward)) {
                ArrayList<StacsNode> ancestorsForward = new ArrayList<>();
                ArrayList<StacsNode> ancestorsBackward = new ArrayList<>();

                getAncestry(nf, ancestorsForward);
                getAncestry(nb, ancestorsBackward);

                Collections.reverse(ancestorsForward);
                Collections.reverse(ancestorsBackward);

                printPath(ancestorsForward);
                printPath(ancestorsBackward);


                intersect = true;
                System.out.println(nf.getPathCost());
                System.out.println(nb.getPathCost());
                System.out.println(exploredForward.size()); // Equivalent to a visited counter
                System.out.println(exploredBackward.size()); // Equivalent to a visited counter
                break;
            }
            else {
                ArrayList<StacsNode> newNodesForward = expand(nf, map, frontierForward, exploredForward, goal);
                ArrayList<StacsNode> newNodesBackward = expand(nb, map, frontierForward, exploredForward, goal);

                insertAll(newNodesForward, frontierForward);
                insertAll(newNodesBackward, frontierBackward);

                printFrontier(frontierForward);
                printFrontier(frontierBackward);
            }
        }

        if (!intersect) {
            System.out.println("fail");
        }
    }

    /**
     * @return True of the frontiers intersect.
     */
    private boolean intersectionTest(LinkedList<StacsNode> frontierForward, LinkedList<StacsNode> frontierBackward) {
        boolean intersect = false;

        ArrayList<Coord> frontierStatesForward = new ArrayList<>();
        for (StacsNode nf : frontierForward) {
            frontierStatesForward.add(nf.getCoord());
        }

        ArrayList<Coord> frontierStatesBackward = new ArrayList<>();
        for (StacsNode nb : frontierBackward) {
            frontierStatesBackward.add(nb.getCoord());
        }

        for (Coord cf : frontierStatesForward) {
            for (Coord cb : frontierStatesBackward) {
                if (cf.equals(cb)) {
                    intersect = true;
                    break;
                }
            }
        }

        return intersect;
    }

    /**
     * @return The {@link StacsNode} at which the frontiers intersect.
     */
    private Coord getIntersection(LinkedList<StacsNode> frontierForward, LinkedList<StacsNode> frontierBackward) {
        Coord intersectionPoint = null;

        ArrayList<Coord> frontierStatesForward = new ArrayList<>();
        for (StacsNode nf : frontierForward) {
            frontierStatesForward.add(nf.getCoord());
        }

        ArrayList<Coord> frontierStatesBackward = new ArrayList<>();
        for (StacsNode nb : frontierBackward) {
            frontierStatesBackward.add(nb.getCoord());
        }

        for (Coord cf : frontierStatesForward) {
            for (Coord cb : frontierStatesBackward) {
                if (cf.equals(cb)) {
                    intersectionPoint = cf;
                    break;
                }
            }
        }

        return intersectionPoint;
    }
}
