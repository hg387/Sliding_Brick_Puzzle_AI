package main.resources;

import java.util.ArrayList;
import java.util.List;

/*State represents any current state of the game given the coordinates matrix */
public class State {
    private ArrayList<List<Integer>> matrix;

    public State(ArrayList<List<Integer>> matrix){
        this.matrix = matrix;
    }

    public ArrayList<List<Integer>> getMatrix() {
        return matrix;
    }

    public void setMatrix(ArrayList<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    // Method for displaying the state with the correct format
    public void display(){
        ArrayList<List<Integer>> matrix = this.matrix;
        Integer rows = matrix.size();
        Integer columns = matrix.get(0).size();
        // looping through the current coordinates
        for (Integer row=0; row < rows; row++){
            for (Integer column =0; column < columns; column++){
                System.out.print(matrix.get(row).get(column) + ",");
            }
            System.out.println();
        }


    }

    // To check if current state is goal or not
    public boolean evaluate(){
        ArrayList<List<Integer>> matrix = this.matrix;
        boolean counter = true;

        Integer rows = matrix.size();
        Integer columns = matrix.get(0).size();
        //looping through the matrix
        for (Integer row=0; row < rows; row++){
            for (Integer column =0; column < columns; column++){
                if (matrix.get(row).get(column) == -1){
                    counter = false; // if -1 found, return false
                }
            }
        }

        return counter;
    }
}
