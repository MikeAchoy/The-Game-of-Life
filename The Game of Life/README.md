# The Game of Life

![Game Of Life GIF](README_files/GOF.gif)

## Getting Started
This is a custom implementation of Conway's Game of Life with additional features. Unlike the traditional infinite grid, this version acts as a sandbox where cells remain within the canvas boundaries. It also includes a structure placement system, allowing users to select premade patterns from a dropdown and place them onto the grid by clicking.

## Features
- `Sandbox Mode`: The simulation is constrained to a fixed canvas, preventing cells from escaping.
- `Structure Placement`: Select from a list of pre-made structures and place them on the grid with a click.
- `Interactive UI`: Includes control panels for simulation management.

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to create your own structures go to `src/utils/LifeStructure.java` and add your structure name to the enum. Next, add your structure type from the enum to the switch in the MouseListener for the gridPanel inside the Window constructor in `src/Window.java`. Inside you implement which LifeCells you want to toggle based on where the user clicked: `gridPanel.toggleLifeCell(row, col);`

Example:
```java
int x = e.getX();
int y = e.getY();

// Calculate row and col for cell block.
int col = x / gridPanel.getCellSize();  // j
int row = y / gridPanel.getCellSize();  // i

LifeStructure structureToToggle = controlPanel.getSelectedStructure();

switch (structureToToggle){
    case BLOCK:
        // Row is y, col is x.
        gridPanel.toggleLifeCell(row, col);
        gridPanel.toggleLifeCell(row, col - 1);
        gridPanel.toggleLifeCell(row - 1, col);
        gridPanel.toggleLifeCell(row - 1, col - 1);
        break;
```

## How to Run
### Prerequisites

- Java Development Kit (JDK) 17 or later
- A Java-compatible IDE (optional) or terminal access

###  Compiling the Project

1. Open a terminal and navigate to the src directory:

`cd "The Game of Life/src"`

2. Compile the project using javac:

`javac -d ../out utils/*.java Main.java`

This will compile all Java files and place the output in the out directory.

### Running the Project

1. Navigate to the out directory:

`cd ../out`

2. Run the application:
`java Main`