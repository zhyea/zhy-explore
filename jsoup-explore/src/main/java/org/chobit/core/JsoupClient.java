package org.chobit.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class JsoupClient {


    public static void main(String[] args) throws IOException {

        String url = "https://www.ke.com/city/";
        String citySelector = ".city-item>.city_list_section>.city_list_ul>.city_list_li>.city_list>.city_province>ul>li>a";

        Document doc = Jsoup.parse(new URL(url), 3000);
        Elements  all = doc.select(citySelector);
        for(Element ele : all){
            String s = ele.attr("href");
            s = s.substring(2);
            String[] arr = s.split("\\.");
            System.out.println( "MAP.put(\"" + ele.text() + "\",\"" + arr[0] + "\");");
        }
    }


}
