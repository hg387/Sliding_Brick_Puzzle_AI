package main.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// deep cloning a state
public class StateCloner {
    private State duplicateState; // return a duplicate state

    public StateCloner(State s){
        ArrayList<List<Integer>> matrix = s.getMatrix();
        ArrayList<List<Integer>> duplicateMatrix = new ArrayList<>();
        // creating matrix for new states
        for (Integer row=0; row < matrix.size(); row++){
            duplicateMatrix.add(row, new ArrayList<>(matrix.get(row)));
        }

        this.duplicateState = new State(duplicateMatrix);

    }

    public State getDuplicateState() {
        return duplicateState;
    }

    public void setDuplicateState(State duplicateState) {
        this.duplicateState = duplicateState;
    }
}
