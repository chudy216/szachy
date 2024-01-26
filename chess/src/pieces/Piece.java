package pieces;

import Main.Board;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {
    public BufferedImage bufferedImage;
    public int x, y, col, row, preCol, preRow, color;
    public Piece hit;
    boolean moved;
    public Piece(int color, int col, int row){
        this.color = color;
        this.row = row;
        this.col = col;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }
    public BufferedImage getBufferedImage(String imagePath){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
    public int getX(int col){
        return col * Board.RozmiarPola;
    }
    public int getY(int row){
        return row * Board.RozmiarPola;
    }
    public int getCol(int x){
        return (x + Board.PolowaPola)/Board.RozmiarPola;
    }
    public int getRow(int y){
        return (y + Board.PolowaPola)/Board.RozmiarPola;
    }
    public int getIndex(){
        for (int i=0;i<GamePanel.simPieces.size(); i++){
             if (GamePanel.simPieces.get(i) == this){
                 return i;
             }
        }
        return 0;
    }

    public void updatePos(){
        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
    }
    public void resetPos(){
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }
    public Piece zajete(int targetCol, int targetRow){
        for (Piece piece:GamePanel.simPieces){
            if (piece.col == targetCol && piece.row == targetRow && piece != this){
                return piece;
            }
        }
        return null;
    }
    public boolean validateMove(int targetCol, int targetRow){
        hit = zajete(targetCol, targetRow);
        if (hit == null){
            return true;
        }
        else {
            if (hit.color != this.color){
                return true;
            }
            else {
                hit = null;
            }
        }
        return false;
    }
    public boolean canMove(int targetCol, int targetRow){
        return false;
    }
    public boolean czysto(int targetCol, int targetRow){
        for (int i = preCol-1; i>targetCol; i--){
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == i && piece.row == targetRow){
                    hit = piece;
                    return true;
                }
            }
        }
        for (int i = preCol+1; i<targetCol; i++){
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == i && piece.row == targetRow){
                    hit = piece;
                    return true;
                }
            }
        }
        for (int i = preRow-1; i>targetRow; i--){
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == targetCol && piece.row == i){
                    hit = piece;
                    return true;
                }
            }
        }
        for (int i = preRow+1; i<targetRow; i++){
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == targetCol && piece.row == i){
                    hit = piece;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean czysto_skos(int targetCol, int targetRow){
        if(targetRow < preRow){
        for (int i = preCol-1; i>targetCol; i--){
            int diff=Math.abs(i-preCol);
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == i && piece.row == preRow-diff){
                    hit = piece;
                    return true;
                }
            }
        }
        for (int i = preCol+1; i<targetCol; i++){
            int diff=Math.abs(i-preCol);
            for (Piece piece: GamePanel.simPieces){
                if (piece.col == i && piece.row == preRow-diff){
                    hit = piece;
                    return true;
                }
            }
        }}
        else {
            for (int i = preCol-1; i>targetCol; i--){
                int diff=Math.abs(i-preCol);
                for (Piece piece: GamePanel.simPieces){
                    if (piece.col == i && piece.row == preRow+diff){
                        hit = piece;
                        return true;
                    }
                }
            }
            for (int i = preCol+1; i<targetCol; i++){
                int diff=Math.abs(i-preCol);
                for (Piece piece: GamePanel.simPieces){
                    if (piece.col == i && piece.row == preRow+diff){
                        hit = piece;
                        return true;
                    }
                }
            }}


        return false;
    }
    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(bufferedImage, x, y, Board.RozmiarPola, Board.RozmiarPola, null);
    }
}
