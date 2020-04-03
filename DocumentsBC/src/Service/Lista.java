package Service;

import java.sql.Timestamp;
import java.util.Scanner;

public class Lista 
{
	class Nodo{

		Nodo ant;

		String bloque;

		String dir1;

		String dir2;

		String document;
		
		String hash;
		
		String hashprev;

		Nodo sig;

	}

 

	private Nodo ap;

	public Lista() {

		ap=null;
	}

	public void insertar(String bloque, String dir1, String dir2, String documento) {

		Nodo aux;

		aux = new Nodo();

		Nodo nuevo;

		nuevo = new Nodo();

		nuevo = ap;
		
		Metodo hs = new Metodo();
		
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (ap==null) {

			aux.ant = null;

			aux.sig = null;

			aux.bloque = bloque;

			aux.dir1 = dir1;

			aux.dir2 = dir2;

			aux.document = documento;
			
			aux.hashprev = hs.Hash( bloque + dir1 + dir2 + documento + time);

			aux.hash =  hs.Hash( bloque + dir1 + dir2 + documento + time + aux.hashprev);
			
			ap = aux;

 

		}else {

			nuevo = ap;
			
			System.out.println(nuevo.hashprev);

			while(nuevo.sig!=null) {

				nuevo = nuevo.sig;

			}
			
			aux.hashprev = nuevo.hash;

			aux.bloque = bloque;

			aux.dir1 = dir1;

			aux.dir2 = dir2;

			aux.document = documento;
			
			aux.hash = hs.Hash( bloque + dir1 + dir2 + documento + time);

			nuevo.sig = aux;

			aux.ant = nuevo;

			aux.sig = null;

		}

	}

 
	public void Buscar_Id(String idBloque) {

		Nodo recorrer=ap;

		boolean encontrado=false;

		while (recorrer!=null && encontrado==false) {

			if (recorrer.bloque==idBloque) {

				encontrado=true;

			}

		}

		if (encontrado==true) {

			System.out.println("\n Bloque: "+"["+recorrer.bloque+"]"+" - "+"["+"Origen: "+recorrer.dir1+"]"+" - "+"["+"Destino: "+recorrer.dir2+"]"+" - "+"["+"[Documento: "+recorrer.document+"]"+" - "+"["+"hash: "+recorrer.hash +"]");

		}else {

			System.out.println("no hay nada ");

		}

	}

	public void imprimir() {

		Nodo aux = ap;

		System.out.println("--------------------");

	    System.out.println("\n Listado de transacciones.");

	    System.out.println("--------------------");

	    while(aux!=null) {

	    	System.out.println("\n Bloque: "+"["+aux.bloque+"]"+" - "+"["+"Origen: "+aux.dir1+"]"+" - "+"["+"Destino: "+aux.dir2+"]"+" - "+"["+"[Documento: "+aux.document+ "]");
	    	System.out.println("["+"Hash Prev: "+aux.hashprev +"]" + "\n["+"Hash: "+aux.hash +"]");
	    	aux=aux.sig;
	    }

	    System.out.println();

	}

 

	public static void main(String[] args) {

		Lista lista1 = new Lista();

		int opc=0;

		System.out.println("---- Benvenido al demo de block chain ------");
 

		do {

			Scanner ingreso= new Scanner(System.in);

			String dir1;

			String dir2 = "";

			String documento = "";

			String hash;

			String bloque;
			

			System.out.println("         __________________");

            System.out.println("        |      MENU        |");

            System.out.println("        | 1. Transaccion   |");

            System.out.println("        | 2. Buscar        |");

            System.out.println("        |__________________|");

            System.out.print("\nDigite opcion:");

			opc = ingreso.nextInt();

 

			switch(opc) {

			case 1:

				System.out.print("Origen : ");

				dir1 = ingreso.next();

				System.out.print("Destino: ");

				dir2 = ingreso.next();

				System.out.print("Documento:  ");

				documento = ingreso.next();
				
				System.out.print("Numero de bloque: " );
				
				bloque = ingreso.next();

				lista1.insertar(bloque, dir1, dir2, documento);

				lista1.imprimir();

			break;
			
			case 2:
				
				System.out.print("Numero de bloque: " );
				
				bloque = ingreso.next();
				
				lista1.Buscar_Id(bloque);
				
			break;
			
			}

		}while(opc!=0);

	}
	
}
