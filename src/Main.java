/********************Starter Code
 *
 * This class contains some examples on how to handle the required inputs and outputs
 * and other debugging options
 *
 * @author at258
 *
 * run with
 * java A1main <Algo> <ConfID>
 *
 */
public class Main {

    public static void main(String[] args) {

        Conf conf = Conf.valueOf(args[1]);

		System.out.println("Configuration:"+args[1]);
		System.out.println("Map:");
		printMap(conf.getMap());
		System.out.println("Start - Person cell (r_p,c_p): "+conf.getP());
		System.out.println("Goal - Safety cell (r_s,c_s): "+conf.getS());
		System.out.println("Search algorithm: "+args[0]);


        //run your search algorithm
        if (args.length > 2) {
            // Overloading the constructor will result in Euclidean distance as heuristic for informed search
            runSearch(args[0],conf.getMap(),conf.getP(),conf.getS(), args[2]);
        }
        else {
            runSearch(args[0],conf.getMap(),conf.getP(),conf.getS());
        }

    }

    /**
     * Runs search the with algorithm specified.
     */
    private static void runSearch(String algo, Map map, Coord start, Coord goal) {
        switch(algo) {
            case "BFS": //run BreadthFirstSearch
                BreadthFirstSearch bfs = new BreadthFirstSearch(map, start, goal);
                break;
            case "DFS": //run DepthFirstSearch
                DepthFirstSearch dfs = new DepthFirstSearch(map, start, goal);
                break;
            case "BestF": //run BestF
                BestFirstSearch bestF = new BestFirstSearch(map, start, goal);
                break;
            case "AStar": //run AStar
                AStarSearch aStar = new AStarSearch(map, start, goal);
                break;
            case "BiDir": //run BiDir
                BidirectionalSearch biDir = new BidirectionalSearch(map, start, goal);
                break;
        }
    }

    /**
     * Runs search when a third parameter for a non-default heuristic, i.e. Euclidean instead of Manhattan distance,
     * is provided.
     */
    private static void runSearch(String algo, Map map, Coord start, Coord goal, String heur) {
        switch(algo) {
            case "BestF": //run BestF
                BestFirstSearchEuclidean bestF = new BestFirstSearchEuclidean(map, start, goal);
                break;
            case "AStar": //run AStar
                AStarSearchEuclidean aStar = new AStarSearchEuclidean(map, start, goal);
                break;
        }
    }

    /**
     * Print function for a {@link Map}.
     */
    private static void printMap(Map m) {
        int[][] map=m.getMap();
        System.out.println();
        int rows=map.length;
        int columns=map[0].length;
        // first line
        for (int r = 0; r < rows + 5; r++) {
            System.out.print(" ");// shift to start
        }
        for (int c = 0; c < columns; c++) {
            System.out.print(c); //index
            if (c < 10) {
                System.out.print(" ");
            }
        }
        System.out.println();
        // second line
        for (int r = 0; r < rows + 3; r++) {
            System.out.print(" ");
        }
        for (int c = 0; c < columns; c++) {
            System.out.print(" -");// separator
        }
        System.out.println();
        // the map
        for (int r = 0; r < rows; r++) {
            for (int d = r; d < rows - 1; d++) {
                System.out.print(" ");// shift to position
            }
            if (r < 10) {
                System.out.print(" ");
            }
            System.out.print(r + "/ ");// index+separator
            for (int c = 0; c < columns; c++) {
                System.out.print(map[r][c] + " ");// value in the map
            }
            System.out.println();
        }
        System.out.println();
    }
}
