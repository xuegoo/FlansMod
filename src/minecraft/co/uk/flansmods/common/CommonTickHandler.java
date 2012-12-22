package co.uk.flansmods.common;

import java.util.EnumSet;

import co.uk.flansmods.common.teams.TeamsManager;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if (type.equals(EnumSet.of(TickType.RENDER)))
		{
			rTick(FMLClientHandler.instance().getClient());
		}
		if (type.equals(EnumSet.of(TickType.CLIENT)))
		{
			cTick(FMLClientHandler.instance().getClient());
		}
		if (type.equals(EnumSet.of(TickType.SERVER)))
		{
			sTick(FMLCommonHandler.instance().getMinecraftServerInstance());
		}
	}
	
	public void rTick(Minecraft minecraft)
	{ /* Client side only */
	}

	public void cTick(Minecraft minecraft)
	{ /* Client side only */
	}

	public void sTick(MinecraftServer minecraft)
	{
		/* Server */
		TeamsManager.getInstance().tick();
		FlansMod.playerHandler.tick();
	}
	
	// TODO: make this didicated server-safe
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT, TickType.SERVER, TickType.RENDER);
	}

	@Override
	public String getLabel()
	{
		return null;
	}

}