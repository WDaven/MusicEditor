import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicEditor {
    public MusicEditor() {
        JFrame frame = new JFrame("My Music Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // tell the content pane to use BorderLayout to manage children
        frame.getContentPane().setLayout(new BorderLayout());

        //ToolPanel
        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, 1));

        // Select and Pen Buttons
        Box selectPenBox = new Box(0);
        JButton penButton = new JButton("Pen");
        JButton selectButton = new JButton("Select");
        selectPenBox.add(selectButton);
        selectPenBox.add(penButton);
        selectPenBox.add(new JSeparator());

        //New Staff and Delete Staff
        Box editStaffBox = new Box(0);
        JButton addStaffButton = new JButton("Add Staff");
        JButton deleteStaffButton = new JButton("Delete Staff");
        editStaffBox.add(addStaffButton);
        editStaffBox.add(deleteStaffButton);
        selectPenBox.add(new JSeparator());


        toolPanel.add(selectPenBox);
        toolPanel.add(editStaffBox);




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
        frame.getContentPane().add(toolPanel, BorderLayout.CENTER);
        JLabel label = new JLabel("This is a static text label");
        frame.getContentPane().add(label, BorderLayout.SOUTH);
        JButton button = new JButton("Click to close");
        // use of Lambda expressions for event listeners.
        button.addActionListener(e -> System.exit(0));
        frame.getContentPane().add(button, BorderLayout.EAST);
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
