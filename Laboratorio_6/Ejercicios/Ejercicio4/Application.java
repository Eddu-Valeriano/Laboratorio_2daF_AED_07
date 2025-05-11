package Ejercicios.Ejercicio4;
import Ejercicios.Ejercicio1.StackLink;

public class Application {

    public static boolean symbolBalancing(String S) {
        StackLink<Character> pila = new StackLink<>();

        // Iterar sobre cada carácter de la cadena
        for (char c : S.toCharArray()) {
            // Si encontramos un corchete de apertura, lo añadimos a la pila
            if (c == '(' || c == '[' || c == '{') {
                pila.push(c);
            }
            // Si encontramos un corchete de cierre, verificamos que coincida con el de la cima de la pila
            else if (c == ')' || c == ']' || c == '}') {
                // Verificamos si la pila está vacía o si no coincide con el corchete de apertura esperado
                if (pila.isEmpty()) {
                    return false;  // No hay corchete de apertura correspondiente
                }
                char apertura = pila.pop();
                // Comprobamos la correspondencia entre corchetes
                if ((c == ')' && apertura != '(') || 
                    (c == ']' && apertura != '[') || 
                    (c == '}' && apertura != '{')) {
                    return false;  // No coincide el par de corchetes
                }
            }
        }

        // Si la pila está vacía al final, significa que todos los corchetes fueron correctamente balanceados
        return pila.isEmpty();
    }

    public static void main(String[] args) {
        String secuencia = "()()[()]";
        boolean resultado = symbolBalancing(secuencia);
        System.out.println("La secuencia '" + secuencia + "' está correctamente anidada: " + resultado);

        String secuencia2 = "[(()]";
        boolean resultado2 = symbolBalancing(secuencia2);
        System.out.println("La secuencia '" + secuencia2 + "' está correctamente anidada: " + resultado2);
    }
}
