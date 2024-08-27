
package avl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Tree {

    Node root;

    public Tree() {
        this.root = null;
    }

    public static int altura(Node n1) {
        if (n1 == null) {
            return 0;
        }
        return Math.max(altura(n1.left), altura(n1.right)) + 1;
    }

    public void imprimirArbol() {  // Método para iniciar la impresión del árbol
        int maxLevel = altura(this.root);  // Calcula la altura del árbol
        imprimirNodos(Collections.singletonList(this.root), 1, maxLevel);  // Llama al método de impresión empezando por la raíz
    }

    private void imprimirEspacios(int count) {  // Método auxiliar para imprimir un número específico de espacios
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private boolean sonTodosNulos(List<Node> nodos) {  // Verifica si todos los nodos en una lista son null
        for (Node nodo : nodos) {
            if (nodo != null) {
                return false;  // Si encuentra un nodo no nulo, devuelve false
            }
        }
        return true;  // Si todos son null, devuelve true
    }

    private void imprimirNodos(List<Node> nodos, int nivel, int maxLevel) {  // Método recursivo para imprimir los nodos en cada nivel
        if (nodos.isEmpty() || sonTodosNulos(nodos)) {
            return;  // Si no hay nodos o todos son null, termina la impresión
        }
        int floor = maxLevel - nivel;  // Calcula el nivel actual del piso (cuántos niveles faltan para llegar al final)
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));  // Calcula cuántas líneas de conexión hay entre los niveles
        int firstSpaces = (int) Math.pow(2, floor) - 1;  // Calcula el espacio inicial antes del primer nodo
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;  // Calcula el espacio entre los nodos en el mismo nivel

        imprimirEspacios(firstSpaces);  // Imprime los espacios iniciales

        List<Node> nuevosNodos = new ArrayList<>();  // Crea una nueva lista para los nodos de la siguiente línea
        for (Node nodo : nodos) {  // Itera sobre los nodos actuales
            if (nodo != null) {  // Si el nodo no es null
                System.out.print(nodo.data);  // Imprime el dato del nodo
                nuevosNodos.add(nodo.left);  // Agrega el hijo left a la lista de nuevos nodos
                nuevosNodos.add(nodo.right);  // Agrega el hijo derecho a la lista de nuevos nodos
            } else {  // Si el nodo es null
                nuevosNodos.add(null);  // Agrega null para mantener la estructura
                nuevosNodos.add(null);  // Agrega null para mantener la estructura
                System.out.print(" ");  // Imprime un espacio vacío en lugar del nodo
            }

            imprimirEspacios(betweenSpaces);  // Imprime los espacios entre nodos en el mismo nivel
        }
        System.out.println("");  // Salto de línea después de imprimir todos los nodos de este nivel

        for (int i = 1; i <= edgeLines; i++) {  // Imprime las líneas de conexión (barras diagonales) entre los nodos
            for (int j = 0; j < nodos.size(); j++) {  // Itera sobre los nodos de nuevo
                imprimirEspacios(firstSpaces - i);  // Imprime los espacios antes de la conexión

                if (nodos.get(j) == null) {  // Si el nodo es null, solo imprime los espacios necesarios
                    imprimirEspacios(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodos.get(j).left != null) {
                    System.out.print("/");  // Imprime "/" para la conexión left
                } else {
                    imprimirEspacios(1);  // Si no hay conexión left, imprime un espacio
                }
                imprimirEspacios(i + i - 1);  // Espacio entre las conexiones left y right

                if (nodos.get(j).right != null) {
                    System.out.print("\\");  // Imprime "\\" para la conexión derecha
                } else {
                    imprimirEspacios(1);  // Si no hay conexión derecha, imprime un espacio
                }
                imprimirEspacios(edgeLines + edgeLines - i);  // Imprime los espacios después de la conexión
            }

            System.out.println("");  // Salto de línea después de imprimir las conexiones de este nivel
        }

        imprimirNodos(nuevosNodos, nivel + 1, maxLevel);  // Llama recursivamente para imprimir el siguiente nivel de nodos
    }

    public int factorEquilibrio(Node node) {
        if (node == null) {
            return 0;
        }
        return altura(node.left) - altura(node.right);
    }

    public Node rotacionDerecha(Node y) {
        Node x = y.left;
        Node temp = x.right;
        x.right = y;
        y.left = temp;
        x.FE = factorEquilibrio(x);
        y.FE = factorEquilibrio(y);
        return x;
    }

    public Node rotacionIzquierda(Node x) {
        Node y = x.right;
        Node temp = y.left;
        y.left = x;
        x.right = temp;
        x.FE = factorEquilibrio(x);
        y.FE = factorEquilibrio(y);
        return y;
    }

    public Node insertar(Node node, int i) {
        if (node == null) {
            return new Node(i);
        }
        if (i < node.data) {
            node.left = insertar(node.left, i);
        } else if (i > node.data) {
            node.right = insertar(node.right, i);
        } else {
            return node;
        }

        int FE = factorEquilibrio(node);
        node.FE = FE;
        
        int vNodoDer = node.right != null ? node.right.data : 0;
        int vNodoIzq = node.left != null ? node.left.data : 0;
        
        if (FE > 1 && i < vNodoIzq) {
            //System.out.println("Vamos a hacer una rotacion a la derecha");
            return rotacionDerecha(node);
        }
        if (FE < -1 && i > vNodoDer) {
            //System.out.println("Vamos a hacer una rotacion a la izquierda");
            return rotacionIzquierda(node);
        }
        
        return node;
    }

    public void addRecursive(int data) {
        this.root = insertar(this.root, data);
    }
    
    public void imprimir ( Node n1 ,int nivel ){
        if ( n1 != null ){
            imprimir ( n1.right , nivel+1 );
            for (int i = 0; i < nivel; i++){
                System.out.print ("      ");
            }
            System.out.println(n1.data );
            imprimir (n1.left , nivel+1 );
           
        }
    }
}
