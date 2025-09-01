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


## El Flujo de Integración Continua (CI)

El archivo `.github/workflows/ci.yml` define un pipeline que ejecuta los siguientes pasos en secuencia:

1.  **Activación (Trigger):** El flujo de trabajo se inicia con un `push` o `pull_request` a las ramas `main` o `develop`.
2.  **Checkout del Código:** Se clona la versión más reciente del repositorio.
3.  **Configuración del Entorno:** Se instala y configura el JDK 11 y Maven con caché para acelerar las ejecuciones futuras.
4.  **Compilación y Pruebas:** Se ejecuta el comando `mvn clean verify`. Este comando se encarga de:
    *   Limpiar compilaciones anteriores.
    *   Compilar el código fuente.
    *   Ejecutar las **pruebas unitarias** de JUnit.
    *   Ejecutar las **pruebas BDD** de Cucumber.
    *   Generar los reportes XML de Surefire y los reportes JSON/HTML de Cucumber.
    *   Empaquetar la aplicación en un archivo `.jar`.
5.  **Pruebas de Rendimiento:** Se ejecuta el script de k6 para simular la carga y se exporta un resumen en formato JSON.
6.  **Generación del Dashboard:** Un script de Python lee todos los reportes generados (JUnit, Cucumber, k6) y construye un dashboard en formato Markdown que se publica en el resumen del job.
7.  **Publicación de Artefactos:** Se guardan todos los reportes detallados (Surefire, Cucumber HTML, k6 JSON) y el archivo `.jar` compilado como artefactos descargables para un análisis posterior.
8.  **Notificación de Fallos:** Si cualquiera de los pasos anteriores falla, se activa un paso final que envía una alerta detallada al canal de Slack configurado.


## Resultados y Reportes

El pipeline proporciona visibilidad de la calidad del código a través de varios mecanismos.

### Dashboard del Pipeline

El resultado más inmediato es el dashboard generado en el resumen de cada ejecución de GitHub Actions. Ofrece una vista consolidada del estado de la aplicación.

![Dashboard del Pipeline](https://i.imgur.com/55SOFZi.png)

### Artefactos Detallados

Para un análisis más profundo, los siguientes reportes se pueden descargar desde la sección "Artifacts" de la ejecución:

*   **`surefire-reports`:** Contiene los resultados de las pruebas unitarias en formato XML.
*   **`cucumber-reports`:** Incluye el reporte HTML navegable de Cucumber, ideal para que stakeholders no técnicos revisen los escenarios ejecutados.
*   **`k6-summary`:** El reporte JSON con todas las métricas detalladas de la prueba de rendimiento.
*   **`app-jar`:** El archivo `.jar` ejecutable de la aplicación.


### Alertas Automáticas

En caso de un fallo, se envía una notificación proactiva a Slack, permitiendo al equipo identificar y solucionar el problema rápidamente.


## Cómo Ejecutar Localmente

Para ejecutar las pruebas en un entorno local, asegúrate de tener los siguientes prerrequisitos:

*   Java 11 (JDK)
*   Apache Maven
*   k6 (Opcional, para las pruebas de rendimiento)

Ejecuta el siguiente comando desde la raíz del repositorio para correr las pruebas unitarias y de BDD:

mvn -B -ntp clean verify --file proyecto-java/pom.xml
