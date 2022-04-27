package blusunrize.immersiveengineering.client.render;

import blusunrize.immersiveengineering.client.ClientUtils;
import blusunrize.immersiveengineering.common.Config;
import blusunrize.immersiveengineering.common.blocks.metal.TileEntitySampleDrill;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.obj.WavefrontObject;
import org.lwjgl.opengl.GL11;

public class TileRenderSampleDrill extends TileEntitySpecialRenderer
{
	static WavefrontObject model = ClientUtils.getModel("immersiveengineering:models/coredrill.obj");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f)
	{
		TileEntitySampleDrill drill = (TileEntitySampleDrill)tile;
		if(drill.pos==0)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x+.5f, (float)y+1.25f, (float)z+.5f);

			ClientUtils.bindTexture("immersiveengineering:textures/blocks/metal_coreDrill.png");
			GL11.glDisable(GL11.GL_CULL_FACE);
			model.renderPart("drill_base");
			GL11.glEnable(GL11.GL_CULL_FACE);

			int max = Config.getInt("coredrill_time");
			if(drill.process>0 && drill.process<max)
			{
				GL11.glRotatef( ((drill.process+f)*22.5f)%360f, 0,1,0);
				float push = drill.process/(float)max;
				if(drill.process>max/2)
					push = 1-push;
				GL11.glTranslatef(0,-2.8f*push,0);
			}
			model.renderPart("drill");

			GL11.glPopMatrix();
		}
	}
}