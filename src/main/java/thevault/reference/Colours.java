package thevault.reference;

public class Colours
{
    public static final int BLACK = -16777216;
    public static final int BLUE = -16776961;
    public static final int CYAN = -16711681;
    public static final int DRKGRAY = -12303292;
    public static final int GRAY = -7829368;
    public static final int GREEN = -16711936;
    public static final int LTGRAY = -3355444;
    public static final int MAGENTA = -65281;
    public static final int RED = -65536;
    public static final int TRANSPARENT = 0;
    public static final int WHITE = -1;
    public static final int YELLOW = -256;

    public static float getRed(int color)
    {
        return (color >> 16 & 255) / 255.0F;
    }

    public static float getGreen(int color)
    {
        return ((color >> 8) & 255) / 255.0F;
    }

    public static float getBlue(int color)
    {
        return (color & 255) / 255.0F;
    }

    public static float getAlpha(int color)
    {
        return ((color >> 24) & 255) / 255.0F;
    }

    public static int RGB(int r, int g, int b)
    {
        return RGBA(r, g, b, 255);
    }

    public static int RGBA(int r, int g, int b, int a)
    {
        return (a << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
    }

    public static int RGB(float red, float green, float blue)
    {
        return RGBA((int) red * 255, (int) green * 255, (int) blue * 255, 255);
    }

    /**
     * Convert an #RRGGBB value to a int colour
     *
     * @param colour the #RRGGBB value
     * @return the int colour value or an {@link java.lang.IllegalArgumentException} if a mal formed input is given
     */
    public static int RGB(String colour)
    {
        if (!colour.startsWith("#") || !(colour.length() == 7)) throw new IllegalArgumentException("Use #RRGGBB format");
        return RGB(Integer.parseInt(colour.substring(1, 3), 16), Integer.parseInt(colour.substring(3, 5), 16), Integer.parseInt(colour.substring(5, 7), 16));
    }

    /**
     * Blends given int colours
     *
     * @param colours an amount of colours
     * @return the mix int colour value or an IllegalArgumentException if colours is empty
     */
    public static int blend(int... colours)
    {
        if (colours.length < 1)
            throw new IllegalArgumentException();

        int[] alphas = new int[colours.length];
        int[] reds = new int[colours.length];
        int[] greens = new int[colours.length];
        int[] blues = new int[colours.length];

        for (int i = 0; i < colours.length; i++)
        {
            alphas[i] = (colours[i] >> 24 & 0xff);
            reds[i] = ((colours[i] & 0xff0000) >> 16);
            greens[i] = ((colours[i] & 0xff00) >> 8);
            blues[i] = (colours[i] & 0xff);
        }

        float a, r, g, b;
        a = r = g = b = 0;
        float ratio = 1.0F / colours.length;

        for (int alpha : alphas)
            a += alpha * ratio;

        for (int red : reds)
            r += red * ratio;

        for (int green : greens)
            g += green * ratio;

        for (int blue : blues)
            b += blue * ratio;

        return ((int) a) << 24 | ((int) r) << 16 | ((int) g) << 8 | ((int) b);
    }

    /**
     * Tone a int colour
     * bigger then 1 will tone up, less then 1 will tone down
     * @param colour colour in int form
     * @param scale scale as float
     * @return the toned colour
     */
    public static int tone(int colour, float scale)
    {
        float r = (colour >> 16) & 255;
        float g = (colour >> 8) & 255;
        float b = colour & 255;
        return RGB(r * scale, g * scale, b * scale);
    }

    /**
     * Gives a colour based of {@link System#currentTimeMillis()} and given params
     *
     * @param freqR strength of the reds
     * @param freqG strength of the greens
     * @param freqB strength of the blues
     * @param phaseR phase shift red
     * @param phaseG phase shift green
     * @param phaseB phase shift blue
     * @param center center value
     * @param width width of colour range
     * @param length change rate
     * @return an int colour
     */
    public static int getRainbowColour(float freqR, float freqG, float freqB, float phaseR, float phaseG, float phaseB, float center, float width, float length)
    {
        long i = Math.abs((int) System.currentTimeMillis()) / (int)length;
        double r = Math.sin(freqR*i + phaseR) * width + center;
        double g = Math.sin(freqG*i + phaseG) * width + center;
        double b = Math.sin(freqB*i + phaseB) * width + center;
        return RGB((float)r, (float)g, (float)b);
    }
}
