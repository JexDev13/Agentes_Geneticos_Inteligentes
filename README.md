# Agentes inteligentes con Algoritmo Genético

Este sistema multiagente implementa un algoritmo genético para encontrar la mejor configuración de parámetros en un espacio de búsqueda definido por población, evoluciones e iteraciones. Consiste en tres agentes principales: Observador, Genético y Auxiliar.

## Agentes

### Agente Observador
- **Función**: Coordina la búsqueda exhaustiva de configuraciones, enviando múltiples configuraciones al agente genético y seleccionando la mejor configuración entre las respuestas recibidas.
- **Comportamiento**: 
  - Envía múltiples configuraciones al agente genético.
  - Recibe las respuestas del agente auxiliar con los resultados y las configuraciones.
  - Selecciona la mejor configuración basada en los resultados recibidos y la envía de nuevo al agente genético.

### Agente Genético
- **Función**: Ejecuta el algoritmo genético con una configuración específica y devuelve los resultados al agente auxiliar.
- **Comportamiento**:
  - Recibe una configuración del agente observador.
  - Ejecuta el algoritmo genético con la configuración dada.
  - Envía los resultados al agente auxiliar.

### Agente Auxiliar
- **Función**: Recibe los resultados del agente genético y los envía al agente observador.
- **Comportamiento**:
  - Recibe los resultados del agente genético.
  - Envía los resultados al agente observador.

## Ejecución

1. Ejecutar el contenedor JADE con los agentes Observador, Genético y Auxiliar.
2. Los agentes se coordinarán automáticamente para realizar la búsqueda exhaustiva y encontrar la mejor configuración.
3. Los resultados finales se imprimirán en la consola del agente observador.

## Requisitos

- JADE (Java Agent DEvelopment Framework)
- JDK (Java Development Kit)

## Configuración

El número de configuraciones a enviar, así como los rangos de población, evoluciones e iteraciones, pueden ajustarse en los respectivos agentes según las necesidades del problema.

## Autor

<JexDev/13>
