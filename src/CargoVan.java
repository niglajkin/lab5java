import java.util.Arrays;
import java.util.Comparator;

/**
 * Represents a cargo van that can be loaded with coffee products.
 *
 * <p>The van enforces two constraints:</p>
 * <ul>
 *   <li>Maximum available volume (including packaging volume)</li>
 *   <li>Maximum available budget (total price)</li>
 * </ul>
 *
 * <p>Loaded products are stored in a fixed-capacity array as required by the lab.</p>
 */
public final class CargoVan
{
    private final double capacityVolumeM3;
    private final double budget;
    private final CoffeeProduct[] products;
    private int count;
    private double usedVolumeM3;
    private double usedBudget;

    /**
     * Creates a cargo van instance.
     *
     * @param capacityVolumeM3 maximum volume capacity in cubic meters
     * @param budget maximum total cost allowed
     * @param maxItems maximum number of products that can be stored in the internal array
     * @throws IllegalArgumentException if parameters are invalid
     */
    public CargoVan(double capacityVolumeM3, double budget, int maxItems)
    {
        if (capacityVolumeM3 <= 0.0)
        {
            throw new IllegalArgumentException("capacityVolumeM3 must be > 0");
        }
        if (budget <= 0.0)
        {
            throw new IllegalArgumentException("budget must be > 0");
        }
        if (maxItems <= 0)
        {
            throw new IllegalArgumentException("maxItems must be > 0");
        }

        this.capacityVolumeM3 = capacityVolumeM3;
        this.budget = budget;
        this.products = new CoffeeProduct[maxItems];
        this.count = 0;
        this.usedVolumeM3 = 0.0;
        this.usedBudget = 0.0;
    }

    /**
     * Adds a product to the van if capacity and budget allow it.
     *
     * @param product product to add
     * @throws IllegalArgumentException if product is null
     * @throws IllegalStateException if array is full, capacity exceeded, or budget exceeded
     */
    public void addProduct(CoffeeProduct product)
    {
        if (product == null)
        {
            throw new IllegalArgumentException("product must not be null");
        }
        if (count >= products.length)
        {
            throw new IllegalStateException("van item limit reached");
        }

        double newUsedVolume = usedVolumeM3 + product.getPackageVolumeM3();
        double newUsedBudget = usedBudget + product.getPrice();

        if (newUsedVolume > capacityVolumeM3)
        {
            throw new IllegalStateException("not enough volume capacity");
        }
        if (newUsedBudget > budget)
        {
            throw new IllegalStateException("not enough budget");
        }

        products[count] = product;
        count++;
        usedVolumeM3 = newUsedVolume;
        usedBudget = newUsedBudget;
    }

    /**
     * Returns a copy of loaded products trimmed to the current count.
     *
     * @return array of loaded products
     */
    public CoffeeProduct[] getLoadedProducts()
    {
        return Arrays.copyOf(products, count);
    }

    /**
     * Calculates total weight of loaded products.
     *
     * @return total weight in kilograms
     */
    public double getTotalWeightKg()
    {
        double sum = 0.0;

        for (int i = 0; i < count; i++)
        {
            sum += products[i].getWeightKg();
        }

        return sum;
    }

    /**
     * Calculates total price of loaded products.
     *
     * @return total price
     */
    public double getTotalPrice()
    {
        return usedBudget;
    }

    /**
     * Returns used volume of loaded products.
     *
     * @return used volume in cubic meters
     */
    public double getUsedVolumeM3()
    {
        return usedVolumeM3;
    }

    /**
     * Returns remaining volume.
     *
     * @return remaining volume in cubic meters
     */
    public double getRemainingVolumeM3()
    {
        return capacityVolumeM3 - usedVolumeM3;
    }

    /**
     * Returns remaining budget.
     *
     * @return remaining budget
     */
    public double getRemainingBudget()
    {
        return budget - usedBudget;
    }

    /**
     * Sorts loaded products by the price-to-weight ratio in ascending order.
     *
     * <p>Price-to-weight ratio is interpreted as price per kilogram.</p>
     */
    public void sortByPriceToWeightRatioAscending()
    {
        Arrays.sort(
                products,
                0,
                count,
                Comparator.comparingDouble(CoffeeProduct::getPriceToWeightRatio)
                        .thenComparing(CoffeeProduct::getQualityScore, Comparator.reverseOrder())
                        .thenComparing(CoffeeProduct::getDisplayName, String.CASE_INSENSITIVE_ORDER)
        );
    }

    /**
     * Finds products with quality score in the inclusive range.
     *
     * @param minQuality minimum quality score (inclusive)
     * @param maxQuality maximum quality score (inclusive)
     * @return array of products matching the range
     * @throws IllegalArgumentException if range is invalid
     */
    public CoffeeProduct[] findByQualityRange(int minQuality, int maxQuality)
    {
        if (minQuality < 0 || maxQuality < 0)
        {
            throw new IllegalArgumentException("quality bounds must be non-negative");
        }
        if (minQuality > maxQuality)
        {
            throw new IllegalArgumentException("minQuality must be <= maxQuality");
        }

        CoffeeProduct[] temp = new CoffeeProduct[count];
        int found = 0;

        for (int i = 0; i < count; i++)
        {
            int q = products[i].getQualityScore();
            if (q >= minQuality && q <= maxQuality)
            {
                temp[found] = products[i];
                found++;
            }
        }

        return Arrays.copyOf(temp, found);
    }
}
