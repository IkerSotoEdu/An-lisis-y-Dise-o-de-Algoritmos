import time
from collections import defaultdict


class GrafoMST:
    """Clase para crear un grafo"""
    
    def __init__(self, vertices):
        self.V = vertices
        self.edges = []
        self.graph = defaultdict(list)
    
    def agregar_arista(self, u, v, peso):

        self.edges.append((peso, u, v))
        self.graph[u].append((v, peso))
        self.graph[v].append((u, peso))
    
    def prim(self):
        """
        Algoritmo de Prim
        """
        visited = [False] * self.V
        min_cost = 0
        edges_mst = []
        
        visited[0] = True
        
        while len(edges_mst) < self.V - 1:
            min_edge = None
            min_weight = float('inf')

            for u in range(self.V):
                if visited[u]:
                    for v, peso in self.graph[u]:
                        if not visited[v] and peso < min_weight:
                            min_weight = peso
                            min_edge = (u, v, peso)
            
            if min_edge:
                u, v, peso = min_edge
                visited[v] = True
                edges_mst.append(min_edge)
                min_cost += peso
        
        return edges_mst, min_cost

    def kruskal(self):
        """
        Algoritmo de Kruskal
        """
        edges_sorted = sorted(self.edges)
        
        parent = list(range(self.V))
        rank = [0] * self.V
        min_cost = 0
        edges_mst = []
        
        def find(x):

            if parent[x] != x:
                parent[x] = find(parent[x])
            return parent[x]
        
        def union(x, y):

            px, py = find(x), find(y)
            if px == py:
                return False
            if rank[px] < rank[py]:
                parent[px] = py
            elif rank[px] > rank[py]:
                parent[py] = px
            else:
                parent[py] = px
                rank[px] += 1
            return True
        
        for peso, u, v in edges_sorted:
            if union(u, v):
                edges_mst.append((u, v, peso))
                min_cost += peso
                if len(edges_mst) == self.V - 1:
                    break
        
        return edges_mst, min_cost


def crear_grafo_prueba(vertices=10):
    grafo = GrafoMST(vertices)
    
    aristas_ejemplo = [
        (0, 1, 4),
        (0, 7, 8),
        (1, 2, 8),
        (1, 7, 11),
        (2, 3, 7),
        (2, 8, 2),
        (2, 5, 4),
        (3, 4, 9),
        (3, 5, 14),
        (4, 5, 10),
        (5, 6, 2),
        (6, 7, 1),
        (6, 8, 6),
        (7, 8, 7),
    ]
    
    for u, v, peso in aristas_ejemplo:
        grafo.agregar_arista(u, v, peso)
    
    return grafo


def main():
    print("=" * 60)
    print("COMPARACIÓN: ALGORITMO DE PRIM VS KRUSKAL")
    print("=" * 60)
    
    grafo = crear_grafo_prueba(9)
    
    print(f"\nInformación del Grafo:")
    print(f"  Vértices: {grafo.V}")
    print(f"  Aristas: {len(grafo.edges)}")
    
    print("\n" + "-" * 60)
    print("ALGORITMO DE PRIM")
    print("-" * 60)
    
    inicio = time.time()
    edges_prim, costo_prim = grafo.prim()
    tiempo_prim = time.time() - inicio
    
    print(f"Costo total del MST: {costo_prim}")
    print(f"Aristas del MST:")
    for u, v, peso in edges_prim:
        print(f"  {u} -- {v} : {peso}")
    print(f"Tiempo de ejecución: {tiempo_prim * 1000:.4f} ms")
    
    print("\n" + "-" * 60)
    print("ALGORITMO DE KRUSKAL")
    print("-" * 60)
    
    inicio = time.time()
    edges_kruskal, costo_kruskal = grafo.kruskal()
    tiempo_kruskal = time.time() - inicio
    
    print(f"Costo total del MST: {costo_kruskal}")
    print(f"Aristas del MST:")
    for u, v, peso in edges_kruskal:
        print(f"  {u} -- {v} : {peso}")
    print(f"Tiempo de ejecución: {tiempo_kruskal * 1000:.4f} ms")
    
    # COMPARACIÓN
    print("\n" + "=" * 60)
    print("COMPARACIÓN DE RESULTADOS")
    print("=" * 60)
    print(f"Costo Prim:    {costo_prim}")
    print(f"Costo Kruskal: {costo_kruskal}")
    print(f"Costos iguales: {'Sí' if costo_prim == costo_kruskal else 'No'}")
    
    print(f"\nTiempo Prim:    {tiempo_prim * 1000:.4f} ms")
    print(f"Tiempo Kruskal: {tiempo_kruskal * 1000:.4f} ms")
    
    if tiempo_prim > tiempo_kruskal:
        diferencia = ((tiempo_prim - tiempo_kruskal) / tiempo_kruskal) * 100
        print(f"Kruskal es {diferencia:.2f}% más rápido")
    else:
        diferencia = ((tiempo_kruskal - tiempo_prim) / tiempo_prim) * 100
        print(f"Prim es {diferencia:.2f}% más rápido")
    
    print("=" * 60)


if __name__ == "__main__":
    main()
