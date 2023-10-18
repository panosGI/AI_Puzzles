import java.util.Random;


public class Chromosome implements Comparable<Chromosome>
    {
        int n= GeneticAlgorithm.getNumOfQueens();

        /*oi deiktes tou pinaka deixnoun thn sthlh ths kathe queen
          kathe stoixeio einai queen me ton deixth na deixnei thn sthlh kai to stoixeio thn grammh*/
        private int[] queensLines;

        //akeraios poy deixnei arithmo fitness/katallhlothtas
        private int fitness;

        //Constructs a randomly created chromosome
        Chromosome()
        {
            this.queensLines = new int[n];
            Random r = new Random();
            for(int i = 0; i < this.queensLines.length; i++)
            {
                this.queensLines[i] = r.nextInt(n);
            }
            this.calculateFitness();
        }

        //Constructs a copy of a chromosome
        Chromosome(int[] qLineArray)
        {
            this.queensLines = new int[n];
            for(int i = 0; i < this.queensLines.length; i++)
            {
                this.queensLines[i] = qLineArray[i];
            }
            this.calculateFitness();
        }

        /* vriskei poses "mh-apeiles" yparxoun gia kathe xrwmoswma
            ara krithrio katallhlothtas -> megethos(pros ta panw) tou fitness(threats)
         */

        void calculateFitness()
        {
            int nonThreats = 0;
            for(int i = 0; i < this.queensLines.length; i++)
            {
                for(int j = i+1; j < this.queensLines.length; j++)
                {
                    if((this.queensLines[i] != this.queensLines[j]) &&
                            (Math.abs(i - j) != Math.abs(this.queensLines[i] - this.queensLines[j])))
                    {
                        nonThreats++;
                    }
                }
            }
            this.fitness = nonThreats;
        }

        //Mutate by randomly changing the position of a queen
        void mutate()
        {
            Random r = new Random();
            this.queensLines[r.nextInt(n)] = r.nextInt(n);
            this.calculateFitness();
        }

        public int[] getQueensLines() {
            return this.queensLines;
        }

        public void setQueensLines(int[] queensLines) {
            this.queensLines = queensLines;
        }

        public int getFitness() {
            return this.fitness;
        }

        public void setFitness(int fitness) {
            this.fitness = fitness;
        }

        void print()
        {
            System.out.print("Chromosome : |");
            for(int i = 0; i < this.queensLines.length; i++)
            {
                System.out.print(this.queensLines[i]);
                System.out.print("|");
            }
            System.out.print(", Fitness : ");
            System.out.println(this.fitness);

            System.out.println("------------------------------------");
            for(int i = 0; i < this.queensLines.length; i++)
            {
                for(int j = 0; j < this.queensLines.length; j++)
                {
                    if(this.queensLines[j] == i)
                    {
                        System.out.print("|Q");
                    }
                    else
                    {
                        System.out.print("| ");
                    }
                }
                System.out.println("|");
            }
            System.out.println("------------------------------------");
        }

        //compareTo another chromosome, kanei taxinomhsh meta apo sugkrish katallhlothtas
        @Override
        public int compareTo(Chromosome x)
        {
            return this.fitness - x.fitness;
        }
    }
