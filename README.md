# Coastguard Rescue Simulations

AI search algorithms applied to the task of a coastguard rescue route planner. Algorithms implemented:
- Uninformed search
  - Breadth-first search
    - Bidirectional search
  - Depth-first search
- Informed search
  - Best-first search
  - A* search
  - Option to use Euclidean distance for informed search

Starter code written by [Alice Toniolo](https://at258.host.cs.st-andrews.ac.uk/).

### Compiling and Running Instructions

Navigate to the src directory and compile all `.java` files:

```shell script
cd src
javac *.java
```

To run the application, your command should take the following form:

```shell script
java Main <Algorithm> <Conf_ID> [<EUCL>]
```

For example, to run breadth-first search on CONF0, pass:

```shell script
java Main BFS CONF0
```

Or to run A* on CONF1 with the Euclidean distance, pass:

```shell script
java Main AStar CONF1 EUCL
```

Valid arguments:
- Algorithm
    - BFS
    - DFS
    - BestF
    - AStar
    - BiDir
- Conf_ID
    - TCONF00
    - TCONF01
    - CONF1
    - CONF2
    - CONF3
    - ...
    - CONF24
- EUCL
    - Optional third parameter that, when passed for informed search exclusively, will result in the utilisation of the Euclidean distance as heuristic
    - When omitted, the Manhattan distance will be utilised by default

The output should be:

```
Configuration:TCONF00
Map:

           0 1 2 3 4 5 
          - - - - - -
      0/ 0 0 0 0 0 0 
     1/ 0 0 2 0 0 0 
    2/ 0 0 2 0 0 0 
   3/ 0 1 1 1 1 0 
  4/ 0 0 0 0 0 0 
 5/ 0 0 0 1 0 0 

Start - Person cell (r_p,c_p): (1,1)
Goal - Safety cell (r_s,c_s): (4,5)
Search algorithm: BFS
[(1,1)]
[(1,2), (0,1), (0,0), (1,0), (2,1)]
....
(1,1)(1,2)(1,3)(2,4)(3,5)(4,5)
5.0
22

```
