# Escritura rápida - Mini Proyecto #1
#### **- Autor:** Natalia Andrea Parra Peña | 202516945-3743
#### **- Curso:** Fundamentos de la programación orientada a eventos | 750014C


## Descripción:
Escritura rápida es un juego desarrollado en JavaFx en el jugador debe escribir correctamente las palabras generadas en pantalla antes de que finalice el tiempo.
Cuenta con niveles de juego donde se incrementa la dificultad de juego desafiando la rápidez de escritura.

## Objetivo:
Aplicar fundamentos de programación orientada a eventos mediante el desarrollo de una interfaz gráfica interactiva utilizando JavaFX y Scene Builder.


## Funcionalidades:
- Generación aleatoria de palabras.
- Validación exacta de escritura.
- Sistema de niveles progresivos.
- Temporizador dinámico por nivel 
- Cambio visual de la barra según tiempo restante.
- Pantalla de derrota cuando el tiempo finaliza.
- Pantalla de victoria cuando el jugador supera todos los niveles.
- Reinicio de partida.
- Retroalimentación visual para aciertos y errores.


## Tecnologías utilizadas: 
- Java 17 
- Java FX
- Scene Builder 
- Intellij IDEA 
- Css (Estilos visuales)

## Mecánica del juego:

- El jugador debe escribir correctamente la palabra mostrada.
- Cada palabra correcta aumental el nivel.
- El tiempo se reinicia tras cada acierto.
- Si el tiempo termina, el juego finaliza.
- Cada 5 niveles el tiempo disminuye para aumentar la dificultad.

## Estructura del proyecto:

src/
└── main/
    ├── java/
    │   └── np/escriturarapida/
    │       ├── Main.java
    │
    │       ├── model/
    │       │   ├── IWordManager.java
    │       │   ├── GameModel.java
    │       │   └── RandomWords.java
    │
    │       ├── controller/
    │       │   ├── GameController.java
    │       │   └── ResumeController.java
    │
    │       └── view/stages/
    │           ├── GameStage.java
    │           └── ResumeStage.java
    │
    └── resources/
        └── np/escrituraRapida/
            ├── fxml/
            │   ├── escrituraRapida-view.fxml
            │   └── resumenEscritura-view.fxml
            │
            │
            └── icons/
                ├── confirmIcon.png
                ├── exiticon.png
                ├── favicon.png
                ├── gameOverIcon.png
                ├── tryicon.png
                └── winicon.pnga 


## Ejecución:
1. Clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Configurar JavaFX SDK.
4. Ejecutar la clase Main.
