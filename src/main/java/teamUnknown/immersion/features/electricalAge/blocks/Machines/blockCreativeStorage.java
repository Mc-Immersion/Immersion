package teamUnknown.immersion.features.electricalAge.blocks.Machines;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamUnknown.immersion.Immersion;
import teamUnknown.immersion.core.feature.object.ImmersionContainer;
import teamUnknown.immersion.core.meta.GuiIds;
import teamUnknown.immersion.core.meta.ModMetadata;
import teamUnknown.immersion.core.utils.ChatHelper;
import teamUnknown.immersion.features.electricalAge.blocks.ImmersionElectricalBlock;
import teamUnknown.immersion.features.electricalAge.items.ElectricalItems;
import teamUnknown.immersion.features.electricalAge.tileEntitys.machine.TileEntityCreativeStorage;

public class blockCreativeStorage extends ImmersionElectricalBlock{

    public blockCreativeStorage(String name, Material material){
        super(name, material);
    }

    public IIcon output;
    public IIcon base;

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCreativeStorage();
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            if(!player.isSneaking()) {
                player.openGui(Immersion.instance, GuiIds.GUI_ENERGY_CELL_ID, world, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;//TODO
    }



    /**@Override
    public int getRenderType() {
        return -1;
    }**/

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return output;
    }

    public void registerBlockIcons(IIconRegister iconRegister) {

        output = iconRegister.registerIcon(ModMetadata.MOD_ID + ":" + "tile.machineOutput");
        base = iconRegister.registerIcon(ModMetadata.MOD_ID + ":" + "tile.machineBase");
    }

    public IIcon getIcon(int side, int meta) {
        if (side == 3) return output;
        return base;
    }
}
