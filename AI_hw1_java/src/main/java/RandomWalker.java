package main.java;

import main.resources.*;

import java.util.*;

// Random walker, for a given "N", apply a random move to given state
// and normalize the state, if resulting state is goal, exit; otherwise loop through
// these steps N times.
public class RandomWalker {

    public RandomWalker(int N, State state){
        ArrayList<List<Integer>> matrix = state.getMatrix();
        Integer h = matrix.size();
        Integer w = matrix.get(0).size();
        // displaying the initial state
        System.out.println(w + "," + h + ",");
        state.display();

        // looping through the procedure N times
        for (int i=0; i< N; i++) {
            // get the pieces of current state
            StateToPieces stateToPieces = new StateToPieces(state);
            ArrayList<Piece> pieces = stateToPieces.getPieces();
            HashMap<Integer, ArrayList<List<Integer>>> piecesMap = stateToPieces.getPiecesMap();

            // get the all available moves for a state
            MovesForState movesForState = new MovesForState(state, pieces);
            HashSet<Move> moves = movesForState.getMoves();
            List<Move> movesList = new ArrayList<>(moves);

            // select a move at random
            Random random = new Random();
            Integer randomMove = random.nextInt(movesList.size());

            // if current state is goal -> break
            if (!state.evaluate()) {
                movesList.get(randomMove).display();
                ApplyMove applyMove = new ApplyMove(state, movesList.get(randomMove), piecesMap); // apply the move
                state = applyMove.getNewState(); // get the new state
                System.out.println("\n"+ w + "," + h + ","); // display the state
                state.display();
                State duplicateState = new StateCloner(state).getDuplicateState(); // deep clone the state
                Normalization normalization = new Normalization(duplicateState.getMatrix()); // normalize the cloned state
                if (duplicateState.evaluate()){break;} // if normalized state is goal; break
            }else{break;}

        }
    }
}
