package Backend.State;

import java.util.ArrayList;

public abstract class IState {
     private static final String GOALSTATE = "012345678";
     private String stateValue;
     private int spaceIndex;
     private IState parent;
     private int depth = 0;
     protected void findSpace(){
          for( int i = 0 ; i < stateValue.length() ; i++ ){
               if( stateValue.charAt(i) == '0'){
                    spaceIndex = i;
                    break;
               }
          }
     }
     public int getDepth() {
          return depth;
     }
     
     protected abstract IState moveUp();
     /*
      * Moves the space block in the current state down in the puzzle and returns that state
      * Return: state with the space moved down
      */
     protected abstract IState moveDown();
     /*
      * Moves the space block in the current state left in the puzzle and returns that state
      * Return: state with the space moved left
      */
     protected abstract IState moveLeft();
     /*
      * Moves the space block in the current state right in the puzzle and returns that state
      * Return: state with the space moved right
      */
     protected abstract IState moveRight();
     protected String swap(int newIndex){
          StringBuilder newStateValue = new StringBuilder(stateValue);
          newStateValue.setCharAt(spaceIndex, stateValue.charAt(newIndex));
          newStateValue.setCharAt(newIndex, '0');
          return newStateValue.toString();
     }
     /*
      * gets all the neighbours state of the current state
      * Return: Array of the neighbouring states
      */

     public ArrayList<IState> getNeighbours() {
          ArrayList<IState> neighbours = new ArrayList<IState>();
          IState neighbourState = moveLeft();
          if( neighbourState != null ) neighbours.add(neighbourState);
          neighbourState = moveRight();
          if( neighbourState != null ) neighbours.add(neighbourState);
          neighbourState = moveUp();
          if( neighbourState != null ) neighbours.add(neighbourState);
          neighbourState = moveDown();
          if( neighbourState != null ) neighbours.add(neighbourState);
          return neighbours;
     }

     public String getValue() {
          return this.stateValue;
     }
     public void setStateValue(String value){
          this.stateValue = value;
     }

     public void setParent(IState parentState) {
          this.parent = parentState;
          this.depth = parentState.depth + 1;
     }

     public IState getParent() {
          return this.parent;
     }

     public int getSpaceIndex() {
          return this.spaceIndex;
     }

     public void setSpaceIndex(int index){
          this.spaceIndex = index;
     }

     public boolean isGoal() {
          return this.stateValue.equals(GOALSTATE);
     }

     public void printState(){
          System.out.println(stateValue.substring(0,3));
          System.out.println(stateValue.substring(3,6));
          System.out.println(stateValue.substring(6));
     }
     public static void main( String[] args ){
          IState state = new State("813425670");
          ArrayList<IState> n = state.getNeighbours();
          state.printState();
          System.out.println("----------");
          for( IState s : n){
               s.printState();
               System.out.println("----------");
          }
     }
}
