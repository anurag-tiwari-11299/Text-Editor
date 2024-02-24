/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.text_editor;

// @author anurag tiwari

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Text_Editor implements ActionListener{
    // declaring properties of Text Editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    // file items
    JMenuItem newf, openf, savef;
    // edit items
    JMenuItem cut, copy, paste, select, close;
    
    JTextArea textArea;
    
    // constructor
    Text_Editor(){
        // initialize a frame
        frame = new JFrame();
        // initialize a menuBar
        menuBar = new JMenuBar();
        // initialize a textArea
        textArea = new JTextArea();
        
        // initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        
        // initialize a file menu
        newf = new JMenuItem("New");
        openf = new JMenuItem("Open");
        savef = new JMenuItem("Save");
        
        // add actionListener to file menuItems
        // here "this" to Text_Editor class so it behave as ActionListener
        newf.addActionListener(this);
        openf.addActionListener(this);
        savef.addActionListener(this);
        
        // add menuItems to file
        file.add(newf);
        file.add(openf);
        file.add(savef);
        
        // initialize an edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        select = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        
        // add actionListener to edit menuItems
        // here "this" to Text_Editor class so it behave as ActionListener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        select.addActionListener(this);
        close.addActionListener(this);
        
        // add menuItems to edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(select);
        edit.add(close);
        
        // add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);
        
        // set menuBar to frame, particular funct to add menuBar in frame
        frame.setJMenuBar(menuBar);
        
        // // use panel to make it more attractive in terms of scroll & scope
        // // add textArea to frame, for rest components use add function
        // frame.add(textArea);
        
        // create content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        // add textArea to panel
        panel.add(textArea, BorderLayout.CENTER);
        // create scrollPane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add scroll pane to panel
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);
        
        // set dimensions of frame
        frame.setBounds(50, 50, 600, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    
    public void actionPerformed(ActionEvent actionEvent){
        // edit menus
        if(actionEvent.getSource() == cut) textArea.cut();
        if(actionEvent.getSource() == copy) textArea.copy();
        if(actionEvent.getSource() == paste) textArea.paste();
        if(actionEvent.getSource() == select) textArea.selectAll();
        if(actionEvent.getSource() == close) System.exit(0);
        
        // file menus
        if(actionEvent.getSource() == openf){
            // open a fileChooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if clicked on Open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // getting selected file
                File file = fileChooser.getSelectedFile();
                // get the path of selected file
                String filePath = file.getPath();
                try{
                    // initialize fileReader
                    FileReader fileReader = new FileReader(filePath);
                    // initialize bufferedReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null)
                        output += intermediate + "\n";
                    // set output string to textArea
                    textArea.setText(output);
                } 
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == savef){
            // initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from fileChooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // if clicked on Save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // create a new file with chosen directory path & file name
                // filePath = Path + FileName + ".txt"
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try{
                    // initialize fileWriter
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize BufferedWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write contents of textArea to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newf){
            Text_Editor newtextEditor = new Text_Editor();
        }
    }
    
    public static void main(String[] args) {
        Text_Editor textEditor = new Text_Editor();
    }
}