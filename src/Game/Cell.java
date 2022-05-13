package Game;

/**
 * @author sanmu
 * @version 1.0
 */
class Cell {
    private  boolean alive;
    private int x;
    private int y;

    public Cell(boolean alive, int x, int y) {
        this.alive = alive;
        this.x = x;
        this.y = y;
    }

    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean flag){alive=flag;}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean judge_alive(Cell[][] cells){
        int count=0;
        for(int i=0;i<3;i++){
            if(this.x-1>=0&&this.y-1+i>=0&&this.y-1+i< cells[0].length&&cells[this.x-1][this.y-1+i].alive){
                count++;
            }
        }
        for(int i=0;i<3;i++){
            if(this.x+1< cells.length&&this.y-1+i>=0&&this.y-1+i< cells[0].length&&cells[this.x+1][this.y-1+i].alive){
                count++;
            }
        }
        if(this.y-1>=0&&cells[this.x][this.y-1].alive){
            count++;
        }
        if(this.y+1<cells[0].length&&cells[this.x][this.y+1].alive){
            count++;
        }
        if(count==3){
            return true;
        }
        else if(count==2){
            return this.isAlive();
        }
        else{
            return false;
        }

    }
}