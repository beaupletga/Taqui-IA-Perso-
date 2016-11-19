/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquin2;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Puzzle 
{
    
    private final int size=3;
    private byte puzzle[][]= new byte[size][size];
    
//    private final int puzzle_solution[][]=new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
//    private final int puzzle_list[]= new int[]{1,2,3,4,5,6,7,8,9,0,10,11,12,13,14,15};
    
//    private final int puzzle_solution[][]=new int[][]{{0,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15}};
//    private final int puzzle_list[]= new int[]{1,2,3,4,5,6,7,8,9,0,10,11,12,13,14,15};
    
    
    private final byte puzzle_solution[][]= new byte[][]{{0,1,2},{3,4,5},{6,7,8}};
    private final byte puzzle_list[]= new byte[]{1,2,3,4,5,0,6,7,8}; 
    
//    private final int puzzle_solution[][]= new int[][]{{1,2,3},{4,-1,-1},{-1,-1,-1}};
//    private final int puzzle_list[]= new int[]{1,2,3,4,5,0,6,7,8}; 
    
    
    public byte[][] get_puzzle()
    {
        return puzzle;
    }
    
    public byte[][] get_puzzle_solution()
    {
        return puzzle_solution;
    }
    
    public void set_puzzle(byte[][] new_one)
    {
        puzzle=new_one;
    }
    
    
    public void generate_puzzle(String puzzle_string)
    {
        int count=0;
        for (byte i=0;i<size;i++)
        {
            for(byte j=0;j<size;j++)
            {
                puzzle[i][j]=(byte)((int)puzzle_string.charAt(count)-48);
                count++;
            }
        }
    }
    
    public boolean is_solution()
    {
        for (byte i=0;i<size;i++)
        {
            for (byte j=0;j<size;j++)
            {
                if (puzzle[i][j]!=puzzle_solution[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void display_puzzle()
    {
        for (byte[] i : puzzle)
        {
            for (byte j : i)
            {
                System.out.print(j+ " | ");
            }
            System.out.println();
        }
    }
    
    public int get_h()
    {
//        int total=0;
//        for (int i=0;i<size;i++)
//        {
//            for (int j=0;j<size;j++)
//            {
//                if (puzzle[i][j]!=puzzle_solution[i][j])
//                {
//                    total++;
//                }
//            }
//        }
        
        int total1=0;
        int x,y;
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++)
            {
                x=get_position(puzzle[i][j]).x;
                y=get_position(puzzle[i][j]).y;
                total1+=Math.abs(x-i)+Math.abs(y-j);
            }
        }
        return total1;
        
        
//        if(nb_of_linear_conflict()!=0)
//        {
//            return Math.max(total,total1+2*nb_of_linear_conflict());
//        }
//        return Math.max(total, total1);
        
    }
    
//    public int get_h()
//    {
//        int total=0;
//        for (int i=0;i<size;i++)
//        {
//            for (int j=0;j<size;j++)
//            {
//                if ((puzzle[i][j]!=puzzle_solution[i][j])&& (puzzle[i][j]==1 ||puzzle[i][j]==2))
//                {
//                    total++;
//                }
//            }
//        }
//        int total1=0;
//        int x,y;
//        for (int i=0;i<size;i++)
//        {
//            for (int j=0;j<size;j++)
//            {
//                if(puzzle[i][j]==1 ||puzzle[i][j]==2)
//                {
//                    x=get_position(puzzle[i][j]).x;
//                    y=get_position(puzzle[i][j]).y;
//                    total1+=Math.abs(x-i)+Math.abs(y-j);
//                }
//            }0
//        if(nb_of_linear_conflict()!=0)
//        {
//            return Math.max(total,total1);
//        }
//        return Math.max(total, total1);
//        
//    }
    
    
    
    public Pair get_position(int nb)
    {
        Pair tmp= new Pair();
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++)
            {
                if (puzzle_solution[i][j]==nb)
                {
                    tmp.x=i;
                    tmp.y=j;
                    return tmp;
                }
            }
        }
        return tmp;
    }
    
    
    public int get_manhattan()
    {
        int total=0;
        int x,y;
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++)
            {
                x=get_position(puzzle[i][j]).x;
                y=get_position(puzzle[i][j]).y;
                total+=Math.abs(x-i)+Math.abs(y-j);
            }
        }
        return total;
    }
    
    
    
    public class Pair
    {
        public int x;
        public int y;
    }
    
    public Pair get_position_of_empty_cell()
    {
        Pair tmp= new Pair();
        for (int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++)
            {
                if (puzzle[i][j]==0)
                {
                    tmp.x=i;
                    tmp.y=j;
                    return tmp;
                }
            }
        }
        return tmp;
    }
    
    
    public void deep_copy(byte[][] tab1, byte[][] tab2)
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                tab1[i][j]=tab2[i][j];
            }
        }
    }
    
    
    public ArrayList<byte[][]> next_position_available()
    {
        int x,y;
        x=get_position_of_empty_cell().x;
        y=get_position_of_empty_cell().y;
       
        ArrayList<byte[][]> result= new ArrayList();

        if (x+1<size)
        {
            byte tmp[][]= new byte[size][size];
            deep_copy(tmp,puzzle);
            tmp[x][y]=tmp[x+1][y];
            tmp[x+1][y]=0;
            result.add(tmp);
        }
        if(x-1>=0)
        {
            byte tmp[][]= new byte[size][size];
            deep_copy(tmp,puzzle);
            tmp[x][y]=tmp[x-1][y];
            tmp[x-1][y]=0;
            result.add(tmp);
        }
        if(y-1>=0)
        {
            byte tmp[][]= new byte[size][size];
            deep_copy(tmp,puzzle);
            tmp[x][y]=tmp[x][y-1];
            tmp[x][y-1]=0;
            result.add(tmp);
        }
        if (y+1<size)
        {
            byte tmp[][]= new byte[size][size];
            deep_copy(tmp,puzzle);
            tmp[x][y]=tmp[x][y+1];
            tmp[x][y+1]=0;
            result.add(tmp);
        }
        return result;        
    }
    
    
    public void display_next_position_available(byte[][] tmp)
    {
        for(byte[] i : tmp)
        {
            for (byte j:i)
            {
                System.out.print(j+" | ");
            }
            System.out.println();
        }
        
    }
    
    
    public int nb_of_linear_conflict()
    {
        int total=0;
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if (i+1<size)
                {
                    if((puzzle[i+1][j]==puzzle_solution[i][j]) &&(puzzle[i][j]==puzzle_solution[i+1][j]))
                    {
                        total++;
                    }
                }
                if(i-1>=0)
                {
                    if((puzzle[i-1][j]==puzzle_solution[i][j]) &&(puzzle[i][j]==puzzle_solution[i-1][j]))
                    {
                        total++;
                    }
                }
                if(j-1>=0)
                {
                    if((puzzle[i][j-1]==puzzle_solution[i][j]) &&(puzzle[i][j]==puzzle_solution[i][j-1]))
                    {
                        total++;
                    }
                }
                if (j+1<size)
                {
                    if((puzzle[i][j+1]==puzzle_solution[i][j]) &&(puzzle[i][j]==puzzle_solution[i][j+1]))
                    {
                        total++;
                    }
                }
                
            }
        }
        return total;
    }
}
