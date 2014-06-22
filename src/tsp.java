import java.util.Random;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;



class Permute {

    public static List<List<Integer>> permute(Integer... myInts){
    	
    	

        if(myInts.length==1){
            List<Integer> arrayList = new ArrayList<Integer>();
            arrayList.add(myInts[0]);
            List<List<Integer> > listOfList = new ArrayList<List<Integer>>();
            listOfList.add(arrayList);
            return listOfList;
        }

        Set<Integer> setOf = new HashSet<Integer>(Arrays.asList(myInts));   

        List<List<Integer>> listOfLists = new ArrayList<List<Integer>>();

        for(Integer i: myInts){
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            arrayList.add(i);

            Set<Integer> setOfCopied = new HashSet<Integer>();
            setOfCopied.addAll(setOf);
            setOfCopied.remove(i);

            Integer[] isttt = new Integer[setOfCopied.size()];
            setOfCopied.toArray(isttt);

            List<List<Integer>> permute = permute(isttt);
            Iterator<List<Integer>> iterator = permute.iterator();
            while (iterator.hasNext()) {
                List<java.lang.Integer> list = iterator.next();
                list.add(i);
                listOfLists.add(list);
            }
        }   

        return listOfLists;
    }

}
public class tsp {
	
	
	
	public static void main(String[] args)
	{
		
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;
				
		List<List<Integer>> permute = Permute.permute(0,1,2,3,4,5,6,7);
		System.out.println("Adjacency matrix of random weighted graph \n");
		randomgraph(8, 100, 12754);
		display();
		
		int min_length = 9999;
		int min_index = 0;
		
		for (int i=0;i<permute.size();i++)
		{
			
			int intermediate_sum = 0;
			
			intermediate_sum += edgeLength(0,permute.get(i).get(0));
			for (int k=0;k<n-1;k++)
			{
				intermediate_sum += edgeLength(permute.get(i).get(k),permute.get(i).get(k+1));	
			}
			intermediate_sum += edgeLength(0,permute.get(i).get(n-1));
			
			if (intermediate_sum < min_length)
			{
				min_length = intermediate_sum;
				min_index = i;
				
			}
			
		}
		
		elapsedTime = (new Date()).getTime() - startTime;
		System.out.println("The Minimum Length Path weight is "+ min_length+"\n");
		System.out.println("The Shortest path through each node is: ");
		System.out.println(permute.get(min_index));
		System.out.println("\n");
		System.out.println("Elapsed Time: "+elapsedTime+" miliseconds.");
		
	}
	
	 public static int current_edge;                     
	 public static int[][] M;
	 public static int[][] paths;	
	 public static int n;					
	 public static int nVerts;					
	 public static int x;					
	 public static int y;                                       
	 public static int[] next;                                

public static void randomgraph(int a, int prob, long theseed) 
{
	
	double b;			
	int i, j;
	
	
	n = a;						
	x = 0; 						
	y = 0;                                            
	M = new int[n][n];             			
	nVerts = n;					
	next = new int[n];                  		
	
	for(i=0; i < nVerts; i++)			
	next[i]=-1;
	
	Random gen1 = new Random();
	Random gen2 = new Random(theseed);
	Random mygen; 
	
	if (theseed == -1)
	mygen = gen1;
	else
	mygen = gen2;
	
	for(i=0; i < nVerts; i++) 
	{
		for(j=0; j < nVerts; j++) 
		{
			if (i == j)
			{
				M[i][j]=0;
			}	
			else if (j < i)
			{
				M[i][j] = M[j][i];
			}	
				
			else 
			{
				b = mygen.nextDouble() * 100;
				if (b <= prob)
				{
					M[i][j] = gen1.nextInt(100)+1 ;
				}	
				else
				{
					M[i][j] = 0;                  
			
				}
			}
		}
	}
}


//------------------------------------------------------------------------
public void insertVertex(int a, int x, int y)	
{
	if(x == y)					
	{
		if(a != 0)                                     
		{
			System.out.println("Cannot initialize");
			System.exit(0);
		}
	}

	M[x][y] = a;					

}

//------------------------------------------------------------------------
public static void display()
{
	System.out.println("");    				
	for(int row=0; row<n; row++)
	{
		for(int col=0; col<n; col++)
		{
			System.out.print(M[row][col] + " ");
		}
		System.out.println("");
	}
}

//------------------------------------------------------------------------
public static int edgeLength(int a, int b)
{
	return M[a][b];					

}


}


