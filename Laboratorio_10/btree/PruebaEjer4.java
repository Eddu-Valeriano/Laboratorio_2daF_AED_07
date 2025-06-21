package btree;

public class PruebaEjer4{
    public static void main(String []args){
        
        int[]lista={103,110,101,120,115,125,140,108,132,128,145,122,108};
        String[]nombres={"Ana","Luis","Carlos","Lucia","David","Jorge","Camila","Rosa","Ernesto","Denis","Enrique","Karina","Juan"};
        BTree<RegistroEstudiante> registro=new BTree<>(4);
        for(int i=0;i<13;i++){
            
            registro.insert(new RegistroEstudiante(lista[i], nombres[i]));
            
        }
        registro.imprimir();
        int []busqueda={115,132,999};
        for(int i=0;i<3;i++){
            String salida=registro.buscarNombre(new RegistroEstudiante(busqueda[i], null));
            System.out.println("El codigo de nombre "+busqueda[i]+":"+salida);
        }
        System.out.println("Eliminando Estudiante con codigo 101");
        registro.remove(new RegistroEstudiante(101, null));
        System.out.println("Insertando nuevo estudiante: (106,Sara)");
        registro.insert(new RegistroEstudiante(106, "Sara"));
        System.out.println("Buscar estudiante con codigo 106"+registro.buscarNombre(new RegistroEstudiante(106, null)));
        
    }
}