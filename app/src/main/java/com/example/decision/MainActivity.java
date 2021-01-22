package com.example.decision;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import java.io.*;

public class MainActivity extends Activity {
    private TextView message;
    private TextView question;
    private Button yesButton;
    private Button noButton;
    private DecisionMap map;
    private DecisionMap newMap;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.descText);
        question = findViewById(R.id.quesText);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);

        //Pain and suffering begins here

        writeToFile(thisText(), this);
        String text = readFromFile(this);

        map = new DecisionMap(text);
        navigateMap(map);


    }

    private void navigateMap(DecisionMap map){
        final DecisionNode[] nodeArray = map.getArray();
        int intPointer = 1;
        final DecisionNode[] pointer = {nodeArray[intPointer]};

        for (int i = 0; i < 999; i++){
            String gameDescription = pointer[0].getDescription();
            String gameQuestion = pointer[0].getQuestion();

            message.setText(gameDescription);
            question.setText(gameQuestion);

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int searchValue;
                    searchValue = pointer[0].getYesID();
                    for (int i = 1; i < nodeArray.length; i++){
                        DecisionNode tempSearch = nodeArray[i];
                        if (tempSearch.getNodeID() == searchValue){
                            pointer[0] = tempSearch;
                            Log.i("this should work","yes "+pointer[0].getDescription());
                            message.setText(pointer[0].getDescription());
                            question.setText(pointer[0].getQuestion());
                            return;
                        }
                    }
                }
            });

            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int noSearchValue;
                    noSearchValue = pointer[0].getNoID();
                    for (int i = 1; i < nodeArray.length; i++){
                        DecisionNode noTempSearch = nodeArray[i];
                        if (noTempSearch.getNodeID() == noSearchValue){
                            pointer[0] = noTempSearch;
                            Log.i("this should work","no "+pointer[0].getDescription());
                            message.setText(pointer[0].getDescription());
                            question.setText(pointer[0].getQuestion());
                            return;
                        }
                    }
                }
            });

        }

    }


    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private String thisText(){
        String stringText = "0,3,1,Let's make iced coffee!,Small cup?\n" +
                "1,3,2,Let's make iced coffee!,Big cup?\n" +
                "2,4,17,I guess the floor it is then...,Add whipped cream?\n" +
                "3,7,4,Let's add coffee!,Add coffee?\n" +
                "4,8,17,Needs a little more...,Add eggs?\n" +
                "5,0,0,Well done! You've made Pineapple Coffee!,Start again? Click any button!\n" +
                "6,5,5,No sugar? Adding pineapple instead...,Click any button to continue\n" +
                "7,12,6,Let's add some sugar!,Add sugar?\n" +
                "8,13,9,Nearly there, it's starting to look like something!,Add chocolate?\n" +
                "9,15,16,Don't like chocolate?,Add pork?\n" +
                "10,0,0,Well done! You've made disappointment!,Start again? Click any button!\n" +
                "11,10,10,No syrup? ;( Okay...,Click any button to continue\n" +
                "12,18,11,Let's add some syrup!,Add syrup?\n" +
                "13,28,14,Put a ribbon on it!,Add ribbon?\n" +
                "14,29,29,-,Are you sure?\n" +
                "15,19,16,-,Add some soup?\n" +
                "16,20,17,Not a pork person?,Add chicken?\n" +
                "17,21,21,Veggie? No worries!,Add broccoli!\n" +
                "18,22,24,It's missing something...,Add milk?\n" +
                "19,30,30,Last and not least... Let's add butter!,Click any button to continue\n" +
                "20,31,31,Last and not least... Let's add hay!,Click any button to continue\n" +
                "21,32,32,Last and not least... Let's add a dash of vinegar!,Click any button to continue\n" +
                "22,23,25,Wouldn't be ICED without ice!,Add ice?\n" +
                "23,27,26,Finishing touches...,Add chocolate?\n" +
                "24,0,0,Well done! You've made an Americano!,Start again? Click any button!\n" +
                "25,0,0,Well done! I mean it's room temp but it's still coffee!,Start again? Click any button!\n" +
                "26,0,0,Well done! You've made an iced latte!,Start again? Click any button!\n" +
                "27,0,0,Well done! You've made an iced mocha!,Start again? Click any button!\n" +
                "28,0,0,Well done! You've somehow made a cake!,Start again? Click any button!\n" +
                "29,0,0,Game Over. It doesn't matter. You ALWAYS put ribbons on cakes,Start again? Click any button!\n" +
                "30,0,0,Well done! You've made porridge!,Start again? Click any button!\n" +
                "31,0,0,Well done! Sort of. I don't actually know what you've made...,Start again? Click any button!\n" +
                "32,0,0,Well done! You've made some salad!,Start again? Click any button!\n";

        return stringText;
    }
}