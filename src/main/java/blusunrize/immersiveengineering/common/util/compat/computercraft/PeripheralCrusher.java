package blusunrize.immersiveengineering.common.util.compat.computercraft;

import static blusunrize.immersiveengineering.common.util.Utils.saveStack;

import blusunrize.immersiveengineering.common.blocks.metal.TileEntityCrusher;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PeripheralCrusher extends IEPeripheral
{
	public static final String[] cmds = {"getQueueLength", "setEnabled", "isActive", "getInputStack", "getCurrentMaxProgress", "getCurrentProgress", "getMaxEnergyStored", "getEnergyStored"};
	public PeripheralCrusher(World w, int _x, int _y, int _z)
	{
		super(w, _x, _y, _z);
	}

	@Override
	public String getType()
	{
		return "IE:crusher";
	}

	@Override
	public String[] getMethodNames()
	{
		return cmds;
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments)
			throws LuaException, InterruptedException
	{
		TileEntityCrusher te = (TileEntityCrusher) getTileEntity(TileEntityCrusher.class);
		if (te==null)
			throw new LuaException("The crusher was removed");
		switch (method)
		{
		case 0: //queue length
			synchronized (te.inputs)
			{
				return new Object[]{te.inputs.size()};
			}
		case 1: //set RS simulation
			if (arguments.length!=1||!(arguments[0] instanceof Boolean))
				throw new LuaException("Wrong amount of arguments, needs one boolean");
			boolean param = (boolean)arguments[0];
			te.computerOn = param;
			return null;
		case 2: //get active
			return new Object[]{te.active};
		case 3: //get queue element
			if (arguments.length!=1||!(arguments[0] instanceof Double))
				throw new LuaException("Wrong amount of arguments, needs one integer");
			int id = (int) (double)arguments[0];
			ItemStack stack;
			synchronized (te.inputs) {
				if (id<1||id>te.inputs.size())
					throw new LuaException("The requested place in the queue does not exist");
				stack = te.inputs.get(id-1);
			}
			return new Object[]{saveStack(stack)};
		case 4://max progress
			if (te.inputs.isEmpty())
				throw new LuaException("The crusher doesn't have any inputs");
			int time = te.getRecipeTime(te.inputs.get(0));
			if (time<=0)
				throw new LuaException("The current crusher recipe is invalid");
			return new Object[]{time};
		case 5://current progress
			if (te.inputs.isEmpty())
				throw new LuaException("The crusher doesn't have any inputs");
			time = te.getRecipeTime(te.inputs.get(0))-te.process;
			if (time<=0)
				throw new LuaException("The current crusher recipe is invalid");
			return new Object[]{time};
		case 6://max energy
			return new Object[]{te.energyStorage.getMaxEnergyStored()};
		case 7://current energy
			return new Object[]{te.energyStorage.getEnergyStored()};
		}
		return null;
	}

	@Override
	public void attach(IComputerAccess computer)
	{
		TileEntityCrusher te = (TileEntityCrusher) w.getTileEntity(x, y, z);
		if (te==null)
			return;
		te.computerControlled = true;
		te.computerOn = true;
	}

	@Override
	public void detach(IComputerAccess computer)
	{
		TileEntityCrusher te = (TileEntityCrusher) w.getTileEntity(x, y, z);
		if (te==null)
			return;
		te.computerControlled = false;
	}
}
