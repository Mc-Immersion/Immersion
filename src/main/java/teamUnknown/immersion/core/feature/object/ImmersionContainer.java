package teamUnknown.immersion.core.feature.object;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import teamUnknown.immersion.core.providers.resources.ResourceProvider;

public class ImmersionContainer extends BlockContainer implements IImersionObject {

    private static final Material DEFAULT_MATERIAL = Material.rock;

    public ImmersionContainer() {
        super(DEFAULT_MATERIAL);
    }

    public ImmersionContainer(Material material) {
        super(material);
    }

    public ImmersionContainer(String name) {
        super(DEFAULT_MATERIAL);

        if (name == null)
            name = this.inferName();

        this.setBlockName(ResourceProvider.getBlockName(name));
        this.setBlockTextureName(ResourceProvider.getTextureName(name));

        //Sets a basic creative tab so that it is accessible. Calling this in the constuructor overrides this
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }

    private String inferName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void craftingRegistration() {

    }

    @Override
    public void forgeOreDict() {

    }
}