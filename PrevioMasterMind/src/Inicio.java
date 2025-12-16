import java.util.Scanner;

public class Inicio {
    static Scanner sc=new Scanner(System.in);
    final static char[] Colores={'R','A','V','Z','M','B'};
    /**********************************************************************************************/
    public static int alea(int li,int ls){    return (int)(Math.random()*(ls-li+1))+li;}
    /*********************************************************************************************/
    public static void rellenar(char[] m){
        for(int i=0;i<m.length;i++)
            m[i]=Colores[alea(0, m.length-1)];
    }
    /**********************************************************************************************/
    public static void introducirPorTeclado(char[] m){
        for(int i=0;i<m.length;i++){
            System.out.print("Indique el caracter "+i+" ");
            m[i]=sc.next().charAt(0);
        }
    }
    /**********************************************************************************************/
    public static boolean EvaluacionCombinacion (char[] cb){
        char[] usu=new char[cb.length];
        introducirPorTeclado(usu);
        return comprobar(cb,usu);
    }
    /**********************************************************************************************/
    public static boolean comprobar(char[] ori, char[] usu){
        int pexactas=0,paproximadas=0;
        char[] copia=ori.clone();
        for(int i=0;i<usu.length;i++){
            if (usu[i]==copia[i]){
                pexactas++;
                usu[i]='-';
                copia[i]='-';
            }
        }
        if(pexactas== ori.length){return false;}

        for(int i=0;i<usu.length;i++){
            if (usu[i]=='-')continue;
            for(int j=0;j<copia.length;j++){
                if (copia[j]=='-')continue;
                if (copia[j]==usu[i]){
                    paproximadas++;
                    usu[i]='-';
                    copia[j]='-';
                }
            }
        }
        System.out.printf("Exactas=%d Aproximadas=%d\n",pexactas,paproximadas);
        return true;
    }
    /**********************************************************************************************/
    public static void imprimir(char[] m){
        for(int i=0;i<m.length;i++) System.out.print(m[i]);
        System.out.println();
    }
    /**********************************************************************************************/
    static void main() {
        final int NumColoresCombinacion = 4;
        char[] Combi=new char[NumColoresCombinacion];
        rellenar(Combi);
        imprimir(Combi);
        boolean seguir;
        for(int i=1;i<=20;i++){
            seguir=EvaluacionCombinacion(Combi);
            if (!seguir){
                System.out.println("Enhorabuena ha acertado en el intento "+i);
                break;
            }
        }
    }
}
