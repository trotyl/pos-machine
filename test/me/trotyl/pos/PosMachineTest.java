package me.trotyl.pos;

import me.trotyl.pos.model.Discount;
import me.trotyl.pos.model.Item;
import me.trotyl.pos.promotion.ComsitePromotion;
import me.trotyl.pos.promotion.DiscountPromotion;
import me.trotyl.pos.promotion.Promotion;
import me.trotyl.pos.promotion.SecondHalfPricePromotion;
import me.trotyl.pos.unknown.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class PosMachineTest {

    private List<Item> items;
    private Promotion promotion;
    private PosMachine posMachine;

    @Before
    public void setUp() throws Exception {

        items = asList(new Item("ITEM000001", 40), new Item("ITEM000003", 50), new Item("ITEM000005", 60));

        List<Discount> discounts = asList(new Discount("ITEM000001", 75), new Discount("ITEM000005", 90));
        Promotion discountPromotion = new DiscountPromotion(discounts);

        List<String> barcodes = asList("ITEM000001", "ITEM000003");
        Promotion secondHalfPricePromotion = new SecondHalfPricePromotion(barcodes);

        promotion = new ComsitePromotion(discountPromotion, secondHalfPricePromotion);

        posMachine = new PosMachine(items, promotion);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void settle_should_have_proper_result() {

        List<Entry> entries = asList(new Entry("ITEM000001", 3), new Entry("ITEM000003", 2), new Entry("ITEM000005", 3));

        double result = posMachine.settle(entries);

        assertThat(result, is(312.0));
    }
}
