import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Queue;

//----------------------------------------------------------------------------
// WeightedGraph.java            by Dale/Joyce/Weems                Chapter 10
//
// Implements a directed graph with weighted edges.
// Vertices are objects of class T and can be marked as having been visited.
// Edge weights are integers.
// Equivalence of vertices is determined by the vertices' equals method.
//
// General precondition: Except for the addVertex and hasVertex methods, 
// any vertex passed as an argument to a method is in this graph.
//----------------------------------------------------------------------------

//For Lab 3, we are using an undirected graph. However, we can use a lot of the stuff in WeightedGraph.java in Lab 3.

public class WeightedGraph<T> implements WeightedGraphInterface<T>
{
  public static final int NULL_EDGE = 0;
  private static final int DEFCAP = 50;  // default capacity
  private int numVertices; //the size of the graph; how many verticies are in the graph
  private int maxVertices;
  private T[] vertices; //An array of verticesl
  private int[][] edges; //Our adjacency matrix
  private boolean[] marks;  // marks[i] is mark for vertices[i]

  public WeightedGraph()
  // Instantiates a graph with capacity DEFCAP vertices.
  {
    numVertices = 0;
    maxVertices = DEFCAP;
    vertices = (T[]) new Object[DEFCAP];
    marks = new boolean[DEFCAP];
    edges = new int[DEFCAP][DEFCAP];
  }
 
  public WeightedGraph(int maxV)
  // Instantiates a graph with capacity maxV.
  {
    numVertices = 0;
    maxVertices = maxV;
    vertices = (T[]) new Object[maxV];
    marks = new boolean[maxV];
    edges = new int[maxV][maxV];
  }

  public boolean isEmpty() //returns true if the size of the graph is 0.
  // Returns true if this graph is empty; otherwise, returns false.
  {
	  return (numVertices == 0);
  }

  public boolean isFull()
  // Returns true if this graph is full; otherwise, returns false.
  {
	  return (numVertices==maxVertices);
  }

  public void addVertex(T vertex)
  // Preconditions:   This graph is not full.
  //                  vertex is not already in this graph.
  //                  vertex is not null.
  //
  // Adds vertex to this graph.
  {
    //if we say add philly, what instance variable is going to change? What are we going to add to? 
	  vertices[numVertices] = vertex;
	  //Set our edges here:
	  for(int i = 0; i < numVertices; i++)
	  {
		  edges[numVertices][i] = NULL_EDGE;
		  edges[i][numVertices] = NULL_EDGE;
	  }
	  numVertices++;
  }

  public boolean hasVertex(T vertex) //We want to search the array of vertices for a vertex.
  // Returns true if this graph contains vertex; otherwise, returns false.
  {
	 for(int i = 0; i < numVertices; i++)
	 {
		 if(vertices[i].equals(vertex)) //We are checking to see if the vertex is in the array. If it is, return true.
		 {
			 return true;
		 }
	 }
	  return false;
  }
  
  private int indexIs(T vertex) //Takes a vertex and returns the index place of that vertex. Private method, so it can't be used outside of the class.
  // Returns the index of vertex in vertices.
  {
	  //We have to return the index. Instead of returning true, we will return i.
	  for(int i = 0; i < numVertices; i++)
		 {
			 if(vertices[i].equals(vertex)) //We are checking to see if the vertex is in the array. If it is, return true.
			 {
				 return i;
			 }
		 }
		 return -1;
  }

  public void addEdge(T fromVertex, T toVertex, int weight) //If you want to add the edge from Denver to Chicago, it will look like this: add(Denver (row), Chicago (column), 1000);. We need to know the index of Denver, the index of Chicago, and its weight.
  // Adds an edge with the specified weight from fromVertex to toVertex.
  {
	  int row = indexIs(fromVertex); //this is the row
	  int col = indexIs(toVertex);
	  edges[row][col] = weight;
  }

  public int weightIs(T fromVertex, T toVertex)
  // If edge from fromVertex to toVertex exists, returns the weight of edge (like a distance);
  // otherwise, returns a special “null-edge” value.
  {
	  int row = indexIs(fromVertex); //this is the row
	  int col = indexIs(toVertex);
	  return edges[row][col];
	  //return edges[indexIs(fromVertex)][indexIs(toVertex)];
  }

  public Queue<T> getToVertices(T vertex)
  // Returns a queue of the vertices that vertex is adjacent to.
  {
	  //We are just doing the adjacent vertices.
	  //We need to create a queue and then get the index of the vertices.
	  //Then we will check the column.
	  
	  Queue<T> queue = new ArrayDeque<>(); //<T> in Queue means that it can take any type.
	  int row = indexIs(vertex);
	  for(int col = 0; col < numVertices; col++)
	  {
		  if(edges[row][col] != NULL_EDGE)
		  {
			  queue.add(vertices[col]); //vertices[col] is the actual adjacent vertices.
		  }
	  }
	  return queue;
  }
  
  //We often want to know if there are paths between vertices.

  public void clearMarks()
  // Unmarks all vertices.
  {
	  marks = new boolean[maxVertices]; //maxVertices is the capacity. You want to create an array with maxVertices as the size.
	  //This should start off as all false.
	  //When you declared a new boolean array, you were creating a local array instead of an instance array. The marks array was not being reset; it was being used as a local variable.
  }

  public void markVertex(T vertex)
  // Marks vertex.
  {
	  //You want to get the index place of the vertex.
	  //Indexis method we worke don Wednesday
	  
	  int index = indexIs(vertex); //You are taking it from the big array.
	  
	  marks[index] = true;
	  
  }

  public boolean isMarked(T vertex)
  // Returns true if vertex is marked; otherwise, returns false.
  {
	 int index2 = indexIs(vertex);
	 if(marks[index2] == true)
	 {
		 return true;
	 }
	 else
	 {
		 return false;
	 }
  }
  
  public T getUnmarked()
  // Returns an unmarked vertex if any exist; otherwise, returns null.
  {
	  //You need a for loop to search for something that isn't marked. The firts one you find, you are going to return it.
	  
	  //You want to search for a vertex that is NOT marked.
	  
	  for(int w = 0; w < vertices.length; w++)
	  {
		 if(marks[w] == false)
		 {
			 return vertices[w];
		 }
	  } 
	  return null;
  }
  
  public boolean edgeExists(T vertex1, T vertex2)
  // Preconditions:  vertex1 and vertex2 are in the set of vertices
  //
  // Return value = (vertex1, vertex2) is in the set of edges
  {
    return (edges[indexIs(vertex1)][indexIs(vertex2)] != NULL_EDGE);
  }

  public boolean removeEdge(T vertex1, T vertex2)
  // Preconditions:  vertex1 and vertex2 are in the set of vertices
  //
  // Return value = true if edge was in the graph (and has been removed)
  //              = false if edge was not in the graph
  {
    boolean existed = edgeExists(vertex1, vertex2);
    edges[indexIs(vertex1)][indexIs(vertex2)] = NULL_EDGE;
    return existed;
  }
  
}