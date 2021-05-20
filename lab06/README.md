# lab06: Mundo de Wumpus

For this project we created a console game (_Mundo de Wumpus_, Wumpus' World) based on the retro game _Hunt the Wumpus (1973)_.

The game is set in a system of caves connected by tunnels. Your goal as the hero is to capture a gold chest that is hidden somewhere in the caves, but you must be careful: a dangerous monster, the Wumpus, is also lurking somewhere in the caves and will kill you if you enter his lair.
You also need to watch your step and avoid falling in one of the giant pits hidden all over the cave system.

If you're brave enough, you can also try to hunt the Wumpus: however, you were careless enough to only bring one arrow with you! You only have one shot: you must equip your arrow and, immediately after, enter the room where you think the Wumpus is hiding; you have a 50% chance of slaying the beast.

## Compiling
**Requirements**: Java JDK (preferrably v8, but older versions should be fine)


For UNIX systems, in the root of the `lab06` folder, run the following command:
```
$ javac src/mc322/*/*.java src/mc322/lab06/components/*.java -d bin/ 
```

Precompiled binaries can be found in the [bin](bin/) folder.

## Running
**Requirements**: Java JRE (preferrably v8, but older versions should be fine)

For UNIX systems, in the root of the lab06 folder, run the following command:
```
$ java -classpath bin mc322.lab06.AppMundoWumpus <path-to-csv> 
```
where `<path-to-csv>` is the path to the CSV file containing the initial configuration of the cave system. Sample CSV files can be found in the [data](data/) folder.


# Design patterns highlight

```
public abstract class Component {
    protected Cave cave;
    protected Position position;

    public Component(Cave cave, Position position) {
        this.cave = cave;
        this.position = position;
        spawnSecondaryComponents();
    }
    ...
    protected void spawnSecondaryComponents() {};

}
```
We chose this section from the `mc322.lab06.Component` class to illustrate the use of Inheritance and Polymorphism in this project: all entities in our game are derived from the abstract class `Component`, and all of them have two basic attributes (a `Position` object and a pointer to a `Cave` object) and one common method, `spawnSecondaryComponents()`, that is supposed to be overriden by derived classes and allows for entities like `Wumpus` or `Hole` to spawn `Stink` and `Breeze` entities around them. All other functionalities are implemented by the derived classes (such as the `Hero` class, that has methods to move around in the cave or to use arrows).
