package org.example.Ex2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class Write_file_json {
    public void Write_json() throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(".\\Files_json\\Report.xml");
        printWriter.println("<Reports>");
        for(Map.Entry<String, Double> el : Read_file_json.infoReport.entrySet())
        {
            printWriter.println("\t<Report>");
            printWriter.println("\t\t<type>" + el.getKey() + "</type>");
            printWriter.println("\t\t<fine_amount>" + el.getValue() + "</fine_amount>");
            printWriter.println("\t</Report>");
        }
        printWriter.println("</Reports>");
        printWriter.close();
    }

}
