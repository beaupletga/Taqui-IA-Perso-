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
public class BFS {
    
    private LinkedList<Node> queue;
    
    public BFS(Puzzle puzzle)
    {
        queue= new LinkedList<Node>();
        Node new_node=new Node();
        new_node.set_puzzle(puzzle.get_puzzle());
        queue.add(new_node);
    }
    
    public void add_neighbors(Node new_node)
    {
        ArrayList<byte[][]> result=new_node.get_puzzle().next_position_available();
        Node node_tmp;
        for (int i=0;i<result.size();i++)
        {
            node_tmp= new Node();
            node_tmp.set_puzzle(result.get(i));
            node_tmp.deep_journey_copy(new_node);
            node_tmp.add_element_to_journey(new_node.get_puzzle());
            queue.addLast(node_tmp);
        }
    }
    
    public void start_bfs()
    {
        int count=0;
        Node node_tmp;
        while(queue.size()>0 && !queue.get(0).get_puzzle().is_solution())
        {
            count++;
            node_tmp= new Node();
            node_tmp=queue.remove();
            add_neighbors(node_tmp);
        }
        System.out.println(count);
        queue.getFirst().display_journey();
        System.out.println(queue.getFirst().get_journey().size());
    }
    
}
