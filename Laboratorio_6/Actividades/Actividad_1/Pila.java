package Actividades.Actividad_1;

public class Pila <T>implements Stack<T>{
    T[] arreglo;
    int elementos=0 ;
    int tamanio;

    public Pila(int tamanio){
        arreglo = (T[]) new Object[tamanio]; 
        this.tamanio=tamanio;
    }
    @Override
    public void push(T dato) {
        if(elementos==tamanio){
            throw new IllegalStateException("La pila se encuentra llena");
        }
        arreglo[tamanio-(1+elementos)]=dato;
        elementos++;
    }
    @Override
    public T pop() throws ExceptionIsEmpty{
        T elemento;
        if(elementos==0){
            throw new ExceptionIsEmpty("No existen ningun elemento en el arreglo");
        }
        elemento=arreglo[tamanio-(elementos)];
        arreglo[tamanio-elementos]=null;
        elementos--;
        return elemento;
    }
    @Override
    public T top() throws ExceptionIsEmpty{
        if(elementos==0){
            throw new ExceptionIsEmpty("No existen ningun elemento en el arreglo");
        }
        
        return arreglo[tamanio-(elementos)];
    }
    public boolean isEmpty(){
        return elementos==0?true:false;
    }
    public boolean isFull(){
        return elementos==tamanio?true:false;
    }
    public String toString(){
        String cadena="";
        for(int i=tamanio-elementos;i<tamanio;i++){
            cadena+=arreglo[i]+" ";
        }
        return cadena;
    }
}
