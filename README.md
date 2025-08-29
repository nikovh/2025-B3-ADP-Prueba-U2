# Proyecto de Pruebas Automatizadas con Java y Maven

Este proyecto es una implementación de una calculadora simple desarrollada en Java, utilizada como caso de estudio para la configuración de un entorno de pruebas automatizadas.

El objetivo principal es demostrar la configuración de un repositorio Git, la gestión de dependencias y ciclo de vida con Maven, la implementación de pruebas unitarias con JUnit 5 y la creación de un pipeline de Integración Continua (CI) con GitHub Actions.


##  Prerrequisitos

Para compilar y ejecutar este proyecto localmente, necesitarás tener instalado:

*   **Java Development Kit (JDK)** - Versión 11 o superior.
*   **Apache Maven** - Versión 3.8 o superior.
*   **Git** - Para clonar el repositorio.

##  Configuración del Entorno Local

Sigue estos pasos para tener una copia del proyecto funcionando en tu máquina.

1.  **Clonar el repositorio:**
    
    git clone https://github.com/nikovh/2025-B3-ADP-Prueba-U2.git
    

2.  **Navegar al directorio del proyecto:**

    cd 2025-B3-ADP-Prueba-U2


##  Compilar y Ejecutar Pruebas

El proyecto utiliza Maven para gestionar el ciclo de vida de la construcción y la ejecución de pruebas.

1.  **Navegar a la carpeta del proyecto Maven:**
    *Asegurarse de estar dentro de la subcarpeta que contiene el archivo `pom.xml`.*
    
    cd proyecto-java


2.  **Compilar el código y ejecutar las pruebas unitarias:**
    *Este comando compila el código fuente, ejecuta todas las pruebas definidas en `src/test/java` y empaqueta el resultado en un archivo `.jar` dentro de la carpeta `target`.*
    
    mvn clean package
    
    Si todas las pruebas son exitosas, verás un mensaje de `[INFO] BUILD SUCCESS` en la consola.


##  Pipeline de Integración Continua (CI)

Este repositorio está configurado con un pipeline de GitHub Actions que se ejecuta automáticamente en cada `push` a las ramas `main` y `develop`.

El pipeline realiza las siguientes tareas:
1.  Configura un entorno de ejecución con Java 11.
2.  Compila el código fuente.
3.  Ejecuta el set completo de pruebas unitarias.
4.  Publica los reportes de prueba como un artefacto descargable para su revisión.