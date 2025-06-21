package Excepciones;

public class ExceptionIsEmpty extends Exception{
    //Excepcion que se usara cuando se quiera tomar un elemento a la estrucutra BST
    // y esta no tenga ningun elemento en ella
    public ExceptionIsEmpty(String msg){
        super(msg);
    }
    public ExceptionIsEmpty(){
        super();
    }
}
