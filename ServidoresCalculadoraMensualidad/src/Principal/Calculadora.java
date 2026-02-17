package Principal;

public class Calculadora {
	
	public static double mensualidad(double capital, double años, double interes) {
		
		double im = interes/1200;
		double t = años*12;
		double mensualidad = (capital * im)/(1-Math.pow(1+im, -t));
		return mensualidad;
	}
}
