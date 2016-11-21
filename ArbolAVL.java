
/**
 *
 * @author Armando1
 * @param <T>
 */
public class ArbolAVL<T extends Comparable<T>> extends Arbol<T> {

    public NodoAVL getRaizAVL() {
        return (NodoAVL) raiz;
    }

    public NodoAVL castAVL(NodoAVL nodo) {
        return (NodoAVL) nodo;
    }

    public void revisarI(NodoAVL<T> nodo, Integer direccion) {//revisar rama izquierda
        NodoAVL<T> nodo1, nodo2, nodo3;
        if (direccion == -1) {
            nodo1 = (NodoAVL<T>) nodo.getIzq();
            nodo2 = (NodoAVL<T>) nodo1.getIzq();
        } else {
            nodo1 = (NodoAVL<T>) nodo.getDer();
            nodo2 = (NodoAVL<T>) nodo1.getIzq();
        }
        if (nodo2.getFe() == -1) {// Rotacion II
            nodo1.setIzq(nodo2.getDer());
            nodo2.setDer(nodo1);
            nodo1.setFe(0);
            if (direccion == -1) {
                nodo.setIzq(nodo2);
            } else {
                nodo.setDer(nodo2);
            }
        } else { // Rotacion ID
            nodo3 = (NodoAVL<T>) nodo2.getDer();
            nodo1.setIzq(nodo3.getDer());
            nodo3.setDer(nodo1);
            nodo2.setDer(nodo3.getIzq());
            nodo3.setIzq(nodo2);
            if (nodo3.getFe() == -1) {
                nodo1.setFe(1);
            } else {
                nodo1.setFe(0);
            }
            if (nodo3.getFe() == 1) {
                nodo2.setFe(-1);
            } else {
                nodo2.setFe(0);
            }
            if (direccion == -1) {
                nodo.setIzq(nodo3);
            } else {
                nodo.setDer(nodo3);
            }

        }
        nodo1.setFe(0);
    }

    public void revisarD(NodoAVL<T> nodo, Integer direccion) {//revisar rama derecha
        NodoAVL<T> nodo1, nodo2, nodo3;
        if (direccion == -1) {
            nodo1 = (NodoAVL) nodo.getIzq();
            nodo2 = (NodoAVL) nodo1.getDer();
        } else {
            nodo1 = (NodoAVL) nodo.getDer();
            nodo2 = (NodoAVL) nodo1.getDer();
        }
        if (nodo2.getFe() == 1) {// Rotacion DD
            nodo1.setDer(nodo2.getIzq());
            nodo2.setIzq(nodo1);
            nodo1.setFe(0);
            if (direccion == -1) {
                nodo.setIzq(nodo2);
            } else {
                nodo.setDer(nodo2);
            }

        } else { // Rotacion DI
            nodo3 = (NodoAVL<T>) nodo2.getIzq();
            nodo1.setDer(nodo3.getIzq());
            nodo3.setIzq(nodo1);
            nodo2.setIzq(nodo3.getDer());
            nodo3.setDer(nodo2);
            if (nodo3.getFe() == 1) {
                nodo1.setFe(-1);
            } else {
                nodo1.setFe(0);
            }
            if (nodo3.getFe() == -1) {
                nodo2.setFe(1);
            } else {
                nodo2.setFe(0);
            }
            if (direccion == -1) {
                nodo.setIzq(nodo3);
            } else {
                nodo.setDer(nodo3);
            }

        }
        nodo1.setFe(0);
    }

