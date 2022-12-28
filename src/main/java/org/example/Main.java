package org.example;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.example.Ex1.Read_file_xml;
import org.example.Ex2.Read_file_json;
import org.example.Ex2.Write_file_json;

import javax.swing.text.Document;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Read_file_json readFileJson = new Read_file_json();
        Read_file_xml readFileXml = new Read_file_xml();
        Write_file_json writeFileJson = new Write_file_json();
        readFileXml.Read();
        readFileJson.Read_json();
        writeFileJson.Write_json();
    }
}
