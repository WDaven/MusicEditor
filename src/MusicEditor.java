import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import static java.lang.Integer.valueOf;

public class MusicEditor {
    int staffNumber = 4;
    public MusicEditor() {
        JLabel staffText = new JLabel("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>", SwingConstants.CENTER);
        Dimension buttonBoxSize = new Dimension(200,200);
        Dimension accidentalsSliderSize = new Dimension(200, 600);
        JMenuItem editDeleteStaff = new JMenuItem("Delete Staff");

        JLabel label = new JLabel("This is a static text label");
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
        penButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Pen has been selected");
            }
        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Select has been selected");
            }
        });


        //New Staff and Delete Staff
        Box editStaffBox = new Box(0);
        editStaffBox.setPreferredSize(buttonBoxSize);
        JButton addStaffButton = new JButton("Add Staff");
        JButton deleteStaffButton = new JButton("Delete Staff");
        editStaffBox.add(addStaffButton);
        editStaffBox.add(deleteStaffButton);

        addStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Add Staff has been selected");
                staffNumber++;
                staffText.setText("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>");
                if (staffNumber == 2) {
                    editDeleteStaff.setEnabled(true);
                    deleteStaffButton.setEnabled(true);
                }
            }
        });

        deleteStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Delete Staff has been selected");
                staffNumber--;
                staffText.setText("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>");
                if (staffNumber == 1) {
                    editDeleteStaff.setEnabled(false);
                    deleteStaffButton.setEnabled(false);
                }
            }
        });


        //Play and Stop
        Box playStopBox = new Box(0);
        playStopBox.setPreferredSize(buttonBoxSize);
        JButton playButton = new JButton("Play");
        JButton stopButton = new JButton("Stop");
        playStopBox.add(playButton);
        playStopBox.add(stopButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Play has been selected");
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Stop has been selected");
            }
        });

        //Accidentals
        Box accidentalsBox = new Box(1);
        JRadioButton noteButton = new JRadioButton("Note");
        noteButton.setSelected(true);
        noteButton.addActionListener(e -> label.setText("Note has been selected"));

        JRadioButton restButton = new JRadioButton("Rest");
        restButton.addActionListener(e -> label.setText("Rest has been selected"));
        JRadioButton flatButton = new JRadioButton("Flat");
        flatButton.addActionListener(e -> label.setText("Flat has been selected"));
        JRadioButton sharpButton = new JRadioButton("Sharp");
        sharpButton.addActionListener(e -> label.setText("Sharp has been selected"));

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
        labelTable.put(100, new JLabel("Sixteenth"));
        labelTable.put(75, new JLabel("Eighth"));
        labelTable.put(50, new JLabel("Quarter"));
        labelTable.put(25, new JLabel("Half"));
        labelTable.put(0, new JLabel("Whole"));
        noteTypeSlider.setLabelTable(labelTable);
        noteTypeSlider.setPaintLabels(true);
        noteTypeSlider.addChangeListener(e -> noteTypeSlider.getValue());
        noteTypeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //gets the values from the Note Sluder, then fetches the key-value pair from the table then cast that
                //to a Jlabel then runs getText on the Jlabel and finalls sets the label text
                label.setText(((JLabel) labelTable.get(noteTypeSlider.getValue())).getText() +  " has been selected");
            }

        });

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



        //Menu Bar
        JMenuBar menu = new JMenuBar();
        //File Menu
        JMenu file = new JMenu("File");
        file.addActionListener(e -> label.setText("File has been selected"));
        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.exit(0);} {
            }
        });
        file.add(fileExit);
        //Edit Menu
        JMenu edit = new JMenu("Edit");
        edit.addActionListener(e -> label.setText("Edit has been selected"));
        JMenuItem editNewStaff = new JMenuItem("New Staff");
        editNewStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Edit Staff Add has been selected");
                staffNumber++;
                staffText.setText("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>");
                if (staffNumber == 2) {
                    editDeleteStaff.setEnabled(true);
                    deleteStaffButton.setEnabled(true);
                }
            }
        });

        editDeleteStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Edit Delete Staff has been selected");
                staffNumber--;
                staffText.setText("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>");
                if (staffNumber == 1) {
                    editDeleteStaff.setEnabled(false);
                    deleteStaffButton.setEnabled(false);
                }
            }
        });
        edit.add(editNewStaff);
        edit.add(editDeleteStaff);
        //Add elements to menu
        menu.add(file);
        menu.add(edit);

        //Staves section
        staffText.setFont(new Font("Serif", Font.PLAIN, 30));
        JScrollPane staffPane = new JScrollPane(staffText);
        staffPane.setPreferredSize(new Dimension(600,600));

        // note that when we add component, we have to indicate which they go into
        frame.getContentPane().add(menu, BorderLayout.NORTH);
        frame.getContentPane().add(toolPanelHolder, BorderLayout.WEST);
        frame.getContentPane().add(label, BorderLayout.SOUTH);
        frame.getContentPane().add(staffPane, BorderLayout.CENTER);
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


// use of Lambda expressions for event listeners.
//button.addActionListener(e -> System.exit(0));
