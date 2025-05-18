package Excepciones;
public class ItemDuplicated extends Exception{
    //Excepcion que se usara en la estructura de datos BST
    //Este se usara cuando se ingrese un dato que ya se encuentra en la estrcutura 
    // para denotar como un elemento repetido

    public ItemDuplicated(String msg){
        super(msg);
    }
    public ItemDuplicated(){
        super();
    }
}