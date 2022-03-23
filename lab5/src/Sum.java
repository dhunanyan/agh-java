import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        for(Node var : args ){
            result = result + var.evaluate();
        }
        return sign*result;
    }

    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");

        for(int i  = 0 ; i < args.size(); i++){
            Node var = args.get(i);
            if(i == 0) {
                b.append(var);
            } else if(i > 0){
                b.append(var.getSign() > 0 ? "+" : "");
                b.append(var);
            }
        }

        if(sign<0)b.append(")");
        return b.toString();
    }

    boolean isZero(){
        return false;
    }

    Node diff(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            Node temp = n.diff(var);
//            System.out.println(n.getClass());
//            System.out.println(temp.getClass());
//            System.out.println(temp);
//            for(int i = 0; i < ((Prod) temp).args.size(); i++){
//
//            }
            r.add(n.diff(var));
        }
        return r;
    }
}

