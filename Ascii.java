import javax.swing.*;
import java.util.Random;

public class Ascii{	
	public static void main(String[] args){
		AsciiTable asciiTable=new AsciiTable();
		String[] options={"Print table", "Edit Cell", "Search for a character"};
		boolean repeat=true;
		do{
			String action = (String) JOptionPane.showInputDialog(null, 
					"Choose an action to perform:",
					"OPTIONS",
					JOptionPane.PLAIN_MESSAGE, 
					null,
					options,
					"Print table");
			if(action=="Print table"){
				asciiTable.printTable();
				int n = JOptionPane.showConfirmDialog(null, "Would you like to perform another action?","Another Action", JOptionPane.YES_NO_OPTION);
				if (n==1){
					repeat=false;
				}
			}
			else if(action=="Edit Cell"){
				asciiTable.editCell();
				int n = JOptionPane.showConfirmDialog(null, "Would you like to perform another action?","Another Action", JOptionPane.YES_NO_OPTION);
				if (n==1){
					repeat=false;
				}
			}
			else if(action=="Search for a character"){
				asciiTable.searchTable();
				int n = JOptionPane.showConfirmDialog(null, "Would you like to perform another action?","Another Action", JOptionPane.YES_NO_OPTION);
				if (n==1){
					repeat=false;
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "No action chosen. \nProgram terminates now.", "Exit Program", JOptionPane.PLAIN_MESSAGE);
				repeat=false;
			}
		}		
		while(repeat);
		System.exit(0);
	}
}


