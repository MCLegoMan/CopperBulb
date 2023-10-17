package com.mclegoman.copperbulb.common;

import com.mclegoman.copperbulb.common.block.CopperBulbBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

public class CopperBulb implements ModInitializer {
	public static Block COPPER_BULB = Registry.register(Registries.BLOCK, new Identifier("copperbulb", "copper_bulb"), new CopperBulbBlock(FabricBlockSettings.create().luminance(Blocks.createLightLevelFromLitBlockState(15))));
	public static Item COPPER_BULB_ITEM = Registry.register(Registries.ITEM, new Identifier("copperbulb", "copper_bulb"), new BlockItem(COPPER_BULB, new FabricItemSettings()));
	public static Block EXPOSED_COPPER_BULB = Registry.register(Registries.BLOCK, new Identifier("copperbulb", "exposed_copper_bulb"), new CopperBulbBlock(FabricBlockSettings.create().luminance(Blocks.createLightLevelFromLitBlockState(11))));
	public static Item EXPOSED_COPPER_BULB_ITEM = Registry.register(Registries.ITEM, new Identifier("copperbulb", "exposed_copper_bulb"), new BlockItem(EXPOSED_COPPER_BULB, new FabricItemSettings()));
	public static Block WEATHERED_COPPER_BULB = Registry.register(Registries.BLOCK, new Identifier("copperbulb", "weathered_copper_bulb"), new CopperBulbBlock(FabricBlockSettings.create().luminance(Blocks.createLightLevelFromLitBlockState(7))));
	public static Item WEATHERED_COPPER_BULB_ITEM = Registry.register(Registries.ITEM, new Identifier("copperbulb", "weathered_copper_bulb"), new BlockItem(WEATHERED_COPPER_BULB, new FabricItemSettings()));
	public static Block OXIDIZED_COPPER_BULB = Registry.register(Registries.BLOCK, new Identifier("copperbulb", "oxidized_copper_bulb"), new CopperBulbBlock(FabricBlockSettings.create().luminance(Blocks.createLightLevelFromLitBlockState(3))));
	public static Item OXIDIZED_COPPER_BULB_ITEM = Registry.register(Registries.ITEM, new Identifier("copperbulb", "oxidized_copper_bulb"), new BlockItem(OXIDIZED_COPPER_BULB, new FabricItemSettings()));
	public static Block WAXED_COPPER_BULB = Registry.register(Registries.BLOCK, new Identifier("copperbulb", "waxed_copper_bulb"), new CopperBulbBlock(FabricBlockSettings.create().luminance(Blocks.createLightLevelFromLitBlockState(15))));
	public static Item WAXED_COPPER_BULB_ITEM = Registry.register(Registries.ITEM, new Identifier("copperbulb", "waxed_copper_bulb"), new BlockItem(COPPER_BULB, new FabricItemSettings()));
	public static Block WAXED_EXPOSED_COPPER_BULB = Registry.register(Registries.BLOCK, new Identifier("copperbulb", "waxed_exposed_copper_bulb"), new CopperBulbBlock(FabricBlockSettings.create().luminance(Blocks.createLightLevelFromLitBlockState(11))));
	public static Item WAXED_EXPOSED_COPPER_BULB_ITEM = Registry.register(Registries.ITEM, new Identifier("copperbulb", "waxed_exposed_copper_bulb"), new BlockItem(EXPOSED_COPPER_BULB, new FabricItemSettings()));
	public static Block WAXED_WEATHERED_COPPER_BULB = Registry.register(Registries.BLOCK, new Identifier("copperbulb", "waxed_weathered_copper_bulb"), new CopperBulbBlock(FabricBlockSettings.create().luminance(Blocks.createLightLevelFromLitBlockState(7))));
	public static Item WAXED_WEATHERED_COPPER_BULB_ITEM = Registry.register(Registries.ITEM, new Identifier("copperbulb", "waxed_weathered_copper_bulb"), new BlockItem(WEATHERED_COPPER_BULB, new FabricItemSettings()));
	public static Block WAXED_OXIDIZED_COPPER_BULB = Registry.register(Registries.BLOCK, new Identifier("copperbulb", "waxed_oxidized_copper_bulb"), new CopperBulbBlock(FabricBlockSettings.create().luminance(Blocks.createLightLevelFromLitBlockState(3))));
	public static Item WAXED_OXIDIZED_COPPER_BULB_ITEM = Registry.register(Registries.ITEM, new Identifier("copperbulb", "waxed_oxidized_copper_bulb"), new BlockItem(OXIDIZED_COPPER_BULB, new FabricItemSettings()));
	@Override
	public void onInitialize() {
		OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_BULB, EXPOSED_COPPER_BULB);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_BULB, WEATHERED_COPPER_BULB);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_BULB, OXIDIZED_COPPER_BULB);

		OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_BULB, WAXED_COPPER_BULB);
		OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_BULB, WAXED_EXPOSED_COPPER_BULB);
		OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_BULB, WAXED_WEATHERED_COPPER_BULB);
		OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_BULB, WAXED_OXIDIZED_COPPER_BULB);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
			content.addAfter(Blocks.REDSTONE_LAMP, COPPER_BULB_ITEM);
			content.addAfter(COPPER_BULB_ITEM, EXPOSED_COPPER_BULB_ITEM);
			content.addAfter(EXPOSED_COPPER_BULB_ITEM, WEATHERED_COPPER_BULB_ITEM);
			content.addAfter(WEATHERED_COPPER_BULB_ITEM, OXIDIZED_COPPER_BULB_ITEM);
			content.addAfter(OXIDIZED_COPPER_BULB_ITEM, WAXED_COPPER_BULB_ITEM);
			content.addAfter(WAXED_COPPER_BULB_ITEM, WAXED_EXPOSED_COPPER_BULB_ITEM);
			content.addAfter(WAXED_EXPOSED_COPPER_BULB_ITEM, WAXED_WEATHERED_COPPER_BULB_ITEM);
			content.addAfter(WAXED_WEATHERED_COPPER_BULB_ITEM, WAXED_OXIDIZED_COPPER_BULB_ITEM);
		});
	}
}