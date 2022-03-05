import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Yash Mishra
 * Program that can help solve Linear Regression Statistics problems. Includes key statistics and regression report.
 */
public class LinearRegression {

    static int limit = 0; //default, will be set
    static Scanner sc = new Scanner(System.in);

    public static void main (String[] args) {
        System.out.println("Welcome!");
        System.out.println("What is x?");
        String x = sc.nextLine();
        System.out.println("x unit?");
        String xunit = sc.nextLine();
        System.out.println("What is y?");
        String y = sc.nextLine();
        System.out.println("y unit?");
        String yunit = sc.nextLine();
        System.out.println("Number of data points?");
        limit = sc.nextInt();
        System.out.println("Enter your data!");
        ArrayList<Double> xValues = new ArrayList<Double>();
        ArrayList<Double> yValues = new ArrayList<Double>();
        for (int i = 0; i < limit; i++) {
            System.out.println("Enter x value " + (i + 1));
            double dx = sc.nextDouble();
            xValues.add(dx);
        }
        for (int i = 0; i < limit; i++) {
            System.out.println("Enter y value " + (i + 1));
            double dy = sc.nextDouble();
            yValues.add(dy);
        }
        System.out.println("<CHECK LINREG CONDITIONS: QUANTITATIVE, STRAIGHT ENOUGH, NO OUTLIERS>");

        report(xValues, yValues, x, y, xunit, yunit);

        while (true)
        {
            System.out.println("Type a to add more data points. Type r to remove them. Type p to see points. Type any other character to exit.");
            String response = sc.next();
            if (response.equals("a")) {
                addPoints(xValues, yValues);
                report(xValues, yValues, x, y, xunit, yunit);
            } else if (response.equals("r")) {
                removePoints(xValues, yValues);
                report(xValues, yValues, x, y, xunit, yunit);
            } else if (response.equals("p"))
            {
                showPoints(xValues, yValues);
            } else {
                break;
            }
        }


    }

    public static void report(ArrayList<Double> xValues, ArrayList<Double> yValues, String x, String y, String xunit, String yunit)
    {
        showPoints(xValues, yValues);
        double xavg = average(xValues);
        double yavg = average(yValues);
        double sx = standardDev(xValues, xavg);
        double sy = standardDev(yValues, yavg);
        double r = correlationCoefficient(xValues, yValues);
        double rsquared = Math.pow(r, 2);
        double b1 = (r * sy) / sx;
        double b0 = yavg - (b1 * xavg);
        System.out.println("Total number of points: " + limit);
        System.out.println("x average: " + xavg);
        System.out.println("y average: " + yavg);
        System.out.println("x standard deviation: " + sx);
        System.out.println("y standard deviation: " + sy);
        System.out.println("r = " + r);
        System.out.println("r^2 = " + rsquared);
        System.out.println("b1 = (r * sy) / sx = (" + r + " * " + sy + ") / " + sx + " = " + b1);
        System.out.println("b0 = yavg - (b1 * xavg) = " + yavg + " - (" + b1 + " * " + xavg + ") = " + b0);
        System.out.println("Formula is yhat = " + b0 + " + (" + b1 + ")x");
        System.out.println("<EXPLAIN WHAT THE MODEL SHOWS BY ANALYZING FORM, DIRECTION, STRENGTH, OUTLIERS>");
        System.out.println("Changes in " + x + " explains " + (100 * rsquared) + "% of variability in " + y);
        System.out.println("The slope of the least squares regression line suggests that for each 1 " + xunit + " in " + x + " we expect an increase of " + b1 + " " + yunit + " in " + y + " on average.");
        System.out.println("The y-intercept (" + b0 + ") predicts that a [however you interpret 0 of the x variable] would average " + b0 + " " + yunit + " in " + y + ". IF APPLICABLE, EXPLAIN IT IS AN EXTRAPOLATION>");
        System.out.println("Do you want to make a prediction? Type y if you do. Type anything else to move on.");
        String predResponse = sc.next();
        if (predResponse.equals("y"))
        {
            System.out.println("Ok, let's make a prediction");
            System.out.println("Enter an x value to calculate yhat");
            double xextrap = sc.nextDouble();
            double yextrap = (b1 * xextrap) + b0;
            System.out.println("yhat for " + xextrap + " is " + yextrap + " " + yunit + ".");
            System.out.println("<EXPLAIN IF IT IS APPROPRIATE MODEL, SUCCESSFUL MODEL, AND EXTRAPOLATION>");
            System.out.println("What was the real y value there?");
            double yreal = sc.nextDouble();
            double residual = yreal - yextrap;
            System.out.println("Residual value: " + residual + " " + yunit + ".");
            if (residual < 0)
            {
                System.out.println("Because the residual is negative, the model was an overestimate");
            } else if (residual == 0)
            {
                System.out.println("The model was perfectly accurate here.");
            } else {
                System.out.println("Because the residual is positive, the model was an underestimate");
            }
        }
    }

    public static void showPoints(ArrayList<Double> xVals, ArrayList<Double> yVals)
    {
        String s = "";
        for (int i = 0; i < limit - 1; i++)
        {
            s += "(" + xVals.get(i) + ", " + yVals.get(i) + "), ";
        }
        s += "(" + xVals.get(limit - 1) + ", " + yVals.get(limit - 1) + ")";
        System.out.println("Points: " + s);
    }

    public static void addPoints(ArrayList<Double> xVals, ArrayList<Double> yVals)
    {
        System.out.println("Enter the x value of the new point.");
        double xNewPoint = sc.nextDouble();
        xVals.add(xNewPoint);
        System.out.println("Enter the y value of the new point.");
        double yNewPoint = sc.nextDouble();
        yVals.add(yNewPoint);
        limit++;
    }

    public static void removePoints(ArrayList<Double> xVals, ArrayList<Double> yVals)
    {
        System.out.println("What point was this? Start counting from 1.");
        int point = sc.nextInt() - 1;
        System.out.println("Removed point (" + xVals.get(point) + ", " + yVals.get(point) + ").");
        xVals.remove(point);
        yVals.remove(point);
        limit--;
    }

    public static double average(ArrayList<Double> values)
    {
        double sum = 0.0;
        for (double d : values)
        {
            sum += d;
        }
        double mean = sum / limit;
        return mean;
    }

    public static double standardDev(ArrayList<Double> values, double mean)
    {
        double sdHelper = 0.0;
        for (double d : values)
        {
            sdHelper += Math.pow(d - mean, 2);
        }
        double standardDeviation = Math.sqrt(sdHelper/(limit - 1));

        return standardDeviation;
    }

    public static double correlationCoefficient(ArrayList<Double> x, ArrayList<Double> y)
    {

        double sum_X = 0, sum_Y = 0, sum_XY = 0;
        double squareSum_X = 0, squareSum_Y = 0;

        for (int i = 0; i < limit; i++)
        {
            // sum of elements of array X.
            sum_X = sum_X + x.get(i);

            // sum of elements of array Y.
            sum_Y = sum_Y + y.get(i);

            // sum of X[i] * Y[i].
            sum_XY = sum_XY + x.get(i) * y.get(i);

            // sum of square of array elements.
            squareSum_X = squareSum_X + x.get(i) * x.get(i);
            squareSum_Y = squareSum_Y + y.get(i) * y.get(i);
        }

        // use formula for calculating correlation
        // coefficient.
        double corr = (double)(limit * sum_XY - sum_X * sum_Y)/
                (double)(Math.sqrt((limit * squareSum_X -
                        sum_X * sum_X) * (limit * squareSum_Y -
                        sum_Y * sum_Y)));

        return corr;
    }
}
