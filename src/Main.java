import java.util.Arrays;

/**
 * Laboratory Work №5 — Inheritance and Polymorphism.
 *
 * <p>Variant is determined by {@code C13 = recordBookNumber % 13}.</p>
 * <p>For record book number 9085: C13 = 11.</p>
 *
 * <p>Variant 11 requirements:</p>
 * <ul>
 *   <li>Load a van with coffee products of different physical states</li>
 *   <li>Account for package volume (volume together with packaging)</li>
 *   <li>Sort products by price-to-weight ratio</li>
 *   <li>Find products within a specified coffee quality range</li>
 * </ul>
 */
public class Main
{
    /**
     * Program entry point.
     *
     * @param args command line arguments (not used, but required by JVM)
     */
    public static void main(String[] args)
    {
        try
        {
            final int recordBookNumber = 9085;

            final int c13 = 11;

            System.out.println("Record book number = " + recordBookNumber);
            System.out.println("C13 = " + c13);
            System.out.println();

            CargoVan van = new CargoVan(0.060, 2500.00, 16);

            CoffeeProduct[] available = new CoffeeProduct[] {
                    new CoffeeBeans("Arabica Beans", "Colombia Supremo", 1.0, 0.0045, 650.00, 88, RoastLevel.MEDIUM),
                    new CoffeeBeans("Robusta Beans", "Vietnam", 1.0, 0.0048, 480.00, 75, RoastLevel.DARK),
                    new GroundCoffee("Ground Coffee", "Ethiopia Yirgacheffe", 0.5, 0.0028, 420.00, 92, GrindLevel.MEDIUM),
                    new GroundCoffee("Espresso Ground", "Italy Blend", 0.25, 0.0016, 230.00, 84, GrindLevel.FINE),
                    new InstantCoffeeJar("Instant Coffee Jar", "Gold Classic", 0.2, 0.0014, 310.00, 78, "Glass"),
                    new InstantCoffeeJar("Instant Coffee Jar", "Budget Instant", 0.2, 0.0013, 190.00, 62, "Plastic"),
                    new InstantCoffeeSachets("Instant Sachets", "3-in-1 Pack", 0.3, 0.0025, 210.00, 55, 30),
                    new InstantCoffeeSachets("Instant Sachets", "Black Instant Pack", 0.3, 0.0023, 260.00, 70, 20)
            };

            System.out.println("Available products:");
            for (CoffeeProduct p : available)
            {
                System.out.println(p);
            }
            System.out.println();

            for (CoffeeProduct p : available)
            {
                try
                {
                    van.addProduct(p);
                }
                catch (IllegalStateException ex)
                {
                    System.out.println("Skip (cannot load): " + p.getDisplayName() + " (" + p.getBrand() + ") -> " + ex.getMessage());
                }
            }

            System.out.println();
            System.out.println("Loaded products:");
            for (CoffeeProduct p : van.getLoadedProducts())
            {
                System.out.println(p);
            }
            System.out.println();

            System.out.println("Total weight (kg): " + format2(van.getTotalWeightKg()));
            System.out.println("Total price: " + format2(van.getTotalPrice()));
            System.out.println("Used volume (m^3): " + format4(van.getUsedVolumeM3()));
            System.out.println("Remaining volume (m^3): " + format4(van.getRemainingVolumeM3()));
            System.out.println("Remaining budget: " + format2(van.getRemainingBudget()));
            System.out.println();

            van.sortByPriceToWeightRatioAscending();

            System.out.println("Sorted by price-to-weight ratio (price per kg) ascending:");
            for (CoffeeProduct p : van.getLoadedProducts())
            {
                System.out.println(p.getDisplayName()
                        + " | price/kg=" + format2(p.getPriceToWeightRatio())
                        + " | quality=" + p.getQualityScore());
            }
            System.out.println();

            int minQuality = 70;
            int maxQuality = 90;

            CoffeeProduct[] found = van.findByQualityRange(minQuality, maxQuality);

            System.out.println("Products with quality in range [" + minQuality + ", " + maxQuality + "]:");
            if (found.length == 0)
            {
                System.out.println("(none)");
            }
            else
            {
                System.out.println(Arrays.toString(found));
            }
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Formats a double value with 2 decimal digits without locale dependencies.
     *
     * @param value number to format
     * @return formatted string
     */
    private static String format2(double value)
    {
        return String.format(java.util.Locale.ROOT, "%.2f", value);
    }

    /**
     * Formats a double value with 4 decimal digits without locale dependencies.
     *
     * @param value number to format
     * @return formatted string
     */
    private static String format4(double value)
    {
        return String.format(java.util.Locale.ROOT, "%.4f", value);
    }
}
