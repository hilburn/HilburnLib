package hilburnlib.compatibility;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;

public class VersionHelper
{
    public static boolean isModVersion(String modId, String version)
    {
        if (Loader.isModLoaded(modId))
        {
            for (ModContainer mod : Loader.instance().getActiveModList())
            {
                if (mod.getModId().equals(modId) && mod.getVersion().equals(version))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isModVersionGreater(String modId, int[] versionInt, String deliminator)
    {
        if (Loader.isModLoaded(modId))
        {
            for (ModContainer mod : Loader.instance().getActiveModList())
            {
                if (mod.getModId().equals(modId))
                {
                    String version = mod.getVersion();
                    String[] split = version.split(deliminator);
                    for (int i = 0; i<split.length && i<versionInt.length;i++)
                    {
                        String sub = split[i];
                        try {
                            if (Integer.valueOf(sub)<versionInt[i]) return false;
                        }
                        catch (Exception e)
                        {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
