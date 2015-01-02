package hilburnlib;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import hilburnlib.items.ItemUtils;
import hilburnlib.reference.Metadata;
import hilburnlib.reference.Reference;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION_FULL)
public class BaseMod
{
    static NBTTagCompound tag;
    @Mod.Instance(Reference.ID)
    public static BaseMod instance = new BaseMod();

    @Mod.Metadata(Reference.ID)
    public static ModMetadata metadata;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        metadata = Metadata.init(metadata);
        ItemStack stack = new ItemStack(Blocks.stone);
        tag = ItemUtils.addObjectToNBTTagCompound(new NBTTagCompound(),"stack",stack);
        return;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        ItemStack stack = ItemUtils.getObjectToNBTTagCompound(tag,"stack");
        return;
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event)
    {
    }
}
