package primvskruskal;

import java.util.*;

class Arista implements Comparable<Arista> {
    int origen, destino, peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }

    @Override
    public String toString() {
        return origen + "-" + destino + "(" + peso + ")";
    }
}

class Grafo {
    private int numVertices;
    private List<Arista> aristas;
    private List<List<Arista>> listaAdyacencia;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.aristas = new ArrayList<>();
        this.listaAdyacencia = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    public void agregarArista(int origen, int destino, int peso) {
        Arista arista = new Arista(origen, destino, peso);
        aristas.add(arista);
        listaAdyacencia.get(origen).add(arista);
        listaAdyacencia.get(destino).add(new Arista(destino, origen, peso));
    }

    public int getNumVertices() {
        return numVertices;
    }

    public List<Arista> getAristas() {
        return new ArrayList<>(aristas);
    }

    public List<Arista> getVecinos(int vertice) {
        return listaAdyacencia.get(vertice);
    }

    public void mostrarGrafo() {
        System.out.println("\n=== GRAFO ===");
        System.out.println("Vértices: " + numVertices);
        System.out.println("Aristas:");
        for (Arista arista : aristas) {
            System.out.println("  " + arista);
        }
    }
}

class UnionFind {
    private int[] padre;
    private int[] rango;

    public UnionFind(int n) {
        padre = new int[n];
        rango = new int[n];
        for (int i = 0; i < n; i++) {
            padre[i] = i;
            rango[i] = 0;
        }
    }

    public int encontrar(int x) {
        if (padre[x] != x) {
            padre[x] = encontrar(padre[x]); 
        }
        return padre[x];
    }

    public boolean unir(int x, int y) {
        int raizX = encontrar(x);
        int raizY = encontrar(y);

        if (raizX == raizY) {
            return false;
        }

        if (rango[raizX] < rango[raizY]) {
            padre[raizX] = raizY;
        } else if (rango[raizX] > rango[raizY]) {
            padre[raizY] = raizX;
        } else {
            padre[raizY] = raizX;
            rango[raizX]++;
        }
        return true;
    }
}

class AlgoritmoPrim {
    private Grafo grafo;

    public AlgoritmoPrim(Grafo grafo) {
        this.grafo = grafo;
    }

    public void ejecutar() {
        long tiempoInicio = System.nanoTime();

        int numVertices = grafo.getNumVertices();
        boolean[] visitado = new boolean[numVertices];
        int[] pesoMinimo = new int[numVertices];
        int[] padre = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            pesoMinimo[i] = Integer.MAX_VALUE;
            padre[i] = -1;
        }

        pesoMinimo[0] = 0;
        int pesoTotal = 0;
        List<Arista> arbolExpansionMinimo = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            int u = encontrarVerticeMinimo(pesoMinimo, visitado);

            if (u == -1) break;

            visitado[u] = true;
            pesoTotal += pesoMinimo[u];

            if (padre[u] != -1) {
                arbolExpansionMinimo.add(new Arista(padre[u], u, pesoMinimo[u]));
            }

            for (Arista arista : grafo.getVecinos(u)) {
                int v = arista.destino;
                if (!visitado[v] && arista.peso < pesoMinimo[v]) {
                    pesoMinimo[v] = arista.peso;
                    padre[v] = u;
                }
            }
        }

        long tiempoFin = System.nanoTime();
        long tiempoEjecucion = tiempoFin - tiempoInicio;

        System.out.println("║     ALGORITMO DE PRIM                  ║");
        System.out.println("║ Aristas del Árbol de Expansión Mínimo: ║");
        for (Arista arista : arbolExpansionMinimo) {
            System.out.println("║   " + arista);
        }
        System.out.println("║ Peso total: " + pesoTotal);
        System.out.println("║ Tiempo: " + tiempoEjecucion + " ns (" + (tiempoEjecucion / 1000.0) + " µs)");
    }

    private int encontrarVerticeMinimo(int[] pesoMinimo, boolean[] visitado) {
        int minimo = Integer.MAX_VALUE;
        int vertice = -1;

        for (int i = 0; i < pesoMinimo.length; i++) {
            if (!visitado[i] && pesoMinimo[i] < minimo) {
                minimo = pesoMinimo[i];
                vertice = i;
            }
        }

        return vertice;
    }
}

class AlgoritmoKruskal {
    private Grafo grafo;

    public AlgoritmoKruskal(Grafo grafo) {
        this.grafo = grafo;
    }

    public void ejecutar() {
        long tiempoInicio = System.nanoTime();

        List<Arista> aristas = grafo.getAristas();
        Collections.sort(aristas);

        UnionFind uf = new UnionFind(grafo.getNumVertices());
        List<Arista> arbolExpansionMinimo = new ArrayList<>();
        int pesoTotal = 0;

        for (Arista arista : aristas) {
            if (uf.unir(arista.origen, arista.destino)) {
                arbolExpansionMinimo.add(arista);
                pesoTotal += arista.peso;

                if (arbolExpansionMinimo.size() == grafo.getNumVertices() - 1) {
                    break;
                }
            }
        }

        long tiempoFin = System.nanoTime();
        long tiempoEjecucion = tiempoFin - tiempoInicio;

        System.out.println("║     ALGORITMO DE KRUSKAL               ║");
        System.out.println("║ Aristas del Árbol de Expansión Mínimo: ║");
        for (Arista arista : arbolExpansionMinimo) {
            System.out.println("║   " + arista);
        }
        System.out.println("║ Peso total: " + pesoTotal);
        System.out.println("║ Tiempo: " + tiempoEjecucion + " ns (" + (tiempoEjecucion / 1000.0) + " µs)");
    }
}

public class main {
    public static void main(String[] args) {

        Grafo grafo = new Grafo(7);

        grafo.agregarArista(0, 1, 7);
        grafo.agregarArista(0, 3, 5);
        grafo.agregarArista(1, 2, 8);
        grafo.agregarArista(1, 3, 9);
        grafo.agregarArista(1, 4, 7);
        grafo.agregarArista(2, 4, 5);
        grafo.agregarArista(3, 4, 15);
        grafo.agregarArista(3, 5, 6);
        grafo.agregarArista(4, 5, 8);
        grafo.agregarArista(4, 6, 9);
        grafo.agregarArista(5, 6, 11);

        grafo.mostrarGrafo();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPARACIÓN DE RENDIMIENTO: PRIM vs KRUSKAL");
        System.out.println("=".repeat(50));

        AlgoritmoPrim prim = new AlgoritmoPrim(grafo);
        prim.ejecutar();

        AlgoritmoKruskal kruskal = new AlgoritmoKruskal(grafo);
        kruskal.ejecutar();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("NOTA: Ambos algoritmos usan el MISMO grafo");
        System.out.println("Ambos producen el MISMO árbol de expansión mínimo");
        System.out.println("=".repeat(50));
    }
}