    private Integer _insertar(NodoAVL<T> nodo, T dato) {
        Integer resultado = 0;
        if (dato.compareTo(nodo.getDato()) < 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoAVL(dato));
                switch (castAVL(nodo).getFe()) {
                    case 1: // El arbol se balancea
                        castAVL(nodo).setFe(0);
                        resultado = 0;
                        break;
                    case 0: // Se carga del lado izquierdo
                        castAVL(nodo).setFe(-1);
                        resultado = 1;
                        break;
                }
                //resultado = 1;

            } else {
                switch (_insertar(nodo.getIzq(), dato)) {
                    case 1: // Se inserta un dato nuevo, revisar.
                        switch (castAVL(nodo).getFe()) {
                            case 1: // El arbol se balancea
                                castAVL(nodo).setFe(0);
                                resultado = 0;
                                break;
                            case 0: // Se carga del lado izquierdo
                                castAVL(nodo).setFe(-1);
                                resultado = 1;
                                break;
                            case -1: // Reestructuracion del arbol
                                //if (nodo == raiz){
                                resultado = -1;
                        //}else{                        

                                //}
                                break;
                        }
                        break;
                    case -1:
                        revisarI(castAVL(nodo), -1);
                        break;
                    case -2:
                        revisarD(castAVL(nodo), -1);
                        break;

                }

            }
        } else {
            if (dato.compareTo(nodo.getDato()) > 0) {
                if (nodo.getDer() == null) {
                    nodo.setDer(new NodoAVL(dato));
                    switch (castAVL(nodo).getFe()) {
                        case -1: // El arbol se balancea
                            castAVL(nodo).setFe(0);
                            resultado = 0;
                            break;
                        case 0: // Se carga del lado izquierdo
                            castAVL(nodo).setFe(1);
                            resultado = 1;
                            break;
                    }
                } else {
                    switch (_insertar(nodo.getDer(), dato)) {
                        case 1: // Se inserta un dato nuevo, revisar.
                            switch (castAVL(nodo).getFe()) {
                                case -1: // El arbol se balancea
                                    castAVL(nodo).setFe(0);
                                    resultado = 0;
                                    break;
                                case 0: // Se carga del lado der
                                    castAVL(nodo).setFe(1);
                                    resultado = 1;
                                    break;
                                case 1: // Reestructuracion del arbol
                                    resultado = -2;
                            }
                            break;
                        case -1:
                            revisarI(castAVL(nodo), 1);
                            break;
                        case -2:
                            revisarD(castAVL(nodo), 1);
                            break;

                    }

                }
            }
        }

        return resultado;

    }

    public Integer insertar(T dato) {
        if (raiz == null) {
            raiz = new NodoAVL<T>(dato);
        } else {
            Integer result = _insertar(raiz, dato);
            if (result == -1) {

                // Reestructuracion del arbol
                NodoAVL<T> nodo1, nodo2;
                nodo1 = (NodoAVL) raiz.getIzq();

                if (nodo1.getFe() == -1) {// Rotacion II
                    raiz.setIzq(nodo1.getDer());
                    nodo1.setDer(raiz);
                    castAVL(raiz).setFe(0);
                    raiz = nodo1;
                } else { // Rotacion ID
                    nodo2 = (NodoAVL<T>) nodo1.getDer();
                    raiz.setIzq(nodo2.getDer());
                    nodo2.setDer(raiz);
                    nodo1.setDer(nodo2.getIzq());
                    nodo2.setIzq(nodo1);
                    if (nodo2.getFe() == -1) {
                        castAVL(raiz).setFe(1);
                    } else {
                        castAVL(raiz).setFe(0);
                    }
                    if (nodo2.getFe() == 1) {
                        nodo1.setFe(-1);
                    } else {
                        nodo1.setFe(0);
                    }
                    raiz = nodo2;
                }
                castAVL(raiz).setFe(0);

            } else if (result == -2) {
                // Reestructuracion del arbol
                NodoAVL<T> nodo1, nodo2;
                nodo1 = (NodoAVL) raiz.getDer();

                if (nodo1.getFe() == 1) {// Rotacion DD
                    raiz.setDer(nodo1.getIzq());
                    nodo1.setIzq(raiz);
                    castAVL(raiz).setFe(0);
                    raiz = nodo1;
                } else { // Rotacion DI
                    nodo2 = (NodoAVL<T>) nodo1.getIzq();
                    raiz.setDer(nodo2.getIzq());
                    nodo2.setIzq(raiz);
                    nodo1.setIzq(nodo2.getDer());
                    nodo2.setDer(nodo1);
                    if (nodo2.getFe() == 1) {
                        castAVL(raiz).setFe(-1);
                    } else {
                        castAVL(raiz).setFe(0);
                    }
                    if (nodo2.getFe() == -1) {
                        nodo1.setFe(1);
                    } else {
                        nodo1.setFe(0);
                    }
                    raiz = nodo2;
                }
                castAVL(raiz).setFe(0);
            }
        }
        return 0;
    }
