import java.awt.*;

public class Garchomp {
    public String name;                //holds the name of the Garchomp
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the warrior in the x direction
    public int dy;                    //the speed of the warrior in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;
    public int HP=108;
    public int atk= 138;
    public int defense=95;
    public int SpaAtk=80;
    public int Spdef=85;
    public int speed=102;


    public Garchomp(int wxpos, int wypos, int Wwidth, int wheight) {
        xpos = wxpos;
        ypos = wypos;
        dx = 2;
        dy = 6;
        width = Wwidth;
        height = wheight;
        isAlive = true;
        rec=new Rectangle(xpos,ypos,width,height);
    }
        public void move() {
            xpos = xpos + dx;
            ypos = ypos + dy;
            if (xpos<=0){
                dx=dx*-1;

            }
            if (ypos<=0){
                dy=dy*-1;
            }
            if (xpos>1000){
                dx=dx*-1;
            }
            if (ypos>700){
                dy=dy*-1;
            }
            rec = new Rectangle(xpos, ypos, width, height);

    }
}
