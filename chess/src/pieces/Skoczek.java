package pieces;

import Main.GamePanel;

public class Skoczek extends Piece{

    public Skoczek(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.Biale){
            bufferedImage = getBufferedImage("/piece/w-knight");
        }
        else {
            bufferedImage = getBufferedImage("/piece/b-knight");
        }
    }
    public boolean canMove(int targetCol, int targetRow){

        if (validateMove(targetCol, targetRow)){
        if(Math.abs(targetCol-preCol) == 2 &&Math.abs(targetRow - preRow) == 1) {
                return true;
        }
        if(Math.abs(targetCol-preCol)==1 && Math.abs(targetRow - preRow) == 2) {
            return true;
            }
        }
        return false;
    }
}
