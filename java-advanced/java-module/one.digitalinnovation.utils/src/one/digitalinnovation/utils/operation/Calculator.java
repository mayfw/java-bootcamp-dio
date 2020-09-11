package one.digitalinnovation.utils.operation;

import one.digitalinnovation.utils.operation.internal.DivHelper;
import one.digitalinnovation.utils.operation.internal.MultHelper;
import one.digitalinnovation.utils.operation.internal.SubHelper;
import one.digitalinnovation.utils.operation.internal.SumHelper;

public class Calculator {

    private SumHelper sumHelper;
    private SubHelper subHelper;
    private MultHelper multHelper;
    private DivHelper divHelper;

    public Calculator() {
        sumHelper = new SumHelper();
        subHelper = new SubHelper();
        multHelper = new MultHelper();
        divHelper = new DivHelper();
    }

    public int sum(int a, int b) {
        return sumHelper.execute(a, b);
    }

    public int sub(int a, int b) {
        return subHelper.execute(a, b);
    }

    public int mult(int a, int b) {
        return multHelper.execute(a, b);
    }

    public int div(int a, int b) {
        return divHelper.execute(a, b);
    }

}
