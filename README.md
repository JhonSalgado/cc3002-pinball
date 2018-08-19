# Pinball - Tarea CC3002

Implementación del juego pinball, que forma parte de la tarea 2 del curso de metodologías de diseño y programación, de la universidad de Chile.

## Implementación y patrones de diseño utilizados en el controlador:
Para implementar esta tarea se utilizaron principalmente dos patrones de diseño, facade y observer. Visitor era bastante recomendable pero no se utilizó en este caso.
El paquete facade contiene las funcionales que deben ser implementadas para el controlador del juego mediante las cuales será evaluada la tarea. 
Observer sirve para que los bonus puedan ser notificados de los objetos golpeados y de cambios en la mesa para activarse cuando sea oportuno.
En el paquete controller se encuentra el controlador del juego y en el paquete logic se encuentra la lógica, es decir, el funcionamiento de los bonus, de los elementos del juego tales como los bumpers y targets y de la mesa sobre la que se juega.

### Modo de uso
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
## GUI
En la siguiente versión de esta tarea se implementa una interfaz grafica para el juego, en el package llamado gui. Para crearla se utilizó la librería FXGL. 

### Modo de uso
Para poder jugar se debe correr PinBallApp, luego con la tecla 'N' se crea una nueva mesa y con la barra espaciadora se lanza la pelota, que saldrá desde el centro de la pantalla hacia la diagonal superior derecha. Por defecto se tienen 3 bolitas, las que pueden aumentar si se gana algun bonus. Al perder las 3 bolas el juego se acaba y para jugar de nuevo se debe volver a ejecutar. Para controlar los flippers izquierdo y derecho se usa respectivamente 'A' y 'D'. En está tarea demás se deben elegir entre ciertas features que se deben implementar, a continuación se indicarán las que posee esta implementación.

### Features Mayores:
Estado distinto: Cada vez que un Buper es mejorado, o que un target es desactivado (esto se hace automaticamente por el controlador del juego), el color del Hittable cambiará, haciendose negros los targets inactivos y tornandose de un color más claro los bumpers.

Sonido al golpe: Para cada choque que tiene la pelota contra algún hittable hay un sonido diferente, incluyendo cuando los hittables están desctivados.

### Features menores:
Flippers distintos: Cada flipper tiene su movimiento independiente del otro.

Bonus con sonido: Cuando se activa un Bonus suena, valga la redundancia, un sonido, un poco más extenso que los sonidos de los choques.
