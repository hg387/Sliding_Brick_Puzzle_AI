package main.resources;

// This consists of what block to move and what action to take
public class Move {
    private Integer data; // what block to move
    private Character move; // action to take

    public Move(Integer data, Character move){
        this.data = data;
        this.move = move;
    }

    // getters and setters
    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Character getMove() {
        return move;
    }

    public void setMove(Character move) {
        this.move = move;
    }

    // display the current move in correct format
    public void display(){
        System.out.println("\n" + "(" + this.data + "," + this.move + ")");
    }
}
