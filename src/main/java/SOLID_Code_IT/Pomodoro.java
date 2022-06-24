package SOLID_Code_IT;

import java.util.Arrays;
import java.util.Scanner;

public class Pomodoro {
    static boolean isTest = false;
    public static void main(String[] args) {
        System.out.println("Hello! Введите команду");
        String[] cmd = new Scanner(System.in).nextLine().split(" ");

        int workMin = 25;
        int breakMin = 5;
        int count = 1;

        int sizePrint = 30;

        boolean isCallHelp = false;

        for(int i=0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-help" -> {
                    System.out.println("help");
                    isCallHelp = true;
                }
                case "-w" -> workMin = Integer.parseInt(cmd[++i]);
                case "-b" -> breakMin = Integer.parseInt(cmd[++i]);
                case "-count" -> count = Integer.parseInt(cmd[++i]);
                case "-t" -> isTest = true
            }
        }

        if (!isCallHelp) {
            System.out.printf("Работаем %d min " +
                    "отдыхаем %d min, кол-во подходов %d\n", workMin, breakMin, count);
            long startTime = System.currentTimeMillis();
            for (int i = 1; i <= count; i++) {
                timer(workMin, breakMin, sizePrint)
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Pomodoro таймер истёк " + (endTime - startTime)/(1000+60));
        }
    }

    private  static void  timer (int workTime, int breakTime, int sizeProgressbar) {
        printProgress("Рабочее время:: ", workTime, sizeProgressbar);

        printProgress("Свободное время:: ", breakTime, sizeProgressbar);
    }
}
