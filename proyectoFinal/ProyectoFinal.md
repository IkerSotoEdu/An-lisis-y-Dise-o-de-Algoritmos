# Reporte de Análisis Experimental de Algoritmos de Ordenamiento

**Autor:** [Iker Alejandro Soto Mata]  
**Fecha:** 26 de marzo de 2026  
**Curso:** Análisis y Diseño de Algoritmos  

## Índice

1. [Introducción](#introducción)
2. [Objetivos](#objetivos)
3. [Marco Teórico](#marco-teórico)
4. [Metodología](#metodología)
5. [Resultados](#resultados)
6. [Análisis Comparativo](#análisis-comparativo)
7. [Conclusiones](#conclusiones)
8. [Referencias](#referencias)

## Introducción

Este reporte presenta un análisis experimental de los algoritmos de ordenamiento disponibles en la plataforma VisualGo (https://visualgo.net/en/sorting). Se analiza cada algoritmo en términos de su complejidad temporal (notación Big O), se desglosa la notación, se grafica el desempeño teórico y se realiza un análisis comparativo entre todos los métodos.

Los algoritmos estudiados son: Bubble Sort, Selection Sort, Insertion Sort, Merge Sort, Quick Sort, Random Quick Sort, Counting Sort y Radix Sort.

## Objetivos

- Analizar cada algoritmo de ordenamiento presente en VisualGo.
- Desglosar la notación Big O para cada algoritmo.
- Graficar el desempeño de cada algoritmo.
- Realizar un análisis comparativo de todos los métodos.

## Marco Teórico

### Algoritmos de Ordenamiento

Los algoritmos de ordenamiento se clasifican en varios tipos basados en su complejidad y enfoque:

- **Algoritmos Cuadráticos (O(n²))**: Bubble Sort, Selection Sort, Insertion Sort. Comparan elementos adyacentes o buscan el mínimo repetidamente.
- **Algoritmos Logarítmicos (O(n log n))**: Merge Sort, Quick Sort. Dividen el problema en subproblemas más pequeños.
- **Algoritmos Lineales (O(n))**: Counting Sort, Radix Sort. Asumen restricciones en los datos para lograr eficiencia.

### Notación Big O

La notación Big O describe el comportamiento asintótico del tiempo de ejecución en el peor caso. Para algoritmos de ordenamiento:

- O(n²): Tiempo proporcional al cuadrado del número de elementos.
- O(n log n): Tiempo proporcional a n multiplicado por el logaritmo de n.
- O(n + k): Tiempo lineal más una constante dependiente del rango.

## Metodología

1. **Selección de Algoritmos**: Se identificaron los algoritmos en VisualGo.
2. **Análisis Teórico**: Se revisó la complejidad Big O de cada uno.
3. **Desglose de Big O**: Se explicó el significado y derivación.
4. **Graficación**: Se crearon gráficos teóricos de desempeño vs. tamaño de entrada.
5. **Comparación**: Se compararon los algoritmos en una tabla y gráfico.

Los datos se basan en análisis teórico, ya que VisualGo es una herramienta visual.

## Resultados

### Análisis Individual

#### Bubble Sort
- **Descripción**: Compara elementos adyacentes y los intercambia si están en orden incorrecto.
- **Big O**: O(n²)
- **Desglose**: En el peor caso, para n elementos, realiza n pasadas, cada una con hasta n comparaciones, resultando en n * n = n² operaciones.
- **Gráfico de Desempeño**:

```
Tiempo (unidades arbitrarias)
^
|         *
|       *   *
|     *       *
|   *           *
| *               *
+------------------> n
```

#### Selection Sort
- **Descripción**: Encuentra el elemento mínimo en la sublista no ordenada y lo coloca al inicio.
- **Big O**: O(n²)
- **Desglose**: Realiza n pasadas, cada una buscando el mínimo en una sublista de tamaño decreciente, totalizando sumatoria de i=1 a n de i = n(n+1)/2 ≈ n²/2.
- **Gráfico**: Similar al Bubble Sort.

#### Insertion Sort
- **Descripción**: Inserta cada elemento en la sublista ordenada.
- **Big O**: O(n²)
- **Desglose**: En peor caso, cada inserción requiere desplazar hasta n elementos, total n².
- **Gráfico**: Similar.

#### Merge Sort
- **Descripción**: Divide la lista en mitades, ordena recursivamente y combina.
- **Big O**: O(n log n)
- **Desglose**: La división toma log n niveles, cada nivel procesa n elementos, total n log n.
- **Gráfico**:

```
Tiempo
^
|     *
|   *   *
| *       *
|           *
+------------------> n
```

#### Quick Sort
- **Descripción**: Elige un pivote, particiona la lista y ordena recursivamente.
- **Big O**: O(n log n) promedio, O(n²) peor caso.
- **Desglose**: Promedio: log n niveles con n trabajo, peor caso degenera a n² si pivote malo.
- **Gráfico**: Promedio como Merge, peor como cuadráticos.

#### Random Quick Sort
- **Descripción**: Quick Sort con pivote aleatorio.
- **Big O**: O(n log n) promedio, O(n²) peor caso improbable.
- **Desglose**: La aleatoriedad reduce probabilidad de peor caso.
- **Gráfico**: Similar a Quick Sort.

#### Counting Sort
- **Descripción**: Cuenta ocurrencias de cada valor y reconstruye la lista.
- **Big O**: O(n + k), donde k es el rango.
- **Desglose**: Dos pasadas lineales: contar y acumular.
- **Gráfico**:

```
Tiempo
^
| *
|   *
|     *
|       *
+------------------> n
```

#### Radix Sort
- **Descripción**: Ordena por dígitos desde el menos significativo.
- **Big O**: O(n * d), d dígitos.
- **Desglose**: d pasadas, cada una O(n).
- **Gráfico**: Lineal si d constante.

## Análisis Comparativo

| Algoritmo       | Complejidad Mejor | Promedio | Peor | Estable | In-place |
|-----------------|-------------------|----------|------|---------|----------|
| Bubble Sort    | O(n)             | O(n²)   | O(n²)| Sí     | Sí      |
| Selection Sort | O(n²)            | O(n²)   | O(n²)| No     | Sí      |
| Insertion Sort | O(n)             | O(n²)   | O(n²)| Sí     | Sí      |
| Merge Sort     | O(n log n)       | O(n log n)| O(n log n)| Sí | No      |
| Quick Sort     | O(n log n)       | O(n log n)| O(n²)| No     | Sí      |
| Random Quick   | O(n log n)       | O(n log n)| O(n²)| No     | Sí      |
| Counting Sort  | O(n + k)         | O(n + k)| O(n + k)| Sí | No      |
| Radix Sort     | O(n * d)         | O(n * d)| O(n * d)| Sí | No      |

**Gráfico Comparativo** (Tiempo vs n, log scale):

```
Tiempo
^
| Radix/Counting
| Merge/Quick
| 
| Cuadráticos
|
+------------------> n
```

Los algoritmos O(n log n) son más eficientes para grandes n, mientras que cuadráticos son simples pero lentos.

## Conclusiones

Los algoritmos de ordenamiento varían en eficiencia. Para datos pequeños, cuadráticos son aceptables; para grandes, usar logarítmicos. Counting y Radix requieren suposiciones en datos.

## Referencias

- VisualGo: https://visualgo.net/en/sorting