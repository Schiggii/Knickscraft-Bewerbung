package me.schiggii.knickscraft;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.schiggii.commands.Punktestand;

public class main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Commands();
		Bukkit.getServer().getConsoleSender().sendMessage("§eKnickscraft Bewerbungs Plugin §aaktiviert!");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		Bukkit.getServer().getConsoleSender().sendMessage("§eKnickscraft Bewerbungs Plugin §cdeaktiviert!");
	}

	public void Commands() {
		getCommand("punkte").setExecutor(new Punktestand());
	}

	@EventHandler
	public void Break (BlockBreakEvent e) {
		Player p = e.getPlayer();
		String name = p.getName();

		if (e.getBlock().getType().equals(Material.YELLOW_GLAZED_TERRACOTTA)) {

			yellow(p, name);
			p.sendMessage("§aDu hast §eGelbe Keramik §aabgebaut, dies brachte dir 1 Punkt ein.");

		} else if (e.getBlock().getType().equals(Material.LIGHT_BLUE_GLAZED_TERRACOTTA)) {

			blue(p, name);
			p.sendMessage("§aDu hast §bHellblaue Keramik §aabgebaut, dies brachte dir 2 Punkte ein.");

		} else if (e.getBlock().getType().equals(Material.GREEN_GLAZED_TERRACOTTA)) {

			green(p, name);
			p.sendMessage("§aDu hast §2Grüne Keramik §aabgebaut, dies brachte dir 5 Punkte ein.");

		} else if (e.getBlock().getType().equals(Material.RED_GLAZED_TERRACOTTA)) {

			red(p, name);
			p.sendMessage("§aDu hast §cRote Keramik §aabgebaut, dies brachte dir 10 Punkte ein.");

		}

	}

	public void red (Player p, String name) {

		File file = new File("plugins/KCBP/players/" + p.getUniqueId() + " , " + name , "Keramik" +".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int punkte = cfg.getInt("Punkte" + ".punkte");
		int block = cfg.getInt("Rote Keramik" + ".rot");
		block = block+1;
		cfg.set("Rote Keramik" +  ".rot", block);
		punkte = punkte+10;
		cfg.set("Punkte" +  ".punkte", punkte);
		try
		{
			cfg.save(file);
		}
		catch (IOException ev)
		{
			ev.printStackTrace();
		}

	}

	public void green (Player p, String name) {

		File file = new File("plugins/KCBP/players/" + p.getUniqueId() + " , " + name , "Keramik" +".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int punkte = cfg.getInt("Punkte" + ".punkte");
		int block = cfg.getInt("Grüne Keramik" + ".grün");
		block = block+1;
		cfg.set("Grüne Keramik" +  ".grün", block);
		punkte = punkte+5;
		cfg.set("Punkte" +  ".punkte", punkte);
		try
		{
			cfg.save(file);
		}
		catch (IOException ev)
		{
			ev.printStackTrace();
		}

	}

	public void yellow (Player p, String name) {

		File file = new File("plugins/KCBP/players/" + p.getUniqueId() + " , " + name , "Keramik" +".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int punkte = cfg.getInt("Punkte" + ".punkte");
		int block = cfg.getInt("Gelbe Keramik" + ".gelb");
		block = block+1;
		cfg.set("Gelbe Keramik" +  ".gelb", block);
		punkte = punkte+1;
		cfg.set("Punkte" +  ".punkte", punkte);
		try
		{
			cfg.save(file);
		}
		catch (IOException ev)
		{
			ev.printStackTrace();
		}

	}

	public void blue (Player p, String name) {

		File file = new File("plugins/KCBP/players/" + p.getUniqueId() + " , " + name , "Keramik" +".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int punkte = cfg.getInt("Punkte" + ".punkte");
		int block = cfg.getInt("Blaue Keramik" + ".blau");
		block = block+1;
		cfg.set("Blaue Keramik" +  ".blau", block);
		punkte = punkte+2;
		cfg.set("Punkte" +  ".punkte", punkte);
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
