package com.grupp2.sankaskepp.HistoryFeature;

import com.grupp2.sankaskepp.protokoll.Letter;

import java.lang.StringBuilder;
/**
 * Author: Mikael
 * Feature: Game History
 * As an observer, I want to see history of moves, so that I can analyse them.
 * Given end of game, when I look at text ,then the game shows paragraph of game moves.
 */
public class HistoryOverMoves {

    private final StringBuilder stringBuilder;
    public String latestSavedText;

    public HistoryOverMoves() {
         stringBuilder = new StringBuilder();
    }

    public void addTextLine(String text) {
        stringBuilder.append(text + "\n" );
        latestSavedText = text;
    }

    public boolean isEmpty() {
        return stringBuilder.isEmpty();
    }

    public void removeAllTextLines() {
        stringBuilder.setLength(0);
        latestSavedText = "";
    }

    public void removeLatestTextLine(){
        stringBuilder.setLength(stringBuilder.length() - (latestSavedText.length() + 1));
        StringBuilder temporaryStringBuilder = new StringBuilder(stringBuilder.toString());

        temporaryStringBuilder.setLength(temporaryStringBuilder.length() - 1);
        latestSavedText = stringBuilder.substring(temporaryStringBuilder.lastIndexOf("\n"),stringBuilder.length());
        latestSavedText = latestSavedText.replaceAll("\n", "");
    }

    public void updateLatestTextLine(String updateOfText) {
        stringBuilder.replace(stringBuilder.length() - latestSavedText.length(), stringBuilder.length(), updateOfText);
        latestSavedText = updateOfText;
    }

}
