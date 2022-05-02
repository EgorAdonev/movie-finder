package org.adonev.moviesearch;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;

import static org.adonev.moviesearch.CustomLogger.LOG;

public class MovieSearch {
    private static String movieName;
    private static String status;
    private static String urlEncodedStr;

    public static void main(String[] args) throws IOException {
        //TODO input URL
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter movie name: ");
        movieName = scanner.nextLine();
        String urlEncodedStr=URLEncoder.encode(movieName, StandardCharsets.UTF_8.name());
        if(movieName==null) {
            System.out.println("Movie name cannot be empty!");
        }

        Document docConn = Jsoup.connect("https://www.allmovie.com/search/all/"+urlEncodedStr)
                .userAgent("Chrome")
                .timeout(5000)
                .get();
        System.out.format("Current site: https://www.allmovie.com/search/all/%s\n",urlEncodedStr);
        Element searchResults = docConn.select("#cmn_wrap > div.content-container > div.content > div > ul").first();
        Elements movie = searchResults.children().attr("class","movie");
        String info = movie.attr("class","info").text();

        String[] infoAttrs = info.split(" ");
        String[] infoAttr = info.split("\\d{4}");
        String str =  info.replaceAll("Movie","\nMovie\n")
                .replaceAll("Person","Person\n")
                .replaceAll("Active:","Active\n")
                .replaceAll("Directed by:","\nDirected by:\n")
                .replaceAll("/","\n")
                .replaceAll("Genres:","\nGenres:\n");
        System.out.println(str);
        LOG.log(Level.INFO,info);
    }
}