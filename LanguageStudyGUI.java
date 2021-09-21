/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languagestudygui;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.FileNotFoundException;

class LanguageStudyGUI implements ActionListener
{
   JFrame fr;
   JPanel pa;
   JLabel jl1, jl2; //label
   JTextField jtf1; //text field
   JButton jb; //ok button
   static int languageChoice, randomIndex;
   ArrayList<String> englishWords = new ArrayList<>();
   ArrayList<String> spanishWords = new ArrayList<>();
  
   LanguageStudyGUI()
   {
       fr = new JFrame("Language Study GUI"); //creating frame
      
       pa = new JPanel(); //creating a panel
      
       jl1 = new JLabel("Type your translation into the field below."); //top line
       jl1.setBounds(125,25,275,20);
       jl1.setHorizontalAlignment(JLabel.CENTER);
       jl2 = new JLabel("Word"); //word line
       jl2.setBounds(0, 60, 125, 20);
       jl2.setHorizontalAlignment(JLabel.CENTER);
      
       jtf1 = new JTextField(); //textfield
       jtf1.setBounds(100,60, 150, 25);
       jtf1.addActionListener(this);
      
       jb = new JButton("OK");
       jb.setBounds(330, 60, 80, 25);
       jb.addActionListener(this);
      
       //adding components to jpanel
       pa.add(jl1);
       pa.add(jl2);
       pa.add(jtf1);
       pa.add(jb);
      
       //adding panel to jframe
       fr.setContentPane(pa);
      
       fr.setSize(450,150); //set screen size
       fr.setLayout(null);
       fr.setVisible(true);
       fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close button
   }
  
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource() == jb ||e.getSource() == jtf1)
       {
           //checks for correctness on button click or enter press
           checkCorrectness();
       }
   }
  
   void checkCorrectness()
   {
       /*this method checks for correct or incorrect translation*/
       if(languageChoice == 0)
       {
           String guess = jtf1.getText().toString(); //read from text field
           System.out.println("Guess: "+guess);//debug msg to console
           if(guess.equalsIgnoreCase(spanishWords.get(randomIndex))) //compare it
               jl1.setText("Correct");
           else
               jl1.setText("Incorrect! Answer:"+ spanishWords.get(randomIndex));
       }
       else
       {
           String guess = jtf1.getText().toString(); //read from text field
           System.out.println("guess: "+guess); //debug msg to console
           if(guess.equalsIgnoreCase(englishWords.get(randomIndex))) //compare it
               jl1.setText("Correct");
           else
               jl1.setText("Incorrect! Answer:"+ englishWords.get(randomIndex));
       }
   }
  
   static int getRandomNumber(int x)
   {
       /*this function returns a random number between [0,x-1]*/
       Random rand = new Random();
       return rand.nextInt(x);
   }
  
   void play()
   {
       /*this function has the play logic of the game*/
       jl1.setText("Type your translation into the field below.");
       jtf1.setText(""); //clear text field on ne game
      
       languageChoice = getRandomNumber(2); //random selection of language
      
       if(languageChoice == 0)
           englishPlay();
       else
           spanishPlay();
      
       Timer timer = new Timer();
       timer.schedule(new TimerTask(){
           public void run()
           {
               /*this part will automatically run after 10 seconds*/
               checkCorrectness(); //check for correctness after 10 seconds
               try
               {
                   Thread.sleep(5000);
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }
               play();
           }
       }, 10000);
      
   }
  
   void englishPlay()
   {
       /*this function choose an english word for translation*/
       randomIndex = getRandomNumber(englishWords.size());
       String word = englishWords.get(randomIndex);
       String w = spanishWords.get(randomIndex);
       jl2.setText(word);
       System.out.println("word: "+word+" translation: "+w); //debug msg
   }
  
   void spanishPlay()
   {
       /*this function chose spanish word for translation*/
       randomIndex = getRandomNumber(spanishWords.size());
       String word = spanishWords.get(randomIndex);
       String w = englishWords.get(randomIndex);
       jl2.setText(word);
       System.out.println("word: "+word+" translation: "+w); //debug msg
      
   }
  
   public static void main(String args[]) throws FileNotFoundException
   {
       /*this is the main function of the program*/
      
       File engish = new File("english.txt");
       File spanish = new File("spanish.txt");
      
       Scanner sc1 = new Scanner(engish);
       Scanner sc2 = new Scanner(spanish);
      
      
       LanguageStudyGUI game = new LanguageStudyGUI();
      
       //read from english.txt
       while(sc1.hasNextLine())
       {
           game.englishWords.add(sc1.nextLine());//add the words to array list
       }
      
       //read from spanish.txt
       while(sc2.hasNextLine())
       {
           game.spanishWords.add(sc2.nextLine()); //add the words to array list
       }
      
       game.play(); //lets play the game
      
   }
}