package main.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// For a given state, bundle same set of blocks into a piece
public class StateToPieces {
    private ArrayList<Piece> pieces;
    private HashMap<Integer, ArrayList<List<Integer>>> piecesMap; // provides each piece data and all locations

    public StateToPieces(State s){
        ArrayList<List<Integer>> matrix = s.getMatrix();
        Integer rows = matrix.size();
        Integer columns = matrix.get(0).size();

        HashMap<Integer, ArrayList<List<Integer>>> piecesMap = new HashMap<>();
        ArrayList<Piece> pieces = new ArrayList<>();

        for (Integer row=0; row < rows; row++){
            for (Integer column =0; column < columns; column++){
                Integer data = matrix.get(row).get(column);
                List<Integer> coordinate = Arrays.asList(row, column);

                // no need to know the locations of 0 and 1
                if (data != 0 && data != 1){
                    // check if piece already in the map, add the new locations
                    if (piecesMap.get(data) == null){
                        ArrayList<List<Integer>> position = new ArrayList<>();
                        position.add(coordinate);
                        piecesMap.put(data,position);
                    }
                    else{
                        ArrayList<List<Integer>> position = piecesMap.get(data);
                        position.add(coordinate);
                    }
                }
            }
        }
        // converting hash map into a list of pieces structure
        for (Integer data:piecesMap.keySet()){
            Piece piece = new Piece(data, piecesMap.get(data));
            pieces.add(piece);
        }

        this.pieces = pieces;
        this.piecesMap = piecesMap;
    }

    // getter and setters
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public HashMap<Integer, ArrayList<List<Integer>>> getPiecesMap() {
        return piecesMap;
    }

    public void setPiecesMap(HashMap<Integer, ArrayList<List<Integer>>> piecesMap) {
        this.piecesMap = piecesMap;
    }
}
