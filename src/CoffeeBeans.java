/**
 * Coffee beans product (whole beans).
 */
public final class CoffeeBeans extends CoffeeProduct
{
    private final RoastLevel roastLevel;

    /**
     * Creates a coffee beans product.
     *
     * @param displayName product name
     * @param brand product brand/origin
     * @param weightKg weight in kilograms
     * @param packageVolumeM3 package volume in cubic meters
     * @param price price
     * @param qualityScore quality score
     * @param roastLevel roast level
     * @throws IllegalArgumentException if parameters are invalid
     */
    public CoffeeBeans(
            String displayName,
            String brand,
            double weightKg,
            double packageVolumeM3,
            double price,
            int qualityScore,
            RoastLevel roastLevel)
    {
        super(displayName, brand, weightKg, packageVolumeM3, price, qualityScore);

        if (roastLevel == null)
        {
            throw new IllegalArgumentException("roastLevel must not be null");
        }
        this.roastLevel = roastLevel;
    }

    /**
     * @return roast level
     */
    public RoastLevel getRoastLevel()
    {
        return roastLevel;
    }

    @Override
    public String getStateLabel()
    {
        return "Beans (" + roastLevel + ")";
    }
}
