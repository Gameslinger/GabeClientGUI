/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLI.commands.frac;

/**
 *
 * @author Gabriel.Maxfield
 */
public class Fraction {

    private int a, b, c;

    boolean isClear = false;

    public Fraction(int a, int b) {
        //Test for division by zero here?
        this.a = a;
        this.b = b;
    }

    public static Fraction parseFrac(String str) {
        //
        //str = str.replace(" ", "");
        //
        int a, b, c, length;
        String parts[];
        try {
            parts = str.split("[/_]");
            length = parts.length;
            switch (length) {
                case 1:
                    a = Integer.parseInt(str);
                    b = 1;
                    break;
                case 3:
                    a = Integer.parseInt(parts[1]);
                    b = Integer.parseInt(parts[2]);
                    c = Integer.parseInt(parts[0]);
                    a += c * b;
                    break;
                default:
                    a = Integer.parseInt(parts[0]);
                    b = Integer.parseInt(parts[1]);
                    break;
            }
            return new Fraction(a, b);
        } catch (NumberFormatException ex) {
            return null;
        }

    }

    public static Fraction addFrac(Fraction a, Fraction b) {
        //(a*d)+(c*b)/b*d
        return new Fraction((a.a * b.b) + (b.a * a.b), a.b * b.b);
    }

    public static Fraction multFrac(Fraction a, Fraction b) {
        return new Fraction(a.a * b.a, a.b * b.b);
    }

    public static Fraction divideFrac(Fraction a, Fraction b) {
        return new Fraction(a.a * b.b, a.b * b.a);
    }

    public static Fraction subFrac(Fraction a, Fraction b) {
        return new Fraction((a.a * b.b) - (b.a * a.b), a.b * b.b);
    }

    public Fraction reduce() {
        int max = this.a > this.b ? this.a : this.b;
        if(this.b==0)return this;
        for (int i = max; i > 1; i--) {
            if (this.a % i == 0 && this.b % i == 0) {
                this.a /= i;
                this.b /= i;
            }
        }

        while (a - b >= 0) {
            a -= b;
            c++;
        }
        while (a + b <= 0) {
            a += b;
            c--;
        }
        if (b == 1) {
            c += a;
            isClear = true;
        }
        if (a == 0 || b == 0) {
            isClear = true;
        }
        return this;
    }
   // public static int iter = 0;

