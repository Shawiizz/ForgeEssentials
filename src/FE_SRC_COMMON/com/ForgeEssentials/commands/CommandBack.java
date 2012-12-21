package com.ForgeEssentials.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.ForgeEssentials.core.PlayerInfo;
import com.ForgeEssentials.core.commands.ForgeEssentialsCommandBase;
import com.ForgeEssentials.util.Localization;
import com.ForgeEssentials.util.OutputHandler;
import com.ForgeEssentials.util.AreaSelector.WorldPoint;

public class CommandBack extends ForgeEssentialsCommandBase
{
	@Override
	public String getCommandName()
	{
		return "back";
	}

	@Override
	public void processCommandPlayer(EntityPlayer sender, String[] args)
	{
		PlayerInfo info = PlayerInfo.getPlayerInfo(sender);
		if (info.lastDeath != null)
		{
			WorldPoint death = info.lastDeath;
			EntityPlayerMP player = ((EntityPlayerMP) sender);
			if (player.dimension != death.dim)
			{
				// Home is not in this dimension. Move the player.
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, death.dim);
			}
			player.playerNetServerHandler.setPlayerLocation(death.x+0.5, death.y + 1, death.z+0.5, player.rotationYaw, player.rotationPitch);
		} else
			OutputHandler.chatError(sender, Localization.get(Localization.ERROR_NODEATHPOINT));
	}

	@Override
	public void processCommandConsole(ICommandSender sender, String[] args)
	{
	}

	@Override
	public boolean canConsoleUseCommand()
	{
		return false;
	}

	@Override
	public boolean canPlayerUseCommand(EntityPlayer player)
	{
		return true;
	}

	@Override
	public String getCommandPerm()
	{
		return "ForgeEssentials.BasicCommands." + getCommandName();
	}

}
