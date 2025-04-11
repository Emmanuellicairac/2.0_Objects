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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
	public boolean gameHasbegun=false;
	public Image startImage;
	public Image levelScreenImage;
	public boolean startScreen=false;
	public boolean selectScreen;
	public boolean level1=false;
	public boolean level2=false;
	public boolean level3=false;
	public boolean allowCollision;
	public boolean spiritombCreationtrue=false;
	public int deathTotalLucario=0;
	public int deathTotalSpiritomb=0;
	public boolean runLevelCheck=false;


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
		startImage=Toolkit.getDefaultToolkit().getImage("Start.jpg");
		levelScreenImage=Toolkit.getDefaultToolkit().getImage("Level Screen.jpg");

		GarchompPic =Toolkit.getDefaultToolkit().getImage("Garchomp.jpeg");
		Cynthia=new Garchomp(200,400,100,100);



		SpirtombPic=Toolkit.getDefaultToolkit().getImage("Spiritomb.png");
			GarchompPicLeft=Toolkit.getDefaultToolkit().getImage("Garchomp(flip).png");
			GarchompPic=Toolkit.getDefaultToolkit().getImage("Garchomp.png");
			SpirtombPic=Toolkit.getDefaultToolkit().getImage("Spiritomb(flipped).png");
			LucarioPic=Toolkit.getDefaultToolkit().getImage("lucario.png");
			LucarioPicLeft=Toolkit.getDefaultToolkit().getImage("lucario(flip).png");
			FirePic= Toolkit.getDefaultToolkit().getImage("fire.jpg");
			BackgroundPic=Toolkit.getDefaultToolkit().getImage("Background.png");







	}
	public void levelCheck(){

		if(level1==true && runLevelCheck==true){
			CynthiaSpiritomb=new Spiritomb[1];
			LucarioC=new Lucario[1];
			enemyCreation();
		}
		if(level2==true && runLevelCheck==true){
			CynthiaSpiritomb=new Spiritomb[2];
			LucarioC=new Lucario[2];
			enemyCreation();
		}
		if(level3==true && runLevelCheck==true){
			CynthiaSpiritomb=new Spiritomb[3];
			LucarioC=new Lucario[3];
			enemyCreation();
		}
		checkHealth();


	}

	public void enemyCreation(){
		for(int x=0;x<LucarioC.length;x++){
			LucarioC[x]=new Lucario( (int)(Math.random()*1000), (int)(Math.random()*700),100,100);
			System.out.println("Lucario xpos="+ LucarioC[x].xpos);
			System.out.println("Lucario ypos="+ LucarioC[x].ypos);

		}
		for(int x=0;x<CynthiaSpiritomb.length;x++){
			CynthiaSpiritomb[x]=new Spiritomb( (int)(Math.random()*1000), (int)(Math.random()*700),100,100);
		}
		allowCollision=true;
		runLevelCheck=false;

	}

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

		//for the moment we will loop things forever.
		while (true) {
			render();
			spiritombCreationtrue=true;



			while (gameHasbegun==true){
				if( spiritombCreationtrue==true){
					levelCheck();
					collision();
					moveThings();
				}
				render();



				// paint the graphics




				pause(20); // sleep for 10 ms
			}

		}
	}


	public void moveThings() {
      //calls the move( ) code in the objects
		Cynthia.move();
		for (int x=0;x<CynthiaSpiritomb.length;x++){
			CynthiaSpiritomb[x].move();

		}
		for (int x=0;x<LucarioC.length;x++){
			LucarioC[x].move();
		}


	}
	public void checkHealth(){
		for (int x=0; x<LucarioC.length;x++){
			if(LucarioC[x].HP<=0){
				deathTotalLucario=deathTotalLucario+1;
			}


		}
		for (int x=0; x<CynthiaSpiritomb.length;x++){
			if(LucarioC[x].HP<=0){
				deathTotalSpiritomb=deathTotalSpiritomb+1;
			}

		}
		if (deathTotalLucario==1 && deathTotalSpiritomb==1 && level1==true){
			level1=false;
			level2=true;
			runLevelCheck=true;
		}
		if (deathTotalLucario==3 && deathTotalSpiritomb==3 && level2==true){
			level2=false;
			level3=true;
			runLevelCheck=true;
		}
		if (deathTotalLucario==6 && deathTotalSpiritomb==6 && level3==true){
			level3=false;
			runLevelCheck=false;
			gameHasbegun=false;
			startScreen=true;
		}


	}

	public void collision() {
		if(allowCollision==true){
			for(int x=0;x<LucarioC.length;x++){

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
		if(gameHasbegun==false) {
			g.drawImage(startImage, 0, 0, WIDTH, HEIGHT, null);
		}

		if (startScreen==true){
			g.clearRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(levelScreenImage,0,0,WIDTH,HEIGHT,null);
		}

		if (gameHasbegun==true){
			g.clearRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(BackgroundPic,0,0,WIDTH,HEIGHT,null);
		}
		if(gameHasbegun==true ){
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
			for(int x=0;x<LucarioC.length;x++){
				g.drawRect(LucarioC[x].rec.x, LucarioC[x].rec.y, LucarioC[x].rec.width,LucarioC[x].rec.height);


			}
			for(int x=0;x<CynthiaSpiritomb.length;x++){
				g.drawRect(CynthiaSpiritomb[x].rec.x, CynthiaSpiritomb[x].rec.y, CynthiaSpiritomb[x].rec.width,CynthiaSpiritomb[x].rec.height);


			}


		}




      //draw the image of the astronaut




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
		if(key==32){
			startScreen=true;



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
		if(key==51 && gameHasbegun==false && startScreen==true){
			level3=true;

			System.out.println("Level 3 has begun");

		}
		if(key==49 && gameHasbegun==false && startScreen==true) {
			level1=true;
			runLevelCheck=true;


			gameHasbegun=true;
			System.out.println("game has begun =" +gameHasbegun);


		}
		if (key==50 && gameHasbegun==false && startScreen==true){
			level2=true;

			gameHasbegun=true;
			System.out.println("Level 2 has begun");

		}
		if (key==8){
			gameHasbegun=false;
			selectScreen=true;
		}

	}

	}