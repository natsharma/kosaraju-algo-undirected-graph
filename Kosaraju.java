import java.util.HashSet;
import java.util.Stack;

public class Kosaraju {
	
	public static void main(String [] args) {
		String[] related = {"1100", "1110", "0110", "0001"};
		//undirected graph
		int[][] matrix = {{1, 0, 0, 0, 1, 0},
						  {0, 1, 1, 0, 1, 0},
						  {0, 1, 1, 0, 0, 0},
						  {0, 0, 0, 1, 0, 1},
						  {1, 1, 0, 0, 1, 0},
						  {0, 0, 0, 1, 0, 1}};
		System.out.println(kosaraju(matrix));
	}

	/*
	 * Kosaraju's Algorithm to find strongly connected components
	 * This algorithm is for an undirected graph
	 * Matrix representation of edges from node i to j
	 * edge = 1
	 */
	public static int kosaraju(int[][] matrix) {
		int count = 0;
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for(int curr = 0; curr<matrix.length; curr++) {
			//if i has not been explored yet, you have found a new circle!
			if(!visited.contains(curr)) {
				visited.add(curr);
				count++;
				//add all its adjacent nodes if they haven't been visited
				for(int adj = 0; adj<matrix[curr].length; adj++) {
					//does not count edges from node to itself
					if(adj != curr) {
						int edge = matrix[curr][adj];
						if(edge==1 && !visited.contains(adj)) {
							stack.push(adj);
							visited.add(adj);
						}
					}
				}
			}
			//now traverse the stack, and push adjacents of adjacents to the stack until empty
			while(!stack.isEmpty()) {
				int current = stack.pop();
				for(int adj = 0; adj<matrix[current].length; adj++) {
					if(adj != current) {
						int edge = matrix[current][adj];
						if(edge==1 && !visited.contains(adj)) {
							stack.push(adj);
							visited.add(adj);
						}
					}
				}
			}
		}
		return count;
	}

}
