import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.*;
import java.util.Random;

public class Clicker implements ActionListener{
    //Variables

    JFrame frame;
    JPanel buttons;
    JLabel resorces;
    JButton goal;
    JButton coin;
    JButton wood;
    JButton stone;

    int numCoins;
    int numWood;
    int numStone;
    int numTasks;

    int taskWood;
    int taskStone;

    int goldGeneration;
    public static void main(String[] args) {
        Clicker clicker = new Clicker();
        clicker.run();
        
    }
    
    public Clicker(){
        //Initialize all varibles
        frame = new JFrame();
        buttons = new JPanel();
        resorces = new JLabel();
        goal = new JButton();
        coin = new JButton();
        wood = new JButton();
        stone = new JButton();

        numCoins = 0;
        numWood = 0;
        numStone = 0;
        numTasks = 0;

        taskWood = 2;
        taskStone = 2;

        goldGeneration = 1;
    }

    public void run(){
        
        //Set the text of the buttons
        goal.setText("TASK: 2 Wood and 2 Stone");
        coin.setText("- - - - Coin- - - -");
        wood.setText("Wood: 15 Gold");
        stone.setText("Stone: 25 Gold");
        resorces.setText("Gold: 0 | Wood: 0 | Stone: 0 | Tasks Compleeted: 0");

        //Make all the bottons send a mesage to the class when clicked
        goal.addActionListener(this);
        coin.addActionListener(this);
        wood.addActionListener(this);
        stone.addActionListener(this);
        
        //Adding the buttons to the panel
        buttons.add(goal);
        buttons.add(coin);
        buttons.add(wood);
        buttons.add(stone);
        buttons.add(resorces);
        buttons.setSize(1024, 100);
        
        //Setting up the frame to make it visiable and other stuff
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //checks what button sent the signal 'e' and runs a difrent statement acordingly
        if(e.getSource() == coin){
            numCoins += goldGeneration;
        }
        if(e.getSource() == wood){
            if(numCoins >= 15){
                numCoins -= 15;
                numWood += 1;
            }
        }
        if(e.getSource() == stone){
            if(numCoins >= 25){
                numCoins -= 25;
                numStone += 1;
            }
        }
        
        if(e.getSource() == goal){
            if(numWood >= taskWood && numStone >= taskStone){
                numTasks += 1;
                numWood -= taskWood;
                numStone -= taskStone;
                Random rand = new Random();
                //sets the next task goals along with adding basic progresion (more taks = more gold produced per click and bigger tasks)
                goldGeneration += 1;
                taskWood = rand.nextInt(3) + goldGeneration;
                taskStone = rand.nextInt(3) + goldGeneration;
                goal.setText("TASK: " + taskWood + " Wood and " + taskStone + " Stone");
            }
        }
        
        updateResorces();
    }

    public void updateResorces(){
        //updates the recorces label with all the current information
        resorces.setText("Gold: " + numCoins + " | Wood: " + numWood + " | Stone: " + numStone + " | Tasks Compleeted: " + numTasks);
    }
}

