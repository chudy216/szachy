package pieces;

import Main.GamePanel;

public class Pion extends Piece{

    public Pion(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.Biale){
            bufferedImage = getBufferedImage("/piece/w-pawn");
        }
        else {
            bufferedImage = getBufferedImage("/piece/b-pawn");
        }
    }
    public boolean canMove(int targetCol, int targetRow){
        if((Math.abs(targetCol-preCol)==0 && (targetRow - preRow) == -1 && this.color == GamePanel.Biale) || (Math.abs(targetCol-preCol)==0 && (targetRow - preRow) == 1 && this.color == GamePanel.Czarne) || (preRow == 1 && this.color == GamePanel.Czarne && Math.abs(targetCol-preCol)==0 && (targetRow - preRow) == 2) || (preRow == 6 && this.color == GamePanel.Biale && Math.abs(targetCol-preCol)==0 && (targetRow - preRow) == -2))  {
            if (validateMove(targetCol, targetRow)){
                if(hit==null){
                return true;}
            }
        }
        if (validateMove(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) == 1 && (targetRow - preRow) == -1 && hit !=null && this.color ==GamePanel.Biale) {
return true;
            }
            if (Math.abs(targetCol - preCol) == 1 && (targetRow - preRow) == 1 && hit !=null && this.color ==GamePanel.Czarne) {
                return true;
            }
        }
        return false;
    }
}

