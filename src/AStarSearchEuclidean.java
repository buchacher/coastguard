public class AStarSearchEuclidean extends AStarSearch {

    public AStarSearchEuclidean(Map map, Coord start, Coord goal) {
        super(map, start, goal);
    }

    /**
     * {@inheritDoc}
     * References:
     *  - https://www.redblobgames.com/grids/hexagons/
     */
    @Override
    public double heuristicDistance(Coord curr, Coord goal) {
        int currR = curr.getR()*-1;
        int currC = curr.getC();
        int goalR = goal.getR()*-1;
        int goalC = goal.getC();

        int diffR = currR - goalR;
        int diffC = currC - goalC;

        return Math.sqrt(Math.pow(diffR, 2) + Math.pow(diffC, 2) - (diffR * diffC));
    }
}
