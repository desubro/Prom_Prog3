/**
 * Created by Vladislav on 28.04.2016.
 */
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telephone {
    public static void main(String[] ars){
        Telephone telephonefixer = new Telephone();
        telephonefixer.fix(new File("H:\\g.txt"));
    }
    public Telephone(){

    }
//new Comment
    public void fix(File file){
        String in="";
        int ch;
        try{
            FileReader inFile= new FileReader(file);
            while((ch=inFile.read())!=-1)
            {
                in+=(char)ch;
            }
        } catch (IOException e){
            System.out.println("Couldn't open file to read");
            return;
        }
        Matcher matcher = Pattern.compile("\\s+\\+?(\\d[\\s\\-\\(\\)\\+]{0,3}){11}").matcher(in);
        StringBuffer out = new StringBuffer();
        while (matcher.find()) {
            String number = matcher.group();
            number = number.replaceAll("[^\\d]", "");
            StringBuilder stringBuilder = new StringBuilder (number.replaceFirst("\\d", "1"));
            number = stringBuilder.insert(0, " +").insert(3, " ").insert(4,"(").
                    insert(8,") ").insert(13,"-").insert(16,"-").insert(19," ").toString();
            matcher.appendReplacement(out, number);
        }
        System.out.println(matcher.appendTail(out).toString());
    }
}

