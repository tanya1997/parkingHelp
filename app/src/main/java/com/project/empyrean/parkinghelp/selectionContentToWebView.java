package com.project.empyrean.parkinghelp;

/**
 * Created by empyrean on 01.11.2016.
 */

public class selectionContentToWebView {
    String HtmlFile;

    final static int ItemSity = 0;
    final static int ItemAutodrome = 1;
    final static int ItemPdd = 2;
    final static int ItemFeedback = 3;
    final static String wordAnimation = "%D0%B0%D0%BD%D0%B8%D0%BC%D0%B0%D1%86%D0%B8%D1%8F";


    public void setItem(int item){
        switch (item){
            case ItemSity:
                Sity();
                break;
            case ItemAutodrome:
                Autodrome();
                break;
            case ItemPdd:
                Pdd();
                break;
            case ItemFeedback:
                Feedback();
                break;
        }
    }
    private void Sity(){HtmlFile = "file:///android_asset/город.html";}
    private void Autodrome(){HtmlFile = "file:///android_asset/автодром.html";}
    private void Pdd(){HtmlFile = "file:///android_asset/pdd.html";}
    private void Feedback(){
        HtmlFile ="https://goo.gl/forms/jeBdvjZpMKd722hj2";
    }
    public boolean searchAnimationInString(String url){
      return url.contains(wordAnimation);
    }


    public String getNameHtmlFile(){
        return HtmlFile;
    }

}
