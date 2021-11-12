 package Frontend;

 import Backend.PuzzleSolver;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.Initializable;
 import javafx.scene.control.Button;
 import javafx.scene.control.ComboBox;
 import javafx.scene.control.Label;
 import javafx.scene.control.TextField;
 import javafx.scene.layout.AnchorPane;

 import javax.swing.text.html.ImageView;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.Random;
 import java.util.ResourceBundle;

 public class Controller implements Initializable {

     @FXML
     public Button btn0;
     @FXML
     public Button btn1;
     @FXML
     public Button btn2;
     @FXML
     public Button btn3;
     @FXML
     public Button btn4;
     @FXML
     public Button btn5;
     @FXML
     public Button btn6;
     @FXML
     public Button btn7;
     @FXML
     public Button btn8;
     @FXML
     public Label error;
     @FXML
     public TextField cost;
     @FXML
     public TextField time;
     @FXML
     public TextField depth;
     @FXML
     public TextField input;
     @FXML
     public Button start;
     @FXML
     public Button shuffle;
     @FXML
     public Button nextStep;
     @FXML
     public Button previousStep;
     @FXML
     public Button set;
     @FXML
     public AnchorPane anchor;
     @FXML
     public ComboBox<String> expandChoice;




     private Button[] elements;
     public int currentStateCounter=1;
     public int indexToZero=0;
     public ArrayList<String>  states= new ArrayList<>();
     public ArrayList<String>  randomStates= new ArrayList<>();


     @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {
         elements = new Button[]{btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8};
        setElements("123456780");
        randomStates.add("123456870");
        randomStates.add("123456780");
        randomStates.add("182043765");
        randomStates.add("125340678");
        randomStates.add("123456780");

     }


     public boolean validation(String inputState){
         if(inputState.length()==9){
             HashSet<Character> nums=new HashSet<>();
             for(int i=0;i<9;i++){
                 char c=inputState.charAt(i);
                 if(nums.contains(c) || Character.isDigit(c)==false){
                     error.setText(" Enter a valid state please !");
                     error.setVisible(true);
                     return false;
                 }
                 else nums.add(c);
             }
         }
         else{
             error.setText(" Enter a valid state please !");
             error.setVisible(true);
             return false;
         }
         error.setVisible(false);
         return true;

     }
     public void setGrid(){
         if(validation(input.getText())){
         setElements(input.getText());}
     }
     public void setElements(String state){
       for(int i=0 ;i<9;i++){
            char c=state.charAt(i);
           int val=Integer.valueOf(String.valueOf(c));
           if(val!=0){
               elements[i].setText(String.valueOf(c));}
           else
           {
               elements[indexToZero].setVisible(true);
               indexToZero=i;
               elements[i].setVisible(false);
           }
       }

     }
     public void start() {
         boolean isValid=validation(input.getText());
         
         if(isValid) {
             //call from the backend
             setElements(input.getText());
             PuzzleSolver solver= PuzzleSolver.getInstance();
             String expandType=expandChoice.getValue();
             if(expandType==null)expandType="DFS";
             boolean isSolvable=solver.solvePuzzle(input.getText(),expandType);
             if(isSolvable) {
                 states=solver.getPath();
                 cost.setText(String.valueOf(solver.getCost()));
                 depth.setText(String.valueOf(solver.getSearchDepth()));
                 time.setText(String.valueOf(solver.getRunningTime()));
                 disable();
             }
             else{
                 error.setText(" This state in not solvable !");
                 error.setVisible(true);
             }
         }
     }
     public void nextMove(ActionEvent event) {
         if(currentStateCounter<states.size()){
             simulate();
             currentStateCounter++;
         }
     }

     public void previousMove(ActionEvent event) {
         currentStateCounter--;
         if(currentStateCounter>0){
             currentStateCounter--;
             simulate();
         }
         currentStateCounter++;


     }

     public void simulate(){
         String state = states.get(currentStateCounter);
         setElements(state);
         if (isCompleted(state)) {
             System.out.println();
             Main.endWithCongrats();
             enable();

         }
     }

     public void disable() {
         start.setDisable(true);
         shuffle.setDisable(true);
         previousStep.setDisable(false);
         previousStep.setVisible(true);
         nextStep.setDisable(false);
         nextStep.setVisible(true);
         anchor.setVisible(true);
         error.setVisible(false);
         expandChoice.setDisable(true);
         input.setDisable(true);
         set.setDisable(false);
        // imageLeft.se
     }

     public void enable() {
         start.setDisable(false);
         shuffle.setDisable(false);
         previousStep.setDisable(true);
         previousStep.setVisible(false);
         nextStep.setDisable(true);
         nextStep.setVisible(false);
         anchor.setVisible(false);
         expandChoice.setDisable(false);
         currentStateCounter=1;
         setElements(input.getText());
         input.setDisable(false);
         set.setDisable(true);

     }

     Random random=new Random();
     public void shuffle() {
         int i=random.nextInt(randomStates.size());
         String find=randomStates.get(i);
         setElements(find);
         input.setText(find);

     }

     private boolean isCompleted(String state){
         return state.equals("012345678");
     }

 }