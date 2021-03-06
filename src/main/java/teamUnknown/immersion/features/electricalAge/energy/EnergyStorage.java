package teamUnknown.immersion.features.electricalAge.energy;

import net.minecraft.nbt.NBTTagCompound;

public class EnergyStorage implements IEnergyStorage {

    protected int energy;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public EnergyStorage(int capacity){

        this(capacity, capacity, capacity, false);
    }

    public EnergyStorage(int capacity, boolean shouldStartOfWithMaxEnergy) {

        this(capacity, capacity, capacity, shouldStartOfWithMaxEnergy);
    }

    public EnergyStorage(int capacity, int maxTransfer, boolean shouldStartOfWithMaxEnergy) {

        this(capacity, maxTransfer, maxTransfer, shouldStartOfWithMaxEnergy);
    }

    public EnergyStorage(int capacity, int maxReceive, int maxExtract, boolean shouldStartOfWithMaxEnergy) {

        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        if (shouldStartOfWithMaxEnergy) this.energy = capacity;
    }

    public EnergyStorage readFromNBT(NBTTagCompound nbt) {

        this.energy = nbt.getInteger("Energy");

        if (energy > capacity) {
            energy = capacity;
        }
        return this;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        if (energy < 0) {
            energy = 0;
        }
        nbt.setInteger("Energy", energy);
        return nbt;
    }

    public void setCapacity(int capacity) {

        this.capacity = capacity;

        if (energy > capacity) {
            energy = capacity;
        }
    }

    public void setMaxTransfer(int maxTransfer) {

        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
    }

    public void setMaxReceive(int maxReceive) {

        this.maxReceive = maxReceive;
    }

    public void setMaxExtract(int maxExtract) {

        this.maxExtract = maxExtract;
    }

    public int getMaxReceive() {

        return maxReceive;
    }

    public int getMaxExtract() {

        return maxExtract;
    }

    /**
     * This function is included to allow for server -&gt; client sync. Do not call this externally to the containing Tile Entity, as not all IEnergyHandlers
     * are guaranteed to have it.
     *
     * @param energy
     */
    public void setEnergyStored(int energy) {

        this.energy = energy;

        if (this.energy > capacity) {
            this.energy = capacity;
        } else if (this.energy < 0) {
            this.energy = 0;
        }
    }

    /**
     * This function is included to allow the containing tile to directly and efficiently modify the energy contained in the EnergyStorage. Do not rely on this
     * externally, as not all IEnergyHandlers are guaranteed to have it.
     *
     * @param energy
     */
    public void modifyEnergyStored(int energy) {

        this.energy += energy;

        if (this.energy > capacity) {
            this.energy = capacity;
        } else if (this.energy < 0) {
            this.energy = 0;
        }
    }

    /* IEnergyStorage */
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {

        return energy;
    }

    @Override
    public int getMaxEnergyStored() {

        return capacity;
    }

    @Override
    public boolean canAddEnergy(int amount) {
        return (energy + amount) <= capacity;
    }

    @Override
    public boolean canRemoveEnergy(int amount) {
        return (energy - amount) >= 0;
    }

    @Override
    public int getEnergyTransferRate() {
        return maxReceive;
    }

    public int addEnergyWithRemaining(int amount) {
        energy += amount;
        if (energy > capacity) {
            int powerRemaining = energy - capacity;
            energy -= powerRemaining;
            return powerRemaining;
        }
        return 0;
    }

    @Override
    public void removeEnergy(int amount) {
        energy -= amount;
    }

    @Override
    public void addEnergy(int amount) {
        energy += amount;
    }
}