    public static String exStr(String line) {
        Fraction frac1 = null;
        Fraction frac2 = null;
        int start = 0, end = 0, pCount = 0;
        boolean hasOp = false, found = false;
        int op = 0;
        //divide str by ()
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                found = true;
                if (pCount == 0) {
                    start = i;
                }
                pCount++;
            } else if (line.charAt(i) == ')') {
                if (pCount == 1) {
                    end = i;
                }
                pCount--;
            }
        }
        if (found) {
            return exStr(line.substring(0, start) + exStr(line.substring(start + 1, end)) + line.substring(end + 1));
        }
        //Search for frac & op then frac2
        for (int i = 0; i < line.length();) {
            if (Character.isDigit(line.charAt(i)) || (line.charAt(i) == '-' && line.charAt(i + 1) != ' ')) {
                boolean hasForward = false;//hasUnder = false
                int startFrac = i;
                while (i < line.length() && (Character.isDigit(line.charAt(i)) || line.charAt(i) == '/' || line.charAt(i) == '_' || line.charAt(i) == '-')) {
                    if (line.charAt(i) == '/') {
                        if (hasForward) {
                            break;
                        }
                        hasForward = true;
                    }

//                    if (line.charAt(i) == '_') {
//                        if (hasUnder) {
//                            break;
//                        }
//                        hasUnder = true;
//                    }
                    i++;
                }

                if (op == 0) {
                    frac1 = Fraction.parseFrac(line.substring(startFrac, i));
                } else {
                    frac2 = Fraction.parseFrac(line.substring(startFrac, i));
                }
                if (frac1 != null && frac2 != null && op != 0) {

                    return exStr(Fraction.operate(op, frac1, frac2) + line.substring(i));
                }
            } else {
                //test if op:
                hasOp = true;
                switch (line.charAt(i)) {
                    case '+':
                        op = 1;
                        break;
                    case '-':
                        op = 2;
                        break;
                    case '*':
                        op = 3;
                        break;
                    case '/':
                        op = 4;
                        break;
                    default:
                        hasOp = false;

                }
                i++;
            }

        }
        //Return for if only frac
        if (frac1 != null && op == 0 && frac2 == null) {
            return line;
        }
        //DO NOT COME HERE (I THINK) 
        return exStr(Fraction.operate(op, frac1, frac2).toString());
    }

    public static Fraction operate(int selection, Fraction a, Fraction b) {
        switch (selection) {
            case 1:
                //add
                return Fraction.addFrac(a, b);
            case 2:
                //Subtract
                return Fraction.subFrac(a, b);
            case 3:
                //multiply
                return Fraction.multFrac(a, b);
            case 4:
                //divide
                return Fraction.divideFrac(a, b);
            default:
                //This is not good:
                return null;
        }
    }

    @Override
    public String toString() {
        //TODO Check sooner?
        if (isClear) {
            return c + "";
        }
        if (this.b == 0) {

            if (this.a == 0) {
                return "Undefined";
            }

            return "Indeterminant";
        }
        if (c == 0) {
            return a + "/" + b;
        }
        return c + "_" + this.a + "/" + this.b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

}
/*

 System.out.println(line);
 Fraction frac1 = null, frac2 = null;
 int start = 0, end = 0, pCount = 0;
 boolean hasOp=false, found = false;
 int op;
 int i;
        
 for (i = 0; i < line.length(); i++) {
 //Split string if perintheses
 if (line.charAt(i) == '(') {
 found = true;
 if (pCount == 0) {
 start = i;
 }
 pCount++;
 } else if (line.charAt(i) == ')') {
 if (pCount == 1) {
 end = i;
 }
 pCount--;
 }
 if(){
                    
 }
 //Get fraction and parse
 if(Character.isDigit(line.charAt(i))){
 boolean hasUnder=false,hasForward=false;
 int startFrac = i;
 while(Character.isDigit(line.charAt(i)) || line.charAt(i)=='/' || line.charAt(i)=='_' && line.charAt(i)!=' ' && i < line.length()){
 if(line.charAt(i)=='/'){
 if(hasForward){
 break;
 }
 }
 hasForward=true;
                    
 if(line.charAt(i)=='_'){
 if(hasUnder){
 break;
 }
 hasUnder=true;
 }
 i++;
 }  
                    
 if(!hasOp){
 frac1 = Fraction.parseFrac(line.substring(startFrac, i));
 }else{
 frac2 = Fraction.parseFrac(line.substring(startFrac, i));
 }
 //If not frac check if op:
 }else{
 hasOp = true;
 switch(line.charAt(i)){
 case '+':
 op = 1;
 break;
 case '-':
 op = 2;
 break;
 case '*':
 op = 3;
 break;
 case '/':
 op = 4;
 break;
 default:
 hasOp = false;
 }
 }
 //TODO: How to re-operate on all of equation? |(3+ |( |(5*3)|-|(4/2)|)| )|
 //If line contains '(' seperate line!
 if (line.charAt(i) == '(') {
 found = true;
 if (pCount == 0) {
 start = i;
 }
 pCount++;
 } else if (line.charAt(i) == ')') {
 if (pCount == 1) {
 end = i;
 }
 pCount--;
 }
            
 }
            
        
            
 if (!found) {
 return line;
 }
            
        

 Sample session?
 Please input a fraction in the form a/b
 >>5/6
 Please enter the other fraction:
 >>3/10
 ---------------
 a/b + c/d
 (a*d)+(c*b)/b*d


 Reducing:
 for num in range max([a,b]):
 if(a%num==0 && b%num==0):
 a/=num
 b/=num

 */
