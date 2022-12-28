package org.example.Ex2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Read_file_json
{
    public static double number = 0;
    public static Map<String, Double> infoReport = new HashMap<>();
    public void Read_json() throws IOException
    {
        String[] pathnames;
        File f = new File(".\\Files_json");
        pathnames = f.list();
        for (String pathname : pathnames)
        {
            Scanner scanner = new Scanner(Paths.get(".\\Files_json\\" + pathname), "UTF-8");
            List<String> text = new ArrayList<>();
            String line;
            while (scanner.hasNextLine())
            {
                line =  scanner.nextLine();
                line = line.replaceAll("[\\s]{2,}", "");
                line = line.replace(",", "");
                line = line.replace(" :", ":");
                //System.out.println(line);
                if(line.equals("]") || line.equals("{") || line.equals("[") || line.equals("}") || line.equals(" ") || line.equals("\"repots_details\":["))
                {
                    continue;
                } else {
                    text.add(line);
                }
            }
            List<String> text_for_red = new ArrayList<>();
            for(int i = 0; i < text.size(); i++)
            {
                line = text.get(i).replace(" ","_");
                text_for_red.add(line);
            }
            Pattern logPattern = Pattern.compile("\"(\\S+)\":_\"(\\S+)\"");
            Pattern logPatternNumber = Pattern.compile("\"(\\S+)\":_(\\S+)");
            List<String> pattern = new ArrayList<>();
            for (String logItem : text_for_red) {
                Matcher matcher = logPattern.matcher(logItem);
                Matcher matherNumber = logPatternNumber.matcher(logItem);
                if (matcher.matches())
                {
                    pattern.add(matcher.group(1));
                    pattern.add(matcher.group(2));
                } else if (matherNumber.matches())
                {
                    pattern.add(matherNumber.group(1));
                    pattern.add(matherNumber.group(2));
                }
            }
            for (int i = 0; i < pattern.size(); i++)
            {
                if (pattern.get(i).equals("type"))
                {
                    if(infoReport.containsKey(pattern.get(i + 1)))
                    {
                        number = Double.valueOf(pattern.get(i + 3)) + infoReport.get(pattern.get(i + 1));
                        infoReport.put(pattern.get(i + 1),number);
                    } else {
                        infoReport.put(pattern.get(i + 1), Double.valueOf(pattern.get(i + 3)));
                    }
                }
            }
        }
    }
}
