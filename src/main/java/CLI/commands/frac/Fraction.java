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

    private int numerator, denominator, mixed;

    boolean isClear = false;
    /**
     * Fraction object
     * @param numerator
     * @param denominator
     */
    public Fraction(int numerator, int denominator) {
        //Test for division by zero here?
        this.numerator = numerator;
        this.denominator = denominator;
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
        //(numerator*d)+(mixed*denominator)/b*d
        return new Fraction((a.numerator * b.denominator) + (b.numerator * a.denominator), a.denominator * b.denominator);
    }

    public static Fraction multFrac(Fraction a, Fraction b) {
        return new Fraction(a.numerator * b.numerator, a.denominator * b.denominator);
    }

    public static Fraction divideFrac(Fraction a, Fraction b) {
        return new Fraction(a.numerator * b.denominator, a.denominator * b.numerator);
    }

    public static Fraction subFrac(Fraction a, Fraction b) {
        return new Fraction((a.numerator * b.denominator) - (b.numerator * a.denominator), a.denominator * b.denominator);
    }

    public Fraction reduce() {
        int max = this.numerator > this.denominator ? this.numerator : this.denominator;
        if(this.denominator==0)return this;
        for (int i = max; i > 1; i--) {
            if (this.numerator % i == 0 && this.denominator % i == 0) {
                this.numerator /= i;
                this.denominator /= i;
            }
        }

        while (numerator - denominator >= 0) {
            numerator -= denominator;
            mixed++;
        }
        while (numerator + denominator <= 0) {
            numerator += denominator;
            mixed--;
        }
        if (denominator == 1) {
            mixed += numerator;
            isClear = true;
        }
        if (numerator == 0 || denominator == 0) {
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
        FracOp op = null;
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

                if (op == null) {
                    frac1 = Fraction.parseFrac(line.substring(startFrac, i));
                } else {
                    frac2 = Fraction.parseFrac(line.substring(startFrac, i));
                }
                if (frac1 != null && frac2 != null && op != null) {

                    return exStr(Fraction.operate(op, frac1, frac2) + line.substring(i));
                }
            } else {
                //test if op:
                hasOp = true;
                switch (line.charAt(i)) {
                    case '+':
                        op = FracOp.ADD;
                        break;
                    case '-':
                        op = FracOp.SUB;
                        break;
                    case '*':
                        op = FracOp.MULT;
                        break;
                    case '/':
                        op = FracOp.DIV;
                        break;
                    default:
                        hasOp = false;

                }
                i++;
            }

        }
        //Return for if only frac
        if (frac1 != null && op == null && frac2 == null) {
            return line;
        }
        //DO NOT COME HERE (I THINK) 
        return exStr(Fraction.operate(op, frac1, frac2).toString());
    }
    /**
     * Performs an operation based upon Selected
     * @param selection
     * @param a
     * @param b
     * @return 
     */
    public static Fraction operate(FracOp selection, Fraction a, Fraction b) {
        switch (selection) {
            case ADD:
                //add
                return Fraction.addFrac(a, b);
            case SUB:
                //Subtract
                return Fraction.subFrac(a, b);
            case MULT:
                //multiply
                return Fraction.multFrac(a, b);
            case DIV:
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
            return mixed + "";
        }
        if (this.denominator == 0) {

            if (this.numerator == 0) {
                return "Undefined";
            }

            return "Indeterminant";
        }
        if (mixed == 0) {
            return numerator + "/" + denominator;
        }
        return mixed + "_" + this.numerator + "/" + this.denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
    
}
 enum FracOp{
ADD,SUB,MULT,DIV;
}
