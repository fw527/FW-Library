import java.util.*;
public class Newton {
	
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
		public static double Ah(double sx1, double sx2) {
			double ret = (Math.exp(sx1-sx2-0.4)+ Math.exp(sx1+sx2-0.4) + 2);
			return ret;
		}
		public static double Bh(double sx1, double sx2) {
			double ret = (-Math.exp(sx1-sx2-0.4)+ Math.exp(sx1+sx2-0.4));
			return ret;
		}
		public static double Ch(double sx1, double sx2) {
			double ret = (-Math.exp(sx1-sx2-0.4)+ Math.exp(sx1+sx2-0.4));
			return ret;
		}
		public static double Dh(double sx1, double sx2) {
			double ret = (Math.exp(sx1-sx2-0.4)+ Math.exp(sx1+sx2-0.4)+2);
			return ret;
		}

		public static void main(String[]args) {
			
			double x1=0;
			double x2=0;
			
			int Iter = 0;
			
			while(Iter<50) {

				double testnum = 1;
				double ADminusBC = (Ah(x1,x2)*Dh(x1,x2))-(Bh(x1,x2)*Ch(x1,x2));
				double OnOvADminusBC = 1/ADminusBC;
				
				double x1mult = OnOvADminusBC*((gradX1(x1,x2))*Dh(x1,x2)-Bh(x1,x2)*gradX2(x1,x2));
				double x2mult = OnOvADminusBC*(-(gradX1(x1,x2))*Ch(x1,x2)+Ah(x1,x2)*gradX2(x1,x2));
		
				double newx1 = x1 - x1mult;
				double newx2 = x2 - x2mult;
				

				double compk = funcDouble(newx1,newx2);
				double compkminus1 = funcDouble(x1, x2);
				
				if(Math.abs(compk-compkminus1)>0.0000000000000001) { //determine t
				if (compk>compkminus1) {
				while (!(compk<compkminus1)){
					testnum = 0.6*testnum;
					newx1 = x1 - (x1mult*testnum);
					newx2 = x2 - (x2mult*testnum);
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

