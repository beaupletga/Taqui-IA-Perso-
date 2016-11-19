/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquin2;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author gabriel
 */
public class File_Writer 
{
    public void write_int_file(ArrayList<Integer> list1,ArrayList<Double> list2)
    {
        
        File f = new File ("Astar-best");

        try
        {
            PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter (f)));

            for (int i=0;i<list1.size();i++)
            {
                pw.println (list1.get(i)+ " "+list2.get(i));
            }

            pw.close();
        }
        catch (IOException exception)
        {
            System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
        }
    }
}
