package pieces;

import Main.GamePanel;

public class Goniec extends Piece{

    public Goniec(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.Biale){
            bufferedImage = getBufferedImage("/piece/w-bishop");
        }
        else {
            bufferedImage = getBufferedImage("/piece/b-bishop");
        }
    }
    public boolean canMove(int targetCol, int targetRow){
        if(Math.abs(targetCol-preCol) == Math.abs(targetRow-preRow)){
            if (czysto_skos(targetCol, targetRow) == true || targetRow-preRow == 0){
                return false;
            }
            if (validateMove(targetCol, targetRow)){
                return true;}
        }
        return false;
    }
}
