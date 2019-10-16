package com.mzha.nsuns.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.mzha.nsuns.MainActivity;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {

            double tm_ohp = MainActivity.TM_OHP;
            double tm_squat = MainActivity.TM_SQUAT;
            double tm_bench = MainActivity.TM_BENCH;
            double tm_dead = MainActivity.TM_DL;

            StringBuilder str = new StringBuilder();
            switch(input) {
                case 1:
                    exerciseTable day1Table = new exerciseTable();
                    day1Table.setTm_t1(tm_bench);
                    day1Table.setTm_t2(tm_ohp);
                    str.append(volBenchDay.generateTable(day1Table, input));
                    break;
                case 2:
                    exerciseTable day2Table = new exerciseTable();
                    day2Table.setTm_t1(tm_squat);
                    day2Table.setTm_t2(tm_dead);
                    str.append(deadliftSquatDays.generateTable(day2Table, input));
                    break;
                case 3:
                    exerciseTable day3Table = new exerciseTable();
                    day3Table.setTm_t1(tm_ohp);
                    day3Table.setTm_t2(tm_bench);
                    str.append(ohpBenchDays.generateTable(day3Table, input));
                    break;
                case 4:
                    exerciseTable day4Table = new exerciseTable();
                    day4Table.setTm_t1(tm_dead);
                    day4Table.setTm_t2(tm_squat);
                    str.append(deadliftSquatDays.generateTable(day4Table, input));
                    break;
                case 5:
                    exerciseTable day5Table = new exerciseTable();
                    day5Table.setTm_t1(tm_bench);
                    day5Table.setTm_t2(tm_ohp);
                    str.append(ohpBenchDays.generateTable(day5Table, input));
                    break;
                case 6:
                    str.append("Repeat phase one until you're able to get 5 confidently proper pull-ups in and you were able to complete all the reps of phase 2. If you were able to hit 5 barely and complete all reps, repeat phase 1 but include week 3 (From Phase 2). Then do week 3 again then continue with rest of phase 2.\n" +
                            "\n" +
                            "Week 1 (Phase one):\n" +
                            "\n" +
                            "Day 1: Pull-ups 1x3,1x2, 2x1, Rest of the day is the same\n" +
                            "\n" +
                            "Day 2: Pull-ups 1x3, 1x2, 2x1, Rest of the day is the same\n" +
                            "\n" +
                            "Day 3: Pull-ups 1x3 2x2, 1x1, Rest of the day is the same\n" +
                            "\n" +
                            "Day 4: Pull-ups 2x3, 1x2, 1x1, Rest of the day is the same\n" +
                            "\n" +
                            "Day 5: Pull-ups 1x4, 1x3, 2x1, 1x1, Rest of the day is the same\n" +
                            "\n" +
                            "Week 2:\n" +
                            "\n" +
                            "Day 1: Pull-ups 1x4, 1x3, 1x2, 2x1, \" \"\n" +
                            "\n" +
                            "Day 2: Pull-ups 1x4, 1x3, 2x2, 1x1 \" \"\n" +
                            "\n" +
                            "Day 3: Pull-ups 1x4, 2x3, 1x2, 1x1, \"\"\n" +
                            "\n" +
                            "Day 4: Pull-ups 2x4, 1x3, 1x2, 1x1, \"\"\n" +
                            "\n" +
                            "Day 5: Pull-ups 1x5, 1x4, 1x3, 1x2, 1x1\n" +
                            "\n" +
                            "Week 3 (Phase 2):\n" +
                            "\n" +
                            "Day 1: Pull-ups 1x5, 1x4, 1x3, 1x2, 1x1, \"\"\n" +
                            "\n" +
                            "Day 2: Pull-ups: 1x5, 1x4, 1x3, 2x2, \"\"\n" +
                            "\n" +
                            "Day 3: Pull-ups 1x5, 1x4, 2x3, 1x2, \"\"\n" +
                            "\n" +
                            "Day 4: Pull-ups 1x5, 2x4, 1x3, 1x2\n" +
                            "\n" +
                            "Day 5: Pull-ups 2x5, 1x4, 1x3, 1x2\n" +
                            "\n" +
                            "Week 4:\n" +
                            "\n" +
                            "Day 1: 1x6, 1x5, 1x4, 1x3, 1x2\n" +
                            "\n" +
                            "Day 2: 1x6, 1x5, 1x4, 2x3\n" +
                            "\n" +
                            "Day 3: 1x6, 1x5, 2x4, 1x3\n" +
                            "\n" +
                            "Day 4: 1x6, 2x5, 1x4, 1x3\n" +
                            "\n" +
                            "Day 5: 2x6, 1x5, 1x4, 1x3\n" +
                            "\n" +
                            "Week 4:\n" +
                            "\n" +
                            "Day 1: 1x7, 1x6, 1x5, 1x4, 1x3\n" +
                            "\n" +
                            "Day 2: 1x7, 1x6, 1x5, 2x4\n" +
                            "\n" +
                            "Day 3: 1x7, 1x6, 2x5, 1x4\n" +
                            "\n" +
                            "Day 4: 1x7, 2x6, 1x5, 1x4\n" +
                            "\n" +
                            "Day 5: 2x7, 1x6, 1x5, 1x4\n" +
                            "\n" +
                            "Week 5:\n" +
                            "\n" +
                            "Day 1: 1x8, 1x7, 1x6, 1x5, 1x4\n" +
                            "\n" +
                            "Day 2: 1x8, 1x7, 1x6, 2x5\n" +
                            "\n" +
                            "Day 3: 1x8, 1x7, 2x6, 1x5\n" +
                            "\n" +
                            "Day 4: 1x8, 2x7, 1x6, 1x5\n" +
                            "\n" +
                            "Day 5: 2x8, 1x7, 1x6, 1x5\n" +
                            "\n" +
                            "Week 6:\n" +
                            "\n" +
                            "Day 1: 1x9, 1x8, 1x7, 1x6, 1x5\n" +
                            "\n" +
                            "Day 2: 1x9, 1x8, 1x7, 2x6\n" +
                            "\n" +
                            "Day 3: 1x9, 1x8, 2x7, 1x6\n" +
                            "\n" +
                            "Day 4: 1x9, 2x8, 1x7, 1x6\n" +
                            "\n" +
                            "Day 5: 2x9, 1x8, 1x7, 1x6\n" +
                            "\n" +
                            "If you fail getting reps in week 5, repeat weeks 3-4 again the following week.");
                    break;
                default:
                    break;
            }
            return str.toString();
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
