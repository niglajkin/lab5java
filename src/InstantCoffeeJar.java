/**
 * Instant coffee product packaged in a jar.
 */
public final class InstantCoffeeJar extends CoffeeProduct
{
    private final String jarMaterial;

    /**
     * Creates an instant coffee jar product.
     *
     * @param displayName product name
     * @param brand product brand/origin
     * @param weightKg weight in kilograms
     * @param packageVolumeM3 package volume in cubic meters
     * @param price price
     * @param qualityScore quality score
     * @param jarMaterial jar material (e.g., "Glass", "Plastic")
     * @throws IllegalArgumentException if parameters are invalid
     */
    public InstantCoffeeJar(
            String displayName,
            String brand,
            double weightKg,
            double packageVolumeM3,
            double price,
            int qualityScore,
            String jarMaterial)
    {
        super(displayName, brand, weightKg, packageVolumeM3, price, qualityScore);

        if (jarMaterial == null || jarMaterial.isBlank())
        {
            throw new IllegalArgumentException("jarMaterial must not be blank");
        }
        this.jarMaterial = jarMaterial;
    }

    /**
     * @return jar material
     */
    public String getJarMaterial()
    {
        return jarMaterial;
    }

    @Override
    public String getStateLabel()
    {
        return "Instant (Jar, " + jarMaterial + ")";
    }
}
