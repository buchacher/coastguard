/**
 * A class for a Node, named StacsNode to differentiate from Java's Node class.
 */
public class StacsNode {
    private Coord coord;
    private StacsNode parentNode;
    private double pathCost;
    private Coord goal;
    private double fCost;

    public StacsNode(Coord coord, StacsNode parentNode, double pathCost) {
        this.coord = coord;
        this.parentNode = parentNode;
        this.pathCost = pathCost;
    }

    public StacsNode(Coord coord, StacsNode parentNode, double pathCost, Coord goal, double fCost) {
        this.coord = coord;
        this.parentNode = parentNode;
        this.pathCost = pathCost;
        this.goal = goal;
        this.fCost = fCost;
    }

    /**
     * @return A StacsNode's {@link Coord}
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * @return A StacsNode's parent node.
     */
    public StacsNode getParentNode() {
        return parentNode;
    }

    /**
     * @return A StacsNode's path cost.
     */
    public double getPathCost() {
        return pathCost;
    }

    /**
     * @return A StacsNode's cost function.
     */
    public double getFCost() {
        return fCost;
    }
}
