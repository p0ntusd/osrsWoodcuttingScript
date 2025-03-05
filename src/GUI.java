import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class GUI extends JFrame implements ActionListener {
    private Container container;
    private JLabel title;
    private JLabel fishingTypeLabel ;
    private JComboBox<String> fishingTypeCombo;
    private JLabel inventoryToKeepLabel;
    private JTextField inventoryToKeepTextField;
    private JLabel fishingSpotLabel;
    private JTextField fishingSpotTextfField;
    private JLabel fishgCaughtLabel;
    private JTextField fishCaughtTextfField;
    private JLabel fishingSpeedLabel;

    private JButton submitButton;
    private JRadioButton fastRadioButton, mediumRadioButton, slowRadioButton;
    private ButtonGroup speedButtonGroup;
    private JLabel baitIDLabel;
    private JTextField baitIDTextField;

    public GUI() {
        //Setting up basic GUI objects
        setTitle("Combat Utility Window");
        setBounds(400, 90, 700, 700);
        setBackground(Color.blue);
        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Woodcutter 1.0");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(400, 50);
        title.setLocation(150, 30);
        container.add(title);

        //fishing type objects
//        fishingTypeLabel = new JLabel("Select Type of Fishing:");
//        fishingTypeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
//        fishingTypeLabel.setSize(250, 30);
//        fishingTypeLabel.setLocation(50, 100);
//        container.add(fishingTypeLabel);

//        fishingTypeCombo = new JComboBox<>(FISH_OPTIONS);
//        fishingTypeCombo.setFont(new Font("Arial", Font.PLAIN, 15));
//        fishingTypeCombo.setSize(350, 30);
//        fishingTypeCombo.setLocation(300, 100);
//        container.add(fishingTypeCombo);

        //inventory objects
        inventoryToKeepLabel = new JLabel("Items to keep in inventory: ");
        inventoryToKeepLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        inventoryToKeepLabel.setSize(250, 30);
        inventoryToKeepLabel.setLocation(50, 150);
        container.add(inventoryToKeepLabel);

        inventoryToKeepTextField = new JTextField();
        inventoryToKeepTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        inventoryToKeepTextField.setSize(350, 30);
        inventoryToKeepTextField.setLocation(300, 150);
        container.add(inventoryToKeepTextField);

        //fishing spot objects
        fishingSpotLabel = new JLabel("Enter TREE IDS:");
        fishingSpotLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        fishingSpotLabel.setSize(250, 30);
        fishingSpotLabel.setLocation(50, 200);
        container.add(fishingSpotLabel);

        fishingSpotTextfField = new JTextField();
        fishingSpotTextfField.setFont(new Font("Arial", Font.PLAIN, 15));
        fishingSpotTextfField.setSize(350, 30);
        fishingSpotTextfField.setLocation(300, 200);
        container.add(fishingSpotTextfField);

        //fish caught objects
        fishgCaughtLabel = new JLabel("Logs cut ID:");
        fishgCaughtLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        fishgCaughtLabel.setSize(250, 30);
        fishgCaughtLabel.setLocation(50, 250);
        container.add(fishgCaughtLabel);

        fishCaughtTextfField = new JTextField();
        fishCaughtTextfField.setFont(new Font("Arial", Font.PLAIN, 15));
        fishCaughtTextfField.setSize(350, 30);
        fishCaughtTextfField.setLocation(300, 250);
        container.add(fishCaughtTextfField);


        //Fishing speed objects (radio buttons)
        fishingSpeedLabel = new JLabel("Woodcutting Speed:");
        fishingSpeedLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        fishingSpeedLabel.setSize(200, 30);
        fishingSpeedLabel.setLocation(50, 300);
        container.add(fishingSpeedLabel);

        slowRadioButton = new JRadioButton("Slow");
        slowRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
        slowRadioButton.setSize(70, 30);
        slowRadioButton.setLocation(270, 300);
        slowRadioButton.setSelected(false);
        container.add(slowRadioButton);

        fastRadioButton = new JRadioButton("Medium");
        fastRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
        fastRadioButton.setSize(80, 30);
        fastRadioButton.setLocation(360, 300);
        fastRadioButton.setSelected(false);
        container.add(fastRadioButton);

        mediumRadioButton = new JRadioButton("Fast", true);
        mediumRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
        mediumRadioButton.setSize(80, 30);
        mediumRadioButton.setLocation(500, 300);
        mediumRadioButton.setSelected(true);
        container.add(mediumRadioButton);

        speedButtonGroup = new ButtonGroup();
        speedButtonGroup.add(slowRadioButton);
        speedButtonGroup.add(mediumRadioButton);
        speedButtonGroup.add(fastRadioButton);

//        //bait id objects
//        baitIDLabel = new JLabel("Bait ID:");
//        baitIDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
//        baitIDLabel.setSize(200, 30);
//        baitIDLabel.setLocation(50, 350);
//        container.add(baitIDLabel);

        baitIDTextField = new JTextField();
        baitIDTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        baitIDTextField.setSize(350, 30);
        baitIDTextField.setLocation(300, 350);
        baitIDTextField.setEditable(false);
        container.add(baitIDTextField);

        //button object
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 25));
        submitButton.setSize(200, 50);
        submitButton.setLocation(150, 580);
        submitButton.addActionListener(this);
        container.add(submitButton);

        //setting the baitID to editable if appropriate
//        fishingTypeCombo.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if(fishingTypeCombo.getSelectedItem().toString().equals("Bait") ||
//                        fishingTypeCombo.getSelectedItem().toString().equals("Lure") ||
//                        fishingTypeCombo.getSelectedItem().toString().equals("Use-rod"))
//                    baitIDTextField.setEditable(true);
//                else
//                    baitIDTextField.setEditable(false);
//            }
//        });
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
            try {
                //initializes Fishing type, inventory to keep, fishing spots and fish caught (to drop)
//                Woodcutter.WOODCUTTING_TYPE = fishingTypeCombo.getSelectedItem().toString();
                Woodcutter.INVENTORY_TO_KEEP = Arrays.stream(inventoryToKeepTextField.getText().split(",")).mapToInt(Integer::parseInt).toArray();
                Woodcutter.TREE_IDS = Arrays.stream(fishingSpotTextfField.getText().split(",")).mapToInt(Integer::parseInt).toArray();
                Woodcutter.WOOD_CUT_IDS = Arrays.stream(fishCaughtTextfField.getText().split(",")).mapToInt(Integer::parseInt).toArray();

                //initializing fishing speed accordingly
                if(fastRadioButton.isSelected())
                    Woodcutter.WOODCUTTING_SPEED= 0.3;
                else if(slowRadioButton.isSelected())
                    Woodcutter.WOODCUTTING_SPEED=  3;

            }catch(Exception ex){
                System.out.print(ex);
                System.out.print("Invalid Input into GUI");
                //System.exit(1);
            }
            setVisible(false);
            dispose();
        }
    }
}




