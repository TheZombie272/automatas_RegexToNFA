import algoritm.DanielAlgorithm;

public class App {
    
    private DanielAlgorithm danielAlgorithm;
    public static void main(String[] args) throws Exception {
        String regex="ab+c";

        DanielAlgorithm danielAlgorithm=new DanielAlgorithm();
        System.out.println(danielAlgorithm.regexToNFA(regex, false).toString());
    }

}
