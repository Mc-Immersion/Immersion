package cf.mcdTeam.immersion.features.spawnFeature;

import net.minecraftforge.common.MinecraftForge;
import cf.mcdTeam.immersion.core.feature.Feature;
import cf.mcdTeam.immersion.core.feature.Feature.FeatureData;
import cf.mcdTeam.immersion.core.feature.Feature.FeatureData.Data;
import cf.mcdTeam.immersion.core.feature.Feature.FeatureElement;
import cf.mcdTeam.immersion.core.feature.Feature.FeatureElement.Element;
import cf.mcdTeam.immersion.core.feature.configuration.IConfigurationProvider;
import cf.mcdTeam.immersion.core.feature.logging.FeatureLogger;
import cf.mcdTeam.immersion.core.feature.FeatureCommon;

/**
 *  Feature that forces player to spawn randomly
 */
@Feature(name = "playerspawning", version = "1.0")
public class FeatureSpawning extends FeatureCommon {
	
	@FeatureData(Data.LOGGER)
    FeatureLogger logger;
	
	private SpawnEventListener.RespawnConfig _spawnCfg;

    @FeatureElement(Element.CONFIGURATION)
    public void registerConfiguration(IConfigurationProvider cfg) 
    {
        this._spawnCfg = new SpawnEventListener.RespawnConfig();
        
		this._spawnCfg.MinDistanceFromWorldSpawn = cfg.getConfig("MinDistanceFromSpawn", "Minimum distance from world spawn", 500);
        this._spawnCfg.RespawnRadius = cfg.getConfig("RespawnRadius", "The distance between minimum and maximum distance of the respawn", 500);
        this._spawnCfg.RespawnTries = cfg.getConfig("RespawnTries", "The maximum number of times minecraft tries to find good spawn", 50);
    }

    @FeatureElement(Element.EVENTBUS_EVENT)
    public void registerEventListeners() 
    {
        MinecraftForge.EVENT_BUS.register(new SpawnEventListener(logger, this._spawnCfg));
    }
}