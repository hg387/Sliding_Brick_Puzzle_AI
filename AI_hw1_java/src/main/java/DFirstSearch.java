package main.java;

import main.resources.Move;
import main.resources.Node;
import main.resources.State;

import java.math.BigInteger;
import java.util.*;

// depth first search
public class DFirstSearch {
    private long nodeCounter = 0; // counting the nodes explored
    private Node solution; // storing the solution found
    private List<Move> movesTrace = new ArrayList<>(); // back tracing the solution found moves set

    public Node getSolution() {
        return solution;
    }

    public List<Move> getMovesTrace() {
        return movesTrace;
    }

    public long getNodeCounter() {
         return nodeCounter;
    }

    // search function
    public void search(ArrayList<List<Node>> graph){
        List<Node> level = graph.get(0); // gettting the first level of nodes
        Stack<Node> stack = new Stack<>();
        for (Node node : level) {
            stack.add(node); // adding the nodes in order of LIFO
            this.nodeCounter++;
            while(!stack.isEmpty()) {
                Node top = stack.peek();
                stack.pop(); // removing the current node
                this.nodeCounter++; // increasing the node counter
                if (top.isSolution()) {
                    this.solution = top;
                    break;// if solution found store it
                }
                // if child nodes found explore them, before moving to next node at same level
                // increasing depth order
                if (top.getChildren().size() !=0) {
                    for (Integer i = top.getChildren().size() - 1; i >= 0; i--) {
                        stack.push(top.getChildren().get(i)); // adding the child nodes in order of LIFO
                    }
                }
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
    // Initializing the depth first search
    public DFirstSearch(ArrayList<List<Node>> graph){

        this.search(graph);
        // if solution found back trace it
        if (this.solution != null){
            this.movesTrace = this.backTracing(solution);
        }
    }
}
