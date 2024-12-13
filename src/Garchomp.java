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
    public int defense=95;;
    public int speed=102;
    public double DragonClaw;
    public double StrongFlame;
    public Fire myFilre;



    public Garchomp(int wxpos, int wypos, int Wwidth, int wheight) {
        xpos = wxpos;
        ypos = wypos;
        dx = 0;
        dy = 0;
        width = Wwidth;
        height = wheight;
        DragonClaw=50;
        StrongFlame=150;



        rec=new Rectangle(xpos,ypos,width,height);
        myFilre= new Fire(xpos+120,ypos,50,50);
    }
        public void move() {
            if(HP>0){
                isAlive = true;
            } else {
                isAlive=false;
            }
            xpos = xpos + dx;
            ypos = ypos + dy;
            rec = new Rectangle(xpos, ypos, width, height);


                myFilre= new Fire(xpos+120,ypos,33,33);


        }
}
