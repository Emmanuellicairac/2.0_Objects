public class wizard {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the wizard in the x direction
    public int dy;                    //the speed of the wizaard in the y direction
    public int width;
    public int height;
    public boolean isAlive;


    public wizard(int wxpos, int wypos, int Wwidth, int wheight){
        xpos = wxpos;
        ypos = wypos;
        dx = 3;
        dy = 4;
        width = Wwidth;
        height = wheight;
        isAlive = true;

    }
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
    }
}
