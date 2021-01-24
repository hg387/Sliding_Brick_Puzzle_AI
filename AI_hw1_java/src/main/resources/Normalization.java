package main.resources;

import java.util.ArrayList;
import java.util.List;

// Normalization of a given state
public class Normalization {

    private int index =3;
    private int h;
    private int w;

    public Normalization(ArrayList<List<Integer>> matrix){
        this.h = matrix.size();
        this.w = matrix.get(0).size();
        // looping through the matrix
        for(int i = 0; i < this.h; i++) {
            for(int j = 0; j < this.w; j++) {
                if (matrix.get(i).get(j)==this.index) {
                    this.index++;
                } else if (matrix.get(i).get(j) > this.index) {
                    swapIdx(this.index,matrix.get(i).get(j), matrix);
                    this.index++;
                }
            }
        }

    }

    // helper function sap first instance of indexes in the matrix
    public void swapIdx(int idx1,int idx2, ArrayList<List<Integer>> matrix){
        for(int i = 0;i < this.h;i++) {
            for(int j = 0;j < this.w;j++) {
                if (matrix.get(i).get(j)==idx1) {
                    matrix.get(i).set(j,idx2);
                } else if (matrix.get(i).get(j)==idx2) {
                    matrix.get(i).set(j,idx1);
                }
            }
        }
    }
}
