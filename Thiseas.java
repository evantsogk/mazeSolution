import java.util.NoSuchElementException;
public class Thiseas {
	static int exitX;
	static int exitY;
	public static void main( String args[] ) {
		
		ReadMaze mazeFile=new ReadMaze();
		mazeFile.loadFile(args[0]);
		
		String[][] maze=mazeFile.getMaze();
        int[] entrance=mazeFile.getEntrance();
	    int rows=mazeFile.getRows();
	    int cols=mazeFile.getColumns();
		
		boolean success=new Thiseas().findExit(maze, entrance, rows, cols);
        if (success)
		    System.out.println("Exit: ("+exitX+", "+exitY+")");
        else
		    System.out.println("No exit found!");
			
	}
    
	//Uses a stack to locate exit and returns true if it is found.
	public boolean findExit(String[][] maze, int[] cell, int rows, int cols) {
		StringStackImpl<int[]> stack=new StringStackImpl();
		int x=cell[0];
		int y=cell[1];
		int[] currentCell=cell;
		stack.push(cell);
		while (!stack.isEmpty()) {
			try {
                currentCell=stack.peek();
				x=currentCell[0];
				y=currentCell[1];
				if ((x==0 || x==rows-1 || y==0 || y==cols-1) && !maze[x][y].equals("E")) {
					if(maze[x][y].equals("0")) {
						exitX=x;
					    exitY=y;
						return true;
					}
					else
						stack.pop();
				}
				else {
					if (up(maze, currentCell) || down(maze, currentCell, rows) || left(maze, currentCell) || right(maze, currentCell, cols)) {
						if (up(maze, currentCell)) {
							currentCell[0]=x-1;
						    currentCell[1]=y;
						}
						else if (down(maze, currentCell, rows)) {
							currentCell[0]=x+1;
						    currentCell[1]=y;
						}
						else if (left(maze, currentCell)){
							currentCell[0]=x;
						    currentCell[1]=y-1;
						}
					    else if (right(maze, currentCell, cols)){
							currentCell[0]=x;
						    currentCell[1]=y+1;
						}
						stack.push(new int[] {currentCell[0], currentCell[1]});
					}
					else {
					    stack.pop();
					}
				}
	        }
	        catch (NoSuchElementException nsee) {
		        nsee.printStackTrace();
	        }
			maze[x][y]="X"; // visited cells are marked as "X"
		}
		return false;
	}
	//The following methods return true if there is open path at the named direction.
	public boolean up(String[][] maze, int[] cell) {
		int xcord=cell[0];
        int ycord=cell[1];
        if (xcord==0)
			return false;
		else
			return maze[xcord-1][ycord].equals("0");
	}
	
	public boolean down(String[][] maze, int[] cell, int rows) {
		int xcord=cell[0];
        int ycord=cell[1];
        if (xcord==rows-1)
			return false;
		else
			return maze[xcord+1][ycord].equals("0");
	}
	
	public boolean left(String[][] maze, int[] cell) {
		int xcord=cell[0];
        int ycord=cell[1];
        if (ycord==0)
			return false;
		else
			return maze[xcord][ycord-1].equals("0");
	}
	
	public boolean right(String[][] maze, int[] cell, int cols) {
	    int xcord=cell[0];
        int ycord=cell[1];
        if (ycord==cols-1)
			return false;
		else
			return maze[xcord][ycord+1].equals("0");
	}
}

