/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquin2;

import java.util.LinkedList;

/**
 *
 * @author gabriel
 */
public class Node 
{
    protected Puzzle puzzle=new Puzzle();
    
    protected LinkedList<Puzzle> journey=new LinkedList<Puzzle>();

    
    public Puzzle get_puzzle()
    {
        return puzzle;
    }
    
    public LinkedList<Puzzle> get_journey()
    {
        return journey;
    }
    
    public void set_puzzle(byte[][] new_one)
    {
        puzzle.set_puzzle(new_one);
    }
    
    
    public void display_journey()
    {
        for(Puzzle i : journey)
        {
            i.display_puzzle();
            System.out.println();
        }
    }
    
    public void deep_journey_copy(Node new_node)
    {
        for (Puzzle i : new_node.get_journey())
        {
            journey.add(i);
        }
    }
    
    public void add_element_to_journey(Puzzle puzzle)
    {
        journey.add(puzzle);
    }
}
