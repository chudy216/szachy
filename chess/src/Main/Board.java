package Main;
import java.awt.*;

public class Board {
    final int COL = 8, ROW = 8;
    public static final int RozmiarPola = 100, PolowaPola = RozmiarPola/2;
    int kolor = 1;
    public void draw(Graphics2D graphics2D){
        for(int i = 0; i < ROW; i++){
            if(kolor == 0){
                kolor++;
            }
            else{
                kolor = 0;
            }
            for(int j = 0; j<COL; j++){
                if (kolor == 0){
                    graphics2D.setColor(new Color(200, 160, 130));
                    kolor++;
                }
                else{
                    graphics2D.setColor(new Color(0, 0, 0));
                    kolor = 0;
                }
                graphics2D.fillRect(j*RozmiarPola, i*RozmiarPola, RozmiarPola, RozmiarPola);
            }
        }
    }
}
