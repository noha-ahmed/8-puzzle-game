package Backend.State;
import java.util.ArrayList;

public class State extends IState{
    /*private static final String GOALSTATE = "012345678";
    private String stateValue;
    private int spaceIndex;
    private IState parent;

     */
    public State(String str){
        //this.stateValue = str;
        this.setStateValue(str);
        this.findSpace();
    }

    private State(String str , int spaceIndex){
        //this.stateValue = str;
        //this.spaceIndex = spaceIndex;
        this.setStateValue(str);
        this.setSpaceIndex(spaceIndex);
    }
    /*
    * Moves the space block in the current state up in the puzzle and returns that state
    * Return: state with the space moved up
    */
    /*private void findSpace(){
        for( int i = 0 ; i < stateValue.length() ; i++ ){
            if( stateValue.charAt(i) == '0'){
                spaceIndex = i;
                break;
            }
        }
    }

     */
    protected State moveUp(){
        if( this.getSpaceIndex() < 3 ) return null;
        return new State(this.swap(this.getSpaceIndex() - 3) ,this.getSpaceIndex()-3);
    }
    /*
     * Moves the space block in the current state down in the puzzle and returns that state
     * Return: state with the space moved down
     */
    protected State moveDown(){
        if( this.getSpaceIndex() > 5 ) return null;
        return new State(this.swap(this.getSpaceIndex() + 3) , this.getSpaceIndex()+3);
    }
    /*
     * Moves the space block in the current state left in the puzzle and returns that state
     * Return: state with the space moved left
     */
    protected State moveLeft(){
        if( this.getSpaceIndex() % 3 == 0 ) return null;
        return new State(this.swap(this.getSpaceIndex() - 1) , this.getSpaceIndex() - 1);
    }
    /*
     * Moves the space block in the current state right in the puzzle and returns that state
     * Return: state with the space moved right
     */
    protected State moveRight(){
        if( (this.getSpaceIndex() + 1) % 3 == 0) return null;

        return new State(this.swap(this.getSpaceIndex() + 1), this.getSpaceIndex() + 1);
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
