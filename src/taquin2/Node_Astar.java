/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquin2;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author gabriel
 */
public class Node_Astar extends Node
{
    private int g;
    private int h;
    private int f;
    
    public Node_Astar(String puzzle_string)
    {
        puzzle= new Puzzle();
        journey=new LinkedList<Puzzle>();
        journey.add(puzzle);
        puzzle.generate_puzzle(puzzle_string);
        g=0;
        h=puzzle.get_h();
        f=g+h;
    }
    
    public Node_Astar(Puzzle new_puzzle,int new_cost, LinkedList<Puzzle> new_journey)
    {
        puzzle=new_puzzle;
        g=new_cost+1;
        h=new_puzzle.get_h();
        f=g+h;
        journey=new LinkedList<Puzzle>();
        for(Puzzle i : new_journey)
        {
            journey.add(i);
        }
        journey.add(puzzle);
    }
    
    public int get_g()
    {
        return g;
    }
    
    public int get_h()
    {
        return h;
    }
    
    public int get_f()//get heuristic
    {
        return f;
    }
    
    
    public ArrayList<Node_Astar> get_next_nodes()
    {
        ArrayList<byte[][]> result=puzzle.next_position_available();
        ArrayList<Node_Astar> result_node= new ArrayList<Node_Astar>();
        for (int i=0;i<result.size();i++)
        {
            Puzzle puzzle= new Puzzle();
            puzzle.set_puzzle(result.get(i));
            Node_Astar node= new Node_Astar(puzzle,g,journey);
            result_node.add(node);
        }
        return result_node;
    }
}
