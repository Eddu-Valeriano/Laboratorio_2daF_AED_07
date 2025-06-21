package Excepciones;

public class ItemNotFound extends Exception{
    // Se usara cuando en la estrcutura de datos no se encuentre un elemento exacto
    // o que no tenga las cualidades que se desea
    public ItemNotFound(String msg){
        super(msg);
    }
    public ItemNotFound(){
        super();
    }
}
