import java.util.*;
public class Problem1{

public static double funcDouble(double sx1, double sx2){
double ret = (Math.exp(sx1-sx2-0.4)+ Math.exp(sx1+sx2-0.4) + Math.pow((sx1-1),2) + Math.pow((sx2+1.0),2));
return ret;
}
public static double gradX1(double sx1, double sx2) {
	double ret = (Math.exp(sx1-sx2-0.4)+ Math.exp(sx1+sx2-0.4) + 2*(sx1-1.0));
	return ret;
}
public static double gradX2(double sx1, double sx2) {
	double ret = (-Math.exp(sx1-sx2-0.4)+ Math.exp(sx1+sx2-0.4) + 2*(sx2+1.0));
	return ret;
}

public static void main(String[]args) {
	double be = 0.6;
	
	double x1=0;
	double x2=0;
	
	int Iter = 0;
	double t = 1;
		
	while(Iter<50) { //iterations
		double testnum = 1;
		double newx1 = x1 - (gradX1(x1,x2)*t);
		double newx2 = x2 - (gradX2(x1,x2)*t);
		
		double compk = funcDouble(newx1,newx2);
		double compkminus1 = funcDouble(x1, x2);
		System.out.println(Iter);
		if(Math.abs(compk-compkminus1)>0.0000000000000001) { //determine t
		if (compk>compkminus1) {
		while (!(compk<compkminus1)){
			testnum = 0.6*testnum;
			newx1 = x1 - (gradX1(x1,x2)*testnum);
			newx2 = x2 - (gradX2(x1,x2)*testnum);
			compk = funcDouble(newx1, newx2);
			compkminus1 = funcDouble(x1,x2);

		}

		}
		}
		
		else {
			break;
		}
		
		
		x1 = newx1;
		x2 = newx2;

		Iter++;
		
		
		
	}
	
	System.out.println("final coords:");
	
	
	System.out.println(x1);
	System.out.println(x2);
	


	
}


    
}
