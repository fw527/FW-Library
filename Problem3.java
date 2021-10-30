import java.util.ArrayList;
import java.util.Random;

public class Problem3 {

public final static int n = 100;
static Random gen = new Random();

public static int[][] allAs() {
	int [][] b = new int[n][n];
	for (int x = 0;x<n;x++) {
	for (int y =0;y<n;y++) {
	b[x][y]=gen.nextInt(10);
	}
	}
return b;
}

public static double ObjFunction (double[] x, int[][]a) {
	
	double Sum1 = 0;
	double Sum2 = 0;
	
	//Sum 1 
	
	for (int i = 0; i<n; i++) {
		if(2-(x[i]*x[i])>=0) {
	Sum1+=Math.log(2-(x[i]*x[i]));

		}
	}
	
	//Sum 2
	for (int i = 0; i<n; i++) {
		
	double aix = 0;
	
	for (int j = 0; j<n; j++) {
	aix = (2*a[i][j]*x[j]);
	}
	if(aix!=1) {
	Sum2+=Math.log(1-aix);

	}
	
	}
	double total = 0-Sum1-Sum2;
	return total;
}

public static double DotProduct() {
	return 0;
	
}

public static double FGradient (double[] x, int[][]a, int index) {
	double Sum1 = 0;
	double Sum2 = 0;
	
	Sum1 = (2*x[index])/(2-(x[index]*x[index]));
	
	//
	
	for (int i = 0; i<n; i++) {
	Sum2 += (2*a[i][index])/(1-(2*a[i][index]*x[index]));
	}
	
	return Sum1+Sum2;
}

public static void main(String[]args) {
	double[]X = new double[n];
	for (int p = 0;p<n;p++) {
		X[p]=0;
	}
	double xi=0;
	
	int[][]A=allAs();		
	double compk = ObjFunction(X,A);

		for(int m = 0;m<300;m++) { 
		System.out.println(compk);
		for(int i = 0; i<n; i++) {
			
		xi=X[i];
		
		
		double testnum = 0.4;
		double newxi = xi - testnum*FGradient(X,A,i);
		compk = ObjFunction(X,A);
		double compkminus1 = ObjFunction(X,A);
		
			if (compk>compkminus1) {
				while (!(compk<compkminus1)){
					testnum = 0.4*testnum;
					newxi = xi - testnum*FGradient(X,A,i);;
					compk = ObjFunction(X,A);
					compkminus1 = ObjFunction(X,A);
				}
				}
				
		X[i]=newxi;
		
		}
		
		System.out.println(compk);

	
	
}





	
}}
