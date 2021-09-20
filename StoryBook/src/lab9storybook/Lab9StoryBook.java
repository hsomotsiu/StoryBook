/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9storybook;

/**
 *
 * @author hsomotsiu
 */
public class Lab9StoryBook {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StorybookModel myModel = new StorybookModel();
        StartFrame myStartFrame = new StartFrame(myModel);
        myStartFrame.setVisible(true);
        
   
    }
    
}
