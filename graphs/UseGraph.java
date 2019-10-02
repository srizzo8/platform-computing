import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

//----------------------------------------------------------------------------
// UseGraph.java             by Dale/Joyce/Weems                    Chapter 10
//
// Examples of uses of the Graph ADT.
//----------------------------------------------------------------------------


public class UseGraph
{

private static boolean isPathDF(WeightedGraphInterface<String> graph,
                                String startVertex, 
                                String endVertex    )
// Returns true if a path exists on graph, from startVertex to endVertex; 
// otherwise returns false. Uses depth-first search algorithm.
{
  Stack<String> stack = new Stack<String>();
  Queue<String> vertexQueue = new ArrayDeque<String>();
 
  boolean found = false;
  String currVertex;      // vertex being processed
  String adjVertex;       // adjacent to currVertex
 
  graph.clearMarks();
  graph.markVertex(startVertex);
  stack.push(startVertex);
  
  do
  {
    currVertex = stack.pop();
    //System.out.println(currVertex);
    if (currVertex.equals(endVertex))
       found = true;
    else
    {
      vertexQueue = graph.getToVertices(currVertex); 
      while (!vertexQueue.isEmpty())
      {
        adjVertex = vertexQueue.remove();
        if (!graph.isMarked(adjVertex))
        {
          graph.markVertex(adjVertex);
          stack.push(adjVertex);
        }
      }
    }
  } while (!stack.isEmpty() && !found);
  
  return found;
}

private static boolean isPathBF(WeightedGraphInterface<String> graph,
                                String startVertex, 
                                String endVertex    )

// Returns true if a path exists on graph, from startVertex to endVertex; 
// otherwise returns false. Uses breadth-first search algorithm.

{
  Queue<String> queue = new ArrayDeque<String>();
  Queue<String> vertexQueue = new ArrayDeque<String>();
 
  boolean found = false;
  String currVertex;      // vertex being processed
  String adjVertex;       // adjacent to currVertex

  graph.clearMarks();
  graph.markVertex(startVertex);
  queue.add(startVertex);
  
  do
  {
    currVertex = queue.remove();
   // System.out.println(currVertex);
    if (currVertex.equals(endVertex))
       found = true;
    else
    {
      vertexQueue = graph.getToVertices(currVertex); 
      while (!vertexQueue.isEmpty())
      {
        adjVertex = vertexQueue.remove();
        if (!graph.isMarked(adjVertex))
        {
          graph.markVertex(adjVertex);
          queue.add(adjVertex);
        }
      }
    }
  } while (!queue.isEmpty() && !found);
  
  return found;
}


  public static void main(String[] args) 
  {
    // Create and analyze graph in Figure 10.3
    System.out.println("Creating graph in figure 10.3");
    WeightedGraphInterface<String> graph = new WeightedGraph<String>();
    String s0 = new String("Atlanta   ");
    String s1 = new String("Austin    ");
    String s2 = new String("Chicago   ");
    String s3 = new String("Dallas    ");
    String s4 = new String("Denver    ");
    String s5 = new String("Houston   ");
    String s6 = new String("Washington");

    //add cities as a vertex to the graph
    graph.addVertex(s0);
    graph.addVertex(s1);
    graph.addVertex(s2);
    graph.addVertex(s3);
    graph.addVertex(s4);
    graph.addVertex(s5);
    graph.addVertex(s6);

    //add edges
    //edges will be represented in the adjacency matrix
    //Try removing edges to see if you get a false returned
    graph.addEdge(s0, s5, 800);
    graph.addEdge(s0, s6, 600);
    graph.addEdge(s1, s3, 200);
    graph.addEdge(s1, s5, 160);
    graph.addEdge(s2, s4, 1000);
    graph.addEdge(s3, s1, 200);
    graph.addEdge(s3, s2, 900);
    graph.addEdge(s3, s4, 780);
    graph.addEdge(s4, s0, 1400);
    graph.addEdge(s4, s2, 1000);
    graph.addEdge(s5, s0, 800);
    graph.addEdge(s6, s0, 600);
    graph.addEdge(s6, s3, 1300);

    boolean result;

    System.out.println("Question: Is there a flight from Atlanta to Austin?");
    System.out.println("Determining path using depth first ...");
    System.out.println();
    
    System.out.println(s0 + " to " + s1+ " expected true");
    result = isPathDF(graph, s0, s1);
    System.out.println("Actual:"+result);
    System.out.println();
    
    System.out.println();
    System.out.println("Determining path using breadth first ...");
    System.out.println();
    
    System.out.println(s0 + " to " + s1 +" expected true");
    result = isPathBF(graph, s0, s1);
    System.out.println("Actual:" +result);
    System.out.println();
    
    System.out.println("Question: Is there a flight from Atlanta to Washington?");
    System.out.println("Determining path using depth first ...");
    System.out.println();
    
    System.out.println(s0 + " to " + s6+ " expected true");
    result = isPathDF(graph, s0, s6);
    System.out.println("Actual:" +result);
    System.out.println();
    
    System.out.println();
    System.out.println("Determining path using breadth first ...");
    System.out.println();
    
    System.out.println(s0 + " to " + s6 + " expected true");
    result = isPathBF(graph, s0, s6);
    System.out.println("Actual: "+result);
    System.out.println();
    
    } 
} 