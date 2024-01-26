package pieces;

import Main.GamePanel;

public class Wieza extends Piece{

    public Wieza(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.Biale){
            bufferedImage = getBufferedImage("/piece/w-rook");
        }
        else {
            bufferedImage = getBufferedImage("/piece/b-rook");
        }
    }
    public boolean canMove(int targetCol, int targetRow){
        if(targetCol == preCol || targetRow == preRow){
            if(targetCol == preCol && targetRow == preRow){
                return false;
            }
            if (czysto(targetCol, targetRow) == true){
                return false;
            }
            if (validateMove(targetCol, targetRow)){
                //GamePanel.roszada = null;
                this.moved = true;
            return true;}
        }
        return false;
    }
}
