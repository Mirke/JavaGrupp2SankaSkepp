package com.grupp2.sankaskepp.HistoryFeature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Author: Mikael
 * Feature: Game History
 * As an observer, I want to see history of moves, so that I can analyse them.
 * Given end of game, when I look at text ,then the game shows paragraph of game moves.
 */
class HistoryOverMovesTest {

    @Test
    void createAndSaveTextLine(){
        HistoryOverMoves historyOverMoves = new HistoryOverMoves();
        historyOverMoves.addTextLine("line of text");
        Assertions.assertEquals("line of text",historyOverMoves.latestSavedText);
    }
    @Test
    void removeSavedTextLine(){
        HistoryOverMoves historyOverMoves = new HistoryOverMoves();
        historyOverMoves.addTextLine("salad");
        historyOverMoves.removeAllTextLines();
        assertTrue(historyOverMoves.isEmpty());

    }
    @Test
    void readSavedTextLine(){
        HistoryOverMoves historyOverMoves = new HistoryOverMoves();
        historyOverMoves.addTextLine("line of text");
        Assertions.assertEquals("line of text",historyOverMoves.latestSavedText);
    }
    @Test
    void updateSavedTextLine(){
        HistoryOverMoves historyOverMoves = new HistoryOverMoves();
        historyOverMoves.addTextLine("line of text");
        historyOverMoves.updateLatestTextLine("updated of text");
        Assertions.assertEquals("updated of text",historyOverMoves.latestSavedText);
        Assertions.assertEquals("updated of text",historyOverMoves.latestSavedText);
    }




}