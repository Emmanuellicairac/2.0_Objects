public class warrior {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the warrior in the x direction
    public int dy;                    //the speed of the warrior in the y direction
    public int width;
    public int height;
    public boolean isAlive;


    public warrior(int wxpos, int wypos, int Wwidth, int wheight){
        xpos = wxpos;
        ypos = wypos;
        dx = -5;
        dy = 6;
        width = Wwidth;
        height = wheight;
        isAlive = true;

    }
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
    }
}
