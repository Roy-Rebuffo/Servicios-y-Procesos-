package Principal;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Encuesta2 {
	private static final int NUM_ZONAS = 20;

	public static void main(String[] args) {
		ResultadosEncuesta2 resultados = new ResultadosEncuesta2();
		
		//Thread [] encuestadores = new Thread[NUM_ZONAS];
		EncuestadorZona2 [] encuestadores = new EncuestadorZona2[NUM_ZONAS];
		for (int i = 0; i < NUM_ZONAS; i++) {
			//encuestadores [i] = new Thread(new EncuestadorZona("zona_" + (i + 1),resultados));
			encuestadores [i] = new EncuestadorZona2("ZONA_" + (i + 1), resultados);
			//encuestadores[i].start();
		}
		for (int i = 0; i < NUM_ZONAS; i++) encuestadores[i].start();
		
		for(Thread encuestador : encuestadores) {
			try {
				encuestador.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/************************************************************/
		System.out.println("Encuestas por Zona");
		int total = 0,parcial;
		for(String zona : resultados.obtenZonas()) {
			parcial = resultados.obtenNumRespuestasZonza(zona);
			total +=parcial;
			System.out.printf("%s: %d\n", zona, parcial);
		}
		System.out.printf("TOTAL: %d\n",total);
		/************************************************************/
		for(String respuesta: resultados.obtenRespuestas()) {
			parcial = resultados.obtenNumRespuestas(respuesta);
			total +=parcial;
			System.out.printf("%s: %d\n", respuesta !=null ? respuesta : "NS/NC", parcial);
		}
		/************************************************************/
		System.out.printf("TOTAL: %d\n",total);
	}
}
/************************************************************/
class ResultadosEncuesta2{
	private final HashMap<String, Integer> totalPorRespuesta = 
			new HashMap<String, Integer>();
	
	private final HashMap<String, Integer> totalPorZona = 
			new HashMap<String, Integer>();
	
	synchronized public void anotaRespuesta(String idZona, String respuesta) {
		Integer n = this.totalPorRespuesta.get(respuesta);
		this.totalPorRespuesta.put(respuesta, n==null ? 1 : n+1);
		n = this.totalPorZona.get(idZona);
		this.totalPorZona.put(idZona, n==null ? 1 : n+1);
	}
	
	synchronized public Set<String> obtenZonas(){
		return this.totalPorZona.keySet();
	}
	synchronized public Set<String> obtenRespuestas(){
		return this.totalPorRespuesta.keySet();
	}
	
	synchronized public int obtenNumRespuestasZonza(String zona) {
		return this.totalPorZona.get(zona);
	}
	synchronized public int obtenNumRespuestas(String respuesta) {
		return this.totalPorRespuesta.get(respuesta);
	}
}
/************************************************************/
/************************************************************/

class EncuestadorZona2 extends Thread {
	public final String idZona;
	private final ResultadosEncuesta2 resultados;
	
	public EncuestadorZona2(String idZona, ResultadosEncuesta2 resultados) {
		super();
		this.idZona = idZona;
		this.resultados = resultados;
	}

	@Override
	public void run() {
		System.out.printf(">>>Encuestador para zona %s comienza.\n",this.idZona);
		Random r = new Random();
		int numRespuestas = aleatorio.alea(100,200);
		
		for (int i = 0; i < numRespuestas; i++) {
			int numRespuesta = aleatorio.alea(0, 9);
			String respuesta = numRespuesta == 0 ? null : "Respuesta_" + numRespuesta;
			if(numRespuesta > 0) respuesta = "Respuesta_" + numRespuesta;
			this.resultados.anotaRespuesta(this.idZona, respuesta);
		}
		System.out.printf("Encuestador zona %s terminada \n", this.idZona);
	} 
}




