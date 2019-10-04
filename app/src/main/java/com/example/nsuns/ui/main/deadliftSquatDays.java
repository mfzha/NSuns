package com.example.nsuns.ui.main;

import java.util.ArrayList;
import java.util.Arrays;

public class deadliftSquatDays {
    public static String generateTable(exerciseTable myTable, Integer day) {

        myTable.sett1Percentages(new ArrayList<>(Arrays.asList(75.0, 85.0, 95.0, 90.0, 85.0, 80.0, 75.0, 70.0, 65.0)));
        if (day == 2) {
            myTable.sett2Percentages(new ArrayList<>(Arrays.asList(50.0, 60.0, 70.0, 70.0, 70.0, 70.0, 70.0, 70.0)));
        } else {
            myTable.sett2Percentages(new ArrayList<>(Arrays.asList(35.0, 45.0, 55.0, 55.0, 55.0, 55.0, 55.0, 55.0)));
        }


        final String[][] t1Lifts = myTable.gett1Lifts();
        final String[][] t2Lifts = myTable.gett2Lifts();
        ArrayList<Double> t1Percentages = myTable.gett1Percentages();
        ArrayList<Double> t2Percentages = myTable.gett2Percentages();

        for (int i = 0; i < t1Lifts.length; i++) {
            String repCount = "\t";
            if (day == 2) {
                switch (i){
                    case 0:
                    case 6:
                    case 7:
                        repCount = "\t5";
                        break;
                    case 2:
                        repCount = "\t1+";
                        break;
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                        repCount = "\t3";
                        break;
                    case 8:
                        repCount = "\t5+";
                        break;
                }
            } else {
                switch (i){
                    case 0:
                        repCount = "\t5";
                        break;
                    case 2:
                        repCount = "\t1+";
                        break;
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        repCount = "\t3";
                        break;
                    case 8:
                        repCount = "\t3+";
                        break;
                }
            }


            t1Lifts[i] = new String[] {t1Percentages.get(i).intValue() + "\t\t\t", Double.toString(2.5*(Math.round(myTable.getTm_t1()*t1Percentages.get(i)/(100*2.5)))), "\t\t" + repCount};
        }

        for (int i = 0; i < t2Lifts.length; i++) {
            String repCount = "\t";
            switch (i){
                case 0:
                case 6:
                    repCount = "\t6";
                    break;
                case 1:
                case 3:
                    repCount = "\t5";
                    break;
                case 2:
                    repCount = "\t3";
                    break;
                case 4:
                    repCount = "\t7";
                    break;
                case 5:
                    repCount = "\t4";
                    break;
                case 7:
                    repCount = "\t8";
                    break;
            }

            t2Lifts[i] = new String[] {t2Percentages.get(i).intValue() + "\t\t\t", Double.toString(2.5*(Math.round(myTable.getTm_t2()*t2Percentages.get(i)/(100*2.5)))), "\t\t" + repCount};
        }

        StringBuilder str = new StringBuilder();

        if (day == 2) {
            str.append("Squat\n");
        } else {
            str.append("Deadlifts\n");
        }
        str.append("%\t\t\t\tWt\t\t\t\tReps\n");

        for (final String[] row : t1Lifts) {
            for (String item : row) {
                str.append(item);
            }
            str.append("\n");
        }

        if (day == 2) {
            str.append("\nSumo DL\n");
        } else {
            str.append("\nFront Squats\n");
        }
        str.append("%\t\t\t\tWt\t\t\t\tReps\n");

        for (final String[] row : t2Lifts) {
            for (String item : row) {
                str.append(item);
            }
            str.append("\n");
        }

        if (day == 2) {
            str.append("\nAccessories: Legs, Abs\nlegs: \t\n" +
                    "\tsquat machine 3x10\n" +
                    "\tleg curls 3x10\n" +
                    "\tcalf raises 3x12-15 \n" +
                    "\tab/adductors 3x10-12 \n" +
                    "\tback raises 3x10 \n" +
                    "Abs:\t\n" +
                    "\tdecline situps 3x10\n");
            //str.append("Chest:\n\t\tdb press 3x8-10\n\t\tcable xover 3x10-12\n");
            //str.append("Shoulders:\n\t\trear delt flies curl 3x12-15\n\t\tlat raises 3x10-12\n\t\tface pulls 3x12-15");
        } else {
            str.append("\nAccessories: Back, Abs\t\n" +
                    "back:\t\n" +
                    "\tlat pull 3x8-10\n" +
                    "\tcable row 3x8-10\n" +
                    "\tone more row machine\n" +
                    "Abs: \t\n" +
                    "\tmedball leg raise 3x10"); /*Arms, Other\n");
            str.append("Arms:\n\t\tbarbell curls 3x10-12\n\t\ttricep pushdowns 3x10-12\n\t\t\t\tSS db lat raise\n\t\thammer curls 3x10-12\n\t\t\t\tSS cable lat raise\n");
            str.append("Shoulders:\n\t\tface pulls 3x12-15\n");
            str.append("Feel free to add more core work");*/
        }


        return str.toString();

    }
}
