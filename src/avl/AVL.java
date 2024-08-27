/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package avl;

/**
 *
 * @author USER
 */
public class AVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.addRecursive( 30);
        tree.addRecursive( 40);
        tree.addRecursive( 35);
        tree.addRecursive( 15);
        tree.addRecursive( 50);
        tree.addRecursive( 55);
        tree.addRecursive( 56);
        tree.addRecursive( 57);
        tree.addRecursive( 58);
        
        tree.imprimir(tree.root, 1);
        
        
    }
    
}
