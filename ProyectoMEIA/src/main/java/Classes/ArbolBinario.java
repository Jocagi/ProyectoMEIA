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
    String material;
    int posicion;
    
    Nodo izquierdo;
    Nodo derecho;
    
    Nodo(String Material)
    {
        this.material = Material;
    }
    Nodo(String Material, int Posicion)
    {
        this.material = Material;
        this.posicion = Posicion;
    }
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
        if ((nuevo.material.compareTo(raiz.material)) < 0)
        {
            if (raiz.izquierdo == null) 
            {
            raiz.izquierdo = nuevo;      
            }
            else
            {
            Insertar(raiz.izquierdo, nuevo);    
            }
        }
        else
        {
            if (raiz.derecho == null) 
            {
            raiz.derecho = nuevo;      
            }
            else
            {
            Insertar(raiz.derecho, nuevo);    
            }
        }
    }
    public Nodo Buscar(String item)
    {
        if (raiz != null) {
            return Buscar(item, raiz);
        }
        else return null;
    }
    private Nodo Buscar(String item, Nodo raiz)
    {
        if (raiz != null) {
            if (item.equals(raiz.material)) {
                return raiz;
            }
            else if ((item.compareTo(raiz.material)) < 0)
            {
            return Buscar(item, raiz.izquierdo);
            }
            else
            {
            return Buscar(item, raiz. derecho);
            }
        }
        else
        {
            return null;
        }
    }
}
