import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class MusicEditor {
    public MusicEditor() {
        Dimension buttonBoxSize = new Dimension(200,200);
        Dimension accidentalsSliderSize = new Dimension(200, 600);
        JFrame frame = new JFrame("My Music Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // tell the content pane to use BorderLayout to manage children
        frame.getContentPane().setLayout(new BorderLayout());

        //ToolPanel
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, 1));
        toolPanel.setPreferredSize(new Dimension(200,600));
        JPanel toolPanelHolder = new JPanel();
        JPanel dummyPanel = new JPanel();
        toolPanelHolder.add(toolPanel);
        toolPanelHolder.add(dummyPanel);

        // Select and Pen Buttons
        Box selectPenBox = new Box(0);
        selectPenBox.setPreferredSize(buttonBoxSize);
        JButton penButton = new JButton("Pen");
        JButton selectButton = new JButton("Select");
        selectPenBox.add(selectButton);
        selectPenBox.add(penButton);


        //New Staff and Delete Staff
        Box editStaffBox = new Box(0);
        editStaffBox.setPreferredSize(buttonBoxSize);
        JButton addStaffButton = new JButton("Add Staff");
        JButton deleteStaffButton = new JButton("Delete Staff");
        editStaffBox.add(addStaffButton);
        editStaffBox.add(deleteStaffButton);


        //Play and Stop
        Box playStopBox = new Box(0);
        playStopBox.setPreferredSize(buttonBoxSize);
        JButton playButton = new JButton("Play");
        JButton stopButton = new JButton("Stop");
        playStopBox.add(playButton);
        playStopBox.add(stopButton);

        //Accidentals
        Box accidentalsBox = new Box(1);
        JRadioButton noteButton = new JRadioButton("Note");
        noteButton.setSelected(true);

        JRadioButton restButton = new JRadioButton("Rest");
        JRadioButton flatButton = new JRadioButton("Flat");
        JRadioButton sharpButton = new JRadioButton("Sharp");

        ButtonGroup accidentalsGroup = new ButtonGroup();
        accidentalsGroup.add(noteButton);
        accidentalsGroup.add(restButton);
        accidentalsGroup.add(flatButton);
        accidentalsGroup.add(sharpButton);

        accidentalsBox.add(noteButton);
        accidentalsBox.add(restButton);
        accidentalsBox.add(flatButton);
        accidentalsBox.add(sharpButton);

        //Note Type slider
        Box noteTypeBox = new Box(1);
        JSlider noteTypeSlider = new JSlider(JSlider.VERTICAL, 0, 100, 100);
        noteTypeSlider.setMajorTickSpacing(25);
        noteTypeSlider.setSnapToTicks(true);
        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(100), new JLabel("Sixteenth"));
        labelTable.put(new Integer(75), new JLabel("Eighth"));
        labelTable.put(new Integer(50), new JLabel("Quarter"));
        labelTable.put(new Integer(25), new JLabel("Half"));
        labelTable.put(new Integer(0), new JLabel("Whole"));
        noteTypeSlider.setLabelTable(labelTable);
        noteTypeSlider.setPaintLabels(true);
        noteTypeBox.add(noteTypeSlider);






        toolPanel.add(selectPenBox);
        toolPanel.add(new JSeparator());
        toolPanel.add(editStaffBox);
        toolPanel.add(new JSeparator());
        toolPanel.add(playStopBox);
        toolPanel.add(new JSeparator());

        Box accidentalsSlider = new Box(0);
        accidentalsSlider.setPreferredSize(accidentalsSliderSize);
        accidentalsSlider.add(accidentalsBox);
        accidentalsSlider.add(noteTypeSlider);
        toolPanel.add(accidentalsSlider);




        JTextArea text = new JTextArea("This is a text area.\nYou can type into it!", 20, 40);
        text.setEditable(true);

        //Menu Bar
        JMenuBar menu = new JMenuBar();
        //File Menu
        JMenu file = new JMenu("File");
        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.exit(0);} {
            }
        });
        file.add(fileExit);
        //Edit Menu
        JMenu edit = new JMenu("Edit");
        JMenuItem editNewStaff = new JMenuItem("New Staff");
        JMenuItem editDeleteStaff = new JMenuItem("Delete Staff");
        edit.add(editNewStaff);
        edit.add(editDeleteStaff);
        //Add elements to menu
        menu.add(file);
        menu.add(edit);

        // note that when we add component, we have to indicate which they go into
        frame.getContentPane().add(menu, BorderLayout.NORTH);
        frame.getContentPane().add(toolPanelHolder, BorderLayout.WEST);
        JLabel label = new JLabel("This is a static text label");
        frame.getContentPane().add(label, BorderLayout.SOUTH);
        JButton button = new JButton("Click to close");
        // use of Lambda expressions for event listeners.
        button.addActionListener(e -> System.exit(0));
        frame.getContentPane().add(button, BorderLayout.CENTER);
        frame.pack();
        frame.show();
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MusicEditor app = new MusicEditor();
            }
        });
    }
}
