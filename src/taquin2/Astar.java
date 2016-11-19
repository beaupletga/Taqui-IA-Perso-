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
public class Astar 
{    
    public Astar(String puzzle_string)
    {
        Tree tree= new Tree();

        Node_Astar root=new Node_Astar(puzzle_string);

        tree.add_node_to_open_list(root);//

        while (tree.get_open_list().size()>0)
        {            
            Node_Astar current_node=tree.get_least_heuristic_node();

            ArrayList<Node_Astar> successors= current_node.get_next_nodes();

            if (current_node.get_h()==0)
            {
                break;
            }

            Node_Astar successor;
            for (int i=0;i<successors.size();i++)
            {
                successor=successors.get(i);
                if(tree.is_present_in_closed_list(successor))
                {
                   if(tree.have_better_heuristic_closed_list(successor))
                   {
                       tree.add_node_to_open_list(successor);
                   }
                }
                else
                {
                    if (tree.is_present_in_open_list(successor))
                    {
                        if(tree.have_better_heuristic_open_list(successor))
                        {
                            tree.add_node_to_open_list(successor);
                        }
                    }
                    else
                    {
                        tree.add_node_to_open_list(successor);
                    }
                }
            }
            tree.add_node_to_close_list(current_node);
        }
    }
}
