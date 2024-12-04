import java.awt.*;

public class Lucario {
    public String name;                //holds the name of the Garchomp
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the warrior in the x direction
    public int dy;                    //the speed of the warrior in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;
    public int HP=70;
    public int atk= 110;
    public int defense=70;
    public int SpaAtk=115;
    public int Spdef=70;
    public int speed=90;

    public Lucario(int wxpos, int wypos, int Wwidth, int wheight){
        xpos = wxpos;
        ypos = wypos;
        dx = 4;
        dy = 4;
        width = Wwidth;
        height = wheight;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
    }
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        if (xpos <= 0) {
            dx = dx * -1;


        }
        if (ypos <= 0) {
            dy = dy * -1;
        }
        if (xpos > 1000) {
            dx = dx * -1;
        }
        if (ypos > 700) {
            dy = dy * -1;
        }
        rec = new Rectangle(xpos, ypos, width, height);
    }
}
