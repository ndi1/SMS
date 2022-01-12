package helpers;

import java.util.List;

public class TableFormat {


    //Helper class and method to format tables correctly for output
    public String formatTable(List<List<String>> rows)
    {
        int[] maxLen = new int[rows.get(0).size()];
        for (List<String> row : rows)
        {
            for (int i = 0; i < row.size(); i++)
            {
                maxLen[i] = Math.max(maxLen[i], row.get(i).length());
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int maxLength : maxLen)
        {
            stringBuilder.append("%-").append(maxLength + 5).append("s");
        }
        String format = stringBuilder.toString();

        StringBuilder result = new StringBuilder();
        for (List<String> row : rows)
        {
            result.append(String.format(format, row.toArray(new String[0]))).append("\n");
        }
        return result.toString();
    }
}
