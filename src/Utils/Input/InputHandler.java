package Utils.Input;

import static java.lang.System.out;
import Exceptions.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class InputHandler implements Serializable {
    public static String readLine() throws ReturnException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = br.readLine();
            if (str!=null){
                if(str.equals("<-"))
                    throw new ReturnException(); /*Apanha um <-, manda excecao pro metodo que o chama.
                                                   Quando apanhamos esta excecao, sabemos que é pra
                                                   retroceder um nivel no menu
                                                 */
            }
        } catch (IOException ex) {
            out.println(ex.getMessage());
        }
        return str;
    }

    public static int readInteger() throws ReturnException, InvalidInputException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        int number = 0;
        try {
            str  = br.readLine();
            if (str!=null){
                if(str.equals("<-"))
                    throw new ReturnException();
            }
            number = Integer.parseInt(str);
        } catch (NumberFormatException | IOException ex){
            throw new InvalidInputException();
        }
        return number;
    }

    public static long readLong() throws ReturnException, InvalidInputException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        long number = 0;
        try {
            str  = br.readLine();
            if (str!=null){
                if(str.equals("<-"))
                    throw new ReturnException();
            }
            number = Long.parseLong(str);
        } catch (NumberFormatException | IOException ex) {
            throw new InvalidInputException();
        }
        return number;
    }

    public static double readDouble(BufferedReader br) throws ReturnException, InvalidInputException{
        String str;
        double number = 0.0;
        try {
            str  = br.readLine();
            if (str!=null){
                if(str.equals("<-"))
                    throw new ReturnException();
            }
            number = Double.parseDouble(str);
        } catch (NumberFormatException | IOException ex) {
            throw new InvalidInputException();
        }
        return number;
    }

    public static void waitForAcceptance() { //espera por um ENTER
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            br.readLine();
        } catch (IOException ex){
            out.print(ex.getMessage());
        }
    }

    public static List<String> readFile(String file){
        List<String> array = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            stream.forEach(l -> array.add(l));
        } catch (IOException e) {
            out.print(e.getMessage());
        }
        return array;
    }
}