package thevault.compatibility.lua.methods.liquids;

import thevault.compatibility.lua.conversion.ConversionRegistry;
import thevault.reference.NBTTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.HashMap;
import java.util.Map;

public class LuaGetTankInfo extends LuaFluidMethod
{
    public LuaGetTankInfo()
    {
        super("getTankInfo", "(Direction direction)", 0, 1, String.class);
    }

    @Override
    public Object[] action(TileEntity te, Object[] args) throws Exception
    {
        ForgeDirection direction;
        if (args.length == 0) direction = ForgeDirection.UNKNOWN;
        else
        {
            direction = ConversionRegistry.getInstance().fromLua(args[1]);
            if (direction == null) throw new Exception("Invalid Direction");
        }
        return new Object[]{tanksToMap(((IFluidHandler)te).getTankInfo(direction))};
    }

    public static Map<Number, Object> tanksToMap(FluidTankInfo[] tanks)
    {
        Map<Number, Object> result = new HashMap<Number, Object>();
        for (int i = 0; i < tanks.length; i++)
        {
            if (tanks[i] != null) result.put(i, getTankMap(tanks[i]));
        }
        return result;
    }

    public static Map<String, Object> getTankMap(FluidTankInfo tank)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(NBTTags.FLUID, tank.fluid.getFluid().getName());
        result.put(NBTTags.AMOUNT, tank.fluid.amount);
        result.put(NBTTags.CAPACITY, tank.capacity);
        return result;
    }
}
