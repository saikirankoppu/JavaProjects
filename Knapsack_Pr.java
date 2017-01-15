class Knapsack_Pr {
  public static void main(String[] args) throws Exception {
    int prof[] = {6,5,8,9,6,7,3};
	int wt[] = {2,3,6,7,5,9,4};
    int W = 9;
    System.out.println("Profit " + knapsack(prof, wt, W));
  }

  public static int knapsack(int prof[], int wt[], int W) {
    int N = wt.length; // number of items
    int[][] V = new int[N + 1][W + 1]; // matrix from 0-N and 0-W
    int[][] T = new int[N + 1][W + 1];
    // if w=0 then 0
    for (int col = 0; col <= W; col++) {
      V[0][col] = 0;
      T[0][col] = 0;
    }
    //if no item then 0
    for (int row = 0; row <= N; row++) {
      V[row][0] = 0;
      T[row][0] = 0;
    }
    for (int item = 1; item <= N; item++) {
      //filling the matrix r
      for (int weight = 1; weight <= W; weight++) {

        if (wt[item - 1] <= weight) {
          //current weight, check  value of the current item + value of the item that we could afford with the remaining
          //is greater than the value without the current item itself
          if (prof[item - 1] + V[item - 1][weight - wt[item - 1]] > V[item - 1][weight]) {
            V[item][weight] = prof[item - 1] + V[item - 1][weight - wt[item - 1]];
            T[item][weight] = 1;
          } else {
            V[item][weight] = V[item - 1][weight];
            T[item][weight] = 0;
          }
        } else {
          V[item][weight] = V[item - 1][weight];
          T[item][weight] = 0;
        }
      }
    }

    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= W; j++) {
        System.out.print("  " + V[i][j]);

      }
      System.out.println("");
    }
    System.out.println("T matrix");
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= W; j++) {
        System.out.print("  " + T[i][j]);

      }
      System.out.println("");
    }
    // to get the selected items
    selecteditems(V, T, N, W, V[N][W], wt);
    return V[N][W];
  }
  public static void selecteditems(int V[][], int T[][], int N, int W, int c, int wt[]) {
    //for(int j=W;j>0;j--)
    // if the V[i][j]!=V[i-1][j] then i th element is included else not included;if included we need to subtract ith weight and go to [j-ith] column and check the same 
    int j = W;
    for (int i = N; i > 0; i--)
      if (V[i][j] != V[i - 1][j]) {
        System.out.println("selected item: " + i);
        j = j - (wt[i - 1]);
        //c-=(wt[i-1]);
      }
  }
}