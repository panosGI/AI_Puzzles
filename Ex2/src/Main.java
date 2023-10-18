import java.util.Scanner;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Give the number of queens : ");
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        //the
        long start = System.currentTimeMillis();
        GeneticAlgorithm algorithm = new GeneticAlgorithm(n);
        //populationSize, mutationProbability, maximumSteps, minimumFitness
        Random r = new Random();
        Chromosome solution = algorithm.run(10000, r.nextDouble(), 100000, (n*n-n)/2);
        long end = System.currentTimeMillis();
        long time = end - start;
        solution.print();
        System.out.println("Run time : " + time + " ms");
    }
}



