package me.trotyl.pos.promotion;

import me.trotyl.pos.model.Discount;
import me.trotyl.pos.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class DiscountPromotionTest {

    private DiscountPromotion promotion;

    @Before
    public void setUp() throws Exception {

        List<Discount> discounts = asList(new Discount("ITEM000001", 75), new Discount("ITEM000005", 90));

        promotion = new DiscountPromotion(discounts);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void apply_should_have_proper_result() {

        List<Item> items = asList(new Item("ITEM000001", 40), new Item("ITEM000003", 50), new Item("ITEM000005", 60));

        List<Item> appliedItems = promotion.apply(items);

        assertThat(appliedItems.size(), is(3));
        assertThat(appliedItems.get(0).getBarcode(), is("ITEM000001"));
        assertThat(appliedItems.get(0).getPrice(), is(30.0));
        assertThat(appliedItems.get(1).getBarcode(), is("ITEM000003"));
        assertThat(appliedItems.get(1).getPrice(), is(50.0));
        assertThat(appliedItems.get(2).getBarcode(), is("ITEM000005"));
        assertThat(appliedItems.get(2).getPrice(), is(54.0));
    }
}
