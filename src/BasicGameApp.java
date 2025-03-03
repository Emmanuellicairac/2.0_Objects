//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;


//*******************************************************************************
// Class Definition Section


public class BasicGameApp implements Runnable, KeyListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;

	public Image GarchompPic;
	public Image SpirtombPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type


	public Garchomp Cynthia;
	public Spiritomb[] CynthiaSpiritomb;
	public Lucario[] LucarioC;
	public boolean isMove=true;
	public boolean RightFacing=true;
	public boolean drawFire=false;
	public boolean drawFireleft=false;
	public Image GarchompPicLeft;
	public Image LucarioPic;
	public Image LucarioPicLeft;
	public Image FirePic;
	public Image BackgroundPic;






   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start(); //creates a threads & starts up the code in the run( ) method

	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {

      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up
		GarchompPic =Toolkit.getDefaultToolkit().getImage("Garchomp.jpeg");
		Cynthia=new Garchomp(200,400,100,100);
		LucarioC=new Lucario[5];
		for(int x=0;x<LucarioC.length;x++){
			LucarioC[x]=new Lucario(400+(x*100),450+(x*100),200,200);
		}


		CynthiaSpiritomb=new Spiritomb[5];
		for(int x=0;x<CynthiaSpiritomb.length;x++){
			CynthiaSpiritomb[x]=new Spiritomb(200+(x*100),300+(x*100),100,100);

		}

		SpirtombPic=Toolkit.getDefaultToolkit().getImage("Spiritomb.png");
			GarchompPicLeft=Toolkit.getDefaultToolkit().getImage("Garchomp(flip).png");
			GarchompPic=Toolkit.getDefaultToolkit().getImage("Garchomp.png");
			SpirtombPic=Toolkit.getDefaultToolkit().getImage("Spiritomb(flipped).png");
			LucarioPic=Toolkit.getDefaultToolkit().getImage("lucario.png");
			LucarioPicLeft=Toolkit.getDefaultToolkit().getImage("lucario(flip).png");
			FirePic= Toolkit.getDefaultToolkit().getImage("fire.jpg");
			BackgroundPic=Toolkit.getDefaultToolkit().getImage("Background.png");







	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {
		collision();
		if (isMove==true){
			moveThings();  //move all the game objects
		}
         render();  // paint the graphics
         pause(20); // sleep for 10 ms

		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		Cynthia.move();
		for (int x=0;x<CynthiaSpiritomb.length;x++){
			CynthiaSpiritomb[x].move();
		}
		for (int x=0;x<LucarioC.length;x++){
			LucarioC[x].move();
		}


	}

	public void collision() {
		for(int x=0;x<CynthiaSpiritomb.length;x++){
			if(LucarioC[x].rec.intersects(CynthiaSpiritomb[x].rec)){
				LucarioC[x].dx= LucarioC[x].dx*-1;
				LucarioC[x].dy= LucarioC[x].dy*-1;

			}
			if(CynthiaSpiritomb[x].rec.intersects((LucarioC[x].rec))){
				CynthiaSpiritomb[x].dx= CynthiaSpiritomb[x].dx*-1;
				CynthiaSpiritomb[x].dy=CynthiaSpiritomb[x].dy*-1;

			}
			if(Cynthia.myFilre.rec.intersects(CynthiaSpiritomb[x].rec)&& drawFire==true){
				drawFire=false;
				CynthiaSpiritomb[x].HP=CynthiaSpiritomb[x].HP-(Cynthia.StrongFlame-CynthiaSpiritomb[x].defense);
				System.out.println("Spritomb #"+ x +"HP is "+CynthiaSpiritomb[x].HP);
			}
			if(Cynthia.myFilre.rec.intersects(LucarioC[x].rec)&& drawFire==true){
				drawFire=false;
				LucarioC[x].HP= LucarioC[x].HP+(Cynthia.DragonClaw-LucarioC[x].defense);
				System.out.println("Lucario #"+ x +" HP is   "+LucarioC[x].HP);
			}
		}






	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
	  canvas.addKeyListener(this);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }






	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(BackgroundPic,0,0,WIDTH,HEIGHT,null);


      //draw the image of the astronaut
		if (RightFacing==true){
			g.drawImage(GarchompPicLeft, Cynthia.xpos, Cynthia.ypos,Cynthia.width, Cynthia.height, null);

		}else {
			g.drawImage(GarchompPic, Cynthia.xpos, Cynthia.ypos,Cynthia.width, Cynthia.height, null);

		}
		for(int x=0;x<CynthiaSpiritomb.length;x++){
			if (CynthiaSpiritomb[x].isAlive==true){
				g.drawImage(SpirtombPic, CynthiaSpiritomb[x].xpos,CynthiaSpiritomb[x].ypos,CynthiaSpiritomb[x].width,CynthiaSpiritomb[x].height, null);

			} else {

			}

		}

		for(int x=0;x<LucarioC.length;x++){
			if (LucarioC[x].isAlive==true){
				g.drawImage(LucarioPic,LucarioC[x].xpos, LucarioC[x].ypos,LucarioC[x].width, LucarioC[x].height, null);
			} else {

			}

		}



		if(drawFire==true){
			g.drawImage(FirePic,Cynthia.myFilre.xpos,Cynthia.ypos,Cynthia.width, Cynthia.height, null);

		}


		g.dispose();

		bufferStrategy.show();
	}

	@Override
	public void keyTyped(KeyEvent e) {



	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		char keyChar = e.getKeyChar();

		System.out.println(keyChar + ", " + key);
		if (key == 89) {
			System.out.println("works");
		}
		if (key == 81) {
			isMove = false;
		} else
			isMove = true;
		if(key==87) {
			Cynthia.dy = -5;
		}
		if(key==83){
			Cynthia.dy=5;
		}
		if(key==65){
			Cynthia.dx=-5;
			RightFacing=false;
		}
		if(key==68){
			Cynthia.dx=5;
			RightFacing=true;
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		char keyChar = e.getKeyChar();
		if(key==80){
			System.out.println(Cynthia.DragonClaw);
			if (RightFacing==true){
				drawFire=true;
			}

		}
		if(key==87) {
			Cynthia.dy = 0;
		}
		if(key==83){
			Cynthia.dy=0;
		}
		if(key==65){
			Cynthia.dx=0;
			RightFacing=false;
			System.out.println("test");

		}
		if(key==68){
			Cynthia.dx=0;
			RightFacing=true;
		}
	}
}