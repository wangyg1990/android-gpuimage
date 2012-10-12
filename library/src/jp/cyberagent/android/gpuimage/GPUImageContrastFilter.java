
package jp.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class GPUImageContrastFilter extends GPUImageFilter {
    public static final String CONTRAST_FRAGMENT_SHADER = "" +
            "varying highp vec2 textureCoordinate;\n" + 
            " \n" + 
            " uniform sampler2D inputImageTexture;\n" + 
            " uniform lowp float contrast;\n" + 
            " \n" + 
            " void main()\n" + 
            " {\n" + 
            "     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" + 
            "     \n" + 
            "     gl_FragColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n" + 
            " }";

    private int mContrastLocation;
    private float mContrast = 1.2f;
    private boolean mIsInitialized = false;

    public GPUImageContrastFilter() {
        super(NO_FILTER_VERTEX_SHADER, CONTRAST_FRAGMENT_SHADER);
    }

    @Override
    public void onInit() {
        super.onInit();
        mContrastLocation = GLES20.glGetUniformLocation(getProgram(), "contrast");
        mIsInitialized = true;
        setContrast(mContrast);
    }

    public void setContrast(final float contrast) {
        mContrast = contrast;
        if (mIsInitialized) {
            setFloat(mContrastLocation, mContrast);
        }
    }
}