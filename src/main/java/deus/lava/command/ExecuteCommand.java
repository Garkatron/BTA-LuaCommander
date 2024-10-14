package deus.lava.command;

import deus.lava.Lava;
import deus.lava.setup.LuaSandbox;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExecuteCommand extends Command {


	public ExecuteCommand(String... alts) {
		super("execute", alts);

	}

	@Override
	public boolean execute(CommandHandler commandHandler, CommandSender commandSender, String[] strings) {
		SubCommands.executeCommand.execute(commandSender, strings);
		return true;
	}

	@Override
	public boolean opRequired(String[] strings) {
		return false;
	}

	@Override
	public void sendCommandSyntax(CommandHandler commandHandler, CommandSender commandSender) {
		commandSender.sendMessage("§8< Command Syntax >");
		commandSender.sendMessage("§8> /execute <kind> <filePath.extension>");
	}
}
