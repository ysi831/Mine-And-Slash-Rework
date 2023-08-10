package com.robertx22.age_of_exile.mixin_methods;

import com.mojang.blaze3d.vertex.PoseStack;
import com.robertx22.age_of_exile.capability.entity.EntityData;
import com.robertx22.age_of_exile.config.forge.ClientConfigs;
import com.robertx22.age_of_exile.database.data.rarities.MobRarity;
import com.robertx22.age_of_exile.database.registry.ExileDB;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.uncommon.utilityclasses.ClientOnly;
import com.robertx22.age_of_exile.uncommon.utilityclasses.HealthUtils;
import com.robertx22.age_of_exile.uncommon.utilityclasses.LookUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.joml.Matrix4f;

public class RenderMobInfo {

    static Entity lastLooked;

    public static void renderLivingEntityLabelIfPresent(Font textRenderer, EntityRenderDispatcher dispatcher, LivingEntity entity,
                                                        PoseStack matrixStack,
                                                        MultiBufferSource vertex, int i) {
        try {


            if (!ClientConfigs.getConfig().RENDER_MOB_HEALTH_GUI.get()) {
                return;
            }

            double squaredDistance =
                    dispatcher.distanceToSqr(entity);
            if (squaredDistance <= 300) {

                // todo
                if (ClientConfigs.getConfig().ONLY_RENDER_MOB_LOOKED_AT.get()) {
                    if (LookUtils.getEntityLookedAt(Minecraft.getInstance().player) != entity) {
                        if (lastLooked != entity) {
                            return;
                        }
                    } else {
                        lastLooked = entity;
                    }
                }
                if (entity instanceof ArmorStand) {
                    return;
                }
                if (entity == ClientOnly.getPlayer()) {
                    return;
                }
                if (entity.hasPassenger(x -> true)) {
                    return; // dont display horse's bar if the player is riding it
                }

                float yOffset = entity.getNameTagOffsetY();
                EntityData data = Load.Unit(entity);

                boolean hidelvl = data.getLevel() - 10 > Load.Unit(Minecraft.getInstance().player)
                        .getLevel();
                MutableComponent lvlcomp =
                        Component.literal(" [" + data.getLevel() + "]").withStyle(ChatFormatting.YELLOW);

                if (hidelvl) {
                    lvlcomp =
                            Component.literal(" [" + "???" + "]").withStyle(ChatFormatting.YELLOW);
                }
                Component text = data.getName()
                        .append(lvlcomp)
                        .withStyle(ChatFormatting.RED, ChatFormatting.BOLD);

                float percent = HealthUtils.getCurrentHealth(entity) / HealthUtils.getMaxHealth(entity) * 100F;

                MutableComponent hpText = Component.literal("[").withStyle(ChatFormatting.DARK_RED);
                int times = 0;

                for (int x = 0; x < 10; x++) {
                    times++;

                    if (percent > 0) {
                        hpText.append(Component.literal("|")
                                .withStyle(ChatFormatting.RED)
                        );
                    } else {
                        hpText.append(Component.literal("|")
                                .withStyle(ChatFormatting.DARK_RED)
                        );
                    }

                    if (times == 5) {
                        hpText.append(Component.literal((int) HealthUtils.getCurrentHealth(entity) + "").withStyle(ChatFormatting.GOLD));
                    }
                    percent -= 10;

                }

                hpText.append(Component.literal("]").withStyle(ChatFormatting.DARK_RED));

                MobRarity rar = ExileDB.MobRarities()
                        .get(data.getRarity());

                String icon = rar.textFormatting() + rar.name_add;


                matrixStack.pushPose();


                try {

                    float yadd = 0.22F;
                    float y = 0;
                    renderNameTag(entity, text, y, matrixStack, vertex, i);
                    y += yadd;
                    renderNameTag(entity, hpText, y, matrixStack, vertex, i);
                    y += yadd;

                    // todo scale
                    if (!rar.name_add.isEmpty()) {
                        //matrixStack.scale(2, 2, 2);
                        renderNameTag(entity, Component.literal(icon), y, matrixStack, vertex, i);
                        //matrixStack.scale(0.5F, 0.5F, 0.5F);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                matrixStack.popPose();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected static void renderNameTag(LivingEntity en, Component name, float yadd, PoseStack matrix, MultiBufferSource pBuffer, int light) {
        double d0 = Minecraft.getInstance().getEntityRenderDispatcher().distanceToSqr(en);
        if (net.minecraftforge.client.ForgeHooksClient.isNameplateInRenderDistance(en, d0)) {
            boolean flag = !en.isDiscrete();
            float y = en.getNameTagOffsetY() + yadd;
            int i = "deadmau5".equals(name.getString()) ? -10 : 0;
            matrix.pushPose();
            matrix.translate(0.0F, y, 0.0F);
            matrix.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
            matrix.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrix.last().pose();
            float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int j = (int) (f1 * 255.0F) << 24;
            Font font = Minecraft.getInstance().font;
            float f2 = (float) (-font.width(name) / 2);
            font.drawInBatch(name, f2, (float) i, 553648127, false, matrix4f, pBuffer, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.NORMAL, j, light);
            if (flag) {
                font.drawInBatch(name, f2, (float) i, -1, false, matrix4f, pBuffer, Font.DisplayMode.NORMAL, 0, light);
            }

            matrix.popPose();
        }
    }
}
