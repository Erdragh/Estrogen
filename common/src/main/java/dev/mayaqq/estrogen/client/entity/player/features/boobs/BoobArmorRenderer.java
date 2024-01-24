package dev.mayaqq.estrogen.client.entity.player.features.boobs;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.core.Direction;

import java.util.List;
import java.util.random.RandomGenerator;

@Environment(value=EnvType.CLIENT)
public final class BoobArmorRenderer {
    public static final float DEFAULT_SCALE = 1.0f;
    public float pivotX;
    public float pivotY;
    public float pivotZ;
    public float pitch;
    public float yaw;
    public float roll;
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float scaleZ = 1.0f;
    public boolean visible = true;
    public boolean skipDraw;
    private final List<BoobArmorModel> models;
    private PartPose initalTransform = PartPose.ZERO;

    public BoobArmorRenderer(List<BoobArmorModel> models) {
        this.models = models;
    }

    public PartPose getTransform() {
        return PartPose.offsetAndRotation(this.pivotX, this.pivotY, this.pivotZ, this.pitch, this.yaw, this.roll);
    }

    public PartPose getInitialTransform() {
        return this.initalTransform;
    }

    public void setInitialTransform(PartPose initialTransform) {
        this.initalTransform = initialTransform;
    }

    public void resetTransform() {
        this.setTransform(this.initalTransform);
    }

    public void setTransform(PartPose rotationData) {
        this.pivotX = rotationData.x;
        this.pivotY = rotationData.y;
        this.pivotZ = rotationData.z;
        this.pitch = rotationData.xRot;
        this.yaw = rotationData.yRot;
        this.roll = rotationData.zRot;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.scaleZ = 1.0f;
    }

    public void copyTransform(ModelPart part) {
        this.scaleX = part.xScale;
        this.scaleY = part.yScale;
        this.scaleZ = part.zScale;
        this.pitch = part.xRot;
        this.yaw = part.yRot;
        this.roll = part.zRot;
        this.pivotX = part.x;
        this.pivotY = part.y;
        this.pivotZ = part.z;
    }

    public void setPivot(float x, float y, float z) {
        this.pivotX = x;
        this.pivotY = y;
        this.pivotZ = z;
    }

