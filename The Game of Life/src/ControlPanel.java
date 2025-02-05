import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.LifeStructure;

import javax.swing.*;

public class ControlPanel extends JPanel{

    // Control objects for this panel.
    private final JButton startButton;
    private final JButton stopButton;
    private final JButton clearButton;
    private final JSlider speedSlider;
    private final JLabel speedLabel;
    private final JComboBox<LifeStructure> structureComboBox;

    // GridPanel reference for controlling the grid canvas.
    private final GridPanel gridPanelRef;

    // Main constructor.
    public ControlPanel(GridPanel gridPanelRefToSet){
        // Render panel width and height.
        int PANEL_WIDTH = 1000;
        int PANEL_HEIGHT = 100;
        // Set Control Panel options
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Create new components
        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.clearButton = new JButton("Clear");
        this.speedSlider = new JSlider(50, 1000, 500);
        this.speedLabel = new JLabel(Integer.toString(speedSlider.getValue()));
        this.structureComboBox = new JComboBox<>(LifeStructure.values());

        // Set girdPanel reference to ref passed in constructor.
        this.gridPanelRef = gridPanelRefToSet;

        // Add control objects to panel.
        this.add(startButton);
        this.add(stopButton);
        this.add(clearButton);
        this.add(speedSlider);
        this.add(speedLabel);
        this.add(structureComboBox);

        // Adds listener objects to control objects.
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

        this.structureComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                LifeStructure selectedStructure = (LifeStructure)structureComboBox.getSelectedItem();
                // Add this structure to gridPanel ref.
            }
        });
    }
}