class AsciiTable{
    private final int numberOfChars=3;
    private final int numberOfEqualSign=6;
    private final int numberOfConstantGrid=8;
    private final int numberOfAlphabet=26;
    private final int min=32;
    private final int max=126;
	private final char[] forColumn= 
	{' ','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private final char[] forColumn2= 
	{' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};


	private String[][] ascii=new String[0][0];
	private boolean rowError;
    private boolean columnError;
    private int row;
    private int column;
	private int rn=0;
	private char c;
	
	AsciiTable(){
    	Random random = new Random();
	    do{
		    try{
		        rowError=false;
		        row = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of row/s: ", "Enter Row"));
		    }
		    catch(NumberFormatException e){
		        JOptionPane.showMessageDialog(null, "Please enter a valid row number!");
		        rowError=true;
                row=1;
		    }
		    if (row==0){
		        JOptionPane.showMessageDialog(null, "Please enter a valid row number!");
		        rowError=true;
		    }
		}while(rowError);
		
		do{
		    try{
		        columnError=false;
		        column = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of column/s: ", "Enter Column"));
		    }
		    catch(NumberFormatException f){
		        JOptionPane.showMessageDialog(null, "Please enter a valid column number!");
		        columnError=true;
		        column=1;
		    }
		    if (column==0){
		        JOptionPane.showMessageDialog(null, "Please enter a valid column number!");
		        columnError=true;
		    }
		}while(columnError);
        ascii=new String[row][column];
		
		for(int rowln=1; rowln<=row; rowln++){
			for(int columnln=1; columnln<=column; columnln++){
				for(int a=1; a<=3; a++){
					rn=random.nextInt(max - min + 1) + min;
					if (a==1){
						c=(char) rn;
						ascii[rowln-1][columnln-1] =String.valueOf(c);
					}
					else{
						ascii[rowln-1][columnln-1] += (char) rn;
					}
				}
			}
		}
	}
		
		
	void printTable(){
		int grid=numberOfConstantGrid + (numberOfChars*column) + numberOfEqualSign*(column-1);
		System.out.println("");
		int orSign=0;
		for(int columnRef=1; columnRef<=column; columnRef++){
			if (columnRef==1){
				System.out.print("       "+columnRef);
				columnRef++;
			}
			System.out.print("        "+columnRef);
		}
		System.out.print("\n");
		for(int rowln=1; rowln<=row; rowln++){
			for(int equalSign=1; equalSign<=grid; equalSign++){
				if(equalSign==1){
					System.out.print("   |");
				}
				else if(equalSign==2){
					System.out.print("|");
				}
				else{
					if(orSign<7){
						System.out.print("=");
						orSign++;
					}
					else{
						System.out.print("|");
						orSign++;
					}
					if(orSign==9){
						orSign=0;
					}
				}
			}
			System.out.println("");
			    int forColumnIndex1=0;
			    int forColumnIndex2=0;
				for(int columnln=1; columnln<=column; columnln++){
					if (columnln==1){
					    if (rowln<27){
						    System.out.print(forColumn[rowln] +"  ||");
						}
						else{
						    forColumnIndex1=rowln/numberOfAlphabet;
						    forColumnIndex2=rowln-(forColumnIndex1*numberOfAlphabet);
						    if(forColumnIndex2==0){
						        forColumnIndex1=forColumnIndex1-1;
						        forColumnIndex2=numberOfAlphabet;
						    }
						    System.out.print(forColumn[forColumnIndex1]+""+forColumn[forColumnIndex2] +" ||");
						}
					}
					System.out.print("  " + ascii[rowln-1][columnln-1] + "  ||");
				}
				System.out.println("");
		}

		orSign=0;
		for(int equalSign=1; equalSign<=grid; equalSign++){
			if(equalSign==1){
				System.out.print("   |");
			}
			else if(equalSign==2){
				System.out.print("|");
			}
			else{
				if(orSign<7){
					System.out.print("=");
					orSign++;
				}
				else{
					System.out.print("|");
					orSign++;
				}
				if(orSign==9){
					orSign=0;
				}
			}
		}
		System.out.print("\n\n");
	}


	void editCell(){
		Random random = new Random();
		JFrame frame = new JFrame(" ");
		boolean repeat=true;
		char[] checkCharacter = new char[0];
		do{
			String cellRef = JOptionPane.showInputDialog(frame, "Please enter cell reference to modify", "Enter Cell Reference");
			checkCharacter=cellRef.toCharArray();
			if(cellRef.length()==2){
				if (!(Character.isLetter(checkCharacter[0]))){
				    JOptionPane.showMessageDialog(null, "Please input valid cell reference!");
			        continue;
				}
				if (!(Character.isDigit(checkCharacter[1]))){
				    JOptionPane.showMessageDialog(null, "Please input valid cell reference!");
			        continue;
				}
			}
			
			else if(cellRef.length()==3){
				if (!(Character.isLetter(checkCharacter[0]))){
				    JOptionPane.showMessageDialog(null, "Please input valid cell reference!");
			        continue;
				}
				if (!(Character.isLetter(checkCharacter[1]))  &&  !(Character.isDigit(checkCharacter[1])) ){
				    JOptionPane.showMessageDialog(null, "Please input valid cell reference!");
			        continue;
				}
				if (!(Character.isDigit(checkCharacter[2]))){
				    JOptionPane.showMessageDialog(null, "Please input valid cell reference!");
			        continue;
				}
			}
			else if(cellRef.length()==4){
				if (!(Character.isLetter(checkCharacter[0]))  ||  !(Character.isLetter(checkCharacter[1]))){
				    JOptionPane.showMessageDialog(null, "Please input valid cell reference!");
			        continue;
				}
				if (!(Character.isDigit(checkCharacter[2]))  ||  !(Character.isDigit(checkCharacter[3]))){
				    JOptionPane.showMessageDialog(null, "Please input valid cell reference!");
			        continue;
				}
			}
			
			else{
			    JOptionPane.showMessageDialog(null, "Please input valid cell reference!");
			    continue;
			}
			int colIndex=0;
			int rowIndex=-1;
			String s=cellRef.substring(0,1);
			char colRef = s.charAt(0);
			if(cellRef.length()==2){
				for(int b=1; b<=numberOfAlphabet; b++){
					if (colRef==forColumn[b] || colRef==forColumn2[b]){
						rowIndex=b;
					}
				}
				if(rowIndex>row){
					JOptionPane.showMessageDialog(null,"Cell doesnt exist!");	
					continue;
				}
				else{
				    rowIndex=rowIndex-1;
				}
				if(Integer.parseInt(cellRef.substring(1,2))<=column){
					colIndex=Integer.parseInt(cellRef.substring(1,2))-1;
				}
				else{
					JOptionPane.showMessageDialog(null,"Cell doesnt exist!");	
					continue;
				}
			}
			
			if(cellRef.length()==3){
				//second char; if number
				if(Character.isDigit(checkCharacter[1])){
					for(int b=1; b<=numberOfAlphabet; b++){
						if (colRef==forColumn[b] || colRef==forColumn2[b]){
							rowIndex=b;
						}
					}
					if(rowIndex>row){
					    JOptionPane.showMessageDialog(null,"Cell doesnt exist!");	
					    continue;
				    }
				    else{
				        rowIndex=rowIndex-1;
				    }
				    
					if(Integer.parseInt(cellRef.substring(1,3))<=column){
						colIndex=Integer.parseInt(cellRef.substring(1,3))-1;
					}
					else{
						JOptionPane.showMessageDialog(null,"Cell doesnt exist!");	
						continue;
					}
				}
				
				//second char; if letter
				if(Character.isLetter(checkCharacter[1])){
					for(int b=1; b<=numberOfAlphabet; b++){
						if (colRef==forColumn[b] || colRef==forColumn2[b]){
							rowIndex=b*numberOfAlphabet;
						}
					}
					s=cellRef.substring(1,2);
					colRef = s.charAt(0);
					for(int b=1; b<=numberOfAlphabet; b++){
						if (colRef==forColumn[b] || colRef==forColumn2[b]){
							rowIndex=rowIndex+b;
						}
					}
					if(rowIndex>row){
					    JOptionPane.showMessageDialog(null,"Cell doesnt exist!");	
					    continue;
				    }
				    else{
				        rowIndex=rowIndex-1;
				    }
				    
				    
					if(Integer.parseInt(cellRef.substring(2,3))<=column){
						colIndex=Integer.parseInt(cellRef.substring(2,3))-1;
					}
					else{
						JOptionPane.showMessageDialog(null,"Cell doesnt exist!");	
						continue;
					}
				}
			}
			
			if(cellRef.length()==4){
				for(int b=1; b<=numberOfAlphabet; b++){
					if (colRef==forColumn[b] || colRef==forColumn2[b]){
						rowIndex=b*numberOfAlphabet;
					}
				}
				s=cellRef.substring(1,2);
				colRef = s.charAt(0);
				for(int b=1; b<=numberOfAlphabet; b++){
					if (colRef==forColumn[b] || colRef==forColumn2[b]){
						rowIndex=rowIndex+b;
					}
				}
				if(rowIndex>row){
				    JOptionPane.showMessageDialog(null,"Cell doesnt exist!");	
				    continue;
			    }
			    else{
			        rowIndex=rowIndex-1;
			    }
				
				if(Integer.parseInt(cellRef.substring(2,4))<=column){
					colIndex=Integer.parseInt(cellRef.substring(2,4))-1;
				}
				else{
					JOptionPane.showMessageDialog(null,"Cell doesnt exist!");	
					continue;
				}
				JOptionPane.showMessageDialog(null,ascii[rowIndex][colIndex]+ " " +rowIndex+" "+colIndex);
		
			}
			String origValue=ascii[rowIndex][colIndex];
			String newValue="";
			boolean forNewValue=true;
			int checkCharacterInt=0;
			do{
			    newValue  = JOptionPane.showInputDialog(frame, "Enter three characters", "New Value");
			    if(newValue.length()!=3){
			        JOptionPane.showMessageDialog(null,"Invalid number of characters!");
			        continue;
			    }
			    for(int a=0; a<newValue.length(); a++){
			        checkCharacter=newValue.toCharArray();
			        checkCharacterInt=(int)checkCharacter[a];
			        if (checkCharacterInt<min || checkCharacterInt>max){
			            forNewValue=true;
			            JOptionPane.showMessageDialog(null,"Invalid ASCII Character!");
			        }
			    }
			    forNewValue=false;
			}while(forNewValue);
			
			ascii[rowIndex][colIndex]=newValue;
			System.out.println("\n\nMODIFIED TABLE:");
			printTable();
			JOptionPane.showMessageDialog(null, "Cell " + cellRef + " successfully edited. \nOrig value: " + origValue + "\nNew Value: " + newValue);
			repeat=false;
		}
		while(repeat);
	}

    void searchTable(){
	    String searchValue ="";
        boolean forSearchValue=true;
		int checkCharacterInt=0;
		char[] checkCharacter = new char[0];
		do{
		    searchValue = JOptionPane.showInputDialog(null, "Type one to three ASCII characters to search:", "Enter Character/s");
		    if(searchValue.length()<1  ||  searchValue.length()>3){
		        JOptionPane.showMessageDialog(null,"Invalid number of characters!");
		        continue;
		    }
		    for(int a=0; a<searchValue.length(); a++){
		        checkCharacter=searchValue.toCharArray();
		        checkCharacterInt=(int)checkCharacter[a];
		        if (checkCharacterInt<min || checkCharacterInt>max){
		            forSearchValue=true;
		            JOptionPane.showMessageDialog(null,"Invalid ASCII Character!");
		        }
		    }
		    forSearchValue=false;
		}while(forSearchValue);
		int tableAppearance=0;
		System.out.println("");
		for(int rowln=0; rowln<row; rowln++){
			for(int columnln=0; columnln<column; columnln++){
				String compare=ascii[rowln][columnln];
				if(compare.contains(searchValue)){
		            System.out.println("Character/s "+searchValue+" appears in cell "+forColumn[rowln+1]+""+(columnln+1));
		            tableAppearance=tableAppearance+1;
		        }
			}
		}
		if (tableAppearance>0){
			System.out.println("SUMMARY: Character/s "+searchValue+" appeared in the table "+tableAppearance+"X.");
		}
		else{
			System.out.println("Character/s "+searchValue+" didn't appear in any cell of the table.");
		}
	}
}
