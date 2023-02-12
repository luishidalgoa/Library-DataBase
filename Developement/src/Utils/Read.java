package Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Read{
    private final static Scanner sc=new Scanner(System.in);
    /**
     * This method will read an integer
     * @param msn receive the message to print
     * @return return a reader number
     */
    public static int readInt(String msn){
        int numero=-1;
        boolean isCorrect=false;
        do{
            try{
                System.out.println(Utils.amarillo+msn);
                numero=sc.nextInt();
                isCorrect=true;
            }catch (Exception e){
                System.out.println(Utils.rojo+"Enter a valid number"+Utils.b);
            }
            sc.nextLine();
        }while(!isCorrect);
        return numero;
    }

    /**
     * This method will read a string of characters.
     * @param msn receive the message to print
     * @return return a String
     */
    public static String readString(String msn){
        Scanner sc=new Scanner(System.in);
        String evento=null;
        try{
            System.out.println(Utils.amarillo+msn);
            evento=sc.nextLine();
            evento=evento.toUpperCase();

        }catch (Exception e){
            System.out.println(Utils.rojo+"Enter the option correct"+Utils.b);
        }
        return evento;
    }

    /**
     * Metodo el cual leera una fecha para retornarla en el formato correspondiente
     * @param msn Mensaje a imprimir
     * @return fecha completa
     */
    public static Date readDate(String msn) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date date = null;
        boolean success = false;
        while (!success) {
            System.out.println(Utils.amarillo+msn+" Enter a date in format dd/MM/yyyy:"+Utils.b);
            String inputDate = sc.nextLine();
            try {
                date = dateFormat.parse(inputDate);
                success = true;
            } catch (ParseException e) {
                System.out.println(Utils.rojo+"Invalid date format, please try again."+Utils.b);
            }
        }
        return date;
    }

    /**
     * Metodo que leera un dni a partir de que cumpla las requisitos de un dni. si no volvera a pedirlo. Este metodo
     * es capaz de calcular la letra del dni si el usuario no lo introduce
     * @param msn Mensaje que se leera
     * @return retorna el dni completo
     */
    public static String readDNI(String msn){
        String dni;
        boolean isCorrect;
        do{
            isCorrect=true;
            System.out.println(Utils.amarillo+msn+Utils.b);
            dni=sc.nextLine();
            dni=dni.toUpperCase();
            if (dni.length()==9){ //buscara que todos los numeros sean numeros
                String numerosDNI="";
                for(int c=0;c<dni.length()-1 && isCorrect;c++){
                    isCorrect=isNumber(dni.charAt(c));
                    numerosDNI+=dni.charAt(c);//esto los almacena correctamente
                }
                if(isCorrect){ //si los 8 primeros digitos so5n numeros entonces se comprueba que la letra sea correcta
                    if(dni.charAt(8)== calculateLetterDNI(numerosDNI)){
                        isCorrect=true;
                    }else{
                        isCorrect=false;
                    }
                }
            }else if(dni.length()==8){
                boolean isTrue=Utils.confirm("La letra es "+ calculateLetterDNI(dni)+" ?");
                if(isTrue){
                    dni+= calculateLetterDNI(dni);
                }else{
                    isCorrect=false;
                }
            } else if (dni.length()==8 || dni.length()==9){
                System.out.println(Utils.rojo+"Introduce un dni que no este registrado"+ Utils.b);
                isCorrect=false;
            }else{
                isCorrect=false;
            }
            if (dni.length()!=8 && dni.length()!=9 || !isCorrect) {
                System.out.println(Utils.rojo+"Introduzca correctamente el DNI"+ Utils.b);
            }
        }while(!isCorrect);

        return dni;
    }

    /**
     * Metodo que es usado por leeDNI. su objetivo es comprobar que todos los supuestos numeros escritos por el usuario son caracteres del 0-9
     * si no retorna un false a leeDNI haciendo que este vuelva a pedir el dni
     * @param c comprueba que el caracter sea un numero
     * @return retorna
     */
    public static boolean isNumber(char c){ //funcion que calcula q los valores introducidos en dni sean todos numeros
        if(c>='0' && c<='9'){
            return true;
        }
        return false;
    }

    /**
     * Metodo que sera utilizado por leeDNI con 2 objetivos . o bien comprobar que la letra del dni sea correspondiente al dni introducido
     * o si el usuario no introduce la letra del dni . el programa le sujiera la letra
     * @param numeroDNI Importa los numeros del dni
     * @return retorna la letra calculada
     */
    public static char calculateLetterDNI(String numeroDNI){ //
        char[] letras={'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        char letra= letras [Integer.valueOf(numeroDNI) % 23]; //Integer.valueOF(numeroDNI)  coge la cadena string NumeroDNI lo convertira a numerico (como si fuese un int)
        return letra;
    }
}