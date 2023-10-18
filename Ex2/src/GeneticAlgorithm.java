import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm
{
    private static int numOfQueens; //eisodos programmatos (arithmos vasilisswn)
    private ArrayList<Chromosome> population; // plhthismos xrwmoswmatwn (plhthos katastasewn)
    private ArrayList<Integer> occurrences; // lista me arithmous fitness gia kathe xrwmosoma tou plhthismou

    GeneticAlgorithm(int n)
    {
        this.numOfQueens=n;
        this.population = null;
        this.occurrences = null;
    }
    static int getNumOfQueens(){
        return numOfQueens;
    }

    Chromosome run(int populationSize, double mutationProbability, int maxSteps, int minFitness)
    {

        this.initializePopulation(populationSize); //ftiaxnei populationSize "tuxaies" katastaseis (arxikopoihsh)
        Random r = new Random();
        for(int step = 0; step < maxSteps; step++)  //kapoia oria...
        {
            ArrayList<Chromosome> newPopulation = new ArrayList<>();  //arxikopoihsh gia ton epomeno plhthismo
            for(int i = 0; i < populationSize / 2; i++)  /*h loupa trexei populationSize/2 fores wste to athroisma twn
             paidiwn pou geniountai se kathe epanalhpsh (2 kathe fora) apo to ekastote zeygari na isoutai me ton plhthismo */
            {
              //dialegoume 2 xrwmoswmata me krithrio to fitness(krithrio katallhlothtas)

                int xIndex = this.occurrences.get(r.nextInt(this.occurrences.size())); //pairnei ena tyxaio fitness
                Chromosome xParent = this.population.get(xIndex);   //pairnei to antistoixo xrwmoswma
                int yIndex = this.occurrences.get(r.nextInt(this.occurrences.size()));  //pairnei ena tyxaio fitness
                while(xIndex == yIndex)                 //elegxos oti den pairnoume to idio xrwmoswma
                {
                    yIndex = this.occurrences.get(r.nextInt(this.occurrences.size()));
                }

                Chromosome yParent = this.population.get(yIndex);   //pairnei to antistoixo xrwmoswma
                //We generate the children of the two chromosomes
                Chromosome[] children = this.reproduce(xParent, yParent); //pinakas pou periexei ta paidia

                //We might then mutate the children
                if(r.nextDouble() < mutationProbability)
                {
                        children[0].mutate();
                        children[1].mutate();

                }
                //prosthetei ta duo nea xrwmoswmata ston neo plhthismo
                newPopulation.add(children[0]);
                newPopulation.add(children[1]);
            }
            this.population = new ArrayList<>(newPopulation);   //ananewnei ton plhthismo (idio plhthos)
            //taxinomei me krithrio fitness
            this.population.sort(Collections.reverseOrder());
            //epistrefoume to xrwmoswma me to kalutero fitness an einai apodekto
            if(this.population.get(0).getFitness() >= minFitness) return this.population.get(0);
            //ananewsh twn fitness..
            this.updateOccurrences();
        }

        return this.population.get(0);
    }

    //We initialize the population by creating random chromosomes
    void initializePopulation(int populationSize)
    {
        this.population = new ArrayList<>();
        for(int i = 0; i < populationSize; i++)
        {
            this.population.add(new Chromosome());
        }
        this.updateOccurrences();
    }

    //Updates the list that contains indexes of the chromosomes in the population ArrayList
    void updateOccurrences()
    {
        this.occurrences = new ArrayList<>();
        for(int i = 0; i < this.population.size(); i++)
        {
            for(int j = 0; j < this.population.get(i).getFitness(); j++)
            {
                //Each chromosome index exists in the list as many times as its fitness score
                //By creating this list this way, and choosing a random index from it,
                //the greater the fitness score of a chromosome, the greater chance it will be chosen.
                this.occurrences.add(i);
            }
        }
    }

    //Reproduces two chromosomes and generates their children
    Chromosome[] reproduce(Chromosome x, Chromosome y)
    {
        Random r = new Random();

        //Randomly choose the intersection point
        int intersectionPoint = r.nextInt(numOfQueens);
        int[] firstChild = new int[numOfQueens];
        int[] secondChild = new int[numOfQueens];

        //gemizei tis arxikes theseis gia to prwto paidi me thn aristerh pleura tou  gwniou x
        //gemizei tis arxikes theseis gia to deutero paidi me thn aristerh pleura tou  gwniou y
        for(int i = 0; i < intersectionPoint; i++)
        {
            firstChild[i] = x.getQueensLines()[i];      /*ta paidia einai xrwmoswmata kai oi pinakes
                                                         tous periehoun tis nees theseis grammwn twn vasilisswn*/
            secondChild[i] = y.getQueensLines()[i];

        }
        //gemizei tis telikes theseis gia to prwto paidi me thn dexia pleura tou  gwniou y
        //gemizei tis telikes theseis gia to deutero paidi me thn dexia pleura tou  gwniou x
        for(int i = intersectionPoint; i < firstChild.length; i++)
        {
            firstChild[i] = y.getQueensLines()[i];
            secondChild[i] = x.getQueensLines()[i];
        }
        return new Chromosome[] {new Chromosome(firstChild), new Chromosome(secondChild)}; //ftiaxnei xrwmoswmata
        // me tous pinakes first,second child kai ta bazei ston pinaka Chromosome[] ton opoio  epistrefei
    }
}
