package cf.mcdTeam.immersion.features.electricalAge;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.network.NetworkRegistry;
import mcp.mobius.waila.api.IWailaRegistrar;
import cf.mcdTeam.immersion.Immersion;
import cf.mcdTeam.immersion.core.feature.Feature;
import cf.mcdTeam.immersion.core.feature.FeatureCommon;
import cf.mcdTeam.immersion.features.electricalAge.blocks.ElectricalBlocks;
import cf.mcdTeam.immersion.features.electricalAge.handler.ElectricalFeatureGuiHandler;
import cf.mcdTeam.immersion.features.electricalAge.items.ElectricalItems;
import cf.mcdTeam.immersion.features.electricalAge.thirdParty.WailaHandler;

@Feature(name = "Electrical Age", version = "0.1", isBase = true)
public class ElectricalFeature extends FeatureCommon{

    @Feature.FeatureElement(Feature.FeatureElement.Element.CONFIGURATION)
    public void registerConfiguration(){

    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.PREINITIALIZATION)
    public void preInit(){

        ElectricalBlocks.init();
        ElectricalItems.init();
    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.INTITIALIZATION)
    public void init(){
        FMLInterModComms.sendMessage("Waila", "register", "cf.mcdTeam.immersion.features.electricalAge.thirdParty.doWailaRegistry");
    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.EVENTBUS_EVENT)
    public void registerEventHandlers(){

        NetworkRegistry.INSTANCE.registerGuiHandler(Immersion.instance, new ElectricalFeatureGuiHandler());
    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.MOD_COMPATIBILITY)
    public void thirdPartyMods(){
    }

    @Feature.FeatureElement(Feature.FeatureElement.Element.NONSETUP)
    public void doWailaRegistry(IWailaRegistrar registrar){
        registrar.registerBodyProvider(new WailaHandler(), Immersion.class);
    }

    /**@Feature.FeatureElement(Feature.FeatureElement.Element.)
    public void clientThings(){

    }**/
}