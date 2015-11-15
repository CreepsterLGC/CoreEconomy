package main.java.me.creepsterlgc.coreeconomy;

import java.util.logging.Logger;

import main.java.me.creepsterlgc.core.files.FileCommands;
import main.java.me.creepsterlgc.coreeconomy.commands.CommandMoney;

import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "CoreEconomy", name = "Economy Module for Core", dependencies = "required-after:Core")

public class CoreEconomy {

	@Inject
	private Game game;
	
	@Inject
	Logger logger;
	
	public static CoreEconomy core;
	
	public static CoreEconomy getInstance() { return core; }
	public Game getGame() { return game; }
	
    @Listener
    public void onEnable(GameStartingServerEvent event) {
    	
    	if(FileCommands.MONEY()) game.getCommandDispatcher().register(this, new CommandMoney(), "money");
    	
    }
	
}