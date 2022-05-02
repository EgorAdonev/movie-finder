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
    private static final String movieSiteURL = "https://www.allmovie.com/search/all/";
    private static final String userAgent = "Chrome";
    private static String movieName;
    private static String status;
    private static String urlEncodedMovieName;


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter movie name: ");
        movieName = scanner.nextLine();
        urlEncodedMovieName=URLEncoder.encode(movieName, StandardCharsets.UTF_8.name());
        if(movieName==null || movieName.isEmpty()) {
            System.out.println("Movie name cannot be empty!");
        }

        Document docConnection = Jsoup.connect(movieSiteURL+urlEncodedMovieName)
                .userAgent(userAgent)
                .timeout(5000)
                .get();
        System.out.format("Current site: %s,%s\n",movieSiteURL,urlEncodedMovieName);
        Element searchResults = docConnection.select("#cmn_wrap > div.content-container > div.content > div > ul").first();
        Elements movie = searchResults.children().attr("class","movie");
        String info = movie.attr("class","info").text();

        String[] infoAttrs = info.split(" ");
        String[] infoAttr = info.split("\\d{4}");
        String prettyPrintInfo =  info.replaceAll("Movie","\nMovie\n")
                .replaceAll("Person","Person\n")
                .replaceAll("Active:","Active\n")
                .replaceAll("Directed by:","\nDirected by:\n")
                .replaceAll("/","\n")
                .replaceAll("Genres:","\nGenres:\n");
        System.out.println(prettyPrintInfo);
        LOG.log(Level.INFO,info);
    }
}