    public void setAngles(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public void render(PoseStack stack, VertexConsumer vertices, int light, int overlay) {
        this.render(stack, vertices, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void render(PoseStack stack, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        if (!this.visible) {
            return;
        }
        if (this.models.isEmpty()) {
            return;
        }
        stack.pushPose();
        this.rotate(stack);
        if (!this.skipDraw) {
            this.renderCuboids(stack.last(), vertices, light, overlay, red, green, blue, alpha);
        }
        stack.popPose();
    }

    public void rotate(PoseStack matrices) {
        matrices.translate(this.pivotX / 16.0f, this.pivotY / 16.0f, this.pivotZ / 16.0f);
        if (this.roll != 0.0f) {
            matrices.mulPose(Vector3f.ZP.rotation(this.roll));
        }
        if (this.yaw != 0.0f) {
            matrices.mulPose(Vector3f.YP.rotation(this.yaw));
        }
        if (this.pitch != 0.0f) {
            matrices.mulPose(Vector3f.XP.rotation(this.pitch));
        }
        if (this.scaleX != 1.0f || this.scaleY != 1.0f || this.scaleZ != 1.0f) {
            matrices.scale(this.scaleX, this.scaleY, this.scaleZ);
        }
    }

    private void renderCuboids(PoseStack.Pose entry, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        for (BoobArmorModel boobArmorModel : this.models) {
            boobArmorModel.renderCuboid(entry, vertexConsumer, light, overlay, red, green, blue, alpha);
        }
    }

    public BoobArmorModel getRandomCuboid(RandomGenerator random) {
        return this.models.get(random.nextInt(this.models.size()));
    }

    public boolean isEmpty() {
        return this.models.isEmpty();
    }

    public void translate(Vector3f vec) {
        this.pivotX += vec.x();
        this.pivotY += vec.y();
        this.pivotZ += vec.z();
    }

    public void rotate(Vector3f vec) {
        this.pitch += vec.x();
        this.yaw += vec.y();
        this.roll += vec.z();
    }

    public void scale(Vector3f vec) {
        this.scaleX += vec.x();
        this.scaleY += vec.y();
        this.scaleZ += vec.z();
    }

    @FunctionalInterface
    @Environment(value=EnvType.CLIENT)
    public static interface CuboidConsumer {
        public void visit(PoseStack.Pose var1, String var2, int var3, BoobArmorModel var4);
    }

    @Environment(value=EnvType.CLIENT)
    public static class BoobArmorModel {
        private final ModelPart.Polygon[] sides; //private, add to AW/AT or smth, same deal with modelpart.vertex

        public BoobArmorModel(int textureWidth, int textureHeight, float sizeX, float sizeY, float sizeZ, boolean mirror, float squishU, float squishV) {
            this.sides = new ModelPart.Polygon[4];

            ModelPart.Vertex vertex = new ModelPart.Vertex(-4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            ModelPart.Vertex vertex2 = new ModelPart.Vertex(4.0F, 0.0F, 0.0F, 0.0F, 8.0F);
            ModelPart.Vertex vertex3 = new ModelPart.Vertex(4.0F, 1.08F, 1.68F, 8.0F, 8.0F);
            ModelPart.Vertex vertex4 = new ModelPart.Vertex(-4.0F, 1.08F, 1.68F, 8.0F, 0.0F);
            ModelPart.Vertex vertex5 = new ModelPart.Vertex(-4.0F, -1.68F, 1.68F, 0.0F, 0.0F);
            ModelPart.Vertex vertex6 = new ModelPart.Vertex(4.0F, -1.68F, 1.68F, 0.0F, 8.0F);
            float j = textureWidth;
            float k = (float)textureWidth + sizeZ;
            float l = (float)textureWidth + sizeZ + sizeX;
            float n = (float)textureWidth + sizeZ + sizeX + sizeZ;
            float p = textureHeight;
            float q = (float)textureHeight + sizeZ;
            float r = (float)textureHeight + sizeZ + sizeY;
            this.sides[2] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex6, vertex5, vertex, vertex2}, k, p, l, q, squishU, squishV, mirror, Direction.DOWN);
            this.sides[1] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex, vertex5, vertex5, vertex4}, j, q, k, r, squishU, squishV, mirror, Direction.WEST);
            this.sides[3] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex2, vertex, vertex4, vertex3}, k, q, l, r, squishU, squishV, mirror, Direction.NORTH);
            this.sides[0] = new ModelPart.Polygon(new ModelPart.Vertex[]{vertex6, vertex2, vertex3, vertex3}, l, q, n, r, squishU, squishV, mirror, Direction.EAST);
        }

        public void renderCuboid(PoseStack.Pose entry, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
            Matrix4f matrix4f = entry.pose();
            Matrix3f matrix3f = entry.normal();
            for (ModelPart.Polygon quad : this.sides) {
                Vector3f vector3f = quad.normal.copy();
                float f = vector3f.x();
                float g = vector3f.y();
                float h = vector3f.z();
                for (ModelPart.Vertex vertex : quad.vertices) {
                    float i = vertex.pos.x() / 16.0f;
                    float j = vertex.pos.y() / 16.0f;
                    float k = vertex.pos.z() / 16.0f;
                    Vector4f vector4f = new Vector4f(i, j, k, 1.0f);
                    vector4f.transform(matrix4f);
                    vertexConsumer.vertex(vector4f.x(), vector4f.y(), vector4f.z(), red, green, blue, alpha, vertex.u, vertex.v, overlay, light, f, g, h);
                }
            }
        }
    }
}

