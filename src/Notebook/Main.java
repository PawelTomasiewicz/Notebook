package Notebook;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * @author Paweł Tomasiewicz
 */

public class Main extends JFrame
{
    JTextArea textArea;
    JScrollPane scroll;
    JButton button1;
    JButton button2;
    JButton button3;

    public Main()
    {
        super("Notatnik");

        // building the frame

        this.setDefaultCloseOperation(3);
        this.setBounds(35, 20, 1300, 740);

        // loading the picture to the background

        JPanel obrazPanel = new Picture();
        this.getContentPane().add(obrazPanel);

        // creating the components and setting their parameters

        button1 = new JButton("Zapisz");
        button2 = new JButton("Wczytaj");
        button3 = new JButton("Wyczyść");

        textArea = new JTextArea();
        textArea.setRows(12);
        textArea.setColumns(110);
        textArea.setWrapStyleWord(true);

        // adding components to the panel

        obrazPanel.add(textArea);
        scroll = new JScrollPane(textArea);
        obrazPanel.add(scroll);
        obrazPanel.add(button1);
        obrazPanel.add(button2);
        obrazPanel.add(button3);

        // adding actionListeners to buttons with commends

        button1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String filePath = "file.txt";
                String save = textArea.getText();
                FileWriter fileWriter = null;

                // saving a text from the textArea to the new file "file.txt"

                try
                {
                    fileWriter = new FileWriter(filePath);
                    fileWriter.write(save);

                    if (fileWriter != null)
                    {
                        fileWriter.close();
                    }
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String filePath = "file.txt";
                BufferedReader fileReader = null;

                // loading a text from the file and displaying on textArea

                try
                {
                    fileReader = new BufferedReader(new FileReader(filePath));
                    String textLine = fileReader.readLine();

                    textArea.setText(textLine);

                    if (fileReader != null)
                    {
                        fileReader.close();
                    }
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // clearing a text from the textArea

                if(!textArea.equals(" "))
                {
                    textArea.setText(" ");
                }
            }
        });
    }

    public class Picture extends JPanel
    {
        private BufferedImage netImage;
        private BufferedImage diskImage;

        public Picture()
        {
            super();

            try
            {
                // creating the URL address to image

                URL imageURL = new URL("http://www.polankatravel.com/content/oferty/1369051529-3.jpg");

                // reading the URL address

                netImage = ImageIO.read(imageURL);

                // creating a new file on hard disk

                File imageFile = new File("java.jpg");

                // saving the picture to the file

                ImageIO.write(netImage, "png", imageFile);

                // reading the picture from the file

                diskImage = ImageIO.read(imageFile);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D) g;

            // painting the picture

            g2d.drawImage(diskImage, 0, 0, this);
        }
    }

    public static void main(String[] args)
    {
        new Main().setVisible(true);
    }
}