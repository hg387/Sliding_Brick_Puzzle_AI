package main.java;

import main.resources.Move;
import main.resources.Node;

import java.math.BigInteger;
import java.util.*;

public class BFirstSearch {
        private long nodeCounter=0;
        private Node solution;
        private List<Move> movesTrace = new ArrayList<>();;

        public Node getSolution() {
        return solution;
    }

        public List<Move> getMovesTrace() {
        return movesTrace;
    }

        public long getNodeCounter() {
        return nodeCounter;
    }
    // main search function recursive
    public void search(ArrayList<List<Node>> graph){
        List<Node> level = graph.get(0);
        Queue<Node> queue = new LinkedList<Node>(); // storing nodes in FIFO order
        // adding all the nodes at the same level
        queue.addAll(level); // check on a same level first
        while(queue.size() != 0) {
            this.iterate(queue); // move to the children
        }


    }
        // helper function for search
     public void search2(ArrayList<List<Node>> graph){
          for (List<Node> level:graph){
              this.nodeCounter++;
              for (Node node: level){
                  if (node.isSolution()) {
                      this.nodeCounter++;
                      this.solution = node; // is solution at the current level
                  }
              }
          }
     }

    // helper function recursive
    public boolean iterate(Queue<Node> queue) {
        if (queue.size() != 0) {
            Node top = queue.poll(); // removing the first element
            this.nodeCounter++;
            if (top.isSolution()) {
                this.solution = top; // is solution at the current level
                return true;
            }
            if (top.getChildren().size() !=0) {
                queue.addAll(top.getChildren()); // adding the child nodes in FIFO order
                this.iterate(queue);
            }
        }
        return false;
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

    // Initializing the breadth first search
    public BFirstSearch(ArrayList<List<Node>> graph){

        this.search2(graph);
        // if solution found, get the move set
        if (solution != null){
            this.movesTrace = this.backTracing(solution);
        }
    }
}
