import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;


public class StudentInfo extends JFrame {

    private Container c;
    private JLabel headerLabel;
    private JLabel nameLabel;
    private JLabel departmentLabel;
    private JLabel batchLabel;
    private JLabel sectionLabel;
    private JLabel idLabel;
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField batchField;
    private JTextField sectionField;
    private JTextField idField;
    private JButton submitButton;
    private JButton showInfoButton;
    
    public StudentInfo(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 50, 500, 400);
        setTitle("Project");

        initComponent();
    }

    public void initComponent(){
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.GREEN);

        headerLabel = new JLabel("Student Information Form");
        headerLabel.setBounds(75, 10, 400, 20);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        c.add(headerLabel);

        // name 
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(90, 75, 50, 20);
        c.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 75, 140, 20);
        c.add(nameField);
        
        // Id 
        idLabel = new JLabel("ID:");
        idLabel.setBounds(90, 105, 50, 20);
        c.add(idLabel);

        idField = new JTextField();
        idField.setBounds(180, 105, 140, 20);
        c.add(idField);

        // department 
        departmentLabel = new JLabel("Department:");
        departmentLabel.setBounds(90, 135, 100, 20);
        c.add(departmentLabel);

        departmentField = new JTextField();
        departmentField.setBounds(180, 135, 140, 20);
        c.add(departmentField);

        // batch 
        batchLabel = new JLabel("Batch:");
        batchLabel.setBounds(90, 165, 100, 20);
        c.add(batchLabel);

        batchField = new JTextField();
        batchField.setBounds(180, 165, 140, 20);
        c.add(batchField);
        
        // Section 
        sectionLabel = new JLabel("Section:");
        sectionLabel.setBounds(90, 195, 100, 20);
        c.add(sectionLabel);

        sectionField = new JTextField();
        sectionField.setBounds(180, 195, 140, 20);
        c.add(sectionField);

        // submit button 
        submitButton = new JButton("Submit Form");
        submitButton.setBounds(125, 250, 150, 30);
        c.add(submitButton);

        showInfoButton = new JButton("Student Info");
        showInfoButton.setBounds(125, 290, 150, 30);
        c.add(showInfoButton);

        // submit student informations
        submitButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                String name = String.format("%s", nameField.getText());
                String id = String.format("%s", idField.getText());
                String department = String.format("%s", departmentField.getText());
                String batch = String.format("%s", batchField.getText());
                String section = String.format("%s", sectionField.getText());


                CreateTextFile.openFile();
                CreateTextFile.addRecords(name, id, department, batch, section);
                CreateTextFile.closeFile();

                System.out.println(name+id+department+batch+section);
            }
        });

        // Read student informations
        showInfoButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                Date date = new Date();

                ReadTextFile.openFile();
                ReadTextFile.readRecords();
                ReadTextFile.closeFile();

                ArrayList<Student> details = ReadTextFile.getArrayList();

                ViewData viewData= new ViewData();
                DefaultTableModel model= (DefaultTableModel) viewData.jTable2.getModel();

                DefaultTableCellRenderer renderer= new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(SwingConstants.CENTER );
                viewData.jTable2.getColumnModel().getColumn(0).setCellRenderer(renderer );
                viewData.jTable2.getColumnModel().getColumn(1).setCellRenderer(renderer );
                viewData.jTable2.getColumnModel().getColumn(2).setCellRenderer(renderer );
                viewData.jTable2.getColumnModel().getColumn(3).setCellRenderer(renderer );
                viewData.jTable2.getColumnModel().getColumn(4).setCellRenderer(renderer );
                viewData.jTable2.getColumnModel().getColumn(5).setCellRenderer(renderer );

                for(Student student: details){
                    Object[] info = {student.getId(), student.getName(), student.getDepartment(), student.getBatch(), student.getSection(), date.toString()};
                    model.addRow(info);
                }

                viewData.showTable();



                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        viewData.setVisible(true);
                    }
                });
            }
        });
        // JOptionPane.showMessageDialog(null, "done");
    }

    public static void main(String[] args) {
        StudentInfo frame = new StudentInfo();
        frame.setVisible(true);
    }
}