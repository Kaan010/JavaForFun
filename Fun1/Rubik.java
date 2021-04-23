package ceng344_lab2;


//Kaan Kalan
//201611032
//CENG 344 Lab2
//Section 1


import java.awt.Color;

public class Rubik {
	Color[] colors = new Color[5];
	Color[] currentColors = new Color[4];
	
	public Rubik() {
		StdDraw.setCanvasSize(500, 500);
		for(int i=0;i<colors.length;i++) 
			colors[i]=randomColor(); 
		for(int i=0;i<currentColors.length;i++) 
			currentColors[i]=colors[(int) (4 * (Math.random()))];
	
		drawAll();
		// Pick random 4 colors with the method you have written
		// Change the currentColors array accordingly with these colors
		// Set the colors for filling the squares and call the filledSquare method
	}

	//Draws one rectangle
	public void draw(Color color, double posX, double posY, double halfR) {
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(posX, posY, halfR);
	}
	
	//Draws all rectangles
	public void drawAll() {
		draw(currentColors[0],0.250, 0.250, 0.250); //sol alt
		draw(currentColors[1],0.750, 0.250, 0.250); //sað alt
		draw(currentColors[2],0.250, 0.750, 0.250); //sol üst
		draw(currentColors[3],0.750, 0.750, 0.250); //sað üst
		//System.out.println("refresh");//debug
	}
	
	
	// Randomly chooses and returns a Color from your colors[] array
	public Color randomColor() {
		return new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
	}
	
	// Returns true if all 4 squares have the same color, otherwise false
	public boolean match(Color rand1, Color rand2, Color rand3, Color rand4) {
		if(rand1.equals(rand2) && rand3.equals(rand4) && rand1.equals(rand3)) return true;
		return false;
	}

	
	// Implements the game mechanism, while all squares do not have the same color lets the 
	// user press squares to change their colors.
	// When all the squares have matching colors displays a text that says “You won!”
	public void play() {
		StdDraw.enableDoubleBuffering();
		for(;;) {
			if(StdDraw.isMousePressed()) {//if user trying to change the color
				if(whichSquare(mouseLocation())!=-1) {
					changeColor(whichSquare(mouseLocation()));
					StdDraw.clear();
					drawAll();
					StdDraw.show();
					StdDraw.pause(200);
				}
				else continue;
			}
			if(match(currentColors[0],currentColors[1],currentColors[2],currentColors[3])) {//if user won!
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.text(0.50, 0.50, "You Won!");
				StdDraw.show();
				break;
			}
		}
	}
	
	// Returns a double array which contains x and y coordinates of the mouse location
	public double[] mouseLocation() {
		double x=StdDraw.mouseX();
		double y=StdDraw.mouseY();
		//System.out.println(x+" -- "+y+" point triggered"); //debug
		return new double[]{ x,y };
	}
	
	// Finds and returns the square which the mouse location resides in
	public int whichSquare(double[] mouseLoc) { 
		int a;
		if(mouseLoc[0]<0.500 && mouseLoc[1]<0.500)  //sol alt
			a=0;
		else if(mouseLoc[0]>0.500 && mouseLoc[1]<0.500)  //sað alt
			a=1;
		else if(mouseLoc[0]<0.500 && mouseLoc[1]>0.500)  //sol üst
			a=2;
		else if(mouseLoc[0]>0.500 && mouseLoc[1]>0.500)  //sað üst
			a=3;
		else a=-1;
		//System.out.println(a+" th rectangle triggered");debug
		return a;
	
	}
	
	// Takes the square which the user has clicked and changes the color of that square randomly
	public void changeColor(int whichSquare) {
		Color a=colors[(int) (4 * (Math.random()))];
		while(a.equals(currentColors[whichSquare])) //if new color is the same with before one, roll again
			a=colors[(int) (4 * (Math.random()))];
		
		currentColors[whichSquare]=a;
		//System.out.println(a.toString()); debug
	}
	
	
	// Main method should exactly be this
	public static void main(String[] args) {
		Rubik r = new Rubik();
		r.play();
	}

}
