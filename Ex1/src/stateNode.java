import java.util.ArrayList;

public class stateNode implements Comparable<stateNode> {
    /*ftiaxe pinaka poy periehei arithmo kanibalwn kai ierapostolwn se kathe oxthi,
    thn thesi kai twn arithmo atomwn ths barkas*/
    private int[] state={0,0,0,0,0,0};
    private int score = 0;
    private int  h,g;
    stateNode father =null;


    public  stateNode(int misL,int canL,int boatCapacity,int misR,int canR,int boatIsL){
        this.state[0]=misL;
        this.state[1]=canL;
        this.state[2]=boatCapacity;
        this.state[3]=misR;
        this.state[4]=canR;
        this.state[5]=boatIsL;

        this.h = (this.state[0]+this.state[1])/boatCapacity; //h=((missionariesLeft+cannibalsLeft) /maxPeopleInBoat)
        this.score=this.h+this.g;

    }

    public stateNode(int [] A){
        for (int i =0;i<=5;i++){
            this.state[i]=A[i];

        }
    }

    public void  setg(int g){
        this.g=g;
    }
    public int getg(){
        return g;
    }

    public void seth(int h){
        this.h=h;
    }

    public int geth(){
        return h;
    }

    public void setFather(stateNode father){
        this.father=father;
    }

    stateNode getFather()
    {
        return this.father;
    }

    public void setScore(int g, int h){
        this.score = g +h;
    }

    public int getScore(){
        return this.score;
    }

    boolean isFinal(){
        if(this.state[0]==0 && this.state[1]==0){
            return true;
        }else{
            return false;
        }

    }
    ArrayList<stateNode> getChildren(){

        ArrayList<stateNode> children = new ArrayList<>();

        for(int i=0;i<=this.state[2];i++){

            for(int j=0;j<=this.state[2];j++) {
                if (this.state[5]==1) {
                    if ((i + j) != 0 && i + j <= this.state[2] && i <= this.state[0] && j <= this.state[1]) {
                        boolean pass = false;
                        boolean pass2 = false;
                        if (this.state[0] - i > 0) {

                            if (this.state[0] - i >= this.state[1] - j) {
                                pass = true;
                            }
                        } else if (this.state[0] - i == 0) {
                            pass = true;
                        }
                        if (this.state[3] + i > 0) {
                            if (this.state[3] + i >= this.state[4] + j) {
                                pass2 = true;
                            }
                        } else if (this.state[3] + i == 0) {
                            pass2 = true;
                        }
                        if (pass && pass2) {
                            stateNode child = new stateNode(this.state[0] - i, this.state[1] - j, this.state[2], this.state[3] + i, this.state[4] + j, 0);
                            child.setFather(this);
                            child.setg(this.g+1);
                            child.setScore(child.getg(),child.geth());
                            children.add(child);

                        }
                    }
                }
                else{
                    if((i+j)!=0&&i+j<=this.state[2]&&i<=this.state[3]&&j<=this.state[4]){
                        boolean pass=false;
                        boolean pass2=false;
                        if(this.state[3]-i>0) {

                            if (this.state[3] - i >= this.state[4] - j) {
                                pass=true;
                            }
                        }
                        else if(this.state[3]-i==0){
                            pass=true;
                        }
                        if(this.state[0]+i>0){
                            if(this.state[0]+i>=this.state[1]+j){
                                pass2=true;
                            }
                        }else if(this.state[0]+i==0){
                            pass2=true;
                        }
                        if(pass&&pass2){
                            stateNode child = new stateNode(this.state[0]+i,this.state[1]+j,this.state[2],this.state[3]-i,this.state[4]-j,1);
                            child.setFather(this);
                            child.setg(this.g+1);
                            child.setScore(child.getg(),child.geth());
                            children.add(child);
                        }
                    }
                }
            }
        }
        return children;
    }

    void print() {
        System.out.println("------------------------------------");
        String s ="M ";
        String c = "C ";
        if(this.state[0]==0){
            System.out.print(multiply("  ", this.state[1])); //2kena gia kathe "C "
            System.out.print("            |~~|            "); //3tab -potami(1tab)-3tab
            System.out.println(multiply(s, this.state[3]));

            System.out.print(multiply("  ", this.state[1])); //2kena gia kathe "M "

            if (this.state[5] == 1) {
                System.out.print("    (__)    |~~|            "); //1tab+barka+1tab-potami-3tab
            } else {
                System.out.print("            |~~|    (__)    "); //3tab-potami-1tab+barka+1tab
            }

            System.out.println();

            System.out.print(multiply(c, this.state[1]));
            System.out.print("            |~~|            "); //1tab+barka+1tab-potami-3tab
            System.out.println(multiply(c, this.state[4]));
            System.out.println("------------------------------------");


        }
        else {
            System.out.print(multiply(s, this.state[0]));
            System.out.print("            |~~|            "); //3tab -potami(1tab)-3tab
            System.out.println(multiply(s, this.state[3]));


            System.out.print(multiply("  ", this.state[0])); //2kena gia kathe "M "

            if (this.state[5] == 1) {
                System.out.print("    (__)    |~~|            "); //1tab+barka+1tab-potami-3tab
            } else {
                System.out.print("            |~~|    (__)    "); //3tab-potami-1tab+barka+1tab
            }

            System.out.println("    "); //1tab

            //System.out.println();

            System.out.print(multiply(c, this.state[1]));
            System.out.print(multiply("  ", this.state[0] - this.state[1]));
            System.out.print("            |~~|            "); //1tab+barka+1tab-potami-3tab
            System.out.println(multiply(c, this.state[4]));
            System.out.println("------------------------------------");
        }

    }


    private static String multiply(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }



    @Override
    public int compareTo(stateNode o) {
        if(this.getScore()<=o.getScore()){
            return this.getScore();
        }else {
            return o.getScore();
        }

    }
}