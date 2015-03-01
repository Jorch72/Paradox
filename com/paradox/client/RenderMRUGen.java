package com.paradox.client;

import org.lwjgl.opengl.GL11;

import cofh.api.energy.IEnergyHandler;

import com.paradox.api.IParadoxStorage;
import com.paradox.tile.TileHandyGenerator;
import com.paradox.tile.TileRFGenerator;

import ec3.api.ITERequiresMRU;
import ec3.utils.common.ECUtils;
import DummyCore.Utils.DummyDataUtils;
import DummyCore.Utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderMRUGen extends TileEntitySpecialRenderer{
	
	public static final ResourceLocation generatorTextures = new ResourceLocation("paradoxmod","textures/models/common/powerCharge.png");

	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_,
			double p_147500_4_, double p_147500_6_, float p_147500_8_)
	{
		int x = p_147500_1_.xCoord;
		int y = p_147500_1_.yCoord;
		int z = p_147500_1_.zCoord;
		World w = p_147500_1_.getWorldObj();
		Minecraft.getMinecraft().renderEngine.bindTexture(generatorTextures);
		GL11.glPushMatrix();
		
		RenderHelper.disableStandardItemLighting();
			GL11.glTranslated(p_147500_2_, p_147500_4_, p_147500_6_);
			Tessellator tec = Tessellator.instance;
			
			for(int i = 0; i < 4; ++i)
			{
				GL11.glPushMatrix();

				ParadoxClient.glRotatefBasedOnLoop(i);

				GL11.glColor3f(1, 1, 1);
				
				IParadoxStorage storage = (IParadoxStorage) p_147500_1_;
				
				ParadoxClient.drawParadoxEnergy(storage);
				
				ITERequiresMRU gen = (ITERequiresMRU) p_147500_1_;
				
				ParadoxClient.drawMRU(gen);

				GL11.glPopMatrix();
			}
		ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, true, 2);
		ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, false, 3);

		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		
		ECUtils.renderMRUBeam(p_147500_1_, 0, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_, 0);
	}

}
