package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX,sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.remove();
            marked[v] = true;
            announce();
            if (v == t) {
                targetFound = true;
                break;
            }
            for (int w: maze.adj(v)) {
                if (!marked[w]) {
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                    announce();
                    queue.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

