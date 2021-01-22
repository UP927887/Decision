package com.example.decision;


import android.util.Log;

import static java.lang.Integer.valueOf;

public class DecisionMap {

    DecisionNode head;
    DecisionNode tail;
    DecisionNode[] allNodesFinal;


    public DecisionMap(String dataText) {
        DecisionNode[] tempArray = stringHandling(dataText);
        Log.w("temparray",tempArray[1].getDescription());
        transferInformation(tempArray);
    }

    public DecisionNode[] stringHandling(String thingsToSplit){
        String[] seperateLines = lineSplitter(thingsToSplit);
        Log.i("linesplitter","done!");

        DecisionNode[] allNodes = stringSplitter(seperateLines);
        Log.i("stringsplitter","done!");

        Log.w("node 1",": "+allNodes[1].getDescription()+allNodes[1].getQuestion());

        return allNodes;
    }

    public void transferInformation(DecisionNode[] array){
        allNodesFinal = array.clone();
        Log.i("All Nodes Final","All done!");

    }

    public DecisionNode[] getArray(){
        Log.i("allnodefinal1",allNodesFinal[1].getDescription());
        return allNodesFinal;
    }

    public static String[] lineSplitter(String someText){
        String lines[] = someText.split("\n");
        return lines;
    }

    public DecisionNode[] stringSplitter(String[] moreLines){
        DecisionNode[] allNodes = new DecisionNode[moreLines.length];

        //Allows decisionnode to be made
        for(int t = 1; t < moreLines.length;t++){
            Log.w("STRINGSPLITTER",": "+ moreLines[t]);

            String newTempLine = moreLines[t];
            String[] newNewLine = newTempLine.split(",");

            allNodes[t] = toBuild(newNewLine);

        }

        return allNodes;
    }

    public DecisionNode toBuild(String[] lineBuilder){
        DecisionNode n = new DecisionNode(Integer.valueOf(lineBuilder[0]),Integer.valueOf(lineBuilder[1]),Integer.valueOf(lineBuilder[2]),lineBuilder[3],lineBuilder[4]);
        Log.w("node just made: ","" + n);
        return n;
    }

}