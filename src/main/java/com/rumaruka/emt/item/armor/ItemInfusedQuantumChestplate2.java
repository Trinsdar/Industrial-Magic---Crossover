package com.rumaruka.emt.item.armor;

import com.rumaruka.emt.EMT;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;

public class ItemInfusedQuantumChestplate2 extends ItemArmor implements IElectricItem, ISpecialArmor {
    public ItemInfusedQuantumChestplate2(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
    }

    public boolean isClassicLoaded(){
        if (Loader.isModLoaded("ic2-classic-spmod")){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return EMT.TEXTURE_PATH+":textures/models/armor/quantumarmor.png";
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            ItemStack empty = new ItemStack(this, 1, 0);
            ItemStack full = new ItemStack(this, 1, 0);
            ElectricItem.manager.discharge(empty, 2.147483647E9D, 2147483647, true, false, false);
            ElectricItem.manager.charge(full, 2.147483647E9D, 2147483647, true, false);
            items.add(empty);
            items.add(full);
        }
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        if (isClassicLoaded()){
            return true;
        }else {
            return ElectricItem.manager.getCharge(stack) != this.getMaxCharge(stack);
        }
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        return 1.0D - ElectricItem.manager.getCharge(stack) / this.getMaxCharge(stack);
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        if (isClassicLoaded()){
            return 800000;
        }else{
            return 8000000;
        }
    }

    @Override
    public int getTier(ItemStack itemStack) {
        if (isClassicLoaded()){
            return 3;
        }else {
            return 4;
        }
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        if (isClassicLoaded()){
            return 1000;
        }else {
            return 10000;
        }
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        return null;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {

    }
}
