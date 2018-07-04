# Pinball - Tarea CC3002

Implementación del juego pinball, sin interfaz grafica, que forma parte de la tarea 2 del curso de metodologías de diseño y programación, de la universidad de Chile.

## Implementación y patrones de diseño utilizados:
Para implementar esta tarea se utilizaron principalmente dos patrones de diseño, facade y observer. 
El paquete facade contenía las funcionales que debían ser implementadas para el controlador del juego mediante las cuales será evaluada la tarea. 
Observer sirve para que los bonus puedan ser notificados de los objetos golpeados y de cambios en la mesa para activarse cuando sea oportuno.
En el paquete controller se encuentra el controlador del juego y en el paquete logic se encuentra la lógica, es decir, el funcionamiento de los bonus, de los elementos del juego tales como los bumpers y targets y de la mesa sobre la que se juega.

##Modo de uso
Para crear un nuevo juego basta con usar el constructor de Game poniendo la cantidad de bolas con las que se quiere empezar:
```java
  int numberOfBalls=3;
  Game game=new Game(numberOfBalls);
```
Luego para efectivamente poder jugar se necesita crear una mesa y configurarla como la mesa del juego, para ambas cosas existen los siguientes metodos de Game:
```java
String name="Ipinball";
int numberOfBumpers=3;
int probPopBumper=0.5;
int numberOfSpotTargets=2;
int numberOfDropTargets=2;
Table table = game.newFullPlayableTable(name, numberOfBumpers, probPopBumper, numberOfSpotTargets, numberOfDropTargets);
game.setGameTable(table);
```
Siempre que un elemento perteneciente a la mesa del juego sea golpeado notificará a los bonus, y si aplica, el aumento de puntaje o bolas se hará automaticamente, pero para sumar puntaje al juego mediante golpes se debe necesariamente utilizar su método addScore de la siguiente manera:
```java
game.addScore(BumperOfTheTable.hit());
```