/**
 * 
 * 
 * 
 * 
 * falta el metodo insertar Ordenado**/
    
    
    
    public void EliminaBalanceado(NodoAVL<T> nodo, boolean bo, int infor) {
        NodoAVL<T> otro; //variable auxiliar tipo puntero
        if (nodo != null) {
            if (infor < nodo.getFe()) {
                EliminaBalanceado(nodo.getIzq(), bo, infor);
                restructura1(nodo, bo);
            } else {
                if (infor > nodo.getFe()) {
                    EliminaBalanceado(nodo.getDer(), bo, infor);
                    restructura2(nodo, bo);
                } else {
                    otro = nodo;
                    bo = true;
                    if (otro.getDer() == null) {
                        nodo = otro.getIzq();
                    } else {
                        if (otro.getIzq() == null) {
                            nodo = otro.getDer();
                        } else {
                            borra(otro.getIzq(), otro, bo);
                            restructura1(nodo, bo);
                        }
                    }
                }
            }
        } else {
            System.out.println("El nodo no se encuentra en el arbol");
        }
    }

    public void restructura1(NodoAVL<T> nodo, boolean bo) {
        NodoAVL<T> nodo1, nodo2;
        if (bo == true) {
            switch (nodo.getFe()) {
                case -1:
                    nodo.setFe(0);
                    break;
                case 0:
                    nodo.setFe(1);
                    bo = false;
                    break;
                case 1://Restructuracion del Arbol
                    nodo1 = nodo.getDer();
                    if (nodo1.getFe() == 0) {//Rotacion DD
                        nodo.setDer(nodo1.getIzq());
                        nodo1.setIzq(nodo);
                        switch (nodo1.getFe()) {
                            case 0:
                                nodo.setFe(1);
                                nodo1.setFe(-1);
                                bo = false;
                                break;
                            case 1:
                                nodo.setFe(0);
                                nodo1.setFe(0);
                                break;
                        }//fin
                        nodo = nodo1;
                        //Termina la rotacion DD
                        break;
                    } else {//Rotacion DI
                        nodo2 = nodo1.getIzq();
                        nodo.setDer(nodo2.getIzq());
                        nodo2.setIzq(nodo);
                        nodo1.setIzq(nodo2.getDer());
                        nodo2.setDer(nodo1);
                        if (nodo2.getFe() == 1) {
                            nodo.setFe(-1);
                        } else {
                            nodo.setFe(0);
                        }//fin
                        if (nodo.getFe() == -1) {
                            nodo1.setFe(1);
                        } else {
                            nodo1.setFe(0);
                        }//fin
                        nodo = nodo2;
                        nodo2.setFe(0);
                    }//Termina la rotacion DI
            }
        }
    }

    public void restructura2(NodoAVL<T> nodo, boolean bo) {
        NodoAVL<T> nodo1, nodo2;
        if (bo == true) {
            switch (nodo.getFe()) {
                case 1:
                    nodo.setFe(0);
                    break;
                case 0:
                    nodo.setFe(-1);
                    bo = false;
                    break;
                case -1://Restructuracion del arbol
                    nodo1 = nodo.getIzq();
                    if (nodo1.getFe() <= 0) {//Rotacion II
                        nodo.setIzq(nodo1.getDer());
                        nodo1.setDer(nodo);
                        switch (nodo1.getFe()) {
                            case 0:
                                nodo.setFe(-1);
                                nodo1.setFe(1);
                                bo = false;
                                break;
                            case -1:
                                nodo.setFe(0);
                                nodo1.setFe(0);
                                break;
                        }
                        nodo = nodo1;
                        //Termina la rotacion II
                        break;
                    } else {//Rotacion ID
                        nodo2 = nodo1.getDer();
                        nodo.setIzq(nodo2.getDer());
                        nodo2.setDer(nodo);
                        nodo1.setDer(nodo2.getIzq());
                        nodo2.setIzq(nodo1);
                        if (nodo2.getFe() == -1) {
                            nodo.setFe(1);
                        } else {
                            nodo.setFe(0);
                        }
                        if (nodo2.getFe() == 1) {
                            nodo1.setFe(-1);
                        } else {
                            nodo1.setFe(0);
                        }//fin
                        nodo = nodo2;
                        nodo2.setFe(0);
                        //Termina la rotacion ID
                    }//fin
            }//fin
        }//fin
    }

    public void borra(NodoAVL<T> aux, NodoAVL<T> otro, boolean bo) {
        if (aux.getDer() != null) {
            borra(aux.getDer(), otro, bo);
            restructura2(aux, bo);
        } else {
            otro.setFe(aux.getFe());
            aux = aux.getIzq();
            bo = true;
        }
    }
}
