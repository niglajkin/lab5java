/**
 * Instant coffee product packaged as individual sachets.
 */
public final class InstantCoffeeSachets extends CoffeeProduct
{
    private final int sachetsCount;

    /**
     * Creates an instant coffee sachets product.
     *
     * @param displayName product name
     * @param brand product brand/origin
     * @param weightKg weight in kilograms
     * @param packageVolumeM3 package volume in cubic meters
     * @param price price
     * @param qualityScore quality score
     * @param sachetsCount number of sachets in the pack
     * @throws IllegalArgumentException if parameters are invalid
     */
    public InstantCoffeeSachets(
            String displayName,
            String brand,
            double weightKg,
            double packageVolumeM3,
            double price,
            int qualityScore,
            int sachetsCount)
    {
        super(displayName, brand, weightKg, packageVolumeM3, price, qualityScore);

        if (sachetsCount <= 0)
        {
            throw new IllegalArgumentException("sachetsCount must be > 0");
        }
        this.sachetsCount = sachetsCount;
    }

    /**
     * @return number of sachets
     */
    public int getSachetsCount()
    {
        return sachetsCount;
    }

    @Override
    public String getStateLabel()
    {
        return "Instant (Sachets x" + sachetsCount + ")";
    }
}
