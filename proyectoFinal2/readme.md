# Algoritmos de Ordenamiento - Diagramas UML 
## Iker Alejandro Soto Mata
### Salvando el semestre

## 1. Bubble Sort

**Descripción:** Compara elementos adyacentes repetidamente e intercambia si están en orden incorrecto. Se repite hasta que la lista esté ordenada.

```mermaid
flowchart TD
    A["Inicio: array, n=length"] --> B["i = 0"]
    B --> C{"i < n-1"}
    C -->|No| Z["Fin: array ordenado"]
    C -->|Sí| D["j = 0"]
    D --> E{"j < n-i-1"}
    E -->|No| F["i = i + 1"]
    F --> C
    E -->|Sí| G{"array[j] > array[j+1]"}
    G -->|Sí| H["Intercambiar<br/>array[j] y array[j+1]"]
    G -->|No| I["j = j + 1"]
    H --> I
    I --> E
```

---

## 2. Selection Sort

**Descripción:** Divide el array en dos partes: ordenada y no ordenada. Encuentra el mínimo en la parte no ordenada y lo coloca al inicio.

```mermaid
flowchart TD
    A["Inicio: array, n=length"] --> B["i = 0"]
    B --> C{"i < n-1"}
    C -->|No| Z["Fin: array ordenado"]
    C -->|Sí| D["min_idx = i"]
    D --> E["j = i + 1"]
    E --> F{"j < n"}
    F -->|No| G{"min_idx ≠ i"}
    F -->|Sí| H{"array[j] < array[min_idx]"}
    H -->|Sí| I["min_idx = j"]
    H -->|No| J["j = j + 1"]
    I --> J
    J --> F
    G -->|Sí| K["Intercambiar<br/>array[i] y array[min_idx]"]
    G -->|No| L["i = i + 1"]
    K --> L
    L --> C
```

---

## 3. Insertion Sort

**Descripción:** Construye el array ordenado de una en una. Toma cada elemento y lo inserta en su posición correcta.

```mermaid
flowchart TD
    A["Inicio: array, n=length"] --> B["i = 1"]
    B --> C{"i < n"}
    C -->|No| Z["Fin: array ordenado"]
    C -->|Sí| D["key = array[i]"]
    D --> E["j = i - 1"]
    E --> F{"j >= 0 Y<br/>array[j] > key"}
    F -->|Sí| G["array[j+1] = array[j]"]
    G --> H["j = j - 1"]
    H --> F
    F -->|No| I["array[j+1] = key"]
    I --> J["i = i + 1"]
    J --> C
```

---

## 4. Merge Sort

**Descripción:** Divide el array recursivamente por la mitad, ordena las mitades y las fusiona.

```mermaid
flowchart TD
    A["Inicio: array, left, right"] --> B{"left < right"}
    B -->|No| Z["Fin: array ordenado"]
    B -->|Sí| C["mid = (left + right) / 2"]
    C --> D["MergeSort(array, left, mid)"]
    D --> E["MergeSort(array, mid+1, right)"]
    E --> F["Merge(array, left, mid, right)"]
    F --> G["Combinar dos subarrays<br/>ordenados"]
    G --> Z
```

---

## 5. Quick Sort

**Descripción:** Selecciona un pivote, particiona el array en menores y mayores, y ordena recursivamente.

```mermaid
flowchart TD
    A["Inicio: array, low, high"] --> B{"low < high"}
    B -->|No| Z["Fin: array ordenado"]
    B -->|Sí| C["pi = Partition(array, low, high)"]
    C --> D["QuickSort(array, low, pi-1)"]
    D --> E["QuickSort(array, pi+1, high)"]
    E --> Z
    F["Partition: elegir pivote<br/>y dividir"] -.->|parte de| C
```

---

## 6. Random Quick Sort

**Descripción:** Similar a Quick Sort pero elige el pivote de forma aleatoria para evitar peor caso.

```mermaid
flowchart TD
    A["Inicio: array, low, high"] --> B{"low < high"}
    B -->|No| Z["Fin: array ordenado"]
    B -->|Sí| C["random_idx = Random(low, high)"]
    C --> D["Intercambiar<br/>array[random_idx] y array[high]"]
    D --> E["pi = Partition(array, low, high)"]
    E --> F["RandomQuickSort(array, low, pi-1)"]
    F --> G["RandomQuickSort(array, pi+1, high)"]
    G --> Z
```

---

## 7. Counting Sort

**Descripción:** Cuenta la frecuencia de cada elemento y reconstruye el array ordenado.

```mermaid
flowchart TD
    A["Inicio: array"] --> B["max = elemento máximo<br/>min = elemento mínimo"]
    B --> C["range = max - min + 1"]
    C --> D["Crear array count[range] = 0"]
    D --> E["Contar frecuencias<br/>de cada elemento"]
    E --> F["Crear array acumulativo"]
    F --> G["Recorrer array original<br/>y colocar en salida"]
    G --> Z["Fin: array ordenado"]
```

---


