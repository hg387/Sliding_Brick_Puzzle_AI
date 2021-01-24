package main.java;


import main.resources.*;

import java.io.IOException;
import java.util.*;
// Main function
public class Main {

    public static void main(String[] args) {
        String fileName = args[0]; // reading SBP-level1.txt
        String fileName1 = args[1]; // reading SBP-level0.txt for random walker
        String N = args[2]; // for random walker
        try {
            HashSet<ArrayList<List<Integer>>> states = new HashSet<>(); // set of all the states covered
            ArrayList<List<Integer>> matrix = new FileReader().fileReader(fileName); //opening SBP-level1.txt

            // Game play Begin
            // Initializing the state , pieces data and locations, and all the moves possible
            State initialState = new State(matrix);
            StateToPieces stateToPieces = new StateToPieces(initialState);
            ArrayList<Piece> pieces = stateToPieces.getPieces();
            HashMap<Integer, ArrayList<List<Integer>>> piecesMap = stateToPieces.getPiecesMap();

            MovesForState movesForState = new MovesForState(initialState, pieces);
            HashSet<Move> moves = movesForState.getMoves();
            List<Move> movesList = new ArrayList<>(moves);

            ArrayList<List<Node>> levels = new ArrayList<>(); // for storing all the nodes
            List<Node> graph = new ArrayList<>();
            for (Move move: moves){
                graph.add(new Node(new StateCloner(initialState).getDuplicateState(), move));
            }
            levels.add(graph);

            // Generating new nodes by applying moves to current nodes
            for (Integer i = 0; i < levels.size(); i++){
                List<Node> level = levels.get(i);
                List<Node> childNodes = new ArrayList<>();
                Iterator<Node> j = level.iterator();
                while(j.hasNext()) {
                    Node node = j.next();
                    if (!node.getS().evaluate()) {
                        ApplyMove applyMove = new ApplyMove(node.getS(), node.getMove(), new StateToPieces(node.getS()).getPiecesMap());
                        if (!states.contains(applyMove.getNewState().getMatrix())) { // state already present, kill current iteration
                            states.add(applyMove.getNewState().getMatrix());
                            if (!applyMove.getNewState().evaluate()) { // if goal is reached, kill the current iteration
                                // for every new state find the pieces location and moves possible
                                stateToPieces = new StateToPieces(applyMove.getNewState());
                                pieces = stateToPieces.getPieces();
                                piecesMap = stateToPieces.getPiecesMap();

                                movesForState = new MovesForState(applyMove.getNewState(), pieces);
                                moves = movesForState.getMoves();
                                movesList = new ArrayList<>(moves);

                                // moves possible that can applied
                                for (Move move : moves) {
                                    childNodes.add(new Node(new StateCloner(applyMove.getNewState()).getDuplicateState(), move));
                                }
                                // make the new nodes, children of current node
                                node.addChildren(childNodes);

                            } else {
                                // if goal reached, add the states into the hash set and make that node children of current node
                                Node solNode = new Node(new StateCloner(applyMove.getNewState()).getDuplicateState(), true);
                                childNodes.add(solNode);
                                node.addChildren(solNode);
                            }
                        }
                        else {
                            j.remove(); // kill the duplicate nodes
                        }
                    }

                }
                if (!childNodes.isEmpty()){
                    levels.add(i+1, childNodes); // add the nodes in the graph
                }
            }
            // All the console output
            // Random walker
            System.out.println("Random walker started");
            ArrayList<List<Integer>> matrixForWalker = new FileReader().fileReader(fileName1);
            State initialStateForWalker = new State(matrixForWalker);
            RandomWalker randomWalker = new RandomWalker(Integer.parseInt(args[2]), initialStateForWalker);
            System.out.println("Random walker ended\n");

            // breadth first search
            System.out.println("Breadth first search");
            long start = System.currentTimeMillis();
            BFirstSearch bFirstSearch = new BFirstSearch(levels);
            long end = System.currentTimeMillis();
            Node solution = bFirstSearch.getSolution();
            System.out.println("\n"+bFirstSearch.getNodeCounter() + " nodes explored in "+ (end - start) + "ms");
            solution.getS().display();
            List<Move> movesTrace = bFirstSearch.getMovesTrace();

            // depth first search
            System.out.println("\nDepth first search");
            start = System.currentTimeMillis();
            DFirstSearch dFirstSearch = new DFirstSearch(levels);
            end = System.currentTimeMillis();
            solution = dFirstSearch.getSolution();
            System.out.println("\n"+dFirstSearch.getNodeCounter() + " nodes explored in "+ (end - start) + "ms");
            solution.getS().display();
            movesTrace = dFirstSearch.getMovesTrace();

            //iterative deepening search
            System.out.println("\nIterative first search");
            start = System.currentTimeMillis();
            IterativeDSearch IDSearch = new IterativeDSearch(levels);
            end = System.currentTimeMillis();
            solution = IDSearch.getSolution();
            System.out.println("\n"+IDSearch.getNodeCounter() + " nodes explored in "+ (end - start) + "ms");
            solution.getS().display();
            movesTrace = IDSearch.getMovesTrace();



        }
        catch (IOException e){System.out.println("Exception Thrown : "  + e);}
    }
}
