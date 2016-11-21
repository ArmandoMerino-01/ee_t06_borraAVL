

/**
 *
 * @author Armando1
 */
public abstract class Arbol<T extends Comparable<T>>{
    NodoAVL<T> raiz;
    
    public boolean estaVacio(){
        return raiz == null;
    }
    
    public void preorden(NodoAVL<T> raiz){
        if(raiz != null){
            System.out.print(raiz.getDato());
        preorden(raiz.getIzq());
        preorden(raiz.getDer());
        }
    }
    public void inorden(NodoAVL<T> raiz){
        if(raiz != null){
            inorden(raiz.getIzq());
            System.out.print(raiz.getDato());
            inorden(raiz.getDer());
        }
    }
    public void posorden(NodoAVL<T> raiz){
        if(raiz != null){
            posorden(raiz.getIzq());
            posorden(raiz.getDer());
        System.out.print(raiz.getDato());
        }
    }
   
        public int altura(NodoAVL<T> raiz, int altura){
        altura = altura + 1;
        int izquierda, derecha;
        if(raiz != null){
            System.out.println("El nivel del nodo: "+raiz.getDato()+" es: "+altura);
            izquierda = altura(raiz.getIzq(), altura);
            derecha = altura(raiz.getDer(),altura);
         }else{
             return -1;
         }
        if (izquierda > derecha)
          return izquierda +1;
        else
          return derecha+1; 
    }
    public boolean buscarNodo(NodoAVL<T> raiz, Integer n){
        boolean b = false;
        if(raiz != null){
            if(raiz.getDato() == n){
                return true;
            }else{
                   b = buscarNodo(raiz.getIzq(), n);
                if(b == false)
                   b = buscarNodo(raiz.getDer(), n);
            }
        }
        return b;
    }
        public int numeroNodos(NodoAVL<T> raiz){
        if(raiz == null)
            return 0;
        else
            return 1 + numeroNodos(raiz.getIzq()) + numeroNodos(raiz.getDer());
    }
}

