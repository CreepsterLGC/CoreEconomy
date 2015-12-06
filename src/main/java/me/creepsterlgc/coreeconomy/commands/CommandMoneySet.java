package main.java.me.creepsterlgc.coreeconomy.commands;

import main.java.me.creepsterlgc.core.Controller;
import main.java.me.creepsterlgc.core.customized.CoreDatabase;
import main.java.me.creepsterlgc.core.customized.CorePlayer;
import main.java.me.creepsterlgc.core.utils.PermissionsUtils;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.command.CommandSource;

public class CommandMoneySet {

	public CommandMoneySet(CommandSource sender, String[] args) {
		
		if(!PermissionsUtils.has(sender, "core.money.set")) { sender.sendMessage(Texts.builder("You do not have permissions!").color(TextColors.RED).build()); return; }
		
		if(args.length != 3) { sender.sendMessage(Texts.of(TextColors.YELLOW, "Usage: ", TextColors.GRAY, "/money set <player> <amount>")); return; }
		
		CorePlayer player = CoreDatabase.getPlayer(CoreDatabase.getUUID(args[1].toLowerCase()));
		if(player == null) {
			sender.sendMessage(Texts.of(TextColors.RED, "Player not found!"));
			return;
		}
		
		double amount;
		try { amount = Double.parseDouble(args[2]); }
		catch(NumberFormatException e) {
			sender.sendMessage(Texts.of(TextColors.RED, "<amount> has to be a number!"));
			return;
		}
		
		player.setMoney(amount);
		player.update();
		
		if(Controller.getServer().getPlayer(player.getName()).isPresent()) {
			Player p = Controller.getServer().getPlayer(player.getName()).get();
			sender.sendMessage(Texts.of(TextColors.YELLOW, p.getName(), TextColors.GRAY, "'s money has been set to ", TextColors.GREEN, "$", amount));
			p.sendMessage(Texts.of(TextColors.YELLOW, sender.getName(), TextColors.GRAY, " has set your money to ", TextColors.GREEN, "$", amount));
			return;
		}
	
		sender.sendMessage(Texts.of(TextColors.YELLOW, player.getName(), TextColors.GRAY, "'s money has been set to ", TextColors.GREEN, "$", amount));
		
	}
	
}
