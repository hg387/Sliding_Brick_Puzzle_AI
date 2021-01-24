package main.resources;

import java.util.ArrayList;
import java.util.List;

// structure for storing block data and its locations
public class Piece {
    Integer data;
    ArrayList<List<Integer>> position; // if block size > 1

    public Piece(Integer data, ArrayList<List<Integer>> position){
        this.data = data;
        this.position = position;
    }

    // getters and setters
    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public ArrayList<List<Integer>> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<List<Integer>> position) {
        this.position = position;
    }
}
