package org.adonev.moviesearch;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Scanner;

import static org.asynchttpclient.Dsl.asyncHttpClient;


public class MovieSearch {
    private static String movieName;
    private static String status;
    private static String urlEncodedStr;
    public MovieSearch(){
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter movie name: ");
        movieName = scanner.nextLine();
        String urlEncodedStr=URLEncoder.encode(movieName);
        if(movieName==null) {
            System.out.println("Movie name cannot be empty!");
        }

        Document docConn = Jsoup.connect("https://www.allmovie.com/search/all/"+urlEncodedStr)
                .userAgent("Mozilla")
                .timeout(5000)
                .get();
        System.out.format("Current site: https://www.allmovie.com/search/all/%s\n",urlEncodedStr);
        Element searchResults = docConn.select("#cmn_wrap > div.content-container > div.content > div > ul").first();
        //if(searchResults.hasClass("movie")){
        Element movie = searchResults.children().first().attr("class","movie");
        String info = movie.attr("class","info").text();
        String[] infoAttrs = info.split(" ");

        StringBuilder name = null;
        String year;
        StringBuilder directors = null;
        StringBuilder genres = null;
//        name = infoAttrs[0];
        String infoAttrsString = null;
        for (int i = 0 ; i < infoAttrs.length; i++){
            char[] movieAttr = new char[infoAttrs[0].length()];
            movieAttr = infoAttrs[0].toCharArray();
            infoAttrsString = String.valueOf(movieAttr);

            char[] movieNameAttr = new char[infoAttrs[1].length()];
            movieNameAttr = infoAttrs[1].toCharArray();
            infoAttrsString+=String.valueOf(movieNameAttr);

            char[] movieYearAttr = new char[infoAttrs[2].length()];
            movieNameAttr = infoAttrs[2].toCharArray();
            infoAttrsString+=String.valueOf(movieYearAttr);

            if(infoAttrs[i]=="Directed"){
                infoAttrsString+=infoAttrs[i] + infoAttrs[i+1];
                int countDirector = 0;
                if(infoAttrs[i]
                char[] movieDirectorsAttr = new char[8+3+infoAttrs[i+2].length()+];
                movieNameAttr = infoAttrs[2].toCharArray();
            }


        }
        switch (infoAttrsString){
            case "Movie":
                name = name.append("\n").append(infoAttrs[1]);
                //continue;
            case "Directed by:":
                String s = infoAttrs[].toString().split("");
                directors = directors.;
            case "Genres:":


        }
        for (int i = 0; i < Arrays.stream(infoAttrs).count(); i++){
            if (infoAttrs[i].equals(" ")) infoAttrsString += "\n";

        }

        //;for (String attr : infoAttrs) {

        //}


        Element firstMovieByQuery = searchResults.getElementsByAttributeValue("class","movie").first();
        String infoOfFirstMovie = firstMovieByQuery.attr("class","info").text();//attr("class","info")
        System.out.println(infoOfFirstMovie);

    }
}
