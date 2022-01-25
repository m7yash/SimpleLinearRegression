import java.util.Scanner;

public class ChiSquareTest {
    static Scanner sc = new Scanner(System.in);
    public static void main (String[] args)
    {
        System.out.println("Type o for one sample & one variable or anything else if you have multiple samples or variables");
        String type = sc.next();
        if (type.equals("o"))
        {
            one();
        } else
        {
           multiple();
        }
    }

    public static void one()
    {
        System.out.println("Number of rows?");
        int r = sc.nextInt();
        System.out.println("df = (cells - 1) = " + (r-1));
        double[][] table = new double[r][5];
        double[] hypTable = new double[r];
        double totalValue = 0.0;
        for (int i = 0; i < r; i++)
        {
            System.out.println("Enter what's in row " + (i+1));
            double d = sc.nextDouble();
            table[i][0] = d;
            totalValue += d;
            System.out.println("What is the hyp. prop.?");
            hypTable[i] = sc.nextDouble();
        }
        System.out.println("The total value of all observations is " + totalValue);
        double chisquared = 0.0;
        for (int i = 0; i < r; i++)
        {
           table[i][1] = totalValue * hypTable[i];
           table[i][2] = table[i][0] - table[i][1];
           table[i][3] = table[i][2] * table[i][2];
           table[i][4] = table[i][3] / table[i][1];
           chisquared += table[i][4];
        }
        for (int i = 0; i < r; i++)
        {
            System.out.println("Row " + (i+1));
            for (int j = 0; j < 5; j++)
            {
                System.out.println(table[i][j]);
            }
        }
        System.out.println("Chi Square value is " + chisquared);

    }

    public static void multiple()
    {
        System.out.println("Number of rows?");
        int r = sc.nextInt();
        System.out.println("Number of columns?");
        int c = sc.nextInt();
        System.out.println("df = (r-1)(c-1) = " + (r-1)*(c-1));
        double[][] table = new double[r][c];
        double[] rowTotals = new double[r];
        double[] colTotals = new double[c];
        double grandTotal = 0;
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                System.out.println("Enter what's in row " + (i+1) + " column " + (j+1));
                double d = sc.nextDouble();
                table[i][j] = d;
                grandTotal += d;
            }
        }
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                rowTotals[i] += table[i][j];
            }
            System.out.println("Row " + (i+1) + " total is " + rowTotals[i]);
        }
        for (int j = 0; j < c; j++)
        {
            for (int i = 0; i < r; i++)
            {
                colTotals[j] += table[i][j];
            }
            System.out.println("Col " + (j+1) + " total is " + colTotals[j]);
        }
        System.out.println("Grand Total is " + grandTotal);
        System.out.println("Below are the values.");
        double[][] expTable = new double[r][c];
        double chisquare = 0;
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                expTable[i][j] = rowTotals[i] * colTotals[j] / grandTotal;
                chisquare += (table[i][j] - expTable[i][j]) * (table[i][j] - expTable[i][j]) / expTable[i][j];
                System.out.println(table[i][j] + " / " + expTable[i][j]);
            }
        }
        System.out.println("Chi square value is " + chisquare);
    }
}
