import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Window extends JFrame {

    private GridPanel gridPanel;
    private ControlPanel controlPanel;

    static final int WINDOW_HEIGHT = 1000;
    static final int WINDOW_WIDTH = 1000;

    public Window(){
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        gridPanel = new GridPanel();
        this.controlPanel = new ControlPanel(gridPanel);

        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                // Get the x and y coordinates of click
                int x = e.getX();
                int y = e.getY();

                // Calculate row and col for cell block based on click coords
                int col = x / gridPanel.getCellSizepx();  // j
                int row = y / gridPanel.getCellSizepx();  // i

                // Toggle cell at that location
                gridPanel.toggleLifeCell(row, col);
            }
        });
        
        // Add panel here.
        this.controlPanel.setPreferredSize(new Dimension(1000, 100));
        this.gridPanel.setPreferredSize(new Dimension(1000, 1000));

        this.add(controlPanel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.SOUTH);

        this.pack();
    }

    public void setWindowVisible(){
        this.setVisible(true);
    }
}
