package Main;
import pieces.*;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
public static final int Width = 800, Height = 800;
final int fps=60;
Thread thread;
Board board = new Board();
Mysz mysz = new Mysz();

public static ArrayList<Piece> pieces = new ArrayList<>();
public static ArrayList<Piece> simPieces = new ArrayList<>();
Piece aktywnaFigura;

public static final int Czarne = 0, Biale = 1;
public int Obecne = Biale;
boolean canMove, zajete;
public static Piece roszada;
public GamePanel(){
    setPreferredSize(new Dimension(Width, Height));
    setBackground(Color.BLACK);
    addMouseMotionListener(mysz);
    addMouseListener(mysz);
    setPieces();
    copyPieces(pieces, simPieces);
}
public void launchGame(){
    thread = new Thread(this);
    thread.start();
}
public void setPieces(){
    pieces.add(new Pion(Biale,0,6));
    pieces.add(new Pion(Biale,1,6));
    pieces.add(new Pion(Biale,2,6));
    pieces.add(new Pion(Biale,3,6));
    pieces.add(new Pion(Biale,4,6));
    pieces.add(new Pion(Biale,5,6));
    pieces.add(new Pion(Biale,6,6));
    pieces.add(new Pion(Biale,7,6));
    pieces.add(new Wieza(Biale,0,7));
    pieces.add(new Skoczek(Biale,1,7));
    pieces.add(new Goniec(Biale,2,7));
    pieces.add(new Krolowa(Biale,3,7));
    pieces.add(new Krol(Biale,4,7));
    pieces.add(new Goniec(Biale,5,7));
    pieces.add(new Skoczek(Biale,6,7));
    pieces.add(new Wieza(Biale,7,7));
    pieces.add(new Pion(Czarne,0,1));
    pieces.add(new Pion(Czarne,1,1));
    pieces.add(new Pion(Czarne,2,1));
    pieces.add(new Pion(Czarne,3,1));
    pieces.add(new Pion(Czarne,4,1));
    pieces.add(new Pion(Czarne,5,1));
    pieces.add(new Pion(Czarne,6,1));
    pieces.add(new Pion(Czarne,7,1));
    pieces.add(new Wieza(Czarne,0,0));
    pieces.add(new Skoczek(Czarne,1,0));
    pieces.add(new Goniec(Czarne,2,0));
    pieces.add(new Krolowa(Czarne,3,0));
    pieces.add(new Krol(Czarne,4,0));
    pieces.add(new Goniec(Czarne,5,0));
    pieces.add(new Skoczek(Czarne,6,0));
    pieces.add(new Wieza(Czarne,7,0));
}
private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target){
    target.clear();
    for (int i = 0; i<source.size();i++){
        target.add(source.get(i));
    }
}
    @Override
    public void run(){
        //pętla
        double coIleOdswiezac = 1000000000/fps, delta = 0;
        long ostatniCzas = System.nanoTime(), obecnyCzas;
        while (thread != null){
            obecnyCzas = System.nanoTime();
            delta += (obecnyCzas - ostatniCzas)/coIleOdswiezac;
            ostatniCzas = obecnyCzas;
            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }

    }

private void update(){
if(mysz.pressed){
    if(aktywnaFigura == null){
        for (Piece piece: simPieces){
            if (piece.color == Obecne && piece.col == mysz.x/Board.RozmiarPola &&
                    piece.row == mysz.y/Board.RozmiarPola){
                aktywnaFigura = piece;
            }
        }
    }
    else {
        simulate();
    } }
else {
    if (aktywnaFigura != null){
        if (canMove){
            copyPieces(simPieces, pieces);
            aktywnaFigura.updatePos();
            if (roszada != null){
                zamek();
                roszada.updatePos();
                roszada = null;
            }
            Obecne = ++Obecne%2;
            if(aktywnaFigura.hit != null){
                simPieces.remove(aktywnaFigura.hit.getIndex());
            }
        }
        else{
            copyPieces(simPieces, pieces);
            aktywnaFigura.resetPos();
        }
        aktywnaFigura = null;
    }
}
}
private void zamek(){
    System.out.println("roszada");
    if (roszada !=null){
        if (roszada.col == 0){
            roszada.col+=3;
        }
        if (roszada.col == 7){
            roszada.col-=2;
        }
        roszada.x=roszada.getX(roszada.col);
    }
}
    private void simulate(){
        aktywnaFigura.x = mysz.x-Board.PolowaPola;
        aktywnaFigura.y = mysz.y-Board.PolowaPola;
        aktywnaFigura.col = aktywnaFigura.getCol(aktywnaFigura.x);
        aktywnaFigura.row = aktywnaFigura.getRow(aktywnaFigura.y);
        if(aktywnaFigura.canMove(aktywnaFigura.col, aktywnaFigura.row)){
            canMove = true;
        }
        else{
            canMove = false;
        }
    }
public void paintComponent(Graphics graphics){
    super.paintComponent(graphics);

    Graphics2D graphics2D = (Graphics2D)graphics;
    board.draw(graphics2D);
    for (Piece piece: simPieces){
        piece.draw(graphics2D);
    }
    if (aktywnaFigura != null){
        if (canMove){
        graphics2D.setColor(Color.red);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        graphics2D.fillRect(aktywnaFigura.col*Board.RozmiarPola, aktywnaFigura.row*Board.RozmiarPola, Board.RozmiarPola, Board.RozmiarPola);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        aktywnaFigura.draw(graphics2D);}
    }
}

}
