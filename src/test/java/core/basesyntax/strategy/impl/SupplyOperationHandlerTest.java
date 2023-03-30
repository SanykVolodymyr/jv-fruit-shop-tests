package core.basesyntax.strategy.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SupplyOperationHandlerTest {
    private static OperationHandler supplyHandler;
    private static FruitTransaction validTransaction;

    @BeforeClass
    public static void beforeClass() {
        validTransaction = new FruitTransaction(
                FruitTransaction.Operation.SUPPLY, "banana", 10);
        supplyHandler = new SupplyOperationHandler();
    }

    @Before
    public void setUp() {
        Storage.storage.put("banana", 10);
    }

    @After
    public void tearDown() {
        Storage.storage.clear();
    }

    @Test
    public void calculate_supplyOperation_ok() {
        supplyHandler.operate(validTransaction);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("banana", 20);
        Assert.assertEquals(expected, Storage.storage);
    }

    @Test(expected = NullPointerException.class)
    public void calculate_null_notOk() {
        supplyHandler.operate(null);
    }
}