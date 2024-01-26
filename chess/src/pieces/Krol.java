package pieces;

import Main.GamePanel;

public class Krol extends Piece{

    public Krol(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.Biale){
            bufferedImage = getBufferedImage("/piece/w-king");
        }
        else {
            bufferedImage = getBufferedImage("/piece/b-king");
        }
    }
    public boolean canMove(int targetCol, int targetRow){
        if(Math.abs(targetCol-preCol)+Math.abs(targetRow - preRow) == 1 ||
                (Math.abs(targetCol-preCol) == 1 && Math.abs(targetRow - preRow) == 1) ) {
            if (validateMove(targetCol, targetRow)){
                this.moved = true;
                return true;
            }
        }
        if(moved==false){

            //krotka
            if (targetCol == preCol+2 && targetRow == preRow && czysto(targetCol, targetRow) == false){
            for (Piece piece:GamePanel.simPieces){
            if (piece.col == preCol+3 && piece.row == preRow && piece.moved == false){
            GamePanel.roszada = piece;
                this.moved = true;
            return true;
            }}}
            if (targetCol == preCol-2 && targetRow == preRow && czysto(targetCol, targetRow) == false){
                Piece p[] = new Piece[2];
                for (Piece piece:GamePanel.simPieces){
                    if (piece.col == preCol-3 && piece.row ==targetRow){
                        p[0] = piece;
                    }
                    if (piece.col == preCol-4 && piece.row ==targetRow){
                        p[1] = piece;
                    }
                    if (p[0] == null && p[1] != null && p[1].moved == false){
                        GamePanel.roszada = p[1];
                        this.moved = true;
                        return true;
                    }}}
        }
        return false;
    }
}
