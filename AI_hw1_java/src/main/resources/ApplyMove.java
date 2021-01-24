package main.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// for applying moves in current state
public class ApplyMove {
    private State newState;
    private State previousState;

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public State getNewState() {
        return newState;
    }

    public void setNewState(State newState) {
        this.newState = newState;
    }

    // given sate's move, pieceMap
    public ApplyMove(State s, Move move, HashMap<Integer, ArrayList<List<Integer>>> piecesMap){
        //ArrayList<List<Integer>> matrix = s.getMatrix();
        Character action = move.getMove();
        Integer data = move.getData();
        // deep cloning state
        State newState = new StateCloner(s).getDuplicateState(); // state after move is applied
        ArrayList<List<Integer>> matrix = newState.getMatrix();
        // if block is not in the list
        if(piecesMap.get(data) != null){
            ArrayList<List<Integer>> positions = piecesMap.get(data); // get the data and locations of the piece
            try {
                if (action.equals('u')) { // Up move
                    for (List<Integer> position : positions) { // for all the positions of the block
                        Integer row = position.get(0);
                        Integer column = position.get(1);

                        matrix.get(row - 1).set(column, data); // for up, do row -1
                        matrix.get(row).set(column, 0);
                    }
                } else if (action.equals('d')) {
                    for (Integer i=positions.size()-1; i>=0; i--) { // for all the positions of the block
                        List<Integer> position = positions.get(i);
                        Integer row = position.get(0);
                        Integer column = position.get(1);

                        matrix.get(row + 1).set(column, data); // for down, do row +1
                        matrix.get(row).set(column, 0);

                    }
                } else if (action.equals('l')) {
                    for (List<Integer> position : positions) { // for all the positions of the block
                        Integer row = position.get(0);
                        Integer column = position.get(1);

                        matrix.get(row).set(column - 1, data); // for left, do column -1
                        matrix.get(row).set(column, 0);
                    }
                } else if (action.equals('r')) {
                    for (Integer i=positions.size()-1; i>=0; i--) { // for all the positions of the block
                        List<Integer> position = positions.get(i);
                        Integer row = position.get(0);
                        Integer column = position.get(1);

                        matrix.get(row).set(column + 1, data); // for right, do column +1
                        matrix.get(row).set(column, 0);
                    }
                }
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Exception thrown at data: " + action);
            }
        }
        this.newState = newState; // new state stored
        this.previousState = s;
    }
}
