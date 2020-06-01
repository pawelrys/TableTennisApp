package IO.IO_project;

import java.util.Random;

public class staticFunction {
    static boolean isNumeric(String s){
        if(s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }

    static int getFirstInt(String s){
        if(s.isEmpty()) return 0;
        int i = -1;
        for (int j = 0; j < s.length() &&  isNumber(s.charAt(j)); j++) {
            if(j == 0) i = 0;
            i *= 10;
            i += Integer.parseInt(String.valueOf(s.charAt(j)));
        }
        return i;
    }

    static int getSecondInt(String s){
        if(s.isEmpty()) return 0;
        int i = 0;
        int j = 0;
        for (; j < s.length() &&  isNumber(s.charAt(j)); j++) i++;

        i++;
        int tmp = -1;
        for (; i < s.length() &&  isNumber(s.charAt(i)) ; i++) {
            if (i == j + 1) tmp = 0;
            tmp *= 10;
            tmp += Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        return tmp;
    }

    static boolean isNumber(char s){
        return s >= '0' && s <= '9';
    }

    static int whoWinSet(int a, int b){
        if(a == 0 && b == 0) return 0;
        if(a == 11 && b <= 9) return 1;
        if(b == 11 && a <= 9) return 2;
        if(a > 11 && b == a - 2) return 1;
        if(b > 11 && a == b - 2) return 2;

        return -1;
    }

    static int random(int i){
        Random rand = new Random();
        return rand.nextInt(i);
    }


    public static int getPoints(int miejsce) {
        switch (miejsce){
            case 1:
                return 100;
            case 2:
                return 80;
            case 3:
                return 60;
            case 5:
                return 40;
            case 9:
                return 20;
            case 17:
                return 10;
            default:
                return 5;
        }
    }

    public static int isMatch(String set1, String set2, String set3, String set4, String set5){
        int p11, p12, p21, p22, p31, p32, p41, p42, p51, p52;

        p11 = staticFunction.getFirstInt(set1);
        p12 = staticFunction.getSecondInt(set1);

        p21 = staticFunction.getFirstInt(set2);
        p22 = staticFunction.getSecondInt(set2);

        p31 = staticFunction.getFirstInt(set3);
        p32 = staticFunction.getSecondInt(set3);

        p41 = staticFunction.getFirstInt(set4);
        p42 = staticFunction.getSecondInt(set4);

        p51 = staticFunction.getFirstInt(set5);
        p52 = staticFunction.getSecondInt(set5);

        if(p11 == -1 || p12 == -1 || p21 == -1 || p22 == -1 || p31 == -1 || p32 == -1 || p41 == -1 || p42 == -1 || p51 == -1 || p52 == -1){
            return -1;
        }

        int a = 0;
        int b = 0;

        if(staticFunction.whoWinSet(p11, p12) == -1 || staticFunction.whoWinSet(p21, p22) == -1 || staticFunction.whoWinSet(p31, p32) == -1 ||
                staticFunction.whoWinSet(p41, p42) == -1 || staticFunction.whoWinSet(p51, p52) == -1 || staticFunction.whoWinSet(p11, p12) == 0 ||
                staticFunction.whoWinSet(p21, p22) == 0 || staticFunction.whoWinSet(p31, p32) == 0) return -1;

        if(staticFunction.whoWinSet(p11, p12) == 1) a++;
        else if(staticFunction.whoWinSet(p11, p12) == 2) b++;
        else return -1;

        if(staticFunction.whoWinSet(p21, p22) == 1) a++;
        else if(staticFunction.whoWinSet(p21, p22) == 2) b++;
        else return -1;

        if(staticFunction.whoWinSet(p31, p32) == 1) a++;
        else if(staticFunction.whoWinSet(p31, p32) == 2) b++;
        else return -1;

        if(staticFunction.whoWinSet(p41, p42) == 1) a++;
        else if(staticFunction.whoWinSet(p41, p42) == 2) b++;
        else if(staticFunction.whoWinSet(p41, p42) == 0);
        else return -1;

        if(staticFunction.whoWinSet(p51, p52) == 1) a++;
        else if(staticFunction.whoWinSet(p51, p52) == 2) b++;
        else if(staticFunction.whoWinSet(p51, p52) == 0);
        else return -1;

        if(a == 3) return 1;
        else if (b == 3) return 2;
        else return -1;
    }

    public static String result(String set){
        String res = "";
        int p1, p2;
        p1 = staticFunction.getFirstInt(set);
        p2 = staticFunction.getSecondInt(set);
        if(p1 == 0 && p2 == 0){
            return "";
        }
        if(p1 == -1 || p2 == -1){
            return "error";
        }
        res = p1 + "-" + p2;
        return res;
    }

    public static int returnStartNumber(int number){
        int [] arr = {1, 32, 17, 16, 9, 24, 25, 8, 5, 28, 21, 12, 13, 20, 29, 4, 3, 30, 19, 14, 11, 22, 27,6, 7, 26, 23, 10, 15, 18, 31, 2};
        return arr[number];
    }

}
