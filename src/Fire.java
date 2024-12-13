import java.awt.*;

public class Fire {
    public String name;                //holds the name of the Garchomp
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the warrior in the x direction
    public int dy;                    //the speed of the warrior in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;

    public Fire(int wxpos, int wypos, int Wwidth, int wheight) {
        xpos = wxpos;
        ypos = wypos;
        dx = 0;
        dy = 0;
        width = Wwidth;
        height = wheight;
        rec=new Rectangle(xpos,ypos,width,height);
}
}
