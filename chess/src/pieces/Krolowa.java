package pieces;

import Main.GamePanel;

public class Krolowa extends Piece{

    public Krolowa(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.Biale){
            bufferedImage = getBufferedImage("/piece/w-queen");
        }
        else {
            bufferedImage = getBufferedImage("/piece/b-queen");
        }
    }
    public boolean canMove(int targetCol, int targetRow){
        if(targetCol == preCol || targetRow == preRow){
            if(targetCol == preCol && targetRow == preRow){
            }
            else if (czysto(targetCol, targetRow) == true){
            }
            else if (validateMove(targetCol, targetRow)){
                return true;}
        }
        if(Math.abs(targetCol-preCol) == Math.abs(targetRow-preRow)){
            if (czysto_skos(targetCol, targetRow) == true || targetRow-preRow == 0){
            }
            else {
            if (validateMove(targetCol, targetRow)){
                return true;}}
        }
        return false;
    }
}
