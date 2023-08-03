public class StringJava {
    public static void main(String[] args) {
        //CharAtExample
        System.out.println("--CharAtExample--");
        String name = "bjitacademy";
        char ch = name.charAt(1);
        System.out.println(ch);

        //StringTrimExample
        System.out.println("--StringTrimExample--");
        String s1 = "  bjitacademy   ";
        System.out.println(s1.trim());

        //StringSplitExample
        System.out.println("--StringSplitExample--");
        String s2 = "bjit academy - j2ee";
        //String[] words=s2.split(" ");//splits the string based on whitespace
        String[] words=s2.split("-");//splits the string based on -
        for(String w:words){
            System.out.println(w);
        }

        //StringReplaceExample
        System.out.println("--StringReplaceExample--");
        String s3="bjitacademy is a very good it company";
        String replaceString=s3.replace('a','e');//replaces all occurrences of 'a' to 'e'
        System.out.println(replaceString);

        //StringConcatExample
        System.out.println("--StringConcatExample--");
        String s4="my name is";
        s4.concat("shadril hassan shifat");
        System.out.println(s4);
        s4 = s4.concat(" shadril (immutable so assign it explicitly)");
        System.out.println(s4);

        //StringContainsExample
        System.out.println("--StringContainsExample--");
        String s5="what do you know about me";
        System.out.println(s5.contains("do you know"));
        System.out.println(s5.contains("about"));
        System.out.println(s5.contains("hello"));
    }
}