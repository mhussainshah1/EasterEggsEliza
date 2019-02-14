package oop;

public class PigLatin {

    private String pigString;

    public PigLatin(){
        pigString = "";
    }

    public String getPigString(String str) {
        for (String retval : str.split(" ")) {
            char v = retval.charAt(0);
            if (v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u') {
                int choice = 1+ (int) (Math.random() * 2) ;
                switch (choice){
                    case 1:
                        pigString += retval + "way ";
                        break;
                    case 2:
                        pigString += retval + "tay ";
                        break;
                }
            } else {
                pigString += retval + "ay ";
            }
        }
        return pigString;
    }
}
