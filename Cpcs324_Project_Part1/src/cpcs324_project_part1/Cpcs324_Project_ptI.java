
package cpcs324_project_part1;

import java.io.*;
import java.util.*;



//application class 
public class Cpcs324_Project_ptI {


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("output.txt");
        PrintWriter output = new PrintWriter(file);
        Scanner input = new Scanner(System.in);
        int algonum = -1;
        int n=0,m=0;
        // here place the code of reading choice of n.m from user then going to then calling method with value(n,m,output)
        //or make it as you like 
       while(algonum != 1 && algonum != 2){
        System.out.println("********Comparison of two of the minimum-spanning-tree algorithms ");
        System.out.println("1-Kruskal algorithm and priority-queue (pq) based Prim");
        System.out.println("2-Prim’s algorithm using min-heap and priority-queue(pq) based Prim");
        System.out.print("Enter your choice: ");
        algonum = input.nextInt();
        
        if(algonum != 1 && algonum != 2) {
            System.out.println("****Wrong input!");
            }
       }
        
        if (algonum == 1 || algonum == 2) {
            System.out.println("\nChoose from the following cases: ");
            System.out.println("1- n=1000 , m=10000");
            System.out.println("2- n=1000 , m=15000");
            System.out.println("3- n=1000 , m=25000");
            System.out.println("4- n=5000 , m=15000");
            System.out.println("5- n=5000 , m=25000");
            System.out.println("6- n=10000 , m=15000");
            System.out.println("7- n=10000 , m=25000");
            System.out.println("8- n=20000 , m=200000");
            System.out.println("9- n=20000 , m=300000");
            System.out.println("10- n=50000 , m=1000000");
            System.out.print("Enter your choice: ");
            int incase = input.nextInt();
            while (incase < 1 || incase > 10) {
                System.out.println("****Wrong input!");
                System.out.print("Enter your choice: ");
                incase = input.nextInt();
            }
            
            switch (incase) {
                case 1: {
                    n = 1000;
                    m = 10000; }
                break;
                case 2: {
                    n = 1000;
                    m = 15000; }
                break;
                case 3: {
                    n = 1000;
                    m = 25000;  }
                break;
                case 4: {
                    n = 5000;
                    m = 15000; }
                break;
                case 5: {
                    n = 5000;
                    m = 25000; }
                break;
                case 6: {
                    n = 10000;
                    m = 15000;  }
                break;
                case 7: {
                    n = 10000;
                    m = 25000; }
                break;
                case 8: {
                    n = 20000;
                    m = 200000;  }
                break;
                case 9: {
                    n = 20000;
                    m = 300000;  }
                break;
                case 10: {
                    n = 50000;
                    m = 1000000; }
                break;
            }
            
        Graph g = new Graph();
        Graph done = g.makeGraph(n, m);
       // done.printGraph(output);
        
        if(algonum==1){
           KruskalAlg MST = new KruskalAlg();
           MST.applyKruskal(done, output);
           output.println("//////////////////////////////////////////////////////////////////");
           output.println("//////////////////////////////////////////////////////////////////");
           PQPrimAlg MST3 = new PQPrimAlg();
           MST3.primsPQ(done, output);
        }
        
        else if(algonum==2){
           MHPrimAlg MST2 = new MHPrimAlg();
           MST2.primMH(done, output);
           output.println("//////////////////////////////////////////////////////////////////");
           output.println("//////////////////////////////////////////////////////////////////");
           PQPrimAlg MST3 = new PQPrimAlg();
           MST3.primsPQ(done, output);
        }
        output.close();
        
    }

}
}