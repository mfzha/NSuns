package com.mzha.nsuns.main;

import java.util.ArrayList;
import java.util.Arrays;

public class volBenchDay {
    public static String generateTable(exerciseTable myTable, Integer day) {

        myTable.sett1Percentages(new ArrayList<>(Arrays.asList(65.0, 75.0, 85.0, 85.0, 85.0, 80.0, 75.0, 70.0, 65.0)));
        myTable.sett2Percentages(new ArrayList<>(Arrays.asList(50.0, 60.0, 70.0, 70.0, 70.0, 70.0, 70.0)));

        final String[][] t1Lifts = myTable.gett1Lifts();
        final String[][] t2Lifts = myTable.gett2Lifts();
        ArrayList<Double> t1Percentages = myTable.gett1Percentages();
        ArrayList<Double> t2Percentages = myTable.gett2Percentages();

        for (int i = 0; i < t1Lifts.length; i++) {
            String repCount = "\t";
            switch (i){
                case 0:
                    repCount = "\t8";
                    break;
                case 1:
                case 6:
                    repCount = "\t6";
                    break;
                case 2:
                case 3:
                case 4:
                    repCount = "\t4";
                    break;
                case 5:
                    repCount = "\t5";
                    break;
                case 7:
                    repCount = "\t7";
                    break;
                case 8:
                    repCount = "\t8+";
                    break;
            }

            t1Lifts[i] = new String[] {t1Percentages.get(i).intValue() + "\t\t\t", Double.toString(2.5*(Math.round(myTable.getTm_t1()*t1Percentages.get(i)/(100*2.5)))), "\t\t" + repCount};
        }

        for (int i = 0; i < t2Lifts.length; i++) {
            String repCount = "\t";
            switch (i){
                case 0:
                case 6:
                    repCount = "\t8";
                    break;
                case 1:
                case 4:
                    repCount = "\t6";
                    break;
                case 2:
                    repCount = "\t4";
                    break;
                case 3:
                    repCount = "\t5";
                    break;
                case 5:
                    repCount = "\t7";
                    break;
            }

            if (i == 7) { break; }

            t2Lifts[i] = new String[] {t2Percentages.get(i).intValue() + "\t\t\t", Double.toString(2.5*(Math.round(myTable.getTm_t2()*t2Percentages.get(i)/(100*2.5)))), "\t\t" + repCount};
        }

        StringBuilder str = new StringBuilder();

        str.append("Volume Bench\n%\t\t\t\tWt\t\t\t\tReps\n");

        for (final String[] row : t1Lifts) {
            for (String item : row) {
                str.append(item);
            }
            str.append("\n");
        }

        str.append("\nOHP\n%\t\t\t\tWt\t\t\t\tReps\n");

        for (final String[] row : t2Lifts) {
            if (row == null) {
                break;
            }
            for (String item : row) {
                str.append(item);
            }
            str.append("\n");
        }

        str.append("\nAccessories: Chest, Arms, Back\n");
        str.append("Chest:\n\t\tincline press 3x8-10\n\t\tcable xover 3x10-12\n");
        str.append("Arms:\n\t\tbarbell curl 3x10-12\n\t\tchinups 5x?\n");
        str.append("Back:\n\t\tT-Bar rows 3x8-10\n");

        return str.toString();

    }
}
