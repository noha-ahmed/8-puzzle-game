package Backend.State;

public class AState extends IState implements Comparable<AState>{
    private int costG;
    private int costF;
    private int costH;
    public AState(String str, int initialCost) {
        this.setStateValue(str);
        this.costG = initialCost;
        this.findSpace();
    }
    private AState(String str, int spaceIndex, int cost){
        this.setStateValue(str);
        this.setSpaceIndex(spaceIndex);
        this.setCostG(cost);
    }
    protected IState moveUp(){
        if( this.getSpaceIndex() < 3 ) return null;
        return new AState(this.swap(this.getSpaceIndex() - 3) ,this.getSpaceIndex() - 3, this.costG + 1);
    }
    /*
     * Moves the space block in the current state down in the puzzle and returns that state
     * Return: state with the space moved down
     */
    protected IState moveDown(){
        if( this.getSpaceIndex() > 5 ) return null;
        return new AState(this.swap(this.getSpaceIndex() + 3) , this.getSpaceIndex() + 3, this.costG + 1);
    }
    /*
     * Moves the space block in the current state left in the puzzle and returns that state
     * Return: state with the space moved left
     */
    protected IState moveLeft(){
        if( this.getSpaceIndex() % 3 == 0 ) return null;
        return new AState(this.swap(this.getSpaceIndex() - 1) , this.getSpaceIndex() - 1, this.costG + 1);
    }
    /*
     * Moves the space block in the current state right in the puzzle and returns that state
     * Return: state with the space moved right
     */
    protected IState moveRight(){
        if( (this.getSpaceIndex() + 1) % 3 == 0) return null;

        return new AState(this.swap(this.getSpaceIndex() + 1), this.getSpaceIndex() + 1, this.costG + 1);
    }
    public int getCostG(){
        return this.costG;
    }
    public void setCostG(int cost){
        this.costG = cost;
    }
    public int getCostF(){
        return this.costF;
    }
    public void setCostF(){
        this.costF = this.costH + this.costG;
    }
    public int getCostH(){
        return this.costH;
    }
    public void setCostH(int costH){
        this.costH = costH;
    }

    @Override
    public int compareTo(AState o) {
        if(this.getCostF() == o.getCostF())
            return 0;
        else if(this.getCostF() > o.getCostF())
            return 1;
        else
            return -1;
    }
}
