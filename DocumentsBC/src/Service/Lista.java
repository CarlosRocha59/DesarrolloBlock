package Service;

import java.io.FileNotFoundException;
import java.io.IOException;
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

		Nodo n = ap;
		
		while(n.bloque!=null && n.sig!=null) {
			
			if (n.bloque.contains(idBloque)) {
                
				System.out.println("\n Bloque: "+"["+n.bloque+"]"+" - "+"["+"Origen: "+n.dir1+"]"+" - "+"["+"Destino: "+n.dir2+"]"+" - "+"["+"[Documento: "+n.document+"]"+" - "+"["+"hash: "+n.hash +"]");
			}
			
			n = n.sig;
		}
		
		if (n.bloque.contains(idBloque)) {
            
			System.out.println("\n Bloque: "+"["+n.bloque+"]"+" - "+"["+"Origen: "+n.dir1+"]"+" - "+"["+"Destino: "+n.dir2+"]"+" - "+"["+"[Documento: "+n.document+"]"+" - "+"["+"hash: "+n.hash +"]");
		}
		
	}

	public void imprimir() {

		Nodo aux = ap;

	    System.out.println("\n Listado de transacciones.");

	    System.out.println("------------------------------");

	    while(aux!=null) {

	    	System.out.println("\n Bloque: "+"["+aux.bloque+"]"+" - "+"["+"Origen: "+aux.dir1+"]"+" - "+"["+"Destino: "+aux.dir2+"]"+" - "+"["+"[Documento: "+aux.document+ "]");
	    	System.out.println("\n\n["+"Hash Prev: "+aux.hashprev +"]" + "\n\n["+"Hash: "+aux.hash +"]");
	    	System.out.println("------------------------------");
	    	aux=aux.sig;
	    }

	    System.out.println();

	}

 

	public static void main(String[] args) {

		Lista lista1 = new Lista();

		Archivos arch=new Archivos();
		
		int opc=0;

		System.out.println("---- Benvenido al demo de block chain ------");
 

		do {

			Scanner ingreso= new Scanner(System.in);

			String dir1;

			String dir2 = "";

			String documento = "";

			String hash;

			String bloque;
			
			int id;

			System.out.println("         __________________");

            System.out.println("        |      MENU        |");

            System.out.println("        | 1. Transaccion   |");

            System.out.println("        | 2. Buscar        |");

            System.out.println("        |__________________|");

            System.out.print("\nDigite opcion:");

			opc = ingreso.nextInt();

 

			switch(opc) {

			case 1:
                
				try {
					System.out.print("Origen : ");
	
					dir1 = ingreso.next();
	
					System.out.print("Destino: ");
	
					dir2 = ingreso.next();
	
					System.out.print("Ruta del documento:  ");
					
					documento = ingreso.next();
					
					System.out.println(arch.leer(documento));
					
					documento=arch.leer(documento);
					
					id= (int) Math.random();
					
					System.out.println(id);
					
					bloque = Integer.toString(id);
					
					System.out.println(bloque);
					
					System.out.print("Numero de bloque: "  + bloque);

					lista1.insertar(bloque, dir1, dir2, documento);

					lista1.imprimir();
				
			    } catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

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
