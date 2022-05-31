package org.chobit.core.interval;


import static org.chobit.core.interval.OutputType.STRING;

/**
 * @author robin
 */
public class Dimension {

    private DimensionType type = DimensionType.DEFAULT;

    private OutputType outputType = STRING;

    private String outputName;

    private String dimension;


    public DimensionType getType() {
        return type;
    }

    public void setType(DimensionType type) {
        this.type = type;
    }

    public OutputType getOutputType() {
        return outputType;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}
