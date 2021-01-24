package main.resources;

import java.util.ArrayList;
import java.util.List;

// Node has the current state and the move to implement
public class Node {
    boolean solution; // if current node contains the solution state
    private State s; // current state
    private Move move; // added a move to make tracing easier
    private Node parent = null; // if current node has a parent
    private List<Node> children = new ArrayList<>(); // no. of nodes to which it is connected

    public Node(State s, Move move){
        this.s = s;
        this.move = move;
    }

    // when goal reached: putting the goal node without move
    public Node(State s, boolean solution){
        this.s = s;
        this.solution = solution;
    }

    // add a single child
    public void addChildren(Node child){
        child.setParent(this);
        this.children.add(child);
    }

    // add more than one children
    public void addChildren(List<Node> children){
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    // getters and setters
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }


    public State getS() {
        return s;
    }

    public Move getMove() {
        return move;
    }

    public void setS(State s) {
        this.s = s;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }
}
