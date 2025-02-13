import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import utils.LifeStructure;

public class Window extends JFrame {

    private final GridPanel gridPanel;

    static final int WINDOW_HEIGHT = 1000;
    static final int WINDOW_WIDTH = 1000;

    public Window(){
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        //  Maybe make it resizeable?
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        gridPanel = new GridPanel();
        ControlPanel controlPanel = new ControlPanel(gridPanel);

        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                // Get the x and y coordinates of click.
                int x = e.getX();
                int y = e.getY();

                // Calculate row and col for cell block.
                int col = x / gridPanel.getCellSize();  // j
                int row = y / gridPanel.getCellSize();  // i

                LifeStructure structureToToggle = controlPanel.getSelectedStructure();

                // TODO: Finish implementing all structures types.
                // Control flow for structures to toggle on grid.
                // Toggle cells according to x, y as center
                switch (structureToToggle){
                    case BLOCK:
                        // Row is y, col is x.
                        gridPanel.toggleLifeCell(row, col);
                        gridPanel.toggleLifeCell(row, col - 1);
                        gridPanel.toggleLifeCell(row - 1, col);
                        gridPanel.toggleLifeCell(row - 1, col - 1);
                        break;

                    case BEEHIVE:
                        gridPanel.toggleLifeCell(row - 1, col);
                        gridPanel.toggleLifeCell(row - 1, col - 1);
                        gridPanel.toggleLifeCell(row, col + 1);
                        gridPanel.toggleLifeCell(row, col - 2);
                        gridPanel.toggleLifeCell(row - 1, col);
                        gridPanel.toggleLifeCell(row - 1, col - 1);
                        break;

                    case TUB:
                        gridPanel.toggleLifeCell(row - 1, col);
                        gridPanel.toggleLifeCell(row + 1, col);
                        gridPanel.toggleLifeCell(row, col - 1);
                        gridPanel.toggleLifeCell(row, col + 1);
                        break;

                    case BLINKER:
                        gridPanel.toggleLifeCell(row , col);
                        gridPanel.toggleLifeCell(row + 1, col);
                        gridPanel.toggleLifeCell(row - 1, col);
                        break;

                    case TOAD:
                        gridPanel.toggleLifeCell(row , col);
                        gridPanel.toggleLifeCell(row , col + 1);
                        gridPanel.toggleLifeCell(row , col + 2);
                        gridPanel.toggleLifeCell(row - 1 , col);
                        gridPanel.toggleLifeCell(row - 1, col + 1);
                        gridPanel.toggleLifeCell(row - 1, col - 1);
                        break;

                    case BEACON:
                        gridPanel.toggleLifeCell(row , col + 1);
                        gridPanel.toggleLifeCell(row - 1, col + 1);
                        gridPanel.toggleLifeCell(row - 1, col);
                        gridPanel.toggleLifeCell(row + 1, col - 2);
                        gridPanel.toggleLifeCell(row + 2, col - 2);
                        gridPanel.toggleLifeCell(row + 2, col - 1);
                        break;

                    case GLIDER:
                        gridPanel.toggleLifeCell(row, col + 1);
                        gridPanel.toggleLifeCell(row - 1, col);
                        gridPanel.toggleLifeCell(row +  1, col);
                        gridPanel.toggleLifeCell(row + 1, col + 1);
                        gridPanel.toggleLifeCell(row + 1, col - 1);
                        break;

                    default:
                        gridPanel.toggleLifeCell(row, col);
                        break;
                }
            }
        });
        
        // Add panel here.
        controlPanel.setPreferredSize(new Dimension(1000, 100));
        this.gridPanel.setPreferredSize(new Dimension(1000, 1000));

        this.add(controlPanel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.SOUTH);

        this.pack();
    }

    public void setWindowVisible(){
        this.setVisible(true);
    }
}
