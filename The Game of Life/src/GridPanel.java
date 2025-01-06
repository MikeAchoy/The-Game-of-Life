import javax.swing.JPanel;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GridPanel extends JPanel{

    // Graphics and Matrix variables
    private final int cellSizepx = 10;
    private final  int NUMBER_CELL_COLS = 100;
    private final int NUMBER_CELL_ROWS = 100;

    // Run time window variables
    private int renderDelayMiliseconds = 1000;
    private boolean run = true;

    private LifeCell[][] lifeCells = new LifeCell[100][100];

    public GridPanel(){
        this.setBackground(Color.WHITE);
        this.initCells();
        // this.createPattern();
        renderGrid(lifeCells);
        startLifeOnGrid();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g; 
        
        // Draw each grid cell accorind to lifeCell pos
        g2d.setColor(Color.BLACK);

        for(int i = 0; i < NUMBER_CELL_ROWS; i++){
            for(int j = 0; j < NUMBER_CELL_COLS; j++){
                LifeCell currCell = lifeCells[i][j];
                if(currCell.isAlive()){
                    g2d.fill3DRect(currCell.getX(), currCell.getY(), cellSizepx, cellSizepx, false);
                }
            }
        }
    }
    
    public void renderGrid(LifeCell[][] newCells){
        this.lifeCells = newCells;
        repaint();
    }
    
    // Initializes cells, sets them to dead, and gives them their position on the grid.
    public void initCells(){
        int xDelta = 0;
        int yDelta = 0;
        for(int i = 0; i < NUMBER_CELL_ROWS; i++){
            xDelta = 0;
            for(int j = 0; j < NUMBER_CELL_COLS; j++){
                lifeCells[i][j] = new LifeCell(xDelta, yDelta);
                xDelta += cellSizepx;
            }
            yDelta += cellSizepx;
        }
    }

    public void startLifeOnGrid(){
        new Thread(() -> {
            do {
                // Create a copy of the current grid state
                LifeCell[][] newCells = new LifeCell[NUMBER_CELL_ROWS][NUMBER_CELL_COLS];
                for (int i = 0; i < NUMBER_CELL_ROWS; i++){
                    for (int j = 0; j < NUMBER_CELL_COLS; j++){
                        newCells[i][j] = new LifeCell(lifeCells[i][j].getX(), lifeCells[i][j].getY());
                        if (lifeCells[i][j].isAlive()){
                            newCells[i][j].setAlive();
                        } else{
                            newCells[i][j].setDead();
                        }
                    }
                }
    
                // Compute next generation based on current state
                for (int i = 0; i < NUMBER_CELL_ROWS; i++){
                    for (int j = 0; j < NUMBER_CELL_COLS; j++){
                        int liveNeighbors = countLiveNeighbors(lifeCells, i, j);
                        // Implements Conway's logic to determine if cell will servive.
                        if (lifeCells[i][j].isAlive()){
                            if (liveNeighbors < 2 || liveNeighbors > 3){
                                newCells[i][j].setDead(); // Underpopulation or overpopulation
                            }
                        } else {
                            if (liveNeighbors == 3){
                                newCells[i][j].setAlive(); // Reproduction
                            }
                        }
                    }
                }
    
                // Render the new generation
                renderGrid(newCells);
                
                // Wait timer
                try{
                    TimeUnit.MILLISECONDS.sleep(renderDelayMiliseconds);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
    
            } while(run);
        }).start();
    }
    
    public int countLiveNeighbors(LifeCell[][] matrix, int row, int col){
        int liveNeighbors = 0;
    
        // Iterate through the 3x3 grid around the cell, skipping the cell itself
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                // Skip the current cell
                if (i == 0 && j == 0){
                    continue;
                }
    
                int neighborRow = row + i;
                int neighborCol = col + j;
    
                // Check boundaries to avoid out-of-bounds errors
                if (neighborRow >= 0 && neighborRow < matrix.length && 
                    neighborCol >= 0 && neighborCol < matrix[0].length){
                    if (matrix[neighborRow][neighborCol].isAlive()){
                        liveNeighbors++;
                    }
                }
            }
        }
        return liveNeighbors;
    }

    public void createPattern(){
        for(int i = 0; i < NUMBER_CELL_ROWS; i++){
            for(int j = 0; j < NUMBER_CELL_COLS; j++){
                if(j % 2 == 0){
                    lifeCells[i][j].setAlive();
                }
                else{
                    lifeCells[i][j].setDead();
                }
            }
        }
    }

    public void clearGrid(){
        for(int i = 0; i < NUMBER_CELL_ROWS; i++){
            for(int j = 0; j < NUMBER_CELL_COLS; j++){
                lifeCells[i][j].setDead();
            }
        }
        repaint();
    }

    public LifeCell[][] getLifeCells(){
        return this.lifeCells;
    }

    public void setLifeCells(LifeCell[][] lifeCellsToSet){
        this.lifeCells = lifeCellsToSet;
    }

    public void toggleLifeCell(int i, int j){
        this.lifeCells[i][j].toggleCell();
        repaint();
    }

    public int getCellSizepx(){
        return this.cellSizepx;
    }

    public void start(){
        this.run = true;
        this.startLifeOnGrid();
    }

    public void stop(){
        this.run = false;
    }

    public void setRenderDelay(int delayToSetMS){
        this.renderDelayMiliseconds = delayToSetMS;
    }
}
