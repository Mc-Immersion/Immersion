package teamUnknown.immersion.core.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import teamUnknown.immersion.features.electricalAge.tileEntitys.TileEntityElectricalWire;

public abstract class CommonProxy implements IProxy{

    @Override
    public void registerTileEntitys() {

        GameRegistry.registerTileEntity(TileEntityElectricalWire.class, "ElectricalWire");
    }
}
