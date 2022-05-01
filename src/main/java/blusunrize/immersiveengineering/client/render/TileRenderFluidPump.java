package blusunrize.immersiveengineering.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import blusunrize.immersiveengineering.client.models.ModelIEObj;
import blusunrize.immersiveengineering.common.IEContent;
import blusunrize.immersiveengineering.common.blocks.metal.BlockMetalDevices2;
import blusunrize.immersiveengineering.common.util.chickenbones.Matrix4;

public class TileRenderFluidPump extends TileRenderIE
{
	static ModelIEObj model = new ModelIEObj("immersiveengineering:models/pump.obj") {
		@Override
		public IIcon getBlockIcon(String groupName) {
			return IEContent.blockMetalDevice2.getIcon(6, BlockMetalDevices2.META_fluidPump);
		}
	};

	@Override
	public void renderDynamic(TileEntity tile, double x, double y, double z, float f)
	{
	}

	@Override
	public void renderStatic(TileEntity tile, Tessellator tes, Matrix4 translationMatrix, Matrix4 rotationMatrix)
	{
		translationMatrix.translate(.5, 1, .5);
		model.render(tile, tes, translationMatrix, rotationMatrix, tile.getWorldObj()==null?-1:0, false);
	}
}
