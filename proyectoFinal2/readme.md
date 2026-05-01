# Algoritmos de Ordenamiento - Visualgo.net

Este documento contiene la explicación y los diagramas de actividad UML para los 7 algoritmos de ordenamiento fundamentales.

---

## 1. Bubble Sort (Ordenamiento de Burbuja)
Compara elementos adyacentes y los intercambia si están en el orden incorrecto. El proceso se repite hasta que el arreglo está ordenado.

```mermaid
graph TD
    A([Inicio]) --> B[i = 0]
    B --> C{i < n-1?}
    C -- Sí --> D[j = 0]
    D --> E{j < n-i-1?}
    E -- Sí --> F{A[j] > A[j+1]?}
    F -- Sí --> G[Intercambiar A[j] y A[j+1]]
    G --> H[j++]
    F -- No --> H
    H --> E
    E -- No --> I[i++]
    I --> C
    C -- No --> J([Fin])


    graph TD
    A([Inicio]) --> B[i = 0]
    B --> C{i < n-1?}
    C -- Sí --> D[min_idx = i]
    D --> E[j = i + 1]
    E --> F{j < n?}
    F -- Sí --> G{A[j] < A[min_idx]?}
    G -- Sí --> H[min_idx = j]
    H --> I[j++]
    G -- No --> I
    I --> F
    F -- No --> J[Intercambiar A[i] y A[min_idx]]
    J --> K[i++]
    K --> C
    C -- No --> L([Fin])

    graph TD
    A([Inicio]) --> B[i = 1]
    B --> C{i < n?}
    C -- Sí --> D[clave = A[i]]
    D --> E[j = i - 1]
    E --> F{j >= 0 Y A[j] > clave?}
    F -- Sí --> G[A[j+1] = A[j]]
    G --> H[j = j - 1]
    H --> F
    F -- No --> I[A[j+1] = clave]
    I --> J[i++]
    J --> C
    C -- No --> K([Fin])

    graph TD
    A([Inicio: MergeSort]) --> B{¿Tamaño > 1?}
    B -- Sí --> C[Dividir arreglo a la mitad]
    C --> D[Llamar MergeSort en mitad izquierda]
    D --> E[Llamar MergeSort en mitad derecha]
    E --> F[Mezclar ambas mitades ordenadamente]
    F --> G([Fin])
    B -- No --> G

    graph TD
    A([Inicio: QuickSort]) --> B{¿bajo < alto?}
    B -- Sí --> C[Elegir Pivote]
    C --> D[Particionar arreglo alrededor del pivote]
    D --> E[Llamar QuickSort en sub-arreglo izquierdo]
    E --> F[Llamar QuickSort en sub-arreglo derecho]
    F --> G([Fin])
    B -- No --> G

    graph TD
    A([Inicio: Random QuickSort]) --> B{¿bajo < alto?}
    B -- Sí --> C[Seleccionar índice aleatorio k]
    C --> D[Intercambiar A[k] con A[alto]]
    D --> E[Ejecutar Partición estándar]
    E --> F[Recursión izquierda]
    F --> G[Recursión derecha]
    G --> H([Fin])
    B -- No --> H

    graph TD
    A([Inicio]) --> B[Encontrar valor máximo K]
    B --> C[Crear arreglo de conteo C de tamaño K+1 con 0s]
    C --> D[Contar frecuencias: C[A[i]]++]
    D --> E[Calcular posiciones: Suma acumulada en C]
    E --> F[Construir arreglo de salida usando C]
    F --> G([Fin])