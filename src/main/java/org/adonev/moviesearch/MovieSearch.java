package org.adonev.moviesearch;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MovieSearch extends Logger {
    private static String movieName;
    private static String status;
    private static String urlEncodedStr;
    public static Logger LOG = Logger.getLogger("MovieSearch");
    public MovieSearch(){
        super("MovieSearch","org.adonev.moviesearch");
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

        String infoAttrsString = "";
        for (int i = 0 ; i < infoAttrs.length - 1; i++){
            String movieAttr = infoAttrs[0];
            infoAttrsString = movieAttr;
            infoAttrsString += "\n";

            String movieNameAttr = infoAttrs[1];
            infoAttrsString += movieNameAttr;
            infoAttrsString += "\n";

            char[] movieYearAttr = infoAttrs[2].toCharArray();
            infoAttrsString+=String.valueOf(movieYearAttr);
            infoAttrsString += "\n";
            if(infoAttrs[i].equals("Directed")) {
                String movieDirected = infoAttrs[i];
                if (!infoAttrs[i].equals("Movie")) {
                    String movieBy = infoAttrs[i+1];//spider
                    infoAttrsString += movieDirected + movieBy;
                    infoAttrsString += "\n";
                    if (infoAttrs[i] == "/") {
                        infoAttrsString += infoAttrs[i] + infoAttrs[i + 1];
                        if (infoAttrs[i] == "Genres:") {
                            infoAttrsString += infoAttrs[i];
//                        break;
                        }
                    }
                    infoAttrsString += infoAttrs[i];
                }
            }
                else {
                    continue;
                }
//                char[] movieDirectorsAttr = new char[8+3+infoAttrs[i+2].length()+infoAttrs[i+3].length()];
//                movieDirectorsAttr = infoAttrs[2].toCharArray();
//                infoAttrsString += String.valueOf(movieDirectorsAttr);
//            }
//            else {
//                continue;
//            }

        }
        System.out.println(infoAttrsString);
        LOG.log(Level.INFO,info);
//        StringBuilder name = null;
//        String year;
//        StringBuilder directors = null;
//        StringBuilder genres = null;
//        switch (info){
//            case "Movie":
//                name = name.append("\n").append(infoAttrs[1]);
//                //continue;
//            case "Directed by:":
//                directors = directors.append("\n").append(infoAttrs[6]).append(infoAttrs[7]);
//            case "Genres:":
//        }
//        for (int i = 0; i < infoAttrs.length - 1; i++){
//
//        }
        Element firstMovieByQuery = searchResults.getElementsByAttributeValue("class","movie").first();
        String infoOfFirstMovie = firstMovieByQuery.attr("class","info").text();//attr("class","info")
        System.out.println(infoOfFirstMovie);

    }
}
