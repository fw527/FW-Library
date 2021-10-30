import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
public class Map extends Canvas {
public static Random gen=new Random();

private static int Size = 30;
private static int Scale = 24;
private static int numTrials = 2;
private static int CanvasSize = Size*Scale;
private static final int WIDTH = CanvasSize;
private static final int HEIGHT = CanvasSize;

public static int[][] BuildMap(int size) {
	int x = size;
	int[][]map= new int[x][x];
	int[][]t= new int[x][x];

	for (int i=0;i<x;i++) {
	for (int j=0;j<x;j++) {
	map[i][j]=gen.nextInt(3);
	}
	}
	
	return map;
}

public static double TotalUtility(int[][]mapdet) {
	double x = Size;
	double TotalUtility = 0;
	//determine TotalUtility
	for (int i=0;i<x;i++) {
	for (int j=0;j<x;j++) {
	if (mapdet[i][j]==0) {///////////////
	for (int k=0;k<x;k++) {
	for (int l=0;l<x;l++) {
	switch(mapdet[k][l]) {
	case(0): 
	break; 
	case(1): TotalUtility+=(x-Math.sqrt(((i-k)*(i-k)) +((j-l)*(j-l)))); 
	break; 
	case(2): TotalUtility+=(500000*x);
	TotalUtility-=(x-Math.sqrt(((i-k)*(i-k)) +((j-l)*(j-l)))); 
	break;
	}}}}
	}}
	return TotalUtility;
	
}


public void paint(Graphics g) {
	
	int mapSize = Size;
	int[][]result = new int[mapSize][mapSize];
	double initialUtility = 0;
	int lp=0;
	int[][]test = BuildMap(mapSize);
	result=test.clone();
	
	while(lp<numTrials) {
	test = BuildMap(mapSize);
	if (TotalUtility(test)>initialUtility) {
		double p=TotalUtility(test);
		double unsuccessfulIt=numTrials;
		
		while (unsuccessfulIt>0) {
		for (int a=0;a<test.length;a++) {
		for (int b=0;b<test.length;b++) {
		test[a][b]=0;
		double r1=TotalUtility(test);// System.out.println(r1);
		test[a][b]=1;
		double r2=TotalUtility(test); //System.out.println(r2);
		test[a][b]=2;
		double r3=TotalUtility(test); //System.out.println(r3);
		if (r1>r2&&r1>r3){
			test[a][b]=0;
		}
		else if (r2>r1&&r2>r3){
			test[a][b]=1;	
		}
		else if (r3>r2&&r3>r1) {
			test[a][b]=2;
	}}}
		if(TotalUtility(test)>p) {
		p=TotalUtility(test);	
		}
		else {
			unsuccessfulIt--;
	}}
		
	result = test.clone();
	initialUtility=TotalUtility(result);
	}
	
	lp++;
	}

	
	

	
	System.out.println(Arrays.deepToString(result));
	System.out.println(TotalUtility(result));
	
    super.paint(g);
	for (int i=0;i<result.length;i++) {
	for (int j=0;j<result.length;j++) {
	int rand = result[i][j];
	switch (rand) {
	case 0:g.setColor(Color.ORANGE);break;case 1:g.setColor(Color.GREEN);break;case 2:g.setColor(Color.LIGHT_GRAY);break;
	}
	 for(int x = i*Scale; x < (i+1)*Scale; x++) {
	        for(int y = j*Scale; y < (j+1)*Scale; y++) {
	            
	            g.drawLine(x, y, x, y);
	        }
	    }
	}}}



public static void main (String[]args) {
	
	 JFrame frame = new JFrame();
     frame.setSize(WIDTH+100, HEIGHT+100);
     frame.add(new Map());
     frame.setVisible(true);
	
}
}
