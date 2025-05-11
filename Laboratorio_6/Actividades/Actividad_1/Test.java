package Actividades.Actividad_1;

public class Test {
    public static void main(String[] args) {
        Pila<Integer> pila=new Pila<>(3);
        try{
            System.out.println("La pila esta vacia?:"+pila.isEmpty());
            //pila.pop();
            pila.push(12);
            pila.push(13);
            pila.push(14);
            pila.push(14);
            

            System.out.println(pila);
           
        }catch(ExceptionIsEmpty e){//Excepcion en el caso de que la pila este llena
            System.out.println(e);
        }catch(IllegalStateException e){
            System.out.println(e);
        }
        System.out.println("Esta llena: "+pila.isFull());
        System.out.println(pila);
        System.out.println("El ultimo elemento sacado es: "+pila.pop());
        System.out.println("Esta llena: "+pila.isFull());
        System.out.println(pila);
        System.out.println("El elemento que se encuentra en el tope es: "+ pila.top());

    }
}
