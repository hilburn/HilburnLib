package hilburnlib.reference;

import cpw.mods.fml.common.ModMetadata;

import java.util.Arrays;

public class Metadata
{
    /**
     * Setup mod metadata
     *
     * @param metadata
     * @return edited metadata object
     */
    public static ModMetadata init(ModMetadata metadata)
    {
        metadata.modId = Reference.ID;
        metadata.name = Reference.NAME;
        metadata.description = Reference.DESCRIPTION;
        //metadata.url = "";
        //metadata.logoFile = "assets/" + Reference.ID + "/logo.png";
        metadata.version = Reference.V_MAJOR + "." + Reference.V_MINOR + "." + Reference.V_REVIS;
        metadata.authorList = Arrays.asList(Reference.AUTHORS);
        //metadata.credits = "";
        metadata.autogenerated = false;
        return metadata;
    }
}
