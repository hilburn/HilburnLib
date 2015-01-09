package hilburnlib.base.interfaces;

import net.minecraft.nbt.NBTTagCompound;

public interface ISaveable<T>
{
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound);
    public T readFromNBT(NBTTagCompound tagCompound);
}
