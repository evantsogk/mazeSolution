import java.io.*; 
import java.util.*;

public class ReadMaze {
	private String[][] maze;
	private int rows;
	private int cols;
	private int[] entrance=new int[2];
	
	public void loadFile(String data) {
		File f = null;
        BufferedReader reader = null;
		String line;
		
		try {
            f = new File(data);
        } 
	    catch (NullPointerException e) { 
	        System.err.println("File not found!");
			System.exit(0);
        }    

        try {
            reader = new BufferedReader(new FileReader(f));
        } 
	    catch (FileNotFoundException e) { 
	        System.err.println("Error opening file!");
			System.exit(0);
        }
		
		int l=0;
		int templ=0;
		try {
			//The following loops are used to read numbers with more than one digit. 
			line = reader.readLine();
			if (line!=null) {
				while (true) {
					if (line.substring(l,l+1).equals(" ")) {
						rows=Integer.parseInt(line.substring(0,l));
						break;
					}
					else
						l++;
				}
				templ=l+1;
				if (!line.endsWith(" "))
                    cols=Integer.parseInt(line.substring(l+1, line.length()));
				else {
					while (true) {
					    if (line.substring(l+1,l+2).equals(" ")) {
						    cols=Integer.parseInt(line.substring(templ,l+1));
						    break;
					    }
					    else
						    l++;
					}
				}
			}
			line = reader.readLine();
			l=0;
			if (line!=null) {
				while (true) {
					if (line.substring(l,l+1).equals(" ")) {
						entrance[0]=Integer.parseInt(line.substring(0,l));
						break;
					}
					else
						l++;
				}
				templ=l+1;
				if (!line.endsWith(" "))
			        entrance[1]=Integer.parseInt(line.substring(l+1,line.length()));
				else {
					while (true) {
					    if (line.substring(l+1,l+2).equals(" ")) {
						    entrance[1]=Integer.parseInt(line.substring(templ,l+1));
						    break;
					    }
					    else
						    l++;
					}
				}
			}
			
			int i=0;
			int j;
			int k;
			maze=new String[rows][cols];
			while (i<rows) {
				j=0;
				k=0;
				line = reader.readLine();
				if (line!=null) {
					if(line.length()!=cols+cols-1 && !line.endsWith(" ")) {
						System.out.println("The number of columns does not match the given number!");
						System.exit(0);
					}
					else {
						while (j<cols+cols-1) {
							maze[i][k]=line.substring(j,j+1);
							if (!(maze[i][k].equals("0") || maze[i][k].equals("1") || maze[i][k].equals("E"))) {
								System.out.println("Invalid data found! The maze consists only of '0', '1', and one 'E'!");
								System.exit(0);
							}
							if ((i!=entrance[0] || k!=entrance[1]) && maze[i][k].equals("E")) {
								System.out.println("An entrance ('E') was found somewhere else than the given position! There can only be one entrance!");
								System.exit(0);
							}
							k++;
							j=j+2;
						}
					}
					i++;
				}
				else {
					System.out.println("The number of rows does not match the given number!");
				    System.exit(0);
				}
			}
			line=reader.readLine();
		    if (line!=null) {
				System.out.println("The number of rows does not match the given number!");
				System.exit(0);
			}
			if (!maze[entrance[0]][entrance[1]].equals("E")) {
				System.out.println("The entrance('E') is not found at the given position!");
				System.exit(0);
			}
		}
		catch (IOException e) {
            System.out.println("Error reading line.");
			System.exit(0);
		}
		try {
			reader.close();
		}
		catch (IOException e) {
			System.err.println("Error closing file.");
		}
	}
	
	public String[][] getMaze() {
		return maze;
	}
	
	public int[] getEntrance() {
		return entrance;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return cols;
	}
}

			