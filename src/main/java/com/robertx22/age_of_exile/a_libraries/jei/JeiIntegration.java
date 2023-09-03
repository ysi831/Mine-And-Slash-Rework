package com.robertx22.age_of_exile.a_libraries.jei;

import com.robertx22.age_of_exile.database.data.profession.CraftingStationScreen;
import com.robertx22.age_of_exile.database.data.profession.Profession;
import com.robertx22.age_of_exile.database.data.profession.ProfessionRecipe;
import com.robertx22.age_of_exile.database.registry.ExileDB;
import com.robertx22.age_of_exile.mmorpg.SlashRef;
import com.robertx22.age_of_exile.mmorpg.registers.common.SlashBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

@JeiPlugin
public class JeiIntegration implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return SlashRef.id("plugin");
    }

    public static HashMap<String, RecipeType<ProfessionRecipe>> map = new HashMap<>();

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();

        init();

        for (Map.Entry<String, RecipeType<ProfessionRecipe>> en : map.entrySet()) {
            Profession pro = ExileDB.Professions().get(en.getKey());
            registration.addRecipeCategories(new CraftingCategory(helper, en.getValue(), pro.locName()));
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        init();
        for (Map.Entry<String, RecipeType<ProfessionRecipe>> en : map.entrySet()) {
            Profession pro = ExileDB.Professions().get(en.getKey());
            if (SlashBlocks.STATIONS.containsKey(pro.id)) {
                registration.addRecipeCatalyst(new ItemStack(SlashBlocks.STATIONS.get(pro.id).get()), en.getValue());
            }
        }
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        init();

        for (Map.Entry<String, RecipeType<ProfessionRecipe>> en : map.entrySet()) {
            Profession pro = ExileDB.Professions().get(en.getKey());

            CraftingTransfer t = new CraftingTransfer(map.get(pro.id));
            registration.addRecipeTransferHandler(t);
        }

    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        init();

        for (RecipeType<ProfessionRecipe> value : map.values()) {
            registration.addRecipeClickArea(CraftingStationScreen.class, 88, 32, 28, 23, value);
        }
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        init();

        for (Map.Entry<String, RecipeType<ProfessionRecipe>> en : map.entrySet()) {
            registration.addRecipes(en.getValue(), ExileDB.Recipes().getFilterWrapped(x -> x.profession.equals(en.getKey())).list);
        }

    }

    public void init() {
        if (map.isEmpty()) {
            for (Profession p : ExileDB.Professions().getList()) {
                if (true) {
                    map.put(p.id, RecipeType.create(SlashRef.MODID, p.id, ProfessionRecipe.class));
                }
            }
        }
    }
}
