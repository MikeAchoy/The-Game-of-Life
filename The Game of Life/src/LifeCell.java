public class LifeCell {
    private int x, y;
    private boolean isAlive;

    public LifeCell(int xToSet, int yToSet){
        this.setX(xToSet);
        this.setY(yToSet);
    }

    public void setX(int xToSet){
        this.x = xToSet;
    }

    public void setY(int yToSet){
        this.y = yToSet;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setAlive(){
        this.isAlive = true;
    }

    public void setDead(){
        this.isAlive = false;
    }

    public boolean getLifeStatus(){
        return this.isAlive;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public boolean isDead() {
        if(this.isAlive) {
            return false;
        }
        return true;
    }

    public void toggleCell(){
        this.isAlive = !this.isAlive;
    }
}
