/**
 * Ground coffee product.
 */
public final class GroundCoffee extends CoffeeProduct
{
    private final GrindLevel grindLevel;

    /**
     * Creates a ground coffee product.
     *
     * @param displayName product name
     * @param brand product brand/origin
     * @param weightKg weight in kilograms
     * @param packageVolumeM3 package volume in cubic meters
     * @param price price
     * @param qualityScore quality score
     * @param grindLevel grind level
     * @throws IllegalArgumentException if parameters are invalid
     */
    public GroundCoffee(
            String displayName,
            String brand,
            double weightKg,
            double packageVolumeM3,
            double price,
            int qualityScore,
            GrindLevel grindLevel)
    {
        super(displayName, brand, weightKg, packageVolumeM3, price, qualityScore);

        if (grindLevel == null)
        {
            throw new IllegalArgumentException("grindLevel must not be null");
        }
        this.grindLevel = grindLevel;
    }

    /**
     * @return grind level
     */
    public GrindLevel getGrindLevel()
    {
        return grindLevel;
    }

    @Override
    public String getStateLabel()
    {
        return "Ground (" + grindLevel + ")";
    }
}
