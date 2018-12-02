package com.example.tong.mathrocks_v3;


import com.example.tong.mathrocks_v3.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetTestQuestions {

    public List<Question> generateLevel1Questions(String testType, String testID){

        List<Question> levelOneQuestions = new ArrayList<>();
        Random thisRandom =  new Random();
        int randNum1;
        int randNum2 = 0;
        int result = 0;
        String oper = "";

        for (int i = 0; i < 10; i++) {

            randNum1 = thisRandom.nextInt(13);

            switch (testType) {
                case "add":
                    //randNum1 = thisRandom.nextInt(13);
                    randNum2 = thisRandom.nextInt(13);
                    result = randNum1 + randNum2;
                    oper = "+";
                    break;

                case "sub":
                    //randNum1 = thisRandom.nextInt(13);
                    if (randNum1 == 0){
                        randNum2 = 0;
                    }
                    else{
                        randNum2 = thisRandom.nextInt(randNum1)+1;
                    }
                    result = randNum1 - randNum2;
                    oper = "-";
                    break;

                case "mul":
                    //randNum1 = thisRandom.nextInt(13);
                    randNum2 = thisRandom.nextInt(13);
                    result = randNum1 * randNum2;
                    oper = "*";
                    break;

                case "div":
                    //randNum1 = thisRandom.nextInt(13); //3
                    randNum2 = thisRandom.nextInt(12 - 1) + 1; //5
                    int randTemp = randNum1 * randNum2; //15
                    result = randNum1;  //3
                    randNum1 = randTemp; // 15
                    oper = "/";
                    break;
            }

            Question thisQuestion = new Question(testID,randNum1,randNum2,oper, result,1,"Incorrect","No");
            levelOneQuestions.add(thisQuestion);
        }


        return levelOneQuestions;
    }

    public List<Question> generateLevel2Questions(String testType, String testID){

        List<Question> levelTwoQuestions = new ArrayList<>();
        Random thisRandom =  new Random();
        int randNum1 = 0;
        int randNum2 = 0;
        int result = 0;
        String oper = "";

        for (int i = 0; i < 10; i++) {

            randNum1 = thisRandom.nextInt(21);

            switch (testType) {
                case "add":
                    //randNum1 = thisRandom.nextInt(21);
                    randNum2 = thisRandom.nextInt(21);
                    result = randNum1 + randNum2;
                    oper = "+";
                    break;

                case "sub":
                    //randNum1 = thisRandom.nextInt(21);
                    if(randNum1 == 0){
                        randNum2=0;
                    }
                    else {
                        randNum2 = thisRandom.nextInt(randNum1) + 1;
                    }
                    result = randNum1 - randNum2;
                    oper = "-";
                    break;

                case "mul":
                    //randNum1 = thisRandom.nextInt(21);
                    randNum2 = thisRandom.nextInt(21);
                    result = randNum1 * randNum2;
                    oper = "*";
                    break;

                case "div":
                    //randNum1 = thisRandom.nextInt(21); //3
                    randNum2 = thisRandom.nextInt(20 - 1) + 1; //5
                    int randTemp = randNum1 * randNum2; //15
                    result = randNum1;  //3
                    randNum1 = randTemp; // 15
                    oper = "/";
                    break;
            }

            Question thisQuestion = new Question(testID,randNum1,randNum2,oper, result,2,"Incorrect","No");
            levelTwoQuestions.add(thisQuestion);
        }


        return levelTwoQuestions;
    }

    public List<Question> generateLevel3Questions(String testType, String testID){

        List<Question> levelThreeQuestions = new ArrayList<>();
        Random thisRandom =  new Random();
        int randNum1 = 0;
        int randNum2 = 0;
        int result = 0;
        String oper = "";

        for (int i = 0; i < 10; i++) {

            randNum1 = thisRandom.nextInt(101);

            switch (testType) {
                case "add":
                    //randNum1 = thisRandom.nextInt(101);
                    randNum2 = thisRandom.nextInt(101);
                    result = randNum1 + randNum2;
                    oper = "+";
                    break;

                case "sub":
                    //randNum1 = thisRandom.nextInt(101);
                    if(randNum1 == 0){
                        randNum2=0;
                    }
                    else{
                        randNum2 = thisRandom.nextInt(randNum1) + 1;
                    }
                    result = randNum1 - randNum2;
                    oper = "-";
                    break;

                case "mul":
                    //randNum1 = thisRandom.nextInt(101);
                    randNum2 = thisRandom.nextInt(101);
                    result = randNum1 * randNum2;
                    oper = "*";
                    break;

                case "div":
                    //randNum1 = thisRandom.nextInt(101); //3
                    randNum2 = thisRandom.nextInt(100 - 1) + 1; //5
                    int randTemp = randNum1 * randNum2; //15
                    result = randNum1;  //3
                    randNum1 = randTemp; // 15
                    oper = "/";
                    break;
            }

            Question thisQuestion = new Question(testID,randNum1,randNum2,oper, result,3,"Incorrect","No");
            levelThreeQuestions.add(thisQuestion);
        }


        return levelThreeQuestions;
    }

    public List<Question> generateLevel4Questions(String testType, String testID){

        List<Question> levelFourQuestions = new ArrayList<>();
        Random thisRandom =  new Random();
        int randNum1 = 0;
        int randNum2 = 0;
        int result = 0;
        String oper = "";

        for (int i = 0; i < 10; i++) {

            randNum1 = thisRandom.nextInt(1001);

            switch (testType) {
                case "add":
                    //randNum1 = thisRandom.nextInt(12 + 12 ) - 12;
                    randNum2 = thisRandom.nextInt(101);
                    result = randNum1 + randNum2;
                    oper = "+";
                    break;

                case "sub":
                    //randNum1 = thisRandom.nextInt(12 + 12) - 12;
                    randNum2 = thisRandom.nextInt(randNum1) + 1;
                    result = randNum1 - randNum2;
                    oper = "-";
                    break;

                case "mul":
                    //randNum1 = thisRandom.nextInt(12 + 12) - 12;
                    randNum2 = thisRandom.nextInt(101);
                    result = randNum1 * randNum2;
                    oper = "*";
                    break;

                case "div":
                    //randNum1 = thisRandom.nextInt(12 + 12) - 12; //Check to see if this generates a 0
                    randNum2 = thisRandom.nextInt(100 - 1) + 1; //5
                    int randTemp = randNum1 * randNum2; //15
                    result = randNum1;  //3
                    randNum1 = randTemp; // 15
                    oper = "/";
                    break;
            }

            Question thisQuestion = new Question(testID,randNum1,randNum2,oper, result,4,"Incorrect","No");
            levelFourQuestions.add(thisQuestion);
        }


        return levelFourQuestions;
    }
    public List<Question> generateLevel5Questions(String testType, String testID){

        List<Question> levelFiveQuestions = new ArrayList<>();
        Random thisRandom =  new Random();
        int randNum1 = 0;
        int randNum2 = 0;
        int result = 0;
        String oper = "";

        for (int i = 0; i < 10; i++) {

            randNum1 = thisRandom.nextInt(1001);

            switch (testType) {
                case "add":
                    //randNum1 = thisRandom.nextInt(20 + 20 ) - 20;
                    randNum2 = thisRandom.nextInt(1001 );
                    result = randNum1 + randNum2;
                    oper = "+";
                    break;

                case "sub":
                    //randNum1 = thisRandom.nextInt(20 + 1 + 20) - 20;
                    randNum2 = thisRandom.nextInt(randNum1) + 1;
                    result = randNum1 - randNum2;
                    oper = "-";
                    break;

                case "mul":
                    //randNum1 = thisRandom.nextInt(20 + 1 + 20) - 20;
                    randNum2 = thisRandom.nextInt(1001);
                    result = randNum1 * randNum2;
                    oper = "*";
                    break;

                case "div":
                    //randNum1 = thisRandom.nextInt(20 + 20) - 20; //Check to see if this generates a 0
                    randNum2 = thisRandom.nextInt(1000 - 1) +1; //5
                    int randTemp = randNum1 * randNum2; //15
                    result = randNum1;  //3
                    randNum1 = randTemp; // 15
                    oper = "/";
                    break;
            }

            Question thisQuestion = new Question(testID,randNum1,randNum2,oper, result,5,"Incorrect","No");
            levelFiveQuestions.add(thisQuestion);
        }


        return levelFiveQuestions;
    }

    /*public List<Question> generateLevel6Questions(String testType, String testID){

        List<Question> levelSixQuestions = new ArrayList<>();
        Random thisRandom =  new Random();
        int randNum1 = 0;
        int randNum2 = 0;
        int result = 0;
        String oper = "";

        for (int i = 0; i < 10; i++) {

            randNum1 = thisRandom.nextInt(100 + 100) - 100;

            switch (testType) {
                case "add":
                    //randNum1 = thisRandom.nextInt(100 + 1 + 100 ) - 100;
                    randNum2 = thisRandom.nextInt(100 + 100 ) - 100;
                    result = randNum1 + randNum2;
                    oper = "+";
                    break;

                case "sub":
                    //randNum1 = thisRandom.nextInt(100 + 1 + 100) - 100;
                    randNum2 = thisRandom.nextInt(100 + 100) - 100;
                    result = randNum1 - randNum2;
                    oper = "-";
                    break;

                case "mul":
                    //randNum1 = thisRandom.nextInt(100 + 1 + 100) - 100;
                    randNum2 = thisRandom.nextInt(100 + 100) - 100;
                    result = randNum1 * randNum2;
                    oper = "*";
                    break;

                case "div":
                    //randNum1 = thisRandom.nextInt(100 + 100) - 100; //Check to see if this generates a 0
                    randNum2 = thisRandom.nextInt(100 - 1 + 100) - 100; //5
                    int randTemp = randNum1 * randNum2; //15
                    result = randNum1;  //3
                    randNum1 = randTemp; // 15
                    oper = "/";
                    break;
            }

            Question thisQuestion = new Question(testID,randNum1,randNum2,oper, result,1,false);
            levelSixQuestions.add(thisQuestion);
        }


        return levelSixQuestions;
    }*/
}

