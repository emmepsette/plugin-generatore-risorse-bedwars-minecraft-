package tutorial.tutorial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOExpection

        public class BedwarsResourceGenerator extends JavaPlugin implements Listener {

            private FileConfiguration config;

    @Override
    public void onEnable() {
        //carica o crea il file di configurazione
        loadConfig();

        //registra gli eventi
        getServer().getPluginManager().registerEvents(this, this);
    }

    private void loadConfig() {
        file configFile = new FIle (getDataFolder()), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false)
        }

        config = YamlConfiguration.loadConfiguration(configFile);

    }

    @eventHandler
    public  void onBlockBreak(BlockBreakEvent event) {
        block block = event.getBlock();

        //controlla se il blocco rotto è un generatore di risorse
        if (isResourceGenerator(block.getType())) {
            event.setCancelled(true);  // Impedisce la distruzione del blocco
            generateResources(block);
        }
    }

    private boolean isResourceGenerator(material material) {
        //verifica se il materiale è configurato come generatore si risorse
        return  config.getStringList("generatorMaterials").contains(material.name());
    }

    private void generateResources(Block block) {
        //ottieni il tipo di risorsa dal blocco
        material resourceType = material.getMaterial(config.getString("generatorResources"));

        //ottieni la quantità di risorse dal blocco
        int resourceAmount = config.getInt("generatorAmount");

        //rilascia le risorse
        block.getWorld().dropItemNaturally(block.GetLocation(), new org.bukkit.inventory.ItemStack(resourceType, resourceAmount));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public tutorial getInstance(){
        return instance;
    }
}
