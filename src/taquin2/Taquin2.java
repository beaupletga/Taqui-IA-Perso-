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
public class Taquin2 
{

    /**
     * @param args the command line arguments
     */
     
    public static void main(String[] args) 
    {
        
        ArrayList<Integer> move_nb =new ArrayList<>();
        ArrayList<Double> move_nb_average= new ArrayList<>();
        File_Writer file_writer_class= new File_Writer();
        
        try
        {
           
            File f = new File ("games.txt");
            FileReader fr = new FileReader (f);
            BufferedReader br = new BufferedReader (fr);
            int count=0;
            int current_value=0;
            double moyenne;
            try
            {
                String line = br.readLine();

                while (line != null)
                {
                    if(line.startsWith("%"))
                    {
                        if(line.length()==3)
                        {
                            System.out.println((int)line.charAt(2)-48);
                            current_value=(int)line.charAt(2)-48;
                        }
                        if(line.length()==4)
                        {
                            System.out.println(Integer.parseInt((String)line.subSequence(2, 4)));
                            current_value=Integer.parseInt((String)line.subSequence(2, 4));
                        }
                        line = br.readLine();
                        moyenne=0;
                        count=0;
                        while(line!=null && !line.startsWith("%"))
                        {
                            long startTime = System.nanoTime();
                            Astar astar= new Astar(line);
                            long estimatedTime = System.nanoTime() - startTime;
                            //System.out.print(estimatedTime/(double)1000000000+ " ");
                            moyenne+=(estimatedTime/(double)1000000000);
                            count++;
                            line = br.readLine();
                        }
                        move_nb.add(current_value);
                        move_nb_average.add(moyenne/count);
                        System.out.println(moyenne/count);
                        System.out.println();
                    }
                }

                br.close();
                fr.close();
                file_writer_class.write_int_file(move_nb, move_nb_average);
            }
            catch (IOException exception)
            {
                System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println ("Le fichier n'a pas été trouvé");
        }
        
    }
}
