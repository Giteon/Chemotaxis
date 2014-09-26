import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Chemotaxis extends PApplet {

Bacteria [] spore;
public void setup()
{
	size(1000, 700);
	spore = new Bacteria[200];

	for (int i=0; i<spore.length; i++)
	{

		spore[i] = new Bacteria();
	}
}
public void draw()   
{   
	fill(0, 0, 0, 30);
	rect(0, 0, 1000, 700);
	for (int r=0; r<spore.length; r++) 

	{

		spore[r].show();
		spore[r].move();

		for (int i = 0; i <spore.length; i++)
		{
			if (r != i && dist(spore[r].myX, spore[r].myY, spore[i].myX, spore[i].myY) < spore[r].mySize/2)
			{

				if (spore[i].mySize < spore[r].mySize)
				{
					spore[r].myColor += (int)(Math.random()*8-4); /*= (spore[r].myColor+spore[i].myColor)/2;*/
					spore[r].mySize += (.4f) * spore[i].mySize;
					spore[r].myNum +=1;
					spore[i].mySize = 0;
					spore[i].myX = 9999;
				}
			}
			else 
			{
				if (r != i && dist(spore[r].myX, spore[r].myY, spore[i].myX, spore[i].myY) < spore[i].mySize/2)
				{
					if (spore[r].mySize < spore[i].mySize)
					{
						spore[i].myColor += (int)(Math.random()*8-4); /*= (spore[r].myColor+spore[i].myColor)/2;*/
						spore[i].mySize += (.8f) * spore[r].mySize;
						spore[i].myNum +=1;
						spore[r].mySize = 0;
						spore[r].myX = 9999;
					}
				}
			}
		}
	}
}  
class Bacteria    
{     
	int myX;
	int myY;
	float mySize;
	int myColor;
	int  myNum;

	Bacteria()
	{
		colorMode(HSB);
		myColor = color(random(50, 256), random(50, 255), random(50, 256));
		mySize = (int)(Math.random()*15);  
		myY = (int)(Math.random()*680);    
		myX = (int)(Math.random()*980);
		myNum = (int)(mySize*55+(int)(Math.random()*9));
	}
	public void show()
	{
		fill(myColor);
		myColor = myColor + (int)(Math.random()*6-3);
		noStroke();
		ellipse(myX, myY, mySize, mySize);
		pushStyle();
		fill(0);
		textSize(mySize/2);
		if (myNum < 99)
		{
			text(myNum, myX-mySize/3.2f, myY+mySize/5);
		}
		else
		{
			text(myNum, myX-mySize/2.13f, myY+mySize/5);

		}
		popStyle();

		if (myColor>253)
		{
			myColor = 254;
		}
	}
	public void move()
	{
		boolean mouseDown = false;
		if (mousePressed == true)
		{
			mouseDown = true;
		}
		else 
		{
			mouseDown = false;
		}
		if (mouseDown == false || !(mouseDown == true && (mouseX >= myX-mySize/3 && mouseY >= myY-mySize/3 && mouseX <= (myX+ mySize+mySize/3) && mouseY <= (myY+ mySize+mySize/3))))
		{
			if (mouseX+mySize/2>myX)
			{
				myX = myX + (int)(Math.random()*8-3.82f);
			}
			if (mouseY+mySize/2>myY)
			{
				myY = myY + (int)(Math.random()*8-3.82f);
			}
			if (mouseX-mySize/2<myX)
			{
				myX = myX + (int)(Math.random()*8-4.22f);
			}
			if (mouseY-mySize/2<myY)
			{
				myY = myY + (int)(Math.random()*8-4.22f);
			}
			if (myX>1000)
			{
				myX = myX - (int)(Math.random()*8-4.1f);
			}
			if (myY>700)
			{
				myY = myY - (int)(Math.random()*8-4.1f);
			}
			if (myX<0)
			{
				myX = myX - (int)(Math.random()*8-3.9f);
			}
			if (myY<0)
			{
				myY = myY - (int)(Math.random()*8-3.9f);
			}
		}
		if (mousePressed == true && (mouseX >= myX-mySize/3 && mouseY >= myY-mySize/3 && mouseX <= (myX+ mySize+mySize/3) && mouseY <= (myY+ mySize+mySize/3)))
		{
			if(mouseDown == true) {

				myX = mouseX;
				myY = mouseY;

				}    
			}
		}
	}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Chemotaxis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
