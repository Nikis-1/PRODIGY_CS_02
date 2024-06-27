import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.io.File;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageOperation {
    private static JFrame f;
    private static JButton button3 = new JButton("Submit");
    private static JTextField tf = new JTextField(10);
    private static JLabel l = new JLabel();
    private static JButton button = new JButton("Encrypt");
    private static JButton  button2 = new JButton("Decrypt");

    public static void operate(int key) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[fis.available()];
            fis.read(data);
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (data[i] ^ key);
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(data);
                JOptionPane.showMessageDialog(null, "Operation successful.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void performfunction() {
        l.setText("Enter Key");
        f.remove(button);
        f.remove(button2);
        f.add(l);
        f.add(tf);
        button3.addActionListener(e -> {
            String text = tf.getText();
            if (!text.isEmpty()) {
                int key = Integer.parseInt(text);
                operate(key);
                tf.setText("");
            }
            tf.setText("");
        });
        f.add(button3);
        f.revalidate();
    }

    public static void main(String[] args) {
        f = new JFrame();
        f.getContentPane().setBackground(new Color(255-255-153));
        f.setTitle("Image Operation");
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(e -> performfunction());
        button2.addActionListener(e -> performfunction());

        f.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        f.add(button);
        f.add(button2);
        f.setVisible(true);
    }
}