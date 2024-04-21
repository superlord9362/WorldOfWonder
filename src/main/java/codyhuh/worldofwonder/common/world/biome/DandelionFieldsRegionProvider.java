package codyhuh.worldofwonder.common.world.biome;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import codyhuh.worldofwonder.init.WonderBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.ParameterUtils.Continentalness;
import terrablender.api.ParameterUtils.Depth;
import terrablender.api.ParameterUtils.Erosion;
import terrablender.api.ParameterUtils.Humidity;
import terrablender.api.ParameterUtils.Temperature;
import terrablender.api.ParameterUtils.Weirdness;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class DandelionFieldsRegionProvider extends Region {
public static final ResourceLocation LOCATION = new ResourceLocation("minecraft:overworld");
	
	public DandelionFieldsRegionProvider(ResourceLocation name, int weight) {
		super(name, RegionType.OVERWORLD, weight);
	}
	
	@Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
		Temperature temperature = ParameterUtils.Temperature.WARM;
		Humidity humidity = ParameterUtils.Humidity.NEUTRAL;
		Continentalness continentalness = ParameterUtils.Continentalness.MID_INLAND;
		Erosion erosion = ParameterUtils.Erosion.FULL_RANGE;
		Depth depth = ParameterUtils.Depth.SURFACE;
		Weirdness weirdness = ParameterUtils.Weirdness.PEAK_VARIANT;
		this.addBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, depth, 0.4F, WonderBiomes.DANDELION_FIELDS);
	}
}
