/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author user
 */
class Nodo
{
    int registro;
    MaterialProperties material;
    
    Nodo izquierdo;
    Nodo derecho;
}

public class ArbolBinario 
{
    Nodo raiz;
    
    public ArbolBinario()
    {
        raiz = null;
    }
    
    public ArbolBinario(Nodo inicial)
    {
        raiz = inicial;
    }
    
    public void Insertar(Nodo nuevo)
    {
        if (raiz == null) 
        {
           raiz = nuevo;    
        }
        else
        {
         Insertar(raiz, nuevo);       
        }
    }
    private void Insertar(Nodo raiz, Nodo nuevo)
    {
        if (raiz.derecho == null) 
        {
          raiz.derecho = nuevo;      
        }
        else if (raiz.izquierdo == null) 
        {
          raiz.izquierdo = nuevo;      
        }
        else if ((nuevo.material.Nombre.compareTo(raiz.material.Nombre)) < 0)
        {
            Insertar(raiz.izquierdo, nuevo);
        }
        else
        {
            Insertar(raiz.derecho, nuevo);
        }
    }
    
}
