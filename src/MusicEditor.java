import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import static java.lang.Integer.valueOf;

public class MusicEditor {
    public int staffNumber = 4;
    public MusicEditor() {
        MusicView staffPane = new MusicView(staffNumber);
        staffPane.setPreferredSize(new Dimension(825,700));
        staffPane.setSize(new Dimension(825,700));
        JScrollPane musicViewScroll = new JScrollPane(staffPane);
        //musicViewScroll.setPreferredSize(new Dimension(800,700));

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
                //staffText.setText("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>");
                if (staffNumber == 2) {
                    editDeleteStaff.setEnabled(true);
                    deleteStaffButton.setEnabled(true);
                }
                staffPane.addStaff();
                staffPane.repaint();
                if (staffNumber > 7) {
                    staffPane.setPreferredSize(new Dimension(800,700 + (staffNumber - 7) * 100));
                    musicViewScroll.setViewportView(staffPane);
                }
            }
        });

        deleteStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Delete Staff has been selected");
                staffNumber--;
                //staffText.setText("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>");
                if (staffNumber == 1) {
                    editDeleteStaff.setEnabled(false);
                    deleteStaffButton.setEnabled(false);
                }
                staffPane.deleteStaff();
                staffPane.repaint();
                if (staffNumber == 7) {
                    staffPane.setPreferredSize(new Dimension(800,700));
                    musicViewScroll.setViewportView(staffPane);
                } else if (staffNumber > 8) {
                    staffPane.setPreferredSize(new Dimension(800,700 + (staffNumber - 7) * 100));
                    musicViewScroll.setViewportView(staffPane);
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
                //staffText.setText("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>");
                if (staffNumber == 2) {
                    editDeleteStaff.setEnabled(true);
                    deleteStaffButton.setEnabled(true);
                }
                staffPane.addStaff();
                staffPane.repaint();
                if (staffNumber > 7) {
                    staffPane.setPreferredSize(new Dimension(800,700 + (staffNumber - 7) * 80));
                    staffPane.setSize(new Dimension(800,700 + (staffNumber - 7) * 80));
                    musicViewScroll.setViewportView(staffPane);
                }
            }
        });

        editDeleteStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Edit Delete Staff has been selected");
                staffNumber--;
                //staffText.setText("<html>My Music Editor<br/>Showing " + staffNumber  +" Staves</html>");
                if (staffNumber == 1) {
                    editDeleteStaff.setEnabled(false);
                    deleteStaffButton.setEnabled(false);
                }
                staffPane.deleteStaff();
                staffPane.repaint();
                if (staffNumber == 7) {
                    staffPane.setPreferredSize(new Dimension(800,700));
                    musicViewScroll.setViewportView(staffPane);
                } else if (staffNumber > 8) {
                    staffPane.setPreferredSize(new Dimension(800,700 + (staffNumber - 7) * 100));
                    musicViewScroll.setViewportView(staffPane);
                }

            }
        });
        edit.add(editNewStaff);
        edit.add(editDeleteStaff);
        //Add elements to menu
        menu.add(file);
        menu.add(edit);


        // note that when we add component, we have to indicate which they go into
        frame.getContentPane().add(menu, BorderLayout.NORTH);
        frame.getContentPane().add(toolPanelHolder, BorderLayout.WEST);
        frame.getContentPane().add(label, BorderLayout.SOUTH);
        frame.getContentPane().add(musicViewScroll, BorderLayout.CENTER);
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

class MusicView extends JComponent implements MouseListener, MouseMotionListener {
    int staffNumber;
    public MusicView(int staffNumber) {
        super();
        this.staffNumber=staffNumber;
    }
    public void addStaff() {
        staffNumber++;
    }
    public void deleteStaff() {
        staffNumber--;
    }
    ArrayList<DrawStaff> StaffList = new ArrayList<>();
    ArrayList<RestNote> NoteList = new ArrayList<>();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i =0; i < staffNumber; i++) {
            if (i + 1 < staffNumber) {
                StaffList.add(new DrawStaff(25, 25 + 90 * i, false, g));
            } else {
                StaffList.add(new DrawStaff(25, 25 + 90 * i, true, g));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        NoteList.add(new RestNote(getMousePosition().x, getMousePosition().y,  ))
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

class DrawStaff {
    int x;
    int y;
    boolean lastStaff;
    public Image trebleClefImage;
    Image commonTimeImage;
    Image flatImage;
    Image sharpImage;
    Image naturalImage;
    Image sixteenthNoteImage;
    Image eightNoteImage;
    Image quarterNoteImage;
    Image quarterRestImage;
    Image wholeNoteImage;
    Image wholeRestImage;
    Image halfNoteImage;
    Image halfRestImage;
    Image eightRestImage;
    Image sixteenthRestImage;

    {
        try {
            trebleClefImage = ImageIO.read(getClass().getResource("/images/trebleClef.png"));
            commonTimeImage = ImageIO.read(getClass().getResource("/images/commonTime.png"));

            flatImage = ImageIO.read(getClass().getResource("/images/flat.png"));
            sharpImage = ImageIO.read(getClass().getResource("/images/sharp.png"));
            naturalImage = ImageIO.read(getClass().getResource("/images/natural.png"));

            sixteenthNoteImage = ImageIO.read(getClass().getResource("/images/sixteenthNote.png"));
            eightNoteImage = ImageIO.read(getClass().getResource("/images/eighthNote.png"));
            quarterNoteImage = ImageIO.read(getClass().getResource("/images/quarterNote.png"));
            halfNoteImage = ImageIO.read(getClass().getResource("/images/halfNote.png"));
            wholeNoteImage = ImageIO.read(getClass().getResource("/images/wholeNote.png"));

            sixteenthRestImage = ImageIO.read(getClass().getResource("/images/sixteenthRest.png"));
            eightRestImage = ImageIO.read(getClass().getResource("/images/eighthRest.png"));
            quarterRestImage = ImageIO.read(getClass().getResource("/images/quarterRest.png"));
            halfRestImage = ImageIO.read(getClass().getResource("/images/halfRest.png"));
            wholeRestImage = ImageIO.read(getClass().getResource("/images/wholeRest.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public DrawStaff(){};
    public DrawStaff(int x, int y,boolean lastStaff, Graphics g ) {
        this.x = x;
        this.y = y;
        this.lastStaff = lastStaff;
        g.drawLine(x,y, x+750, y);
        g.drawLine(x,y + 15, x+750, y+15);
        g.drawLine(x,y + 30, x+750, y+30);
        g.drawLine(x,y + 45, x+750, y+45);
        g.drawLine(x,y + 60, x+750, y+60);
        g.drawLine(x,y,x,y+60);
        g.drawImage(trebleClefImage, x, y,40,60, null);
        if (lastStaff) {
            g.drawLine(x+740, y, x+740, y+60);
            g.fillRect(x+745, y, 6, 60);

        } else {
            g.drawLine(x+750, y, x+750, y+60);
        }

    }
}

class RestNote {
    int duration;
    int xLocation;
    int yLocation;
    String pitch;
    RestNote(int xLocation, int yLocation, String pitch, int duration) {
        this.duration = duration;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.pitch = pitch;
        paint(xLocation, yLocation, pitch, duration);
    }
    public void paint(int xLocation, int yLocation, String pitch, int duration) {

    }
}
