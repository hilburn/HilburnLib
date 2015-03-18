package hilburnlib.reference;

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
        return (a << 24) | ((r & 255) << 16) | ((g & 255) << 8) | ((b & 255));
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
}
