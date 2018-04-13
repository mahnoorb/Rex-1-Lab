package eecs.rex1;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2/28/17.
 */
public class RexModel
{
    public static final int SET_SIZE = 3;
    private boolean digit1;
    private boolean letter1;
    private boolean anchor1;
    private String regex;
    private Random rng;

    public RexModel()
    {
        this.regex ="";
        this.digit1 = true;
        this.letter1 =true;
        this.anchor1 = true;
        this.rng = new Random();
    }

    public void setDigit(boolean digit)
    {
        this.digit1 = digit;
    }

    public void setLetter(boolean letter)
    {
        this.letter1 = letter;
    }

    public void setAnchor(boolean anchor)
    {
        this.anchor1 = anchor;
    }

    public java.lang.String getRex()
    {
        return this.regex;
    }

    public boolean doesMatch(java.lang.String s)
    {
        String mess =s;

        String regex = this.getRex();
        Pattern pattern = Pattern.compile(this.getRex());
        Matcher matcher = pattern.matcher(mess);

        while(matcher.find()){
            return true;
        }
        return false;
    }

    public void generate(int repeat)
    {
        this.regex="";
        if(this.anchor1){
            this.regex=this.regex+"^";
        }

        for(int i=0;i<repeat;i++){
            genDigit();
            genLetter();
        }
        if(this.anchor1){
            this.regex=this.regex+"$";
        }
    }

    private void genDigit(){

        if(this.digit1)
        {
            double z = rng.nextDouble();
            int x = rng.nextInt(9);
            if (z< 0.5)
            {
                this.regex = this.regex + "[0-9]";
            }
            else if (z >= 0.5)
            {
                this.regex = this.regex + "[";
                for (int i = 0; i < SET_SIZE; i++)
                {
                    this.regex = this.regex + x;
                }
                this.regex = this.regex + "]";
            }
            genQuantifier();
        }
    }

    private void genLetter(){
        if(this.letter1)
        {
            double z = rng.nextDouble();
            int x = rng.nextInt(9);
            int randInt = rng.nextInt(25) + 97;
            if (z < 0.5)
            {
                this.regex = this.regex + "[a-z]";
            } else if (z>= 0.5)
            {
                this.regex = this.regex + "[";
                for (int i = 0; i < SET_SIZE; i++)
                {
                    this.regex = this.regex + Character.toString((char) randInt);
                }
                this.regex = this.regex + "]";
            }
            genQuantifier();
        }
    }

    private void genQuantifier(){
        double x = rng.nextDouble();
        int y = (rng.nextInt(SET_SIZE)+1);
        if(x<(1.0/6.0)){
            this.regex = this.regex+"*";
        }
        else if(x<(1.0/3.0)){
            this.regex = this.regex+"+";
        }
        else if(x<0.5){
            this.regex = this.regex+"?";
        }
        else
        {
            this.regex = this.regex + "{" +y+ "}";
        }
    }

    public static void main(String[] args)
    {
        RexModel model = new RexModel();
        //model.setLetter(false);

        System.out.println(model.anchor1);
        System.out.println(model.digit1);
        System.out.println(model.letter1);

        model.generate(2);
        System.out.println(model.regex);
    }







}
