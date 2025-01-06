import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class ControlPanel extends JPanel{

    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 100;

    private JButton startButton;
    private JButton stopButton;
    private JButton clearButton;
    private JSlider speedSlider;
    private JLabel speedLabel;

    private GridPanel gridPanelRef;

    public ControlPanel(GridPanel gridPanelRefToSet){
        // Set Control Panel options
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Create new components
        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.clearButton = new JButton("Clear");
        this.speedSlider = new JSlider(50, 1000, 500);
        this.speedLabel = new JLabel(Integer.toString(speedSlider.getValue()));

        this.gridPanelRef = gridPanelRefToSet;

        this.add(startButton);
        this.add(stopButton);
        this.add(clearButton);
        this.add(speedSlider);
        this.add(speedLabel);
        
        initComponentListeners();
    }

    public void initComponentListeners(){

        this.startButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                // I want it to use the gridPanelRef in these methods.
                gridPanelRef.start();
            }
        });

        this.stopButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                gridPanelRef.stop();
            }
        }); 

        this.clearButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                gridPanelRef.clearGrid();
            }
        });

        this.clearButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                gridPanelRef.clearGrid();
            }
            
        });

        this.speedSlider.addChangeListener(new javax.swing.event.ChangeListener(){
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent e){
                // Get newDelay
                int delayMS = speedSlider.getValue();

                // Update new delay in label and gridPanel
                speedLabel.setText(Integer.toString(delayMS));
                gridPanelRef.setRenderDelay(delayMS);
            }
        });
    }
}
