package org.example.Ex1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Read_file_xml
{
    public void Read() throws IOException
    {
        Scanner scanner = new Scanner(Paths.get(".\\Files_xml\\File_befor.xml"), "UTF-8");
        List<String> text = new ArrayList<>();
        List<String> clear_text = new ArrayList<>();
        String line;
        while (scanner.hasNextLine())
        {
            line =  scanner.nextLine();
            text.add(line);
        }
        text.remove(0);
        text.remove(text.size()-1);
        text.add("Its a magic text with this text my program working, please don't touch it");
        String newElement;
        for(String element : text)
        {
            newElement = element.replaceAll("[\\s]{2,}", " ");
            newElement = newElement.replace(" = ", "=");
            clear_text.add(newElement);
        }
        int h = 0;
        for(int i = 0; i < clear_text.size(); i ++)
        {
            if(clear_text.get(i).length() < 50 && clear_text.get(i + 1).length() < 50)
            {
                clear_text = merge(clear_text, i);
                i--;
            }
            if(clear_text.get(i).length() < 50 && clear_text.get(i + 1).length() > 50)
            {
                clear_text = merge(clear_text, i-1);
            }
        }
        clear_text.remove(clear_text.size()-1);
        String fullname = "";
        String surname = "";
        String name = "";
        String birthday = "";
        String format = "";
        PrintWriter printWriter = new PrintWriter(".\\Files_xml\\File_after.xml");
        Pattern logPattern = Pattern.compile(" <person (\\S+)=\"(\\S+)\" (\\S+)=\"(\\S+)\" (\\S+)=\"(\\S+)\" />");
        List<String> pattern = new ArrayList<>();
        printWriter.println("<persons>");
        for (String logItem : clear_text) {
            Matcher matcher = logPattern.matcher(logItem);
            if (matcher.matches()) {
                for(int j = 1; j < 7; j++)
                {
                    pattern.add(matcher.group(j));
                }
                for(int i = 0; i < 6; i++)
                {
                    if(pattern.get(i).equals("name"))
                    {
                        name = pattern.get(i + 1);
                    }
                    if(pattern.get(i).equals("surname"))
                    {
                        surname = pattern.get(i + 1) ;
                    }
                    if(pattern.get(i).equals("birthData") || pattern.get(i).equals("birthDate"))
                    {
                        format = pattern.get(i);
                        birthday = pattern.get(i + 1);
                    }
                }
                fullname = name + " " + surname;
                if(format.equals("birthData"))
                {
                    printWriter.println("\t\t<person name = \"" + fullname + "\"" + "\n\t\t\t   "  + " " + format + " = "+ "\"" + birthday + "\"" + "/>");
                }
                else {
                    printWriter.println("\t\t<person name = \"" + fullname + "\"" + " " + format + " = "+ "\"" + birthday + "\"" + "/>");
                }
            }
            pattern.clear();
            fullname = "";
            birthday = "";
            format = "";
        }
        printWriter.println("</persons>");
        printWriter.close();
    }

    public static List<String> merge(List<String> list, int index)
    {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Cannot merge empty list");
        } else if (index + 1 >= list.size()) {
            throw new IndexOutOfBoundsException("Cannot merge last element");
        } else {
            final List<String> result = new ArrayList<String>(list);
            result.set(index, list.get(index) + list.get(index + 1));
            result.remove(index + 1);
            return result;
        }
    }
}
