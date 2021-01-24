package main.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// all the moves available for a given state
public class MovesForState {
    private HashSet<Move> moves; // hash set to avoid repeating a same move

    public MovesForState(State s, ArrayList<Piece> pieces){
        ArrayList<List<Integer>> matrix = s.getMatrix();
        HashSet<Move> moves = new HashSet<>();
        // iterating over all the pieces given a state
        for (Piece piece: pieces){
            Integer data = piece.getData();
            ArrayList<List<Integer>> position = piece.getPosition();
            if (data != -1) { // do not move -1 block
                try {
                    if (position.size() == 1) { // moves of single block
                        Move move;
                        Integer row = position.get(0).get(0);
                        Integer column = position.get(0).get(1);
                        boolean counter;
                        if (matrix.get(row - 1).get(column) == 0) { // UP
                            move = new Move(data, 'u');
                            moves.add(move);
                        }
                        if (matrix.get(row + 1).get(column) == 0) { // DOWN
                            move = new Move(data, 'd');
                            moves.add(move);
                        }
                        if (matrix.get(row).get(column - 1) == 0) { // LEFT
                            move = new Move(data, 'l');
                            moves.add(move);
                        }
                        if (matrix.get(row).get(column + 1) == 0) { // RIGHT
                            move = new Move(data, 'r');
                            moves.add(move);
                        }
                        if (data == 2){ // moves for 2 as it can move to -1 block
                            if (matrix.get(row - 1).get(column) == -1) {
                                move = new Move(data, 'u');
                                moves.add(move);
                            }
                            if (matrix.get(row + 1).get(column) == -1) {
                                move = new Move(data, 'd');
                                moves.add(move);
                            }
                            if (matrix.get(row).get(column - 1) == -1) {
                                move = new Move(data, 'l');
                                moves.add(move);
                            }
                            if (matrix.get(row).get(column + 1) == -1) {
                                move = new Move(data, 'r');
                                moves.add(move);
                            }
                        }
                    } else { // for block with size > 0
                        Move move;
                        for (List<Integer> coords : position) { // iterating over all positions of a piece
                            Integer row = coords.get(0);
                            Integer column = coords.get(1);
                            boolean counter = true;
                            if (matrix.get(row - 1).get(column) == 0) {
                                for (List<Integer> coord : position){
                                    if (coord.get(0).equals(row)){
                                        if (matrix.get(coord.get(0) - 1).get(coord.get(1)) != 0){counter = false;}
                                    }
                                }
                                if (counter) {
                                    move = new Move(data, 'u');
                                    moves.add(move); // only add a move if all position agrees
                                }
                            }
                            if (data == 2){ // moves for 2 as it can move to -1 block
                                if (matrix.get(row - 1).get(column) == -1) {
                                    for (List<Integer> coord : position) {// iterating over all positions of a piece
                                        if (coord.get(0).equals(row)) {
                                            if (matrix.get(coord.get(0) - 1).get(coord.get(1)) != -1) {
                                                counter = false;
                                            }
                                        }
                                    }
                                    if (counter) {
                                        move = new Move(data, 'u');
                                        moves.add(move); //only add a move if all position agrees
                                    }
                                }
                            }
                        }

                        for (List<Integer> coords : position) {
                            Integer row = coords.get(0);
                            Integer column = coords.get(1);
                            boolean counter = true;
                            if (matrix.get(row + 1).get(column) == 0) {
                                for (List<Integer> coord : position){// iterating over all positions of a piece
                                    if (coord.get(0).equals(row)){
                                        if (matrix.get(coord.get(0) + 1).get(coord.get(1)) != 0){counter = false;}
                                    }
                                }
                                if (counter) {
                                    move = new Move(data, 'd');
                                    moves.add(move); //only add a move if all position agrees
                                }
                            }
                            if (data == 2){ // moves for 2 as it can move to -1 block
                                if (matrix.get(row + 1).get(column) == -1) {
                                    for (List<Integer> coord : position) {// iterating over all positions of a piece
                                        if (coord.get(0).equals(row)) {
                                            if (matrix.get(coord.get(0) + 1).get(coord.get(1)) != -1) {
                                                counter = false;
                                            }
                                        }
                                    }
                                    if (counter) {
                                        move = new Move(data, 'd');
                                        moves.add(move); //only add a move if all position agrees
                                    }
                                }
                            }
                        }

                        for (List<Integer> coords : position) {
                            Integer row = coords.get(0);
                            Integer column = coords.get(1);
                            boolean counter = true;
                            if (matrix.get(row).get(column - 1) == 0) {
                                for (List<Integer> coord : position){// iterating over all positions of a piece
                                    if (coord.get(1).equals(column)){
                                        if (matrix.get(coord.get(0)).get(coord.get(1) - 1) != 0){counter = false;}
                                    }
                                }
                                if (counter) {
                                    move = new Move(data, 'l');
                                    moves.add(move); //only add a move if all position agrees
                                }
                            }
                            if (data == 2){ // moves for 2 as it can move to -1 block
                                if (matrix.get(row).get(column - 1) == -1) {
                                    for (List<Integer> coord : position){// iterating over all positions of a piece
                                        if (coord.get(1).equals(column)){
                                            if (matrix.get(coord.get(0)).get(coord.get(1) - 1) != -1){counter = false;}
                                        }
                                    }
                                    if (counter) {
                                        move = new Move(data, 'l');
                                        moves.add(move); //only add a move if all position agrees
                                    }
                                }
                            }
                        }

                        for (List<Integer> coords : position) {
                            Integer row = coords.get(0);
                            Integer column = coords.get(1);
                            boolean counter = true;
                            if (matrix.get(row).get(column + 1) == 0) {
                                for (List<Integer> coord : position){// iterating over all positions of a piece
                                    if (coord.get(1).equals(column)){
                                        if (matrix.get(coord.get(0)).get(coord.get(1)+1) != 0){counter = false;}
                                    }
                                }
                                if (counter) {
                                    move = new Move(data, 'r');
                                    moves.add(move); //only add a move if all position agrees
                                }
                            }
                            if (data == 2){ // moves for 2 as it can move to -1 block
                                if (matrix.get(row).get(column + 1) == -1) {
                                    for (List<Integer> coord : position){// iterating over all positions of a piece
                                        if (coord.get(1).equals(column)){
                                            if (matrix.get(coord.get(0)).get(coord.get(1)+1) != -1){counter = false;}
                                        }
                                    }
                                    if (counter) {
                                        move = new Move(data, 'r');
                                        moves.add(move); //only add a move if all position agrees
                                    }
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Exception thrown at data: " + data + " and position: " + position);
                }
            }
        }

        this.moves = moves;
    }

    public HashSet<Move> getMoves() {
        return moves;
    }

    public void setMoves(HashSet<Move> moves) {
        this.moves = moves;
    }
}
