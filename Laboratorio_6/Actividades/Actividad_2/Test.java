package Actividades.Actividad_2;
import Actividades.Actividad_1.ExceptionIsEmpty;


public class Test{
    public static void main(String[] args) {
        QueueLink<Integer> lista=new QueueLink<>();
        try{
        lista.enqueue(1);
        lista.enqueue(2);
        lista.enqueue(3);
        System.out.println(lista);
        }catch(ExceptionIsEmpty e){
            System.out.println(e);
        }
    }
}