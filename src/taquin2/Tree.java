/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taquin2;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class Tree 
{
    private final int size=3;
    private ArrayList<Node_Astar> open_list;
    private ArrayList<Node_Astar> closed_list;
    
    public Tree()
    {
        open_list=new ArrayList<Node_Astar>();
        closed_list=new ArrayList<Node_Astar>();
    }
    
    public ArrayList<Node_Astar> get_open_list()
    {
        return open_list;
    }
    
    public ArrayList<Node_Astar> get_closed_list()
    {
        return closed_list;
    }
    
    
    public void add_node_to_close_list(Node_Astar node)
    {
        closed_list.add(node);
    }
    
    public void add_node_to_open_list(Node_Astar node)
    {
        open_list.add(node);
    }
    
    
    public Node_Astar get_least_heuristic_node()
    {
        int min=1000000;
        int position=0;
        for(int i=0;i<open_list.size();i++)
        {
            if (open_list.get(i).get_f()<min)
            {
                min=open_list.get(i).get_f();
                position=i;
            }
        }
        Node_Astar tmp=open_list.get(position);
        open_list.remove(position);
        return tmp;
    }
    
    
    public boolean are_equals(byte[][] tab1, byte[][] tab2)
    {
        for (int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if (tab1[i][j]!=tab2[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean is_present_in_closed_list(Node node)
    {
        for (int i=0;i<closed_list.size();i++)
        {
            if (are_equals(closed_list.get(i).get_puzzle().get_puzzle(),node.get_puzzle().get_puzzle()))
            {
                return true;
            }
        }
        return false;
    }
    
    
    public boolean is_present_in_open_list(Node node)
    {
        for (int i=0;i<open_list.size();i++)
        {
            if (are_equals(open_list.get(i).get_puzzle().get_puzzle(),node.get_puzzle().get_puzzle()))
            {
                return true;
            }
        }
        return false;
    }
    
    
    public boolean have_better_heuristic_open_list(Node_Astar node)
    {
        for (int i=0;i<open_list.size();i++)
        {
            if (are_equals(open_list.get(i).get_puzzle().get_puzzle(),node.get_puzzle().get_puzzle()))
            {
                if(open_list.get(i).get_f()>node.get_f())
                {
                    open_list.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean have_better_heuristic_closed_list(Node_Astar node)
    {
        for (int i=0;i<closed_list.size();i++)
        {
            if (are_equals(closed_list.get(i).get_puzzle().get_puzzle(),node.get_puzzle().get_puzzle()))
            {
                if(closed_list.get(i).get_f()>node.get_f())
                {
                    closed_list.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
   
    
    
    public float moyenne()
    {
        float total=0;
        for (Node_Astar i :closed_list)
        {
            total+=i.get_h();
        }
        return total/closed_list.size();
    }
}
