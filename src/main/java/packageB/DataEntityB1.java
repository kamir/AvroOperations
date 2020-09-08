package packageB;

import org.nd4j.linalg.api.ndarray.INDArray;

public class DataEntityB1 {

    // Generic representation of the data ...
    public String[] elementsAsJSON;

    // Domain specific objects - not ideal for data exchange !!! We want to skip these fields ...
    INDArray myArr;

    org.nd4j.linalg.api.shape.Shape myShape;

}
