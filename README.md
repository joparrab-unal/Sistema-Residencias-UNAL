# Sistema-Residencias-UNAL
Descripcion
Este proyecto implementa un sistema para la asignaciopn prioritarioa de cupos en residencias universitarias de la Universidad Nacional de Colombia, basado en el puntaje socioeconomico de los estudiantes. Amenir puntaje mayor prioridad de la asignacion del cupo. El sistema permite registar estudiantes, aignar cupos disponibles, concultar la informacion por ID y actualizar  puntajes en orden de prioridad.

Integrantes

- Junior José Ortega Herrera 
- Johann Andrés Parra Buitrago
- Sergio Quiroga Sandoval

## Estructuras de Datos Implementadas


- Árbol AVL  `ArbolAVLPrioridad.java`: Ordenar estudiantes por puntaje. El de menor puntaje (mayor prioridad) siempre está accesible en O(log n).
- Árbol BST `ArbolBusquedaID.java`: Buscar, insertar y eliminar estudiantes por ID en O(log n) promedio. 
- Cola `ColaEstudiantes.java` Almacenar las listas finales de estudiantes asignados y en lista de espera con inserción O(1). 

## Funcionalidades del Sistema (`java Main`)

- Registrar estudiante: inserta en ambos árboles (AVL por puntaje, BST por ID).
- Consultar por ID: búsqueda eficiente en el BST.
- Eliminar estudiante: eliminación sincronizada en ambos árboles.
- Asignar cupos: extrae los N estudiantes con menor puntaje del AVL y los encola.
- Generar datos aleatorios: crea N estudiantes para demostración.

## Pruebas de Rendimiento (`java Benchmarks`)

Las pruebas de rendimiento se ejecutan en un programa separado que no interfiere con la solución del proyecto. Mide:
- Inserción de N estudiantes en ambos árboles.
- Búsqueda por ID (promediada sobre 10 ejecuciones para estabilidad).
- Eliminación de estudiantes (promediada sobre 10 ejecuciones).
- Asignación masiva de N/2 cupos.

Los tamaños de prueba son N = 10,000 / 50,000 / 100,000.


Lenguajes Usados
Java


