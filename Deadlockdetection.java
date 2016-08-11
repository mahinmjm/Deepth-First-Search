/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
//package deadlockdectection;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Deadlockdectection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc =  new Scanner(System.in);
        
        int allocationGraph[][];
        
        
        System.out.println("Total No of Process and Resources");
        int numberOfProcess = sc.nextInt();
        int numberOfResource = sc.nextInt();
        int numberOfNodes = numberOfProcess+numberOfResource;
        allocationGraph = new int[numberOfNodes][numberOfNodes];
        
        System.out.println("Enter Resource Allocation Graph");
        
        for(int i=0;i<numberOfNodes;i++){
            for(int j=0;j<numberOfNodes;j++){
                allocationGraph[i][j]=sc.nextInt();
            }
        }
        
        
        CycleDetection cd = new CycleDetection(numberOfNodes);
        cd.DFS(allocationGraph);
        
    }
    
}



class CycleDetection{
    int color[];
    int d[];
    int f[];
    int time;
    int prev[];
    int numberOfNodes;
    int allocationGraph[][];
    
    public CycleDetection(){}
    
    public CycleDetection(int numberOfNodes){
        this.numberOfNodes = numberOfNodes;
        color = new int[numberOfNodes];
        d = new int[numberOfNodes];
        f = new int[numberOfNodes];
        prev = new int[numberOfNodes];
        
    }
    
    void DFS(int allocationGraph[][]){
        this.allocationGraph = allocationGraph;
        for(int i=0;i<numberOfNodes;i++){
            color[i]=0; // 0 = white
            d[i]=Integer.MAX_VALUE;
            f[i]=Integer.MAX_VALUE;
            prev[i]=0;
        }
        
        time=0;
        
        for(int i=0; i<numberOfNodes;i++){
            if(color[i]==0){
                DFS_Visit(i);
            }
        }
        
    }
    
    void DFS_Visit(int u){
        
        color[u] = 1; // 1 = gray
        
        time +=1;
        
        d[u]=time;
        
        for(int v=0;v<numberOfNodes;v++){
            if(allocationGraph[u][v]==1 && color[v]==0){
                prev[v]=u;
                DFS_Visit(v);
            }else if(color[v]==1){
                System.out.println("Is in Deadlock");
                //break;
            }
        }
        
        color[u]=2; // 2=black
        time +=1;
        f[u]=time;        
        
    }
    
    
    
}
