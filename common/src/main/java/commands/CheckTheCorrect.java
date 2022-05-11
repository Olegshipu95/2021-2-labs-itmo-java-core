package commands;//
import entities.Mood;
import entities.WeaponType;

import java.util.Arrays;
import java.util.Scanner;

public class CheckTheCorrect {

    public static Boolean checkTheCorrect(boolean type)  {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                String local = scanner.next();
                if (!local.equals("true") && !local.equals("false")) {
                    throw new NumberFormatException();
                }

                type = Boolean.parseBoolean(local);
                return type;
            } catch (Exception e) {
                System.out.print("Incorrect data entered, please re-enter: ");
            }
        }
    }

    public static Float checkTheCorrect(Float type) {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = Float.valueOf(scanner.next());
                return type;
            } catch (NumberFormatException e) {
                System.out.print("Incorrect data entered, please re-enter: ");
            }
        }
    }

    public static Integer checkTheCorrect(int type)  {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Incorrect data entered, please re-enter: ");
                continue;
            }

            return type;
        }
    }

    public static String checkTheCorrect(String type)  {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = scanner.nextLine();
                if (type != null && !type.trim().isEmpty()) {
                    return type;
                }

                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.print("Incorrect data entered, please re-enter: ");
            }
        }
    }

    public static Mood checkTheCorrect(Mood type){
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Введите значение для класса Mood из тех, которые представлены сейчас перед вами: ");
                Mood[] obj = Mood.values();

                for(int i = 0; i < obj.length; ++i) {
                    Mood localObj = obj[i];
                    System.out.print(localObj + " ");
                }
                System.out.println();
                if (!scanner.hasNext()) {
                    System.exit(1);
                }

                type = Mood.valueOf(scanner.nextLine());
                return type;
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect data entered, please re-enter: ");
            }
        }
    }

    public static WeaponType checkTheCorrect(WeaponType type) {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            try {
                System.out.println("Enter a value from those for the WeaponType class that are now in front of you: ");
                WeaponType[] obj = WeaponType.values();

                for(int i = 0; i < obj.length; ++i) {
                    WeaponType localObj = obj[i];
                    System.out.print(localObj + " ");
                }

                System.out.println();
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                type = WeaponType.valueOf(scanner.nextLine());
                return type;
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect data entered, please re-enter: ");
            }
        }
    }
    public static String[] fillingArgs(String[] args){
        if(args.length!=10)args = arrayToDesiredSize(args,10);
        try {
            String name = String.valueOf(args[0]);
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.print("Enter an Object name: ");
            args[0] = CheckTheCorrect.checkTheCorrect("");
        }
        try {
            Float.valueOf(args[1]);
        } catch (NumberFormatException e) {
            System.out.print("Enter a float value for the x coordinate: ");
            args[1] = CheckTheCorrect.checkTheCorrect(1.1F).toString();
        }
        try {
            Integer.valueOf(args[2]);
        } catch (NumberFormatException e) {
            System.out.print("Enter an Integer value for the y coordinate: ");
            args[2] = CheckTheCorrect.checkTheCorrect(Integer.valueOf(0)).toString();
        }
        try {
            if (!args[3].equals("true") && !args[3].equals("false")) {
                throw new NumberFormatException();
            }
            Boolean.valueOf(args[3]);
        } catch (NumberFormatException e) {
            System.out.print("Enter a bool value for realHero: ");
            args[3] = CheckTheCorrect.checkTheCorrect(Boolean.valueOf(false)).toString();
        }


        try {
            if (!args[4].equals("true") && !args[4].equals("false")) {
                throw new NumberFormatException();
            }
            Boolean.valueOf(args[4]);
        } catch (NumberFormatException e) {
            System.out.print("Enter a bool value for hasToothpick: ");
            args[4] = CheckTheCorrect.checkTheCorrect(Boolean.valueOf(false)).toString();
        }


        try {
            Float.valueOf(args[5]);
        } catch (NumberFormatException e) {
            System.out.print("Enter a float value for impactSpeed: ");
            args[5] = CheckTheCorrect.checkTheCorrect(0.0F).toString();
        }
        try {
            Integer.valueOf(args[6]);
        } catch (NumberFormatException e) {
            System.out.print("Enter an Integer value for the y coordinate: ");
            args[6] = CheckTheCorrect.checkTheCorrect(Integer.valueOf(0)).toString();
        }

        try {
            WeaponType.valueOf(args[7]);
        } catch (IllegalArgumentException e) {
            WeaponType weaponType = null;
            args[7] = CheckTheCorrect.checkTheCorrect(weaponType).toString();
        }

        try {
            Mood.valueOf(args[8]);
        } catch (IllegalArgumentException e) {
            Mood mood = null;
            args[8] = CheckTheCorrect.checkTheCorrect(mood).toString();
        }

        try {
            if (!args[9].equals("true") && !args[9].equals("false")) {
                throw new NumberFormatException();
            }
            args[9] = Boolean.valueOf(args[9]).toString();
        } catch (NumberFormatException e) {
            System.out.print("Enter a bool value for cool: ");
            args[9]=CheckTheCorrect.checkTheCorrect(Boolean.valueOf(false)).toString();
        }
        return args;
    }


    public static String[] arrayToDesiredSize(String[] args, int x){
        String[] local = args;
        if (args.length > x) {
            args = new String[x];
            args = (String[]) Arrays.copyOfRange(local, 0, args.length);
        }
        if (args.length < x) {
            args = new String[x];
            Arrays.fill(args, "");

            for(int i = 0; i < local.length; ++i) {
                args[i] = local[i];
            }

        }
        return args;
    }
}
