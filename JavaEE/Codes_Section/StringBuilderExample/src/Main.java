public class Main {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        //System.out.println(stringBuilder.capacity());
        //Appending strings
        stringBuilder.append("Hello").append(" Shadril").append(" Hassan");
        System.out.println(stringBuilder.toString());
        stringBuilder.append(" BJIT").append(" Academy");
        System.out.println(stringBuilder.toString());

        //Inserting strings at specific positions
        stringBuilder.insert(1, "--");
        System.out.println(stringBuilder.toString());

        //Replacing a substring
        int startIndex = stringBuilder.indexOf("--");
        int endIndex = startIndex + "--".length();
        stringBuilder.replace(startIndex, endIndex, "");
        System.out.println(stringBuilder.toString());

        //Deleting a substring
        int deleteStart = stringBuilder.indexOf("Hassan");
        int deleteEnd = deleteStart + "Hassan".length();
        stringBuilder.delete(deleteStart, deleteEnd);
        System.out.println(stringBuilder.toString());

        System.out.println(stringBuilder.capacity());
    }
}