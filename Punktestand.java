package me.schiggii.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Punktestand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (label.equalsIgnoreCase("Punkte")) {
			if (args.length == 1) {

				p.sendMessage("§cEs ist ein Fehler aufgetreten! Versuche §7/punkte [clear|get] [Spieler]");

			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("clear")) {
					if (p.hasPermission("kc.keramik.clear")) {
						Player target = Bukkit.getPlayer(args[1]);
						if(target == null) {
							p.sendMessage("§cEs ist ein Fehler aufgetreten! §d" + args[1] + " §cist nicht Online!");
						} else if (target.isOnline()) {

								p.sendMessage("§cDu hast die Keramik-Stats von §d" + target.getName() + " §cgelöscht!");
								clear(target, target.getName());

							}
					} else {
						p.sendMessage("§cDu hast nicht die Rechte dafür! §7(kc.keramik.clear)");
					}
				} else if (args[0].equalsIgnoreCase("get")) {
					Player target = Bukkit.getPlayer(args[1]);
					if(target == null)
					{
						p.sendMessage("§cEs ist ein Fehler aufgetreten! §d" + args[1] + " §cist nicht Online!");
					} else if (target.isOnline()) {

							p.sendMessage("");
							p.sendMessage("§d" + target.getName() + " §rbesitzt:");
							p.sendMessage("");
							get(target, target.getName());
							p.sendMessage("");

						}

				} else {
					p.sendMessage("§cEs ist ein Fehler aufgetreten! Versuche §7/punkte [clear|get] [Spieler]");
				}

			} else {
				p.sendMessage("§cEs ist ein Fehler aufgetreten! Versuche §7/punkte [clear|get] [Spieler]");
			}

			return true;
		}
		return false;
	}

	public void get (Player p, String name) {

		File file = new File("plugins/KCBP/players/" + p.getUniqueId() + " , " + name , "Keramik" +".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int gelb = cfg.getInt("Gelbe Keramik" + ".gelb");
		int blau = cfg.getInt("Blaue Keramik" + ".blau");
		int grün = cfg.getInt("Grüne Keramik" + ".grün");
		int rot = cfg.getInt("Rote Keramik" + ".rot");
		int punkte = cfg.getInt("Punkte" + ".punkte");
		p.sendMessage("          §7" + gelb + " §eGelbe Keramik");
		p.sendMessage("          §7" + blau + " §bHell Blaue Keramik");
		p.sendMessage("          §7" + grün + " §2Grüne Keramik");
		p.sendMessage("          §7" + rot + " §cRote Keramik");
		p.sendMessage("          §aInsgesamt §6" + punkte + " §aPunkte");

	}

	public void clear (Player p, String name) {

		File file = new File("plugins/KCBP/players/" + p.getUniqueId() + " , " + name , "Keramik" +".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int block = 0;
		cfg.set("Grüne Keramik" +  ".grün", block);
		cfg.set("Rote Keramik" +  ".rot", block);
		cfg.set("Blaue Keramik" +  ".blau", block);
		cfg.set("Gelbe Keramik" +  ".gelb", block);
		cfg.set("Punkte" +  ".punkte", block);

		try
		{
			cfg.save(file);
		}
		catch (IOException ev)
		{
			ev.printStackTrace();
		}
	}
}
