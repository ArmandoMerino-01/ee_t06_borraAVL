

/**
 *
 * @author Armando
 */
public class test {

    public static void main(String[] args) {
        ArbolAVL<Integer> a = new ArbolAVL();
  
        a.insertar(100);
        a.insertar(45);
        a.insertar(10);
        a.insertar(20);
        a.insertar(56);
        a.insertar(70);
        a.insertar(90);
   
        System.out.println("------------------ARBOL ORINIGAL------------------");
        BTreePrinter.printNode(a.getRaizAVL());
        System.out.println("\n------------------DESPUES DEL METODO ELIMINAR------------------");
        a.EliminaBalanceado(a.getRaizAVL(), true,90);
        BTreePrinter.printNode(a.getRaizAVL());

    }
}



