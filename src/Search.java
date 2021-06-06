import java.util.ArrayList;

public abstract class Search {

    private Map map;
    private Coord start;
    private Coord goal;

    public Search(Map map, Coord start, Coord goal) {
        this.map = map;
        this.start = start;
        this.goal = goal;
    }

    /**
     * @return True when a {@link Coord} is the goal.
     */
    protected static boolean goalTest(Coord c, Coord goal) {
        return c.equals(goal);
    }

    /**
     * @return Valid successors.
     */
    protected static ArrayList<Coord> successorFunction(StacsNode nd, Map map) {
        int r = nd.getCoord().getR();
        int c = nd.getCoord().getC();
        ArrayList<Coord> successors = new ArrayList<>();

        if (map.getMap()[r][c] == 2) {
            // Only two options if we're on a 2 cell
            // 2. E: r, c+1
            if (checkInBounds(r, c+1, map)) {
                int e = map.getMap()[r][c+1];
                if ((e == 0) || (e == 2)) {
                    successors.add(new Coord(r, c+1));
                }
            }
            // 5. W: r, c-1
            if (checkInBounds(r-1, c-1, map)) {
                int w = map.getMap()[r][c-1];
                if ((w == 0) || (w == 2)) {
                    successors.add(new Coord(r, c-1));
                }
            }
        }
        else {
            // 1. SE: r+1, c+1
            if (checkInBounds(r+1, c+1, map)) {
                int se = map.getMap()[r+1][c+1];
                if (se == 0) {
                    successors.add(new Coord(r+1, c+1));
                }
            }

            // 2. E: r, c+1
            if (checkInBounds(r, c+1, map)) {
                int e = map.getMap()[r][c+1];
                if ((e == 0) || (e == 2)) {
                    successors.add(new Coord(r, c+1));
                }
            }

            // 3. NE: r-1, c
            if (checkInBounds(r-1, c, map)) {
                int ne = map.getMap()[r-1][c];
                if (ne == 0) {
                    successors.add(new Coord(r-1, c));
                }
            }

            // 4. NW: r-1, c-1
            if (checkInBounds(r-1, c-1, map)) {
                int nw = map.getMap()[r-1][c-1];
                if (nw == 0) {
                    successors.add(new Coord(r-1, c-1));
                }
            }

            // 5. W: r, c-1
            if (checkInBounds(r, c-1, map)) {
                int w = map.getMap()[r][c-1];
                if ((w == 0) || (w == 2)) {
                    successors.add(new Coord(r, c-1));
                }
            }

            // 6. SW: r+1, c
            if (checkInBounds(r+1, c, map)) {
                int sw = map.getMap()[r+1][c];
                if (sw == 0) {
                    successors.add(new Coord(r+1, c));
                }
            }
        }

        return successors;
    }


    /**
     * @return True if a cell is within the bounds of a {@link Map}.
     */
    protected static boolean checkInBounds(int r, int c, Map map) {
        // (index >= 0) && (index < array.length)
        int mapLength = map.getMap().length;
        return (r >= 0) && (r < mapLength) && (c >= 0) && (c < mapLength);
    }

    /**
     * @return The ancestors of a {@link StacsNode}.
     */
    protected ArrayList<StacsNode> getAncestry(StacsNode nd, ArrayList<StacsNode> ancestors) {
        ancestors.add(nd);
        StacsNode nn = nd.getParentNode();
        if (!(nd.getParentNode() == null)) {
            getAncestry(nn, ancestors);
        }

        return ancestors;
    }

    /**
     * Print the search path based on ancestors.
     */
    protected void printPath(ArrayList<StacsNode> ancestors) {
        for (StacsNode ancestor : ancestors) {
            System.out.print("(" + ancestor.getCoord().getR() + "," + ancestor.getCoord().getC() + ")");
        }
        System.out.println();
    }
}
