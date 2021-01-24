package main.java;

import main.resources.Move;
import main.resources.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// Iterative deep search combination of BFS and DFS
public class IterativeDSearch {

    private long nodeCounter = 0; // number of nodes explored
    private Node solution; // storing the solution
    private List<Move> movesTrace = new ArrayList<>();; // move trace of solution

    public Node getSolution() {
        return solution;
    }

    public List<Move> getMovesTrace() {
        return movesTrace;
    }

    public long getNodeCounter() {
        return nodeCounter;
    }

    // search method main function
    public void search(ArrayList<List<Node>> graph){
        for (int j=0; j<graph.size(); j++) {
            // by passing a subset of graph at a time, we are doing Breadth first on given level
            // passing in the subset of the graph
                DSearch(new ArrayList<List<Node>>(graph.subList(0,j+1)));
            // this will avoid exploring nodes deeper than granted level
        }

    }

    // helper function
    public void DSearch(ArrayList<List<Node>> graph){
        // for a given subset of graph, do deep first search
        for (List<Node> nodes: graph){
            this.nodeCounter++; // nodes explored
            // exploring the children if graph size allows
            for (Node node: nodes){
                this.nodeCounter++;
                if (node.isSolution())
                    this.solution = node; // if solution find; break
                    break;
                }
            }
        }


    public List<Move> backTracing(Node solution){
        List<Move> movesTrace = new ArrayList<>();

        // tracing the moves in order to achieve this solution
        Node counter = solution;
        // when parent is null, initial state reached
        while (counter.getParent() != null){
            Move move = counter.getParent().getMove();
            movesTrace.add(move);
            counter = counter.getParent();
        }

        // reversing the order to make it in step-by-step order
        Collections.reverse(movesTrace);
        return movesTrace;
    }
    // Initializing the iterative deepening search
    public IterativeDSearch(ArrayList<List<Node>> graph){

        this.search(graph);
        // if solution found, backtrace the move set
        if (this.solution != null){
            this.movesTrace = this.backTracing(solution);
        }
    }
